package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class SoundManager {

    /**
     * Retro chiptune sound for the console game. Everything is generated in code
     * (square / triangle waves), so no sound files are needed.
     *
     * Usage:
     *   SoundManager sound = new SoundManager();
     *   sound.start();
     *   sound.setMusic(SoundManager.Track.MAIN);
     *   sound.setMusic(SoundManager.Track.BATTLE);   // battle begins
     *   sound.playEffect(SoundManager.Effect.LOOT);  // short sound effect
     *   sound.shutdown();                            // when the game ends
     */

        public enum Track { NONE, MAIN, BATTLE }

    public enum Effect {
        LOOT, HIT, VICTORY, GAME_OVER,
        PHONE_ERROR,
        RADIO_STATIC,
        SEARCH,
        CAR_CREAK,
        ZOMBIE_GROAN,
        KEYS,
        ENGINE_START,
        DIALOGUE
    }

        private static final float SAMPLE_RATE = 22050f;
        private static final AudioFormat FORMAT =
                new AudioFormat(SAMPLE_RATE, 16, 1, true, false); // 16-bit mono, little endian
        private static final Random RANDOM = new Random();

        // Master volume for everything (music and effects): 0.0 = silent, 1.0 = full.
        private static volatile double masterVolume = 0.15;

        // ------------------------------------------------------------------
        // Songs. Notes are MIDI numbers (60 = middle C, 69 = A4). 0 = rest.
        // One array entry = one "step" (an eighth note). Edit these to change the tunes!
        // ------------------------------------------------------------------
        private static class Song {
            final int[] melody;
            final int[] bass;
            final int stepMs;
            final double duty;       // pulse width of the melody (0.5 = square, 0.25 = thinner/edgier)
            final double melodyVol;
            final double bassVol;

            Song(int[] melody, int[] bass, int stepMs, double duty, double melodyVol, double bassVol) {
                this.melody = melody;
                this.bass = bass;
                this.stepMs = stepMs;
                this.duty = duty;
                this.melodyVol = melodyVol;
                this.bassVol = bassVol;
            }

            int length() {
                return melody.length;
            }
        }

        // Slow and creepy: sparse melody that creeps by semitones (A - Bb - A - G#),
        // then drops to a tritone (E - Eb). The bass is a low "heartbeat" that
        // slides from A to Bb, which clashes on purpose.
        private static final Song MAIN_SONG = new Song(
                new int[]{
                        69, 0, 0, 0, 70, 0, 0, 0,
                        69, 0, 0, 0, 68, 0, 0, 0,
                        76, 0, 0, 0, 75, 0, 0, 0,
                        69, 0, 70, 0, 0, 0, 0, 0
                },
                new int[]{
                        45, 0, 45, 0, 0, 0, 0, 0,
                        45, 0, 45, 0, 0, 0, 0, 0,
                        46, 0, 46, 0, 0, 0, 0, 0,
                        45, 0, 45, 0, 0, 0, 0, 0
                },
                420, 0.125, 0.16, 0.30);

        // Fast and tense: driving octave-jumping bass, edgy pulse melody
        private static final Song BATTLE_SONG = new Song(
                new int[]{
                        81, 0, 80, 0, 81, 0, 84, 0,
                        83, 0, 81, 0, 80, 0, 76, 0,
                        81, 0, 80, 0, 81, 0, 84, 0,
                        88, 0, 86, 0, 84, 0, 83, 0
                },
                new int[]{
                        45, 57, 45, 57, 45, 57, 45, 57,
                        41, 53, 41, 53, 41, 53, 41, 53,
                        45, 57, 45, 57, 45, 57, 45, 57,
                        40, 52, 40, 52, 40, 52, 40, 52
                },
                115, 0.25, 0.20, 0.25);

        // ------------------------------------------------------------------
        // State
        // ------------------------------------------------------------------
        private volatile Track current = Track.NONE;
        private volatile boolean running = false;
        private volatile boolean muted = false;
        private Thread musicThread;

        /** Starts the background music thread (call once). */
        public synchronized void start() {
            if (running) return;
            running = true;
            musicThread = new Thread(this::musicLoop, "music");
            musicThread.setDaemon(true); // won't keep the program alive after the game ends
            musicThread.start();
        }

        /** Switches the background music. Switching to a track restarts it from the beginning. */
        public void setMusic(Track track) {
            current = track;
        }

        /**
         * Sets the master volume from 0.0 (silent) to 1.0 (full).
         * Music reacts almost instantly; effects use the new volume the next time they play.
         */
        public void setVolume(double volume) {
            masterVolume = Math.max(0.0, Math.min(1.0, volume));
        }

        public double getVolume() {
            return masterVolume;
        }

        /** Mutes or unmutes everything. Returns the new muted state. */
        public boolean toggleMute() {
            muted = !muted;
            return muted;
        }

        /** Stops all sound. */
        public void shutdown() {
            running = false;
            current = Track.NONE;
        }

        // ------------------------------------------------------------------
        // Music thread
        // ------------------------------------------------------------------
        private void musicLoop() {
            try (SourceDataLine line = AudioSystem.getSourceDataLine(FORMAT)) {
                line.open(FORMAT, 4096); // small buffer = music switches quickly
                line.start();

                Track playing = Track.NONE;
                int step = 0;

                while (running) {
                    Track wanted = muted ? Track.NONE : current;

                    if (wanted != playing) {
                        line.flush();      // throw away audio still queued from the old track
                        playing = wanted;
                        step = 0;
                    }

                    if (playing == Track.NONE) {
                        Thread.sleep(50);
                        continue;
                    }

                    Song song = (playing == Track.MAIN) ? MAIN_SONG : BATTLE_SONG;
                    byte[] data = renderStep(song, step);
                    line.write(data, 0, data.length); // blocks until there's room, which paces the loop
                    step = (step + 1) % song.length();
                }
                line.stop();
            } catch (Exception e) {
                System.out.println("[Sound unavailable: " + e.getMessage() + "]");
            }
        }

        /** Renders one step of a song (melody + bass) into raw 16-bit samples. */
        private static byte[] renderStep(Song song, int step) {
            int n = (int) (SAMPLE_RATE * song.stepMs / 1000);
            byte[] out = new byte[n * 2];
            double melodyFreq = midiToFreq(song.melody[step]);
            double bassFreq = midiToFreq(song.bass[step]);

            for (int i = 0; i < n; i++) {
                double t = i / SAMPLE_RATE;
                double env = envelope(i, n);
                double sample = 0;

                if (melodyFreq > 0) {
                    double phase = (melodyFreq * t) % 1.0;
                    sample += (phase < song.duty ? 1 : -1) * song.melodyVol * env;
                }
                if (bassFreq > 0) {
                    double phase = (bassFreq * t) % 1.0;
                    double triangle = 4 * Math.abs(phase - 0.5) - 1;
                    sample += triangle * song.bassVol * env;
                }
                putSample(out, i, sample);
            }
            return out;
        }

        // ------------------------------------------------------------------
        // Sound effects
        // ------------------------------------------------------------------

        /** Plays a short sound effect on its own thread (doesn't interrupt the music). */
        public void playEffect(Effect effect) {
            if (muted) return;
            final byte[] data = buildEffect(effect);
            Thread t = new Thread(() -> {
                try (SourceDataLine line = AudioSystem.getSourceDataLine(FORMAT)) {
                    line.open(FORMAT);
                    line.start();
                    line.write(data, 0, data.length);
                    line.drain();
                } catch (Exception ignored) {
                    // no audio device: the game just continues silently
                }
            }, "sfx");
            t.setDaemon(true);
            t.start();
        }

        private static byte[] buildEffect(Effect effect) {
            switch (effect) {
                case LOOT:
                    return concat(
                            tone(988, 988, 70, 0.25, false),
                            tone(1319, 1319, 130, 0.25, false));
                case HIT:
                    return concat(
                            tone(0, 0, 150, 0.30, true),      // crunchy noise burst
                            tone(160, 50, 200, 0.30, false)); // falling thud
                case VICTORY:
                    return concat(
                            tone(523, 523, 130, 0.25, false),
                            tone(659, 659, 130, 0.25, false),
                            tone(784, 784, 130, 0.25, false),
                            tone(1047, 1047, 450, 0.25, false));
                case GAME_OVER:
                    return concat(
                            tone(392, 392, 250, 0.25, false),
                            tone(370, 370, 250, 0.25, false),
                            tone(349, 349, 250, 0.25, false),
                            tone(262, 220, 700, 0.25, false));

                case PHONE_ERROR:
                    return concat(
                            tone(600, 600, 100, 0.15, false),
                            tone(350, 350, 180, 0.15, false));

                case RADIO_STATIC:
                    return concat(
                            tone(0, 0, 250, 0.12, true),
                            tone(0, 0, 150, 0.18, true),
                            tone(0, 0, 300, 0.10, true));

                case SEARCH:
                    return concat(
                            tone(0, 0, 180, 0.12, true),
                            tone(0, 0, 220, 0.10, true));

                case DIALOGUE:
                    return concat(
                            tone(480, 480, 45, 0.20, false),
                            tone(0, 0, 25, 0, false),            // tiny gap
                            tone(560, 560, 45, 0.20, false),
                            tone(0, 0, 25, 0, false),
                            tone(420, 420, 45, 0.20, false),
                            tone(0, 0, 25, 0, false),
                            tone(520, 520, 45, 0.20, false),
                            tone(0, 0, 25, 0, false),
                            tone(600, 600, 45, 0.20, false),
                            tone(0, 0, 25, 0, false),
                            tone(450, 450, 45, 0.20, false));

                case CAR_CREAK:
                    return concat(
                            tone(180, 100, 350, 0.18, false),
                            tone(100, 70, 250, 0.15, false));

                case ZOMBIE_GROAN:
                    return concat(
                            tone(110, 95, 120, 0.30, false),   // wobbly start
                            tone(95, 105, 120, 0.30, false),
                            tone(105, 85, 120, 0.30, false),
                            tone(0, 0, 60, 0.15, true),        // raspy noise burst
                            tone(85, 50, 700, 0.30, false));   // long sinking groan

                case KEYS:
                    return concat(
                            tone(1400, 1100, 70, 0.15, false),
                            tone(1800, 1300, 90, 0.15, false),
                            tone(1200, 900, 80, 0.15, false));

                case ENGINE_START:
                    return concat(
                            tone(90, 130, 250, 0.18, false),
                            tone(130, 65, 500, 0.20, false),
                            tone(65, 90, 300, 0.18, false));
                default:
                    return new byte[0];
            }
        }

        /**
         * Generates a single tone that glides from f0 to f1 Hz.
         * If noise is true, white noise is generated instead (frequencies are ignored).
         */
        private static byte[] tone(double f0, double f1, int ms, double volume, boolean noise) {
            int n = (int) (SAMPLE_RATE * ms / 1000);
            byte[] out = new byte[n * 2];
            double phase = 0;

            for (int i = 0; i < n; i++) {
                double progress = (double) i / n;
                double freq = f0 + (f1 - f0) * progress;
                phase += freq / SAMPLE_RATE;
                phase %= 1.0;

                double wave = noise ? (RANDOM.nextDouble() * 2 - 1) : (phase < 0.5 ? 1 : -1);
                putSample(out, i, wave * volume * envelope(i, n));
            }
            return out;
        }

        // ------------------------------------------------------------------
        // Helpers
        // ------------------------------------------------------------------

        /** Short fade-in, gentle decay, short fade-out. Prevents clicking between notes. */
        private static double envelope(int i, int n) {
            int attack = (int) (SAMPLE_RATE * 0.003);
            int release = (int) (SAMPLE_RATE * 0.010);
            double e = 1.0 - 0.5 * ((double) i / n);
            if (i < attack) e *= (double) i / attack;
            if (i > n - release) e *= (double) (n - i) / release;
            return e;
        }

        private static double midiToFreq(int midi) {
            if (midi <= 0) return 0;
            return 440.0 * Math.pow(2.0, (midi - 69) / 12.0);
        }

        private static void putSample(byte[] out, int index, double sample) {
            sample *= masterVolume;
            sample = Math.max(-1.0, Math.min(1.0, sample));
            short v = (short) (sample * 32767);
            out[index * 2] = (byte) (v & 0xFF);
            out[index * 2 + 1] = (byte) ((v >> 8) & 0xFF);
        }

        private static byte[] concat(byte[]... parts) {
            int total = 0;
            for (byte[] p : parts) total += p.length;
            byte[] result = new byte[total];
            int pos = 0;
            for (byte[] p : parts) {
                System.arraycopy(p, 0, result, pos, p.length);
                pos += p.length;
            }
            return result;
        }
    }
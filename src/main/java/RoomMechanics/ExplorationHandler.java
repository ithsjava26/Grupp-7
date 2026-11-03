
package RoomMechanics;

public class ExplorationHandler {

    public void handleExploration(ExplorationAction action, String playerName) {

        switch (action) {

            case LOOK_OUT_WINDOW:
                IO.println(
                        "The office is several floors above street level. Below, cars "
                                + "are abandoned in the middle of the road. People are running, "
                                + "some stumbling, others chasing them. You notice emergency "
                                + "vehicles in the distance.");
                break;

            case LOOK_AT_PHONE:
                IO.println("Damn, no service");
                break;

            case TURN_ON_RADIO:
                IO.println(
                        "An emergency message is broadcasting. The message is "
                                + "fragmented, but you catch references to violent incidents "
                                + "and instructions to avoid contact with anyone displaying "
                                + "unusual symptoms.");
                break;

            case SEARCH_DESKS:
                IO.println(
                        "You search through the cluttered desks. Most contain "
                                + "ordinary office supplies, but you grab whatever could "
                                + "be useful.");

                // TODO: Add items: pain killers and energy drink
                break;

            case TALK_TO_COWORKER:
                IO.println(
                        "Oh my god, " + playerName + "! I couldn't get you to wake up, "
                                + "so I just left you. I thought you were one of them!\n"
                                + "Things went completely insane downstairs. People were "
                                + "attacking each other. I ran back inside.");

                while (true) {
                    IO.println("\nWhat do you ask?");
                    IO.println("1. What the hell is going on?");
                    IO.println("2. What do you mean, one of them?");
                    IO.println("3. What's with the axe?");
                    IO.println("4. Leave");

                    String choice = IO.readln();

                    if (choice == null) {
                        return;
                    }

                    switch (choice) {

                        case "1":
                            IO.println(
                                    "I...They said it's something in the water supply. "
                                            + "Some kind of contamination. There's talk of "
                                            + "biological warfare. Idk man. People just started "
                                            + "passing out, then they woke up acting like rabid "
                                            + "dogs. Some people seem to be immune.");
                            break;

                        case "2":
                            IO.println(
                                    "A zombie, dude. Face all fucked up, super strength... "
                                            + "I saw Maggie tearing our bosses arm right off, "
                                            + "before she jumped out the window. Can't say "
                                            + "I've never thought of that *laughs*");
                            break;

                        case "3":
                            IO.println(
                                    "Oh this? I grabbed the fire axe for safety but "
                                            + "honestly man you'll need it more than me if "
                                            + "you're heading outside. Im gonna barricade "
                                            + "myself in here and wait for help. Here, take it.");

                            // TODO: Add fire axe to player inventory
                            break;

                        case "4":
                            IO.println(
                                    "You give your co-worker a final nod as you head out.");
                            return;

                        default:
                            IO.println("Invalid option. Try again.");
                    }
                }

            case OBSERVE_FIGURES:
                IO.println(
                        "You notice several figures moving strangely in the "
                                + "distance. One stumbles into a parked car, while another "
                                + "crawls along the pavement. Something is clearly wrong.");
                break;

            case INSPECT_ABANDONED_CAR:
                IO.println(
                        "You see a car with its doors open, there's blood smeared "
                                + "on the shattered wind shield. You search it for anything "
                                + "useful");

                // TODO: Add an emergency medical kit to player inventory
                break;

            case INSPECT_CORPSE:
                IO.println(
                        "You approach the body cautiously. Its clothes are torn, "
                                + "and deep bite marks cover its arms and neck. Whatever "
                                + "happened here, it wasn't an ordinary attack.");
                break;

            case SEARCH_POLICE_CAR:
                IO.println(
                        "You approach the abandoned police car. The driver's door "
                                + "is open, and the officer's belongings are scattered "
                                + "across the seat. You search the vehicle and find a firearm.");

                // TODO: Add firearm and ammo to player inventory
                break;

            case SEARCH_BACKPACK:
                IO.println(
                        "You spot an abandoned backpack near a park bench. "
                                + "Inside, you find some snacks, a water bottle and a "
                                + "motorcycle jacket");

                // TODO: Add items to player inventory
                break;

            case SEARCH_GROUND_FLOOR:
                IO.println(
                        "You search the ground floor. A chair lies overturned "
                                + "in the hallway. You're too stressed to find anything "
                                + "useful.");
                break;

            case SEARCH_FOR_KEYS:
                IO.println(
                        "You search the bedroom and eventually find the car keys.");

                // TODO: Add car keys to player's inventory
                break;

            case CALL_FOR_PARTNER:
                IO.println(
                        "You call out your partner's name, hoping for an answer. "
                                + "The house remains silent. Then, somewhere downstairs, "
                                + "you hear a faint noise. Could it be?");
                break;

            case SEARCH_PARTNER:
                IO.println(
                        "You approach the body and kneel beside them, unable to "
                                + "process what you're seeing.");
                IO.println(
                        "\"I'm so, so sorry,\" you whisper as you wipe the hair "
                                + "from their warped face.");
                IO.println("You start hearing zombies outside.");
                break;

            case GET_IN_CAR:
                IO.println(
                        "You unlock the car and climb inside. The engine starts "
                                + "after a few attempts. You glance at the house one "
                                + "last time before driving away.");
                IO.println("You survived. But at what cost?");
                break;
        }
    }
}


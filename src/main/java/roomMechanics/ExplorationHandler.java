package roomMechanics;

import armor.MotorcycleJacket;
import audio.SoundManager;
import combat.Hero;
import food.Chips;
import food.ProteinBar;
import game.GameUtils;
import inventory.Inventory;
import item.Armor;
import item.Item;
import item.Weapon;
import keyitem.CarKeys;
import keyitem.EngagementRing;
import medkit.Bandage;
import medkit.EnergiDrink;
import medkit.FirstAidKit;
import medkit.PainKillers;
import weapon.Fireaxe;
import weapon.Glock19;

public class ExplorationHandler {

    private final SoundManager soundManager;

    public ExplorationHandler(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    public void handleExploration(
            ExplorationAction action,
            String playerName,
            Hero player) {

        // Play sound effects for exploration actions
        switch (action) {
            case LOOK_AT_PHONE:
                soundManager.playEffect(SoundManager.Effect.PHONE_ERROR);
                break;

            case TURN_ON_RADIO:
                soundManager.playEffect(SoundManager.Effect.RADIO_STATIC);
                break;

            case TALK_TO_COWORKER:
                soundManager.playEffect(SoundManager.Effect.DIALOGUE);
                break;

            case SEARCH_DESKS:
            case INSPECT_ABANDONED_CAR:
            case SEARCH_POLICE_CAR:
            case SEARCH_BACKPACK:
            case SEARCH_GROUND_FLOOR:
                soundManager.playEffect(SoundManager.Effect.SEARCH);
                break;

            case SEARCH_FOR_KEYS:
                soundManager.playEffect(SoundManager.Effect.KEYS);
                break;

            case GET_IN_CAR:
                soundManager.playEffect(SoundManager.Effect.ENGINE_START);
                break;

            default:
                break;
        }

        // Existing exploration narratives
        switch (action) {

            case LOOK_OUT_WINDOW:
                GameUtils.printNarrative(
                        "The office is several floors above street level. Below, cars "
                                + "are abandoned in the middle of the road. People are running, "
                                + "some stumbling, others chasing them. You notice emergency "
                                + "vehicles in the distance.");
                break;

            case LOOK_AT_PHONE:
                GameUtils.printDialogue("Damn, no service");
                break;

            case TURN_ON_RADIO:
                GameUtils.printNarrative(
                        "An emergency message is broadcasting. The message is "
                                + "fragmented, but you catch references to violent incidents "
                                + "and instructions to avoid contact with anyone displaying "
                                + "unusual symptoms.");
                break;

            case SEARCH_DESKS:
                GameUtils.printNarrative(
                        "You search through the cluttered desks"
                                + " and grab whatever could be useful.");

                addItemToInventory(player, new PainKillers());
                addItemToInventory(player, new EnergiDrink());
                break;

            case TALK_TO_COWORKER:
                GameUtils.printDialogue(
                        "Oh my god, " + playerName + "! I couldn't get you to wake up, "
                                + "so I just left you. I thought you were one of them!\n"
                                + "Things went completely insane downstairs. People were "
                                + "attacking each other. I ran back inside.");

                while (true) {
                    IO.println("\nWhat do you ask?");

                    IO.println(ConsoleColors.YELLOW + "1. " + ConsoleColors.RESET
                            + "What the hell is going on?");
                    IO.println(ConsoleColors.YELLOW + "2. " + ConsoleColors.RESET
                            + "What do you mean, one of them?");
                    IO.println(ConsoleColors.YELLOW + "3. " + ConsoleColors.RESET
                            + "What's with the axe? 🔎");
                    IO.println(ConsoleColors.YELLOW + "e. " + ConsoleColors.RESET
                            + "Leave");

                    String choice = IO.readln();

                    if (choice == null) {
                        return;
                    }

                    switch (choice) {

                        case "1":
                            soundManager.playEffect(SoundManager.Effect.DIALOGUE);
                            GameUtils.printDialogue(
                                    "I...They said it's something in the water supply. "
                                            + "Some kind of contamination. There's talk of "
                                            + "biological warfare. Idk man. People just started "
                                            + "passing out, then they woke up acting like rabid "
                                            + "dogs. Some people seem to be immune.");
                            break;

                        case "2":
                            soundManager.playEffect(SoundManager.Effect.DIALOGUE);
                            GameUtils.printDialogue(
                                    "A zombie, dude. Face all fucked up, super strength... "
                                            + "I saw Maggie tear our bosses arm right off, "
                                            + "before she jumped out the window. Can't say "
                                            + "I've never thought of that *laughs*");
                            break;

                        case "3":
                            soundManager.playEffect(SoundManager.Effect.DIALOGUE);
                            GameUtils.printDialogue(
                                    "Oh this? I grabbed the fire axe for safety but "
                                            + "honestly man you'll need it more than me if "
                                            + "you're heading outside. Im gonna barricade "
                                            + "myself in here and wait for help. Here, take it.");

                            addItemToInventory(player, new Fireaxe());
                            break;

                        case "e":
                            GameUtils.printNarrative(
                                    "You give your co-worker a final nod as you head out.");
                            return;

                        default:
                            GameUtils.printSystemMessage("Invalid option. Try again.");
                    }
                }

            case OBSERVE_FIGURES:
                GameUtils.printNarrative(
                        "You notice several figures moving strangely in the "
                                + "distance. One stumbles into a parked car, while another "
                                + "crawls along the pavement. Something is clearly wrong.");
                break;

            case INSPECT_ABANDONED_CAR:
                GameUtils.printNarrative(
                        "You see a car with its doors open, there's blood smeared "
                                + "on the shattered wind shield. You search it for anything "
                                + "useful");

                addItemToInventory(player, new Bandage());
                break;

            case INSPECT_CORPSE:
                GameUtils.printNarrative(
                        "You approach the body cautiously. Its clothes are torn, "
                                + "and deep bite marks cover its arms and neck. Whatever "
                                + "happened here, it wasn't an ordinary attack.");
                break;

            case SEARCH_POLICE_CAR:
                GameUtils.printNarrative(
                        "You approach the abandoned police car. The driver's door "
                                + "is open, and the officer's belongings are scattered "
                                + "across the seat. You search the vehicle and find a firearm.");

                addItemToInventory(player, new Glock19());
                addItemToInventory(player, new FirstAidKit());
                break;

            case SEARCH_BACKPACK:
                GameUtils.printNarrative(
                        "You open the backpack. "
                                + "Inside, you find some snacks, a water bottle and a "
                                + "motorcycle jacket");

                addItemToInventory(player, new Chips());
                addItemToInventory(player, new ProteinBar());
                addItemToInventory(player, new MotorcycleJacket());
                break;

            case SEARCH_GROUND_FLOOR:
                GameUtils.printNarrative(
                        "You search the ground floor. A chair lies overturned "
                                + "in the hallway. You're too stressed to find anything "
                                + "useful.");
                break;

            case SEARCH_FOR_KEYS:
                GameUtils.printNarrative(
                        "You search the bedroom and eventually find the car keys.");

                addItemToInventory(player, new CarKeys());
                break;

            case CALL_FOR_PARTNER:
                GameUtils.printNarrative(
                        "You call out your partner's name, hoping for an answer. "
                                + "The house remains silent. Then, somewhere downstairs, "
                                + "you hear a faint noise. Could it be?");
                break;


            case SEARCH_PARTNER:
                GameUtils.printNarrative(
                        "You approach the body and kneel beside them, unable to "
                                + "process what you're seeing.");

                GameUtils.printDialogue(
                        "\"I'm so, so sorry,\" you whisper as you wipe the hair "
                                + "from their warped face.");

                GameUtils.printNarrative(
                        "You gently slip the ring off of their finger and  "
                                + "hold it in your trembling hand. ");


                addItemToInventory(player, new EngagementRing());

                GameUtils.printWarning("You start hearing zombies outside.");
                break;


            case GET_IN_CAR:
                GameUtils.printNarrative(
                        "You unlock the car and climb inside. The engine starts "
                                + "after a few attempts. You glance at the house one "
                                + "last time before driving away.");
                GameUtils.printNarrative("You survived. But at what cost?");
                break;
        }
    }

    private void addItemToInventory(Hero player, Item item) {
        Inventory inventory = player.getInventory();

        if (inventory.addItem(item)) {
            IO.println();
            IO.print(ConsoleColors.YELLOW + "[SYSTEM] " + ConsoleColors.RESET);
            IO.println(item.getName() + " added to inventory.");

            if (item instanceof Weapon weapon) {
                IO.println();
                IO.println("Do you want to equip " + item.getName() + " now?");
                IO.println(ConsoleColors.YELLOW + "1. Yes" + ConsoleColors.RESET);
                IO.println(ConsoleColors.YELLOW + "e. No" + ConsoleColors.RESET);
                IO.print("Choose an option: ");

                String choice = IO.readln();

                if ("1".equals(choice == null ? null : choice.trim())) {
                    player.equipWeapon(weapon);
                    IO.println("Weapon equipped: " + weapon.getName());
                }

            } else if (item instanceof Armor armor) {
                IO.println();
                IO.println("Do you want to equip " + item.getName() + " now?");
                IO.println(ConsoleColors.YELLOW + "1. Yes" + ConsoleColors.RESET);
                IO.println(ConsoleColors.YELLOW + "e. No" + ConsoleColors.RESET);
                IO.print("Choose an option: ");

                String choice = IO.readln();

                if ("1".equals(choice == null ? null : choice.trim())) {
                    player.equipArmor(armor);
                    IO.println("Armour equipped: " + armor.getName());
                }
            }

        } else {
            IO.println();
            IO.print(ConsoleColors.YELLOW + "[SYSTEM] " + ConsoleColors.RESET);
            IO.println("Inventory full! " + item.getName()
                    + " could not be picked up.");
        }

        IO.println();
    }
}
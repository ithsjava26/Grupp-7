
package RoomMechanics;

import java.util.List;

public class World {
    private final Room office = new Room(
            "office",
            "The Office",
            "You wake up at your desk with a headache. " +
                    "You look around. The office is a mess, lights are off and it's eerily quiet but you can hear " +
                    "a bit of a commotion coming from outside.",
            List.of(
                    new ExplorationOption(
                            "Look out the window",
                            ExplorationAction.LOOK_OUT_WINDOW,
                            false
                    ),
                    new ExplorationOption(
                            "Look at your phone",
                            ExplorationAction.LOOK_AT_PHONE,
                            true
                    ),
                    new ExplorationOption(
                            "Turn on the radio at your desk",
                            ExplorationAction.TURN_ON_RADIO,
                            false
                    ),
                    new ExplorationOption(
                            "Search the desks",
                            ExplorationAction.SEARCH_DESKS,
                            false
                    )
            ),
            "office-hallway"
    );

    private final Room officeHallway = new Room(
            "office-hallway",
            "Office Hallway",
            "You step into the hallway. Emergency lights flicker overhead, and several office doors stand open." +
                    "The stairwell door hangs ajar at the far end and you see one of your co-workers leaning against a wall",
            List.of(
                    new ExplorationOption(
                            "Talk to co-worker",
                            ExplorationAction.TALK_TO_COWORKER,
                            false
                    )
            ),
            "street-1"
    );

    private final Room street1 = new Room(
            "street-1",
            "Street 1",
            "Outside, the city is barely recognizable. Cars have been abandoned at odd angles, alarms echo" +
                    " between buildings, and distant figures move through the smoke. ...Is that gunshots? ",
            List.of(
                    new ExplorationOption(
                            "Observe the figures in the distance",
                            ExplorationAction.OBSERVE_FIGURES,
                            false
                    ),
                    new ExplorationOption(
                            "Search an abandoned car",
                            ExplorationAction.INSPECT_ABANDONED_CAR,
                            false
                    )
            ),
            "street-2"
    );
    private final Room street2 = new Room(
            "street-2",
            "Street 2",
            "The further you walk, the louder the chaos becomes. A bus blocks part of the road, its windows " +
                    "shattered. You trip on some debris and suddenly something starts rattling around inside the bus." +
                    " Before you have time to react you're face-to-face with a creature you can only describe as a zombie",
            //TODO FIRST COMBAT HERE!!! then trigger exploration options after combat
            List.of(
                    new ExplorationOption(
                            "Search an abandoned cop car",
                            ExplorationAction.SEARCH_POLICE_CAR,
                            false
                    ),
                    new ExplorationOption(
                            "Inspect the corpse",
                            ExplorationAction.INSPECT_CORPSE,
                            false
                    )
            ),
            "park-1"
    );

    private final Room park1 = new Room(
            "park-1",
            "Park 1",
            "You reach a park between the buildings - your shortcut home. " +
                    "A discarded backpack lies beside the path, and the bushes rustle somewhere out of sight. " +
                    "You know you're almost there but you can't shake the feeling that you're being watched.",

            List.of(
                    new ExplorationOption(
                            "Search the backpack",
                            ExplorationAction.SEARCH_BACKPACK,
                            false
                    ) //TODO add bandages and a motorcycle jacket
            ),
            "park-2"
    );

    private final Room park2 = new Room(
            "park-2",
            "Park 2",
            " Beyond the trees, you can see the next street leading towards a residential area... Home." +
                    " Just as you're about to start running towards your goal, you hear a gurgling scream behind you." +
                    "You turn around.",
            //TODO SECOND COMBAT HERE - 2 or 3 enemies (Long fight, need to use healing items)
            List.of(),
            "home"
    );

    private final Room home = new Room(
            "home",
            "The House",
            "The building looks untouched from the outside, but the curtains are drawn" +
                    " and no lights are on. You have to try to find your partner. You try the front door and it's unlocked",
            List.of(
                    new ExplorationOption(
                            "Search the ground floor",
                            ExplorationAction.SEARCH_GROUND_FLOOR,
                            false
                    )
            ),
            "upstairs"
    );

    private final Room upstairs = new Room(
            "upstairs",
            "Upstairs",
            "Familiar belongings are scattered across the landing, and a faint noise is coming from " +
                    "somewhere below. You remember why you came here and force yourself to keep moving. You gotta get " +
                    "those keys and get the hell out of here.",
            List.of(
                    new ExplorationOption(
                            "Search the bedroom for the car keys",
                            ExplorationAction.SEARCH_FOR_KEYS,
                            //TODO add car keys to inventory
                            false
                    ),
                    new ExplorationOption(
                            "Call out for your partner",
                            ExplorationAction.CALL_FOR_PARTNER,
                            false
                    )
            ),
            "garage"
    );

    private final Room garage = new Room(
            "garage",
            "Garage",
            "You reach the garage. Your car is still there, but as you approach it you hear footsteps " +
                    "dragging slowly towards you. As you turn to look you see a familiar face, warped into a zombie. " +
                    "Your partner."
            //TODO FINAL BATTLE
            ,
            List.of(
                    new ExplorationOption(
                            "Search your partner",
                            ExplorationAction.SEARCH_PARTNER,
                            false
                    ),
                    new ExplorationOption(
                            "Get in the car",
                            ExplorationAction.GET_IN_CAR,
                            false
                    )
            ),
            null
    );

    public Room getStartingRoom() {
        return office;
    }

    public Room getRoomById(String id) {
        if (office.id().equals(id)) {
            return office;
        }
        if (officeHallway.id().equals(id)) {
            return officeHallway;
        }
        if (street1.id().equals(id)) {
            return street1;
        }
        if (street2.id().equals(id)) {
            return street2;
        }
        if (park1.id().equals(id)) {
            return park1;
        }
        if (park2.id().equals(id)) {
            return park2;
        }
        if (home.id().equals(id)) {
            return home;
        }
        if (upstairs.id().equals(id)) {
            return upstairs;
        }
        if (garage.id().equals(id)) {
            return garage;
        }

        return null;
    }

    public Room getNextRoom(Room currentRoom) {
        if (currentRoom == null || currentRoom.nextRoomId() == null) {
            return null;
        }
        return getRoomById(currentRoom.nextRoomId());
    }
}

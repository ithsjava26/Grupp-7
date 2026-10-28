
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
            "Placeholder description.",
            List.of(),
            "street-1"
    );

    private final Room street1 = new Room(
            "street-1",
            "Street 1",
            "Placeholder description.",
            List.of(),
            "street-2"
    );
    private final Room street2 = new Room(
            "street-2",
            "Street 2",
            "Placeholder description.",
            List.of(),
            "park-1"
    );

    private final Room park1 = new Room(
            "park-1",
            "Park 1",
            "Placeholder description.",
            List.of(),
            "park-2"
    );

    private final Room park2 = new Room(
            "park-2",
            "Park 2",
            "Placeholder description.",
            List.of(),
            "home"
    );

    private final Room home = new Room(
            "home",
            "The House",
            "Placeholder description.",
            List.of(),
            "upstairs"
    );

    private final Room upstairs = new Room(
            "upstairs",
            "Upstairs",
            "Placeholder description.",
            List.of(),
            "garage"
    );

    private final Room garage = new Room(
            "garage",
            "Garage",
            "Placeholder description.",
            List.of(),
            null
    );

    public Room getStartingRoom() {
        return office;
    }
}



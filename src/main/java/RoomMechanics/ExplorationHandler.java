package RoomMechanics;

public class ExplorationHandler {
    public void handleExploration(ExplorationAction action, String playerName) {

        switch (action) {
            case LOOK_OUT_WINDOW:
                IO.println(
                        "The office is several floors above street level. Below, cars are abandoned in the middle of the road." +
                                " People are running, some stumbling, others chasing them. You notice emergency vehicles in" +
                                " the distance.");
                break;

            case LOOK_AT_PHONE:
                IO.println(
                        "Damn, no service");
                break;

            case TURN_ON_RADIO:
                IO.println(
                        "An emergency message is broadcasting. The message is fragmented, but you catch references to violent" +
                                " incidents and instructions to avoid contact with anyone displaying unusual symptoms.");
                break;

            case SEARCH_DESKS:
                IO.println(
                        "You search through the cluttered desks. Most contain ordinary office supplies, " +
                                "but you grab whatever could be useful.");
                break;
            case TALK_TO_COWORKER:
                IO.println("Oh my god, " + playerName + "! I couldn't get you to wake up, "
                        + "so I just left you. I thought you were one of them!\n"
                        + "Things went completely insane downstairs. People were attacking "
                        + "each other. I ran back inside.");
                break;
        }

    }
}


public class GameEngine {

    Room office = new Room(
            "Office",
            "You wake up at your desk with a headache. " +
                    "You look around. The office is a mess, lights are off and it's eerily quiet."
    );

    public void enterRoom(Room room) {
        IO.println("========== " + room.name().toUpperCase() + " ==========");
        IO.println(room.description());
    }

    public void startGame() {
        enterRoom(office);

        GameplayMenu gameplayMenu = new GameplayMenu();
        gameplayMenu.runMenu();
    }
}

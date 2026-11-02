public class World {
    private final Room office = new Room(
            "Office",
            "You wake up at your desk with a headache. " +
                    "You look around. The office is a mess, lights are off and it's eerily quiet."
    );
    public Room getStartingRoom() {
        return office;
    }
}
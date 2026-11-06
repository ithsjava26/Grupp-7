package roomMechanics;

import org.junit.jupiter.api.Test;
import roomMechanics.Room;
import roomMechanics.World;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    private final World world = new World();

    @Test
    void getStartingRoomShouldReturnOffice() {
        Room startingRoom = world.getStartingRoom();

        assertNotNull(startingRoom);
        assertEquals("office", startingRoom.id());
        assertEquals("The Office", startingRoom.name());
    }

    @Test
    void getRoomByIdShouldReturnCorrectRoom() {
        Room room = world.getRoomById("street-1");

        assertNotNull(room);
        assertEquals("street-1", room.id());
        assertEquals("Baker Street", room.name());
    }

    @Test
    void getRoomByIdShouldReturnNullForUnknownId() {
        Room room = world.getRoomById("non-existent");

        assertNull(room);
    }

    @Test
    void getNextRoomShouldReturnCorrectNextRoom() {
        Room office = world.getStartingRoom();

        Room nextRoom = world.getNextRoom(office);

        assertNotNull(nextRoom);
        assertEquals("office-hallway", nextRoom.id());
    }

    @Test
    void getNextRoomShouldReturnNullForFinalRoom() {
        Room garage = world.getRoomById("garage");

        Room nextRoom = world.getNextRoom(garage);

        assertNull(nextRoom);
    }

    @Test
    void getNextRoomShouldReturnNullWhenCurrentRoomIsNull() {
        Room nextRoom = world.getNextRoom(null);

        assertNull(nextRoom);
    }

    @Test
    void getNextRoomShouldFollowEntireRoomProgression() {
        String[] expectedRoomIds = {
                "office",
                "office-hallway",
                "street-1",
                "street-2",
                "park-1",
                "park-2",
                "home",
                "upstairs",
                "garage"
        };

        Room currentRoom = world.getStartingRoom();

        for (String expectedId : expectedRoomIds) {
            assertNotNull(currentRoom);
            assertEquals(expectedId, currentRoom.id());

            currentRoom = world.getNextRoom(currentRoom);
        }

        assertNull(currentRoom);
    }

}
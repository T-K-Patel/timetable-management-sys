import java.util.HashMap;

public class Room {
    String roomId;
    int roomCapacity;
    private HashMap<String, Boolean> _occupiedSlots;

    Room(String roomId, int roomCapacity) {
        this.roomId = roomId;
        this.roomCapacity = roomCapacity;
        this._occupiedSlots = new HashMap<>();
    }

    public void addOccupiedSlot(String slot) {
        _occupiedSlots.put(slot, true);
    }

    public boolean isSlotOccupied(String slot) {
        return _occupiedSlots.containsKey(slot);
    }

    public void removeOccupiedSlot(String slot) {
        _occupiedSlots.remove(slot);
    }

    @Override
    public String toString() {
        return roomId + " (" + roomCapacity + ")\n";
    }
}
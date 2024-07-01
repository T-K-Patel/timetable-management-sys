import java.util.HashMap;

class Professor {
    String professorId;
    String professorName;
    String professorDepartment;
    private HashMap<String, Boolean> _occupiedSlots;

    Professor(String professorId, String professorName, String professorDepartment) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorDepartment = professorDepartment;
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
}
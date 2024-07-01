import java.util.*;

class TimeSlot {
    int day; // Day of the week (e.g., 1 to 5)
    int slotCode; // Slot code for the day (e.g., 1 to 5)

    TimeSlot(int day, int slotCode) {
        this.day = day;
        this.slotCode = slotCode;
    }

    @Override
    public String toString() {
        return "Day " + day + " Slot " + slotCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return day == timeSlot.day && slotCode == timeSlot.slotCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, slotCode);
    }
}

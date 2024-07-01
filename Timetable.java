import java.util.*;

public class Timetable {
    private Map<String, Course> courses;
    private Map<String, Room> rooms;
    private Map<String, Professor> professors;
    private List<TimeSlot> timeSlots;
    private Map<Course, Map<TimeSlot, List<Room>>> courseSlotRoomMap;

    public Timetable(Map<String, Course> courses, Map<String, Room> rooms, Map<String, Professor> professors,
            List<TimeSlot> timeSlots) {
        this.courses = courses;
        this.rooms = rooms;
        this.professors = professors;
        this.timeSlots = timeSlots;
        this.courseSlotRoomMap = new HashMap<>();
    }

    public boolean generateTimetable() {
        Course[] coursesArray = courses.values().toArray(new Course[0]);
        Arrays.sort(coursesArray, Comparator.comparingInt(course -> -course.courseHours)); // Sort by courseHours
                                                                                           // descending
        return assign(coursesArray, 0);
    }

    private boolean assign(Course[] coursesArray, int index) {
        if (index == coursesArray.length) {
            return true; // Base case: All courses are assigned
        }

        Course course = coursesArray[index];
        Map<TimeSlot, List<Room>> slotRoomMap = courseSlotRoomMap.computeIfAbsent(course, k -> new HashMap<>());

        // Sort rooms by capacity ascending
        List<Room> sortedRooms = new ArrayList<>(rooms.values());
        sortedRooms.sort(Comparator.comparingInt(room -> room.roomCapacity));

        // Sort time slots randomly
        List<TimeSlot> availableSlots = new ArrayList<>(timeSlots);
        Collections.shuffle(availableSlots);

        // Attempt to assign the course to each available slot and room
        for (TimeSlot timeSlot : availableSlots) {
            for (Room room : sortedRooms) {
                if (isValid(course, timeSlot, room)) {
                    // Assign the course to the slot and room
                    assignCourseToSlot(course, timeSlot, room);
                    slotRoomMap.computeIfAbsent(timeSlot, k -> new ArrayList<>()).add(room);

                    // Recursive call to assign the next course
                    if (assign(coursesArray, index + 1)) {
                        return true; // Successfully assigned all courses
                    }

                    // Backtrack if the next course cannot be assigned
                    unassignCourseFromSlot(course, timeSlot, room);
                    slotRoomMap.get(timeSlot).remove(room);
                    if (slotRoomMap.get(timeSlot).isEmpty()) {
                        slotRoomMap.remove(timeSlot);
                    }
                }
            }
        }

        return false; // Could not assign all courses for this configuration
    }

    private boolean isValid(Course course, TimeSlot timeSlot, Room room) {
        if (room.roomCapacity < course.registeredStudents) {
            return false;
        }
        if (room.isSlotOccupied(timeSlot.toString())) {
            return false;
        }
        Professor professor = professors.get(course.professorId);
        if (professor.isSlotOccupied(timeSlot.toString())) {
            return false;
        }
        // Additional checks if needed
        return true;
    }

    private void assignCourseToSlot(Course course, TimeSlot timeSlot, Room room) {
        room.addOccupiedSlot(timeSlot.toString());
        professors.get(course.professorId).addOccupiedSlot(timeSlot.toString());
    }

    private void unassignCourseFromSlot(Course course, TimeSlot timeSlot, Room room) {
        room.removeOccupiedSlot(timeSlot.toString());
        professors.get(course.professorId).removeOccupiedSlot(timeSlot.toString());
    }

    public void printTimetable() {
        for (Course course : courseSlotRoomMap.keySet()) {
            Map<TimeSlot, List<Room>> slotRoomMap = courseSlotRoomMap.get(course);
            System.out.print("Course: " + course.courseCode);
            for (TimeSlot timeSlot : slotRoomMap.keySet()) {
                System.out.print(" Slot: [" + timeSlot.toString() + "] Rooms: ");
                List<Room> rooms = slotRoomMap.get(timeSlot);
                for (Room room : rooms) {
                    System.out.print(room.roomId + " ");
                }
            }
            System.out.println();
        }
    }
}

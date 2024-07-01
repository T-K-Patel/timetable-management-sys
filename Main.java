import java.util.*;

public class Main {

    static int COURSES = 5;
    static int ROOMS = 50;
    static int PROFESSORS = 35;

    public static Map<String, Course> getCourseMap() {
        Map<String, Course> courses = new HashMap<>();
        // Add n courses by loop
        for (int i = 0; i < COURSES; i++) {
            // get random number of registered students
            int registeredStudents = (int) (Math.random() * 90 + 10);
            int courseHours = (int) (Math.random() * 2 + 3);
            int professorId = (int) (Math.random() * PROFESSORS);
            courses.put("C" + i, new Course("Course " + i, "C" + i, "P" + professorId, courseHours, courseHours,
                    registeredStudents));
        }
        return courses;
    }

    public static Map<String, Room> getRoomMap() {
        Map<String, Room> rooms = new HashMap<>();
        // Add n rooms by loop
        for (int i = 0; i < ROOMS; i++) {
            // get random number of room capacity
            int roomCapacity = (int) (Math.random() * 150 + 10);
            rooms.put("R" + i, new Room("R" + i, roomCapacity));
        }
        return rooms;
    }

    public static Map<String, Professor> getProfessorMap() {
        Map<String, Professor> professors = new HashMap<>();
        // Add n professors by loop
        for (int i = 0; i < PROFESSORS; i++) {
            professors.put("P" + i, new Professor("P" + i, "Prof. " + (char) ('A' + i), "Department " + i));
        }
        return professors;
    }

    public static void main(String[] args) {
        Map<String, Course> courses = getCourseMap();
        Map<String, Room> rooms = getRoomMap();
        Map<String, Professor> professors = getProfessorMap();

        // System.out.println(courses);
        // System.out.println(rooms);

        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int day = 1; day <= 5; day++) {
            for (int slot = 1; slot <= 5; slot++) {
                timeSlots.add(new TimeSlot(day, slot));
            }
        }

        Timetable timetable = new Timetable(courses, rooms, professors, timeSlots);
        if (timetable.generateTimetable()) {
            timetable.printTimetable();
        } else {
            System.out.println("No valid timetable found.");
        }
    }

}

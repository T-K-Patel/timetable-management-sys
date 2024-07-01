class Course {
    String courseName;
    String courseCode;
    String professorId;
    int courseCredits;
    int courseHours;
    int registeredStudents;

    Course(String courseName, String courseCode, String professorId, int courseCredits, int courseHours,
            int registeredStudents) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredits = courseCredits;
        this.courseHours = courseHours;
        this.professorId = professorId;
        this.registeredStudents = registeredStudents;
    }

    @Override
    public String toString() {
        // print course name and professor id and regsietered students
        return courseName + " - " + professorId + " - " + registeredStudents + "\n";
    }
}
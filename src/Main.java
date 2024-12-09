import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    static Timetable generatedScheduleTimetable = new Timetable();
    static ArrayList<Instructor> instructorsList = new ArrayList<>();
    static ArrayList<Course> coursesList = new ArrayList<>();
    static ArrayList<Classroom> classroomList = new ArrayList<>();

    static String selectedCommonCoursesDocPATH;
    static String selectedInstructorsDocPATH;
    static String selectedClassroomsDocPATH;
    static String selectedCoursesDocPATH;
    static String selectedSavePath;

    public static _label comCoursesPath = new _label("Common Courses file: " + selectedCommonCoursesDocPATH);
    public static _label coursesPath = new _label("Courses file: " + selectedCoursesDocPATH);
    public static _label instructorsPath = new _label("Instructors file: " + selectedInstructorsDocPATH);
    public static _label classroomsPath = new _label("Classrooms file: " + selectedClassroomsDocPATH);

    public static _label savePath = new _label("Save file: " + selectedSavePath);

    public static void main(String[] args) {
        _frame frame = new _frame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        JPanel selectPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel savePanel = new JPanel();

        frame.add(titlePanel);
        frame.add(selectPanel);
        frame.add(infoPanel);
        frame.add(savePanel);

        titlePanel.setBackground(new Color(0x1b1815));
        selectPanel.setBackground(new Color(0x1b1815));
        infoPanel.setBackground(new Color(0x1b1815));
        savePanel.setBackground(new Color(0x1b1815));

        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        ImageIcon logoIcon = new ImageIcon("src/images/BeePlan logo without logo.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(100, -1, Image.SCALE_SMOOTH)));
        logoLabel.setOpaque(true);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        _label head1 = new _label("Welcome to BeePlan");
        head1.setFont(new Font("IM Fell English SC", Font.PLAIN, 24));
        head1.setAlignmentX(Component.CENTER_ALIGNMENT);
        _label head2 = new _label("Warning: For the program to work correctly and accurately, please ensure that all selected and saved files are in .txt format.");
        head2.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(Box.createVerticalStrut(35));
        titlePanel.add(logoLabel);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(head1);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(head2);

        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));

        selectPanel.add(Box.createVerticalStrut(10));
        _button selectCommonCoursesDocButton = new _button("selectCommonCoursesDocButton","Select Common Courses Document", 1);
        selectCommonCoursesDocButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectPanel.add(selectCommonCoursesDocButton);
        selectedCommonCoursesDocPATH = selectCommonCoursesDocButton.selectedFilePath;

        selectPanel.add(Box.createVerticalStrut(10));

        _button selectCoursesDocButton = new _button("selectCoursesDocButton","Select Course Document", 1);
        selectCoursesDocButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectPanel.add(selectCoursesDocButton);
        selectedCoursesDocPATH = selectCoursesDocButton.selectedFilePath;

        selectPanel.add(Box.createVerticalStrut(10));

        _button selectInstructorsDocButton = new _button("selectInstructorsDocButton","Select Instructors Document", 1);
        selectInstructorsDocButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectPanel.add(selectInstructorsDocButton);
        selectedInstructorsDocPATH = selectInstructorsDocButton.selectedFilePath;

        selectPanel.add(Box.createVerticalStrut(10));

        _button selectClassroomsDocButton = new _button("selectClassroomsDocButton","Select Classroom Document", 1);
        selectClassroomsDocButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectPanel.add(selectClassroomsDocButton);
        selectedClassroomsDocPATH = selectClassroomsDocButton.selectedFilePath;

        selectPanel.add(Box.createVerticalStrut(10));

        selectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(comCoursesPath);
        comCoursesPath.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(coursesPath);
        coursesPath.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(instructorsPath);
        instructorsPath.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(classroomsPath);
        classroomsPath.setAlignmentX(Component.LEFT_ALIGNMENT);

        _button generateCourseScedule = new _button("generateCourseScedule","Generate Course Scedule", 0);

        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
        savePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        generateCourseScedule.setAlignmentX(Component.CENTER_ALIGNMENT);

        savePanel.add(Box.createVerticalStrut(10));
        savePanel.add(generateCourseScedule);
        selectedSavePath = generateCourseScedule.selectedFilePath;

        savePanel.add(Box.createVerticalStrut(10));
        savePanel.add(savePath);
        savePath.setAlignmentX(Component.CENTER_ALIGNMENT);
        savePanel.add(Box.createVerticalStrut(35));

        frame.setVisible(true);
    }

    public static void generateCourseScedulePROGRESS() {
        if (selectedCommonCoursesDocPATH==null) {
            System.out.println("Error: Common Courses Document is null!");
        }
        if (selectedInstructorsDocPATH==null) {
            System.out.println("Error: Instructors Document is null!");
        }
        if (selectedClassroomsDocPATH==null) {
            System.out.println("Error: Classroom Document is null!");
        }
        if (selectedCoursesDocPATH==null) {
            System.out.println("Error: Course Document is null!");
        }
        if (selectedSavePath==null) {
            System.out.println("Error: Save Path is null!");
        }

        readInstructors(selectedInstructorsDocPATH);
        readCourses(selectedCoursesDocPATH);
        readClassrooms(selectedClassroomsDocPATH);
        readCommonCourses(selectedCommonCoursesDocPATH);

        try {
            try (FileWriter cleaner = new FileWriter(selectedSavePath, false)) {
                cleaner.close();
            } catch (IOException e) {
                System.err.println("Dosya temizlenirken bir hata oluştu: " + e.getMessage());
            }

            FileWriter detailsWriter = new FileWriter(selectedSavePath);

            for (Course c : coursesList) {
                if (c.instructor == null) {
                    System.err.println("Instructor not found for course: " + c.courseCode);
                    continue;
                }

                if (c.theoricalHours > 0) {
                    if (!scheduleCourse(detailsWriter, c, "theorical", c.theoricalHours)) {
                        System.err.println("Failed to schedule theorical slots for: " + c.courseCode);
                    }
                }

                if (c.practicalHours > 0) {
                    if (c.studentEstimated > 40) {
                        int numGroups = (int) Math.ceil((double) c.studentEstimated / 40);
                        for (int i = 1; i <= numGroups; i++) {
                            if (!scheduleCourse(detailsWriter, c, "practical (" + i + ")", c.practicalHours)) {
                                System.err.println("Failed to schedule practical slots for: " + c.courseCode + " group " + i);
                            }
                        }
                    } else {
                        if (!scheduleCourse(detailsWriter, c, "practical", c.practicalHours)) {
                            System.err.println("Failed to schedule practical slots for: " + c.courseCode);
                        }
                    }
                }
            }

            detailsWriter.close();
            System.out.println("Tüm dersler başarıyla yerleştirildi!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            File resultFile = new File(selectedSavePath);
            if (resultFile.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(resultFile);
            }else {
                System.out.println("File not found!");
            }
        }catch (IOException e) {
            System.out.println("Error: There is an error at opening the file" + e.getMessage());
        }
    }

    public static boolean scheduleCourse(FileWriter detailsWriter, Course c, String type, int hours) throws IOException {
        for (int day = 1; day <= 5; day++) {
            for (int session = 1; session <= (8 - hours + 1); session++) {
                for (Classroom classroom : classroomList) {
                    String dayStr = Integer.toString(day);
                    if (checkConsecutiveSlots(c.instructor, classroom, dayStr, session, hours)) {
                        reserveConsecutiveSlots(c.instructor, classroom, dayStr, session, hours);
                        detailsWriter.write(c.courseCode + "#" + c.courseName + "#" + type + "#" + c.instructor.name + "#" + classroom.classroomCode + "#");
                        for (int i = 0; i < hours; i++) {
                            detailsWriter.write(dayStr + "-" + (session + i));
                            if (i != hours - 1) detailsWriter.write(",");
                        }
                        detailsWriter.write("\n");
                        return true;
                    }
                }
            }
        }
        System.err.println("Failed to schedule " + type + " slots for: " + c.courseCode + " (" + c.courseName + ")");
        return false;
    }

    public static boolean checkConsecutiveSlots(Instructor instructor, Classroom classroom, String day, int startSession, int requiredSlots) {
        int dayIndex = Integer.parseInt(day) - 1;

        if (instructor.dailyTheoricalLectures[dayIndex] + requiredSlots > 4) {
            return false;
        }

        for (int i = 0; i < requiredSlots; i++) {
            String slot = day + "-" + (startSession + i);
            if (!instructor.avaliableSessions.checkAvaliablity(slot)) {
                return false;
            }
            if (!classroom.avaliableSessions.checkAvaliablity(slot)) {
                return false;
            }
        }
        return true;
    }

    public static void reserveConsecutiveSlots(Instructor instructor, Classroom classroom, String day, int startSession, int requiredSlots) {
        int dayIndex = Integer.parseInt(day) - 1;
        instructor.dailyTheoricalLectures[dayIndex] += requiredSlots;

        for (int i = 0; i < requiredSlots; i++) {
            String slot = day + "-" + (startSession + i);
            instructor.avaliableSessions.changeStatus(slot, false);
            classroom.avaliableSessions.changeStatus(slot, false);
        }
    }

    public static void readInstructors(String documentDirectory) {
        try (BufferedReader br = new BufferedReader(new FileReader(documentDirectory))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                instructorsList.add(new Instructor(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }

    public static void readCourses(String documentDirectory) {
        try (BufferedReader br = new BufferedReader(new FileReader(documentDirectory))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                Instructor instructor = findInstructor(parts[6]);
                coursesList.add(new Course(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], instructor));
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }

    public static void readClassrooms(String documentDirectory) {
        try (BufferedReader br = new BufferedReader(new FileReader(documentDirectory))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                classroomList.add(new Classroom(parts[0], parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }

    public static void readCommonCourses(String documentDirectory) {
        try (BufferedReader br = new BufferedReader(new FileReader(documentDirectory))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("#");
                String[] slots = parts[2].split(",");
                for (String slot : slots) {
                    generatedScheduleTimetable.changeStatus(slot, false);
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }

    public static Instructor findInstructor(String instructorName) {
        for (Instructor instructor : instructorsList) {
            if (instructor.name.equals(instructorName)) {
                return instructor;
            }
        }
        return null;
    }
}
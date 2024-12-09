import java.util.HashMap;
import java.util.Map;

public class Timetable {
    public Map<String, Boolean> timetable;
    public String reservedForExams1 = "5-5", reservedForExams2 = "5-6";

    public Timetable() {
        timetable = new HashMap<>();
        generateTimetable();
    }

    public void generateTimetable() {
        for (int day = 1; day < 6; day++) {
            for (int lec = 1; lec <= 8; lec++) {
                String key = day + "-" + lec;
                timetable.put(key, true);
            }
        }
        changeStatus(reservedForExams1, false);
        changeStatus(reservedForExams2, false);
    }

    public boolean checkAvaliablity(String slot) {
        return timetable.get(slot);
    }

    public void changeStatus(String slot, boolean status) {
        this.timetable.put(slot, status);
    }

    public void printTimetable() {
        System.out.println("Timetable:");

        System.out.printf("%-12s", "");
        for (int day = 1; day < 6; day++) {
            System.out.printf("%-10s", "Day " + day);
        }
        System.out.println();

        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[31m";

        for (int lec = 1; lec <= 8; lec++) {
            System.out.printf("%-12s", "Lecture " + lec);
            for (int day = 1; day < 6; day++) {
                String key = day + "-" + lec;
                if (timetable.get(key)) {
                    System.out.printf(GREEN + "%-10s" + RESET, "■");
                } else {
                    System.out.printf(RED + "%-10s" + RESET, "-");
                }
            }
            System.out.println();
        }
    }

}
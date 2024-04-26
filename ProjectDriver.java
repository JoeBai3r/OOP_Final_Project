//Group Members: Joseph Baier, Yoan Molina, Trevor Chandrapaul, David Hablich

import java.util.*;

class IdException extends Exception {
    public IdException(String message) {
        super(message);
    }
}

public static class ProjectDriver {
    public static void main(String[] args) {

        MainMenu menu = new MainMenu();

        while (true) {
            menu.mainMenu();
        }
    }

}

public class MainMenu {

    public void mainMenu() {
        Scanner s = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.println("\tMenu\n");
        System.out.println("1 : Student Management");
        System.out.println("2 : Course Management");
        System.out.println("3 : Exit\n");

        System.out.print("\tEnter your selection: ");

        int option = s.nextInt();
        System.out.println("----------------\n");
        while (true) {

            switch (option) {
                case 1:
                    StudentManagement();
                    break;
                case 2:
                    CourseManagement();
                    break;
                case 0:
                    System.err.println("Goodbye!");
                    System.exit(0);

            }
        }

    }

    private static void StudentManagement() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Student Management Menu: ");
        System.out.println("Choose one of: ");
        System.out.println("A - Add a student");
        System.out.println("B - Search for a student by ID");
        System.out.println("C - Delete a student");
        System.out.println("D - Print fee invoice of student by ID");
        System.out.println("X - Back to main menu");

        char studentOption = scn.next().charAt(0);

        switch (studentOption) {
            case 'A':
            case 'a': // ADD a student
                boolean checkId = true;
                String studentId;
                do {
                    System.out.println("Enter student's ID:");
                    studentId = scn.nextLine();
                    checkId = false;
                    for (Student student : valenceCollege.getList()) {
                        if (student.getId().equals(studentId)) {
                            System.out.println("Sorry, " + studentId + " is already assigned to another student.");
                            checkId = true;
                            break;
                        }
                    }
                } while (checkId);
                System.out.println("Student type (PhD, Ms, or Undergrad): ");
                String studentType = scn.nextLine();
                System.out.println("Enter student name:");
                String studentName = scn.nextLine();
                // add constructor for grad student here?
                break;
            case 'B':
            case 'b': // SEARCH a student
                System.out.println("Search a student by id");
                break;
            case 'C':
            case 'c': // DELETE student
                System.out.print("Delete a student ");
                System.out.println("          deleted!");
                // DON'T FORGET TO UPDATE lec.txt
                break;
            case 'D':
            case 'd': // Print fee
                System.out.println("Print fee invoice of student by id");
                break;
            case 'X': // Go back to main menu
            case 'x':
                break;
            default:
                System.out.println("Invalid option. Please try again");
                break;
        }

    }

    private static void CourseManagement() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Course Management Menu: ");
        System.out.println("Choose one of: ");
        System.out.println("A - Search for a class or lab using the class/lab number");
        System.out.println("B - delete a class");
        System.out.println("C - Add a lab to a class");
        System.out.println("X - Back to main menu");
        System.out.print("Enter your selection: ");

        char courseOption = scn.next().charAt(0);

        switch (courseOption) {
            case 'A':
            case 'a': // SEARCH LAB OR CLASS
                System.out.print("Enter the Class/Lab Number: ");
                break;
            case 'B':
            case 'b': // DELETE CLASS
                System.out.print("Enter the Class/Lab Number: ");
                System.out.println("          deleted!");
                // DON'T FORGET TO UPDATE lec.txt
                break;
            case 'C':
            case 'c': // ADD LAB TO LECTURE
                System.out.println("Enter Lab or Lecture: ");
                System.out.println("Enter the Lecture Number to a Lab to: ");
                int cond = 1;
                if (cond == 1) {
                    System.out.println("   is valid. Enter the rest of the information: ");
                    System.out.println("Lecture added successfully ");
                    // DON'T FORGET TO UPDATE lec.txt
                } else {
                    System.out.println("No Such Lecture Exists!");
                }
                break;
            case 'X':
            case 'x':
                break;
            default:
                System.out.println("Invalid option. Please try again");
                break;
        }
    }

}

abstract class Student {
    private String name;
    private String id;
    private static ArrayList<String> usedIds = new ArrayList<>();

    public Student(String name, String id) throws IdException {
        if(!isValidId(id)){
            throw new IdException(("Invalid id format or ID already exists\nTry again later!"))
        }
        this.name = name;
        this.id = id;
        usedIds.add(id);
    }

    private boolean isValidId(String id) {
        //checks format
        if (!id.matches("[A-Z]{2}\\d{4}")) { //need to verify this works
            return false;
        }
        //checks for duplicate
        if (usedIds.contains(id)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
     * public ArrayList<String> getStudentData() {
     * return studentData;
     * }
     */
    /*
     * public void setStudentData(ArrayList<String> studentData) {
     * this.studentData = studentData;
     * }
     */

    abstract public void printInvoice();

}

public abstract class GraduateStudent extends Student {
    int crn;

    public GraduateStudent(String name, String id, int crn) {
        // crn is the crn that the grad student is a teaching assistant for
        super(name, id);
        this.crn = crn;
    }

    public class UndergraduateStudent extends Student {
        private int undergradCrnsTaken[];
        private double gpa;
        private boolean resident;

        public double creditHourPrice;

        public double calculateCreditCost(double creditHourPrice, boolean resident) {

            if (resident) {
                creditHourPrice = 120.25;
            } else {
                creditHourPrice = 120.25 * 2;
            }
            return creditHourPrice;
        }

        public UndergraduateStudent(String name, String id, int[] undergradCrnsTaken, double gpa, boolean resident) {
            super(name, id);
            this.undergradCrnsTaken = undergradCrnsTaken;
            this.gpa = gpa;
            this.resident = resident;
        }

        @Override
        public void printInvoice() {

            System.out.print("VALENCE COLLEGE\n");
            System.out.print("ORLANDO FL 10101\n");
            System.out.print("---------------------\n\n");
            System.out.print("Fee Invoice Prepared for Student: \n");
            System.out.print(getId() + "-" + getName() + "\n\n");
            double creditCost = calculateCreditCost(creditHourPrice, resident);
            System.out.printf("1 Credit Hour = $%.2f\n\n", creditCost);
            System.out.print("CRN\tCR_PREFIX\tCR_HOURS\n");
            // need to fix with loop
            System.out.printf("4587\tMAT 236\t\t4\t" + "$%.2f\n", creditCost * 4.00);
            System.out.printf("2599\tCOP 260\t\t3\t$%.2f\n\n", creditCost * 3.00);
            System.out.println("\t\tHealth & id fees $35.00\n");
            System.out.println("--------------------------------------");
            double preTotal = (((calculateCreditCost(creditHourPrice, resident) * 4.00))
                    + (calculateCreditCost(creditHourPrice, resident) * 3.00) + 35.00);
            System.out.printf("\t\t\t\t$%.2f\n", preTotal);
            if (gpa >= 3.5 && preTotal > 500.00) {
                double discount = preTotal * 0.25;
                System.out.printf("\t\t\t-$%.2f" + discount);
                System.out.println("\t\t\t----------");
                double totalPayment = preTotal - discount;
                System.out.printf("\t\tTOTAL PAYMENTS\t$%.2f" + totalPayment);
            } else {
                System.out.println("\t\t\t\t----------");
                System.out.printf("\t\tTOTAL PAYMENTS\t$%.2f\n\n\n", preTotal);
            }
        }
    }

    class MsStudent extends GraduateStudent {
        int gradCrnsTaken[];

        public MsStudent(String name, String id, int[] gradCrnsTaken, int crn) {
            // gradCoursesTaken is the array of the crns that the Ms student is taking
            // crn is the course number that the Phd student is a teaching assistant for
            super(name, id, crn);
            this.gradCrnsTaken = gradCrnsTaken;

        }

        @Override
        public void printInvoice() {
            System.out.print("VALENCE COLLEGE\n");
            System.out.print("ORLANDO FL 10101\n");
            System.out.print("---------------------\n\n");
            System.out.print("Fee Invoice Prepared for Student: \n");
            System.out.print(getId() + "-" + getName() + "\n\n");
            System.out.println("1 Credit Hour = $300.00\n");
            double preTotal = 300.00 * 6.00;
            System.out.println("CRN\tCR_PREFIX\tCR_HOURS");
            System.out.printf("7587\tMAT 936\t\t5\t\t$%.2f\n", 300 * 5.00);
            System.out.printf("8997\tGOL 124\t\t1\t\t$%.2f\n\n", 300 * 1.00);
            System.out.println("\t\t\tHealth & id fees $35.00\n");
            System.out.println("--------------------------------------");
            double total = preTotal + 35.00;
            System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", total);
        }

    }

    class PhdStudent extends GraduateStudent {

        private String advisor;
        private String researchSub;
        private int supervisedLabs[];

        public PhdStudent(String name, String id, String advisor, String researchSubject, int crn) {
            super(name, id, crn);
            this.advisor = advisor;
            this.researchSub = researchSubject;
        }

        public void printInvoice() {
            System.out.print("VALENCE COLLEGE\n");
            System.out.print("ORLANDO FL 10101\n");
            System.out.print("---------------------\n\n");
            System.out.print("Fee Invoice Prepared for Student: \n");
            System.out.print(getId() + "-" + getName() + "\n\n");
            System.out.println("RESEARCH");
            double researchFee = 700.00;
            System.out.printf(researchSub + "\t\t\t$%.2f\n\n", researchFee);
            System.out.println("\t\tHealth & id fees $35.00\n\n");
            System.out.println("--------------------------------------");
            double totalPayment = researchFee + 35.00;
            System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", totalPayment);

        }
    }

    class Lab {
        private String crn;

        private String classroom;

        public String getCrn() {
            return crn;
        }

        public void setCrn(String crn) {

            this.crn = crn;
        }

        public String getClassroom() {
            return classroom;
        }

        public void setClassroom(String classroom) {
            this.classroom = classroom;
        }

        @Override
        public String toString() {
            return crn + "," + classroom;
        }

        public Lab(String crn, String classroom) {
            this.crn = crn;
            this.classroom = classroom;
        }
    }

    class Lecture {
        private String crn;
        private String prefix;
        private String lectureName;
        private LectureType lectureType; // Grad or UnderGrad
        private LectureMode lectureMode; // F2F, Mixed or Online
        private String classroom;
        private boolean hasLabs;
        private int creditHours;
        ArrayList<Lab> labs;

        // _________________
        // Helper method-used in constructors to set up the common fields
        private void LectureCommonInfoSetUp(String crn, String prefix, String lectureName, LectureType lectureType,
                LectureMode lectureMode) {
            this.crn = crn;
            this.prefix = prefix;
            this.lectureName = lectureName;
            this.lectureType = lectureType;
            this.lectureMode = lectureMode;
        }

        // Non-online with Labs
    public Lecture( String crn,
    String prefix,
    String lectureName,
    LectureType lectureType,
    LectureMode lectureMode,
    String classroom,
    boolean hasLabs,
    int creditHours,
    ArrayList<Lab> labs ) {
        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMod
        e);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
        this.labs = labs;
    }

        // Constructor for Non-online without Labs
    public Lecture( String crn, String prefix, String lectureName,
    LectureType lectureType, LectureMode lectureMode, String classroom,
    boolean hasLabs, int creditHours) {
        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMod
        e);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

        // Constructor for Online Lectures
    public Lecture(String crn, String prefix, String lectureName,
    LectureType lectureType, LectureMode lectureMode, int creditHours) {
        LectureCommonInfoSetUp(crn,prefix,lectureName,lectureType,lectureMod
        e);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

        // ________
        @Override
        public String toString() {
            String lectureAndLabs = crn + "," + prefix + "," +
                    lectureName + "," + lectureType + ","
                    + lectureMode + "," + hasLabs + "," +
                    creditHours + "\n";
            if (labs != null) {// printing corresponding labs
                // lectureAndLabs+="\n";
                for (Lab lab : labs)
                    lectureAndLabs += lab + "\n";
            }
            return lectureAndLabs;
        }
}

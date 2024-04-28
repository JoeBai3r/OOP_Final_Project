//Group Members: Joseph Baier, Yoan Molina, Trevor Chandrapaul, David Hablich

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Projectdriver {
    public static void createFile(ArrayList<Lecture> list, String fileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File(fileName));
            for (Lecture lecture : list) {
                pw.println(lecture.printToText());
                for (Lab lab : lecture.labs) {
                    pw.println(lab.toString());
                }
            }
        } catch (Exception e) {

        } finally {
            pw.close();
        }
    }

    public static void main(String[] args) {

        MainMenu menu = new MainMenu();
        while (true) {
            menu.mainMenu();
        }

    }

}

enum LectureType {
    GRAD, UNDERGRAD;
}

enum LectureMode {
    F2F, MIXED, ONLINE;
}

// college class keeps track of all student arrays
class College {

    private static ArrayList<Student> StudentArray = new ArrayList<Student>();
    // array list of lectures holds all lectures in from lec.txt
    private static ArrayList<Lecture> ListOfLectures = new ArrayList<Lecture>();

    public College() {
        // the instance of college leads to the text file being read
        try {
            Scanner scanner = new Scanner(new File("lec.txt"));
            String line = "";
            String[] lectureItems;
            Lecture lecture = null;

            boolean skipLine = false;
            boolean oneMorePass = false;

            while (scanner.hasNextLine() || oneMorePass) {

                if (skipLine == false) {
                    line = scanner.nextLine();
                }

                oneMorePass = false;

                lectureItems = line.split(",");

                if (lectureItems.length > 2) { // means it must be lecture

                    LectureType type;
                    LectureMode mode;
                    // new lab array for the specific lab list being made;
                    ArrayList<Lab> labList = new ArrayList<>();

                    type = LectureType.GRAD;
                    if (lectureItems[3].compareToIgnoreCase("Graduate") != 0)
                        type = LectureType.UNDERGRAD;

                    if (lectureItems[4].compareToIgnoreCase("ONLINE") == 0) {
                        skipLine = false;
                        lecture = new Lecture(lectureItems[0], lectureItems[1], lectureItems[2], type,
                                LectureMode.ONLINE, Integer.parseInt(lectureItems[5]));
                    } else {
                        mode = LectureMode.F2F;
                        if (lectureItems[4].compareToIgnoreCase("F2F") != 0)
                            mode = LectureMode.MIXED;

                        boolean hasLabs = true;
                        if (lectureItems[6].compareToIgnoreCase("yes") != 0)
                            hasLabs = false;

                        if (hasLabs) {// Lecture has a lab
                            skipLine = true;
                            String[] labItems;
                            while (scanner.hasNextLine()) {
                                line = scanner.nextLine();
                                if (line.length() > 15) {// True if this is not a lab!

                                    if (scanner.hasNextLine() == false) {// reading the last line if any...
                                        oneMorePass = true;
                                    }

                                    break;
                                }
                                labItems = line.split(",");
                                Lab lab = new Lab(labItems[0], labItems[1]);
                                labList.add(lab);

                            } // end of while
                            lecture = new Lecture(lectureItems[0], lectureItems[1], lectureItems[2], type, mode,
                                    lectureItems[5], hasLabs, Integer.parseInt(lectureItems[7]), labList);
                        } else {// Lecture doesn't have a lab
                            skipLine = false;
                            lecture = new Lecture(lectureItems[0], lectureItems[1], lectureItems[2], type, mode,
                                    lectureItems[5], hasLabs, Integer.parseInt(lectureItems[7]));
                        }
                    }
                }
                ListOfLectures.add(lecture);
            } // end of while

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }// end of main

    public static ArrayList<Student> getStudentList() {
        return StudentArray;
    }

    public static ArrayList<Lecture> getLectureList() {
        return ListOfLectures;
    }

}

class MainMenu {

    public College valenceCollege = new College();

    public void mainMenu() {
        Scanner s = new Scanner(System.in);

        while (true) {

            System.out.println("----------------------------");
            System.out.println("Main Menu\n");
            System.out.println("1 : Student Management");
            System.out.println("2 : Course Management");
            System.out.println("3 : Exit\n");
            System.out.print("\tEnter your selection: ");
            try {
                int option = s.nextInt();
                System.out.println("----------------\n");

                // boolean keeps track to make sure submenu keeps running and is only changed if
                // x is selected
                boolean bool = true;

                switch (option) {
                    case 1:
                        while (bool) {
                            bool = StudentManagement(bool);
                        }
                        break;
                    case 2:
                        while (bool) {
                            bool = CourseManagement(bool);
                        }
                        break;
                    case 3:
                        System.err.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please try again");
                        break;
                }
            } catch (Exception e) {
                // System.out.println("Wrong input, please try again\n");
                s.nextLine();
            }
        }
    }

    private boolean StudentManagement(boolean bool) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Student Management Menu: ");
        System.out.println("Choose one of: ");
        System.out.println("A - Add a student");
        System.out.println("B - Delete a Student");
        System.out.println("C - Print Fee Invoice");
        System.out.println("D - Print List of Students");
        System.out.println("X - Back to main menu");

        char studentOption = scn.next().charAt(0);
        scn.nextLine();

        switch (studentOption) {
            case 'A':
            case 'a': // ADD a student

                // initalize check id variable
                boolean checkId;
                String studentId;
                String studentType;
                String studentName;
                while (true) {
                    try {
                        System.out.println("Enter student's ID:");
                        studentId = scn.nextLine();
                        if (studentId.matches("[a-z]{2}\\d{4}")) {
                            break;
                        } else {
                            throw new IdException("Invalid id format or ID already exists\nTry again later!");
                        }
                    } catch (IdException e) {
                        System.out.println(e.getMessage());
                    }
                }

                /*
                 * for (Student student : valenceCollege.getStudentList()) {
                 * if (student.getId().equals(studentId)) {
                 * System.out.println("Sorry, " + studentId +
                 * " is already assigned to another student.");
                 * checkId = true;
                 * break;
                 * }
                 * }
                 */

                // take in students name
                System.out.println("Enter student's Name:");
                studentName = scn.nextLine();

                System.out.println("Student type (PhD, Ms, or Undergrad): ");
                studentType = scn.nextLine();
                // case if the student entered is an undergraduate

                if (studentType.equals("Undergrad")) {
                    // create a new student to add to the student array;
                    boolean resident;
                    System.out.println("Is student from in state? (true/false):");
                    // state status is either true or false
                    resident = scn.nextBoolean();

                    double gpa;
                    System.out.println("Student gpa:");
                    gpa = scn.nextDouble();

                    int courseNumber;
                    System.out.println("how many courses is this student taking?:");
                    courseNumber = scn.nextInt();

                    ArrayList<Integer> crnArray = new ArrayList<Integer>();
                    for (int i = 0; i < courseNumber; i++) {
                        int courseId;
                        System.out.println("Enter a course Id:");
                        courseId = scn.nextInt();
                        crnArray.add(courseId);

                    }

                    Student newStudent = new UndergraduateStudent(studentName, studentId, crnArray, resident, gpa);
                    valenceCollege.getStudentList().add(newStudent);

                }
                // case if student is phd
                else if (studentType.equals("PhD")) {

                    // add advisor to phd student
                    String advisor;
                    System.out.println("Advisor Name:");
                    advisor = scn.nextLine();

                    // add research subject to phd student
                    String subject;
                    System.out.println("Research Subject:");
                    subject = scn.nextLine();

                    // take in the number of labs this phd student is teaching
                    int labCount;
                    System.out.println("how many labs is this student teaching?:");
                    labCount = scn.nextInt();

                    // loop for each lab teaching and add to lab array for phd student
                    ArrayList<Integer> labArray = new ArrayList<Integer>();
                    for (int i = 0; i < labCount; i++) {
                        int labId;
                        System.out.println("Enter a lab Id:");
                        labId = scn.nextInt();
                        labArray.add(labId);

                    }
                    Student newStudent = new PhdStudent(studentName, studentId, advisor, subject, labArray);
                    // add new student to student array
                    valenceCollege.getStudentList().add(newStudent);

                }
                // final case if student is a graduate student
                else if (studentType.equals("MS")) {
                    int courseNumber;
                    System.out.println("how many courses is this student taking?:");
                    courseNumber = scn.nextInt();

                    ArrayList<Integer> crnArray = new ArrayList<Integer>();
                    for (int i = 0; i < courseNumber; i++) {
                        int crn;
                        System.out.println("Enter a crn number:");
                        crn = scn.nextInt();
                        crnArray.add(crn);
                    }

                    Student newStudent = new MsStudent(studentName, studentId, crnArray);
                    valenceCollege.getStudentList().add(newStudent);

                }

                System.out.println("[ " + studentName + " ] added!");
                System.out.println();

                break;
            case 'B':
            case 'b': // DELETE a student

                String deleteId;
                System.out.println("Enter student id: ");
                deleteId = scn.nextLine();

                for (int i = 0; i < valenceCollege.getStudentList().size(); i++) {
                    if (valenceCollege.getStudentList().get(i).getId().equals(deleteId)) {
                        valenceCollege.getStudentList().remove(i);
                    }
                }

                break;
            case 'C':
            case 'c': // print fee invoice
                String printId;
                System.out.println("Enter student id: ");
                printId = scn.nextLine();

                for (int i = 0; i < valenceCollege.getStudentList().size(); i++) {
                    if (valenceCollege.getStudentList().get(i).getId().equals(printId)) {
                        valenceCollege.getStudentList().get(i).printInvoice();
                    }
                }
                break;
            case 'D':
            case 'd': // Print list of students
                System.out.println();

                // print out all phd students
                System.out.println("PhD Students");
                System.out.println("------------");
                for (Student student : valenceCollege.getStudentList()) {
                    if (student instanceof PhdStudent) {
                        System.out.println("\t- " + student.getName());
                    }
                }
                System.out.println();
                // print out all ms students
                System.out.println("MS Students");
                System.out.println("------------");
                for (Student student : valenceCollege.getStudentList()) {
                    if (student instanceof MsStudent) {
                        System.out.println("\t- " + student.getName());
                    }
                }

                System.out.println();
                // printf out all undergraduate students
                System.out.println("Undergraduate Students");
                System.out.println("------------");
                for (Student student : valenceCollege.getStudentList()) {
                    if (student instanceof UndergraduateStudent) {
                        System.out.println("\t- " + student.getName());
                    }
                }
                System.out.println();

                break;
            case 'X': // Go back to main menu
            case 'x':
                bool = false;
                return bool;
            default:
                System.out.println("Invalid option. Please try again");
                break;
        }

        return bool;

    }

    private boolean CourseManagement(boolean bool) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Course Management Menu: ");
        System.out.println("Choose one of: ");
        System.out.println("A - Search for a class or lab using the class/lab number");
        System.out.println("B - delete a class");
        System.out.println("C - Add a lab to a class");
        System.out.println("X - Back to main menu");
        System.out.print("Enter your selection: ");

        char courseOption = scn.next().charAt(0);
        scn.nextLine();

        switch (courseOption) {
            case 'A':
            case 'a': // SEARCH LAB OR CLASS

                String crn;
                System.out.print("Enter the Class/Lab Number: ");
                crn = scn.nextLine();

                for (Lecture l : valenceCollege.getLectureList()) {
                    if (l.getCrn().equals(crn)) {
                        System.out.println("[ " + l + " ]\n");

                    } else if (l.labs != null) {
                        for (Lab a : l.labs) {
                            if (a.getCrn().equals(crn)) {
                                System.out.println("Lab for[ " + l.toString() + " ]");
                                System.out.println("Lab Rooom " + a.getClassroom() + "\n");
                            }
                        }
                    } else {
                        System.out.println("[ ]\n");
                        break;
                    }
                }
                break;
            case 'B':
            case 'b': // DELETE CLASS
                // define string for crn
                String classNum;
                System.out.print("Enter the Class/Lab Number: ");
                classNum = scn.nextLine();

                for (int i = 0; i < valenceCollege.getLectureList().size(); i++) {
                    if (valenceCollege.getLectureList().get(i).getCrn().equals(classNum)
                            && valenceCollege.getLectureList().get(i).isHasLabs() == false) {
                        System.out.println(valenceCollege.getLectureList().get(i) + " deleted!");
                        valenceCollege.getLectureList().remove(i).getCrn();
                        Projectdriver.createFile(College.getLectureList(), "lec.txt");
                    } else if (valenceCollege.getLectureList().get(i).labs != null) {
                        for (int j = 0; j < valenceCollege.getLectureList().get(i).labs.size(); j++) {
                            if (valenceCollege.getLectureList().get(i).labs.get(j).getCrn().equals(classNum)) {
                                System.out.println(valenceCollege.getLectureList().get(i).labs.get(j) + " deleted!");
                                valenceCollege.getLectureList().get(i).labs.remove(j);
                                Projectdriver.createFile(College.getLectureList(), "lec.txt");
                            }
                        }
                    }

                }
                // DON'T FORGET TO UPDATE lec.txt
                break;
            case 'C':
            case 'c': // ADD LAB TO LECTURE

                // type is either lab or lecture
                String type;
                System.out.println("Enter Lab or Lecture: ");
                type = scn.nextLine();

                if (type.equals("Lab")) {
                    String lecCrn;
                    System.out.println("Enter the Lecture Number to a Lab to: ");
                    lecCrn = scn.nextLine();

                    for (int i = 0; i < valenceCollege.getLectureList().size(); i++) {
                        if (valenceCollege.getLectureList().get(i).getCrn().equals(lecCrn)) {

                            String newCrn;
                            System.out.println("Enter crn: ");
                            newCrn = scn.nextLine();

                            String classroom;
                            System.out.println("Enter classroom: ");
                            classroom = scn.nextLine();

                            valenceCollege.getLectureList().get(i).labs.add(new Lab(newCrn, classroom));

                        }
                    }
                } else if (type.equals("Lecture")) {
                    String labCrn;
                    System.out.println("Enter the Lab Number to a Lecture to: ");
                    labCrn = scn.nextLine();

                    for (int i = 0; i < valenceCollege.getLectureList().size(); i++) {
                        for (int j = 0; j < valenceCollege.getLectureList().get(i).labs.size(); j++) {
                            if (valenceCollege.getLectureList().get(i).labs.get(j).getCrn().equals(labCrn)) {
                                String lectureCrn;
                                String prefix;
                                String lectureName;
                                LectureType Type = null; // may crash code
                                LectureMode lectureMode = null; // may crash code
                                boolean hasLabs;
                                String Classroom;
                                int creditHours;

                                System.out.println("Enter crn: ");
                                lectureCrn = scn.nextLine();

                                System.out.println("Enter prefix: ");
                                prefix = scn.nextLine();

                                System.out.println("Enter name: ");
                                lectureName = scn.nextLine();

                                System.out.println("Enter type: ");
                                Type = Type.valueOf(scn.nextLine());

                                System.out.println("Enter mode: ");
                                lectureMode = lectureMode.valueOf(scn.nextLine());

                                System.out.println("Enter classroom: ");
                                Classroom = scn.nextLine();

                                System.out.println("Does it have labs (true/false): ");
                                hasLabs = scn.nextBoolean();

                                System.out.println("Enter credit hours ");
                                creditHours = scn.nextInt();

                                // contructor for labs
                                if (lectureMode.equals("Online")) {
                                    valenceCollege.getLectureList().add(new Lecture(lectureCrn, prefix, lectureName,
                                            Type, lectureMode, creditHours));
                                }

                            }
                        }
                    }
                }

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
                bool = false;
                return bool;
            default:
                System.out.println("Invalid option. Please try again");
                break;
        }

        return bool;
    }

}

class IdException extends Exception {
    public IdException(String message) {
        super(message);
    }

}

abstract class Student {
    private String name;
    private String id;

    private static ArrayList<String> usedIds = new ArrayList<>();

    public Student(String name, String id) {
        /*
         * if(!isValidId(id)){
         * throw new
         * IdException(("Invalid id format or ID already exists\nTry again later!"));
         * }
         */
        this.name = name;
        this.id = id;
        // usedIds.add(id);
    }

    private boolean isValidId(String id) {
        // checks format
        if (!id.matches("[A-Z]{2}\\d{4}")) { // need to verify this works
            return false;
        }
        // checks for duplicate
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

abstract class GraduateStudent extends Student {

    public GraduateStudent(String name, String id) {

        super(name, id);

    }
}

class UndergraduateStudent extends Student {
    private ArrayList<Integer> crnArray;
    private double gpa;
    private boolean resident;

    public double creditHourPrice;
    private String crn2;

    public double calculateCreditCost(double creditHourPrice, boolean resident) {

        if (resident) {
            creditHourPrice = 120.25;
        } else {
            creditHourPrice = 120.25 * 2;
        }
        return creditHourPrice;
    }

    public UndergraduateStudent(String name, String id, ArrayList<Integer> crnArray, boolean resident, double gpa) {
        super(name, id);
        this.crnArray = crnArray;
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
        double totalHrs = 0;
        for (Integer c : crnArray)
            crn2 = Integer.toString(c);
        for (Lecture l : College.getLectureList()) {
            if (crn2.equals(l.getCrn())) {
                totalHrs += (l.getCreditHours() * creditCost);
                System.out.printf(l.getCrn() + "\t" + l.getPrefix() + "\t\t" + l.getCreditHours() + "\t" + "%.2f\n",
                        (double) (l.getCreditHours() * creditCost));
            }
        }
        System.out.println("\t\tHealth & id fees $35.00\n");
        System.out.println("--------------------------------------");
        double preTotal = totalHrs + 35.00;
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
    // crn array is an array of the courses that the ms student is taking
    private ArrayList<Integer> crnArray;
    private String crn2;

    public MsStudent(String name, String id, ArrayList<Integer> crnArray) {

        super(name, id);
        this.crnArray = crnArray;

    }

    @Override
    public void printInvoice() {
        System.out.print("VALENCE COLLEGE\n");
        System.out.print("ORLANDO FL 10101\n");
        System.out.print("---------------------\n\n");
        System.out.print("Fee Invoice Prepared for Student: \n");
        System.out.print(getId() + "-" + getName() + "\n\n");
        System.out.println("1 Credit Hour = $300.00\n");
        System.out.println("CRN\tCR_PREFIX\tCR_HOURS");
        double totalHrs = 0;
        for (Integer c : crnArray)
            crn2 = Integer.toString(c);
        for (Lecture l : College.getLectureList()) {
            if (crn2.equals(l.getCrn())) {
                totalHrs += l.getCreditHours();
                System.out.printf(l.getCrn() + "\t" + l.getPrefix() + "\t\t" + l.getCreditHours() + "\t" + "%.2f\n\n",
                        (double) (l.getCreditHours() * 300));
            }
        }
        System.out.println("\t\tHealth & id fees $35.00\n\n");
        System.out.println("--------------------------------------");
        double total = (totalHrs * 300) + 35.00;
        System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", total);
    }

}

class PhdStudent extends GraduateStudent {

    private String advisor;
    private String researchSub;
    private ArrayList<Integer> supervisedLabs;

    public PhdStudent(String name, String id, String advisor, String researchSubject,
            ArrayList<Integer> supervisedLabs) {
        super(name, id);
        this.advisor = advisor;
        this.researchSub = researchSubject;
        this.supervisedLabs = supervisedLabs;
    }

    @Override
    public void printInvoice() {
        System.out.print("VALENCE COLLEGE\n");
        System.out.print("ORLANDO FL 10101\n");
        System.out.print("---------------------\n\n");
        System.out.print("Fee Invoice Prepared for Student: \n");
        System.out.print(getId() + "-" + getName() + "\n\n");
        System.out.println("RESEARCH");
        int labs = 0;
        for (Integer i : supervisedLabs) {
            labs++;
        }
        double researchFee = 700.00;
        if (labs >= 3) {
            System.out.printf(researchSub + "\t\t\t$%.2f\n\n", -researchFee);
            System.out.println("\t\tHealth & id fees $35.00\n\n");
            System.out.println("--------------------------------------");
            double totalPayment = 35.00;
            System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", totalPayment);
        } else if (labs == 2) {
            System.out.printf(researchSub + "\t\t\t$%.2f\n\n", (researchFee * 0.5));
            System.out.println("\t\tHealth & id fees $35.00\n\n");
            System.out.println("--------------------------------------");
            double totalPayment = (researchFee * 0.5) + 35.00;
            System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", totalPayment);
        } else {
            System.out.printf(researchSub + "\t\t\t$%.2f\n\n", researchFee);
            System.out.println("\t\tHealth & id fees $35.00\n\n");
            System.out.println("--------------------------------------");
            double totalPayment = researchFee + 35.00;
            System.out.printf("\t\tTotal Payments\t$%.2f\n\n\n", totalPayment);
        }

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
    public Lecture(String crn,
            String prefix,
            String lectureName,
            LectureType lectureType,
            LectureMode lectureMode,
            String classroom,
            boolean hasLabs,
            int creditHours,
            ArrayList<Lab> labs) {

        LectureCommonInfoSetUp(crn, prefix, lectureName, lectureType, lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
        this.labs = labs;
    }

    // Constructor for Non-online without Labs
    public Lecture(String crn, String prefix, String lectureName,
            LectureType lectureType, LectureMode lectureMode, String classroom, boolean hasLabs, int creditHours) {
        LectureCommonInfoSetUp(crn, prefix, lectureName, lectureType, lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

    // Constructor for Online Lectures
    public Lecture(String crn, String prefix, String lectureName, LectureType lectureType, LectureMode lectureMode,
            int creditHours) {
        LectureCommonInfoSetUp(crn, prefix, lectureName, lectureType, lectureMode);
        this.classroom = classroom;
        this.hasLabs = hasLabs;
        this.creditHours = creditHours;
    }

    // ________

    @Override
    public String toString() {
        String lectureAndLabs = crn + "," + prefix + "," + lectureName;
        return lectureAndLabs;
    }

    /*
     * public String toString1() {
     * String printString1 = crn + "," + prefix + "," + lectureName + "," +
     * lectureType + "," + lectureMode + ","
     * + classroom + "," + hasLabs + "," + creditHours;
     * return printString1;
     * }
     */

    public String printToText() {
        String haslabString;
        if (hasLabs == true) {
            haslabString = "YES";
        } else {
            haslabString = "NO";
        }
        String name = (crn + "," + prefix + "," +
                lectureName + "," + lectureType + ","
                + lectureMode + "," + haslabString + "," +
                creditHours);

        /*
         * if (labs != null) {// printing corresponding labs
         * // lectureAndLabs+="\n";
         * for (Lab lab : labs)
         * System.out.println(lab.toString());
         * }
         */
        return name;
    }

    public String getCrn() {
        return this.crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public LectureMode getLectureMode() {
        return lectureMode;
    }

    public void setLectureMode(LectureMode lectureMode) {
        this.lectureMode = lectureMode;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public boolean isHasLabs() {
        return hasLabs;
    }

    public void setHasLabs(boolean hasLabs) {
        this.hasLabs = hasLabs;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public ArrayList<Lab> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Lab> labs) {
        this.labs = labs;
    }

}

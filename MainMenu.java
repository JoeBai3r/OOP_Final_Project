//Group Members: Joseph Baier, Yoan Molina, Trevor Chandrapaul, David Hablich

import java.util.*;

class IdException extends Exception {
    
}

public static class DriverClass {
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
        System.out.println("E - Print list of students");
        System.out.println("X - Back to main menu");
    
        char studentOption = scn.next().charAt(0);
    
        switch (studentOption) {
            case 'A':
            case 'a': //ADD a student
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
                    //add constructor for grad student here?
                    break;
            case 'B':
            case 'b': //SEARCH a student
                System.out.println("Search a student by id");
                break;
            case 'C':
            case 'c': //DELETE student
                System.out.print("Delete a student ");
                System.out.println("          deleted!");
                //DON'T FORGET TO UPDATE lec.txt
                break;
            case 'D':
            case 'd': //Print fee  
                System.out.println("Print fee invoice of student by id");
                break;
            case 'E':
            case 'e': //Print list from txt file
                System.out.println("Print list of students");
                break;
            case 'X': //Go back to main menu
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
            case 'a': //SEARCH LAB OR CLASS
                System.out.print("Enter the Class/Lab Number: ");
                break;
            case 'B':
            case 'b': //DELETE CLASS
                System.out.print("Enter the Class/Lab Number: ");
                System.out.println("          deleted!");
                //DON'T FORGET TO UPDATE lec.txt
                break;
            case 'C':
            case 'c': //ADD LAB TO LECTURE 
                System.out.println("Enter Lab or Lecture: ");
                System.out.println("Enter the Lecture Number to a Lab to: ");
                int cond = 1;
                if(cond == 1){
                    System.out.println("   is valid. Enter the rest of the information: ");
                    System.out.println("Lecture added successfully ");
                    //DON'T FORGET TO UPDATE lec.txt
                }
                else{
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
    //private ArrayList <String> studentData;

    public Student ( String name , String id) {
        this.name = name;
        this.id = id;
        //this.studentData = studentData;
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

    /*public ArrayList<String> getStudentData() {
        return studentData;
    }
    */
     /*
    public void setStudentData(ArrayList<String> studentData) {
        this.studentData = studentData;
    }
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
        //need to fix with loop
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


class College {
    private ArrayList<Student> list;

    public College() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void addStudent(Student student) {
        list.add(student);
        System.out.println("Student added successfully!");
    }

    public boolean searchById(int studentId) {
        for (Student student : list) {
            if (student.getId().equals(studentId))
                return true;
        }
        return false;
    }


    public boolean addCourse(int studentId, int crn) {
        for (Student student : list) {
            if (student.getId().equals(studentId)) {
                student.getStudentData().add(crn);
                System.out.println("Course added successfully!");
                return true;
            }
        }
        return false;
    }

    public boolean addCourse(int studentId, int crn, int placeHolder) {
        for (Student student : list) {
            if (student.getName().equals(studentId)) {
                student.getlistOfCrns().add(crn);
                // System.out.println("Course added successfully!");
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(int studentId, int crn) {
        for (Student student : list) {
            if (student.getId().equals(studentId)) {
                boolean removed = student.getlistOfCrns().remove(Integer.valueOf(crn));
                if (removed) {
                    System.out.println("Course deleted successfully!");
                    return true;
                } else {
                    System.out.println("CRN not found in student's course list!");
                    return false;
                }
            }
        }
        System.out.println("Student not found!");
        return false;
    }

    public void printSortedInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                ArrayList<Integer> courses = student.getlistOfCrns();
                Collections.sort(courses);
                student.printFeeInvoice();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void printInvoice(int studentId) {
        for (Student student : list) {
            if (student.getStudentId() == studentId) {
                student.printFeeInvoice();
                return;
            }
        }
        System.out.println("Student not found!");
    }
}

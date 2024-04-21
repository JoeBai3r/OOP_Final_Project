//Group Members: Joseph Baier, Yoan Molina, Trevor Chandrapaul, David Hablich

import java.util.*;

class IdException extends Exception {
    
}

public class ProjectDriver {
    public static void main(String[] args) {

    }
    
}

public class MainMenu {

    public static void print() {

    }
}


private static void StudentManagement() {
    
}

private static void CourseManagement() {
    Scanner scn = new Scanner(System.in);
    System.out.println("Course Management Menu: ");
    System.out.println("Choose one of: ");
    System.out.println("A - Search for a class or lab using the class/lab number");
    System.out.println("B - delete a class");
    System.out.println("C - Add a lab to a class");
    System.out.println("C - Add a lab to a class");
    System.out.println("X - Back to main menu");
    System.out.print("Enter your selection: ");

    switch (option) {
        case 1:
            System.out.println("Add a new course:");
        case 2:
            System.out.println("delete a course:");
            break;
        case 0:
            break;
        }
}

public abstract class Student {
    private String name;
    private String id;

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

    public Student ( String name , String id) {
        this.name = name;
        this.id = id;
    }
    abstract public void printInvoice();

}

public abstract class GraduateStudent extends Student {
        
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
        // . . .
    }

    @Override
    public void printInvoice() {
        // . . .
        System.out.print("VALENCE COLLEGE\n");
        System.out.print("ORLANDO FL 10101\n");
        System.out.print("---------------------\n\n");
        System.out.print("Fee Invoice Prepared for Student: \n");
        System.out.print(getId() + "-" + getName() + "\n\n");
        double creditCost = calculateCreditCost(creditHourPrice, resident);
        System.out.printf("1 Credit Hour = $%.2f\n\n", creditCost);
        System.out.print("CRN\tCR_PREFIX\tCR_HOURS\n");
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

public class MsStudent extends GraduateStudent {
    public void printInvoice() {

    }
}

public class PhdStudent extends GraduateStudent {
    public void printInvoice() {

    }
}





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

    public Student ( String name , String id) {
        this.name = name;
        this.id = id;
    }
    abstract public void printInvoice();

}

public abstract class GraduateStudent extends Student {
        
}

public class UndergraduateStudent extends Student {
    public void printInvoice(){
        
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





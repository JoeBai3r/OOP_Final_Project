//Group Members: Joseph Baier, Yoan Molina, Trevor Chandrapaul, David Hablich

import java.util.Scanner;

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


public class SubMenu() {
    

}


private static void StudentManagement(){
    
}

private static void CourseManagement(){
    
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





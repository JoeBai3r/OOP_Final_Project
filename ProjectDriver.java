//Group Members: Joseph Baier, Yoan Molina1, Trevor Chandrapaul, David Hablich. bob

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
}

    public class MsStudent extends GraduateStudent {
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
            //System.out.print(getId() + "-" + getName() + "\n\n");
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


    public class PhdStudent extends GraduateStudent {
        private String advisor;
        private String researchSubject;

        public PhdStudent(String name, String id, String advisor, String researchSubject, int crn) {
            // crn is the course number that the Phd student is a teaching assistant for
            super(name, id, crn);
            this.advisor = advisor;
            this.researchSubject = researchSubject;
            // . . .
        }

        public String getAdvisor() {
            return advisor;
        }

        public void setAdvisor(String advisor) {
            this.advisor = advisor;
        }

        public String getResearchSubject() {
            return researchSubject;
        }

        public void setResearchSubject(String researchSubject) {
            this.researchSubject = researchSubject;
        }


        public void printInvoice() {

        }
    }





/*
 * Name: Symbolica.java
 */

// Import libraries
import calculus.differential.Derivative;
import java.util.Scanner;
//import Calculus.Integral.*;

/**
 *
 * @author Alec
 */
public class SymbolMash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner in_scan = new Scanner(System.in);
        int choice;
        
        do{
            System.out.println("------------------Symbolica------------------");
            System.out.println("1. Derivative");
            System.out.println("2. Integral");
            System.out.println("3. Matrix");
            System.out.println("0. exit\n");
            System.out.print("ENTER:");
            choice = in_scan.nextInt();
            
            switch(choice){
                
                case 1:
                    derivative();
                    break;
                    
//                case 2:
//                    integral();
//                    break;
                    
                case 3:
                    System.out.println("coming soon...");
                    break;
                    
            }
            
        }while(choice >= 1 && choice <= 3);
        
    }
    
    public static void derivative(){
        
        Scanner in_scan = new Scanner(System.in);
        String choice;
        do{
            System.out.println("-------------Derivative Calculator-------------");
        
            String f;
            System.out.println("Define a function in a single variable x.");
            System.out.print("f(x) = ");
            f = in_scan.nextLine();        

//            Parse_Tree test_tree = new Parse_Tree(f);
            Derivative b = new Derivative(f);
            System.out.println(b.fPrime);
            
//            System.out.println("f'(x) = " + test_tree.f_prime.f_prime);
            System.out.println("Enter y to differentiate another function or"
                               + " anything else to exit");
            choice = in_scan.nextLine();
        }while(choice.equals("y"));
        
    }
    
//    public static void integral(){
//        Scanner in_scan = new Scanner(System.in);
//        String choice;
//        Integral test = new Integral();
//        do{
//            System.out.println("-------------Integral Calculator-------------");
//        
//            String f;
//            System.out.println("Define a function in a single variable x.");
//            System.out.print("f(x) = ");
//            f = in_scan.nextLine();        
//            test.eval(f);
//            
//            System.out.println("Enter y to integrate another function or"
//                               + " anything else to exit");
//            choice = in_scan.nextLine();
//        }while(choice.equals("y"));
//        
//    }
}

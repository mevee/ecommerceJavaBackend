package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication {
    //	private static final Logger LOG = Logger.getLogger(TodoappApplication.class);
    static class A {
        void printX() {
            System.out.println("printX(A)");
        }
    }

    static class B extends A {
        @Override
        void printX() {
            super.printX();
            System.out.println("printX(B)");

        }

        void printY() {
            super.printX();
            System.out.println("printY(B)");

        }
    }

    public static void main(String[] args) {
        System.out.println("------------------------App Started --------------------");
        SpringApplication.run(TodoappApplication.class, args);
        System.out.println("------------------------ Reading log4j properties file --------------------");
        A refA = new A();
        A refAChild = new B();
        B refb = new B();
        refAChild.printX();
        refb.printY();
        Double d = 10.0 / 0.0;
        System.out.println("" + d);

    }
 void reverceString(String input){
     System.out.println("reverceString()"+input);
     char[] lettersInArray = input.toCharArray();
     int  length = lettersInArray.length;
     boolean  lengthIsEven = length%2==0;
     if (lettersInArray.length==0){
         System.out.println("NO String");
     }else {
         if (lengthIsEven){

             for (int i=0 ;i<length/2;i++){
                var temp = lettersInArray[i];
                 lettersInArray[i] =lettersInArray[length-i];
                 lettersInArray[length-i]=temp;
             }
             System.out.println("Palti hui STRING"+lettersInArray.toString());

         }else{
             for (int i=0 ;i<length/2;i++){
                 var temp = lettersInArray[i];
                 lettersInArray[i] =lettersInArray[length-i];
                 lettersInArray[length-i]=temp;
             }
             System.out.println("Palti hui STRING"+lettersInArray.toString());

         }
     }
 }
}

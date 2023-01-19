package com.bhaveshdhanjani;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTH_IN_YEAR = 12;
    final static byte PERCENT = 100;
    public static void main(String[] args) {
        int principal = (int) readValue("Principal ($1K - $1M): " , 1_000 , 1_000_000);
        float annualInterest = (float) readValue("Annual Interest Rate: ", 0 , 30);
        byte year = (byte) readValue("Periods (Years): ", 1 , 30);
        printMortgage(principal, annualInterest, year);
        printPaymentSchedule(principal, annualInterest, year);
    }

    private static void printMortgage(int principal, float annualInterest, byte year) {
        double mortgage = mortgageCalculator(principal, annualInterest, year);
        System.out.println("Mortgage");
        System.out.println("________");
        System.out.println("Monthly Payment: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    private static void printPaymentSchedule(int principal, float annualInterest, byte year) {
        System.out.println("Payment Schedule");
        System.out.println("________________");
        double remainingBalance;
        for (int noOfPaymentMade = 1; noOfPaymentMade<= year *MONTH_IN_YEAR; noOfPaymentMade++) {
            remainingBalance = calulateBalance(principal, annualInterest, year,noOfPaymentMade);
            System.out.println(NumberFormat.getCurrencyInstance().format(remainingBalance));

        }
    }

    public static double readValue(String prompt,double min,double max){
        Scanner scanner = new Scanner(System.in);
        double value ;
        while (true){
            System.out.print(prompt);
            value = scanner.nextDouble();
            if((value>=min && value<=max))
                break;
            System.out.println("Enter a number between "+min+" and " + max);
        }
        return value;
    }
    public static double mortgageCalculator(int principal , float annualInterest , byte year){
        float monthlyInterest = (annualInterest/MONTH_IN_YEAR)/PERCENT;
        int noOfPayment = year*MONTH_IN_YEAR;
        return principal
                *((monthlyInterest*Math.pow(1+monthlyInterest,noOfPayment))
                /(Math.pow(1+monthlyInterest,noOfPayment)-1));

    }
    public static double calulateBalance(int principal , float annualInterest , byte year , int noOfPaymentMade){
        float monthlyInterest = (annualInterest/MONTH_IN_YEAR)/PERCENT;
        int noOfPayment = year*MONTH_IN_YEAR;
        return   (principal *
                (Math.pow(1+monthlyInterest,noOfPayment)-Math.pow(1+monthlyInterest,noOfPaymentMade)))/
                (Math.pow(1+monthlyInterest,noOfPayment)-1);


    }
}

package com.nexttech.internship;


import com.nexttech.internship.JDBC.DBConnection;

public class App {

    public static void main( String[] args ) {
        //LoopsAndConditions.startGuessTheNumberImproved();
        //LoopsAndConditions.startMentalistGame();
        // System.out.println("RESULT: " +LoopsAndConditions.getResultFromPolishCalculator("3 4 + 5 - 6 *"));
        //LoopsAndConditions.startGuessTheNumberImproved();
         DBConnection connection = new DBConnection();
        //connection.printAllUserNames();
        //connection.printAllInvoicesPayedOnlineForFirmWithId(1);
        //connection.printAllScadentInvoicesOrderedByPastDueTimeForFirmWithId(2);
        //connection.printAllInvoicesFromSupplierForFirmWithId("Electrica", 2);
        //connection.printAllInvoicesFromDateForSupplierForFirmWithId("2019-11-17", "Orange", 1);
        connection.printAllUsersByRolesForFirmWithId(3);
        //connection.printAllSupplierNamesByPaymentFromDateForFirmWithId("cash", "2020-09-17", 1);
        //connection.printTotalPaymentsToSupplierDescForFirmWithId(1);
    }
}

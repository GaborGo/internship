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
        //connection.printAllInvoicesByPaymentTypeForFirmWithId("cash",3);
        //connection.printAllScadentInvoicesOrderedByDueTimeForFirmWithId(3);
        //connection.printAllInvoicesFromSupplierForFirmWithId("Orange", 2);
        //connection.printAllInvoicesFromDateForSupplierForFirmWithId("2020-10-10", "Orange", 1);
        //connection.printAllUsersByRolesForFirmWithId(3);
        //connection.printAllSupplierNamesByPaymentFromDateForFirmWithId("cash", "2019-09-17", 2);
        //connection.printTotalPaymentsToSupplierDescFromDateForFirmWithId("2020-06-10",1);
        connection.closeConnection();
    }
}

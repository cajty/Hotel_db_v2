package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public   class  Helper {


    private static final Scanner input = ScannerSingleton.getScanner();





    public static LocalDate convertToDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        try {
            if (LocalDate.parse(dateStr, formatter).isAfter(today)) {
                return LocalDate.parse(dateStr, formatter);
            }
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }


    public static LocalDate[] inputAndValidateDates() {


        String dateStart;
        String dateEnd;
        LocalDate startDate = null;
        LocalDate endDate = null;


        while (startDate == null || endDate == null) {
            System.out.println("Enter date start (format: yyyy-MM-dd):");
            input.nextLine();
            dateStart = input.nextLine();
            startDate = convertToDate(dateStart);

            if ( startDate == null) {
                System.out.println("Invalid date format for start date. Please try again.");
                continue;
            }

            System.out.println("Enter date end (format: yyyy-MM-dd):");
            dateEnd = input.nextLine();
            endDate = convertToDate(dateEnd);
            if (endDate == null) {
                System.out.println("Invalid date format for end date. Please try again.");
                continue;
            }

            if (startDate.isAfter(endDate)) {
                System.out.println("Start date must be before end date.");
                startDate = null;
                endDate = null;
            }
        }

        return new LocalDate[]{startDate, endDate};
    }


    public static int getIdReservationFromUser() {

        int id = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter id of reservation:");
                id = input.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next();
            }
        }

        return id;
    }
}

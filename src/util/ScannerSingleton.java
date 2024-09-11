package util;

import java.util.Scanner;

public class ScannerSingleton {

    private static Scanner scannerInstance;


    private ScannerSingleton() {

    }

    public static Scanner getScanner() {
        if (scannerInstance == null) {
            scannerInstance = new Scanner(System.in);
        }
        return scannerInstance;
    }

}

package org.example;

import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                5. Visualisering
                e. Avsluta
                """);
            String action = scanner.nextLine();

            switch (action) {
                case "e", "E" -> exit = true;
                case "1" -> addData(scanner);
                case "2" -> fancyPrint();
                case "3" -> sortData();
                case "4" -> bestCharge();
                case "5" -> visualizeData();
            }
        }
    }

    public static void addData() {
        System.out.println("Add data");
    }
    public static void fancyPrint() {
        System.out.println("fancy print");
    }
    public static void sortData() {
        System.out.println("sort data");
    }
    public static void bestCharge() {
        System.out.println("best charge");
    }
    public static void visualizeData() {
        System.out.println("visualize data");
    }
}

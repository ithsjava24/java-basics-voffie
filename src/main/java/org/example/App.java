package org.example;

import java.util.*;

record PriceData(String date, int price) {}

public class App {
    static List<PriceData> data = new ArrayList<>() {{
        add(new PriceData("00-01", 0));
        add(new PriceData("01-02", 0));
        add(new PriceData("02-03", 0));
        add(new PriceData("03-04",0));
        add(new PriceData("04-05",0));
        add(new PriceData("05-06",0));
        add(new PriceData("06-07",0));
        add(new PriceData("07-08",0));
        add(new PriceData("08-09",0));
        add(new PriceData("09-10",0));
        add(new PriceData("10-11",0));
        add(new PriceData("11-12",0));
        add(new PriceData("12-13",0));
        add(new PriceData("13-14",0));
        add(new PriceData("14-15",0));
        add(new PriceData("15-16",0));
        add(new PriceData("16-17",0));
        add(new PriceData("17-18",0));
        add(new PriceData("18-19",0));
        add(new PriceData("19-20",0));
        add(new PriceData("20-21",0));
        add(new PriceData("21-22",0));
        add(new PriceData("22-23",0));
        add(new PriceData("23-24",0));
    }};

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
                case "2" -> minMaxAverage();
                case "3" -> sortData();
                case "4" -> bestCharge();
                case "5" -> visualizeData();
            }
        }
    }

    public static void addData(Scanner scanner) {
        data.replaceAll(priceData -> new PriceData(priceData.date(), scanner.nextInt()));
        scanner.nextLine();
    }
    public static void minMaxAverage() {
        PriceData highest = Collections.max(data, Comparator.comparingInt(PriceData::price));
        PriceData lowest = Collections.min(data, Comparator.comparingInt(PriceData::price));
        float sum = data.stream().mapToInt(PriceData::price).sum();
        float average = sum / (data.size());
        System.out.printf("""
                Lägsta pris: %s, %s öre/kWh
                Högsta pris: %s, %s öre/kWh
                Medelpris: %.2f öre/kWh
                """, lowest.date(), lowest.price(), highest.date(), highest.price(), average);
    }
    public static void sortData() {
        List<PriceData> sorted = new ArrayList<>(data);
        sorted.sort(Comparator.comparingInt(PriceData::price).reversed());
        for (PriceData data : sorted) {
            System.out.println(data.date() + " " + data.price() + " öre");
        }
    }
    public static void bestCharge() {
        System.out.println("best charge");
    }
    public static void visualizeData() {
        System.out.println("visualize data");
    }
}

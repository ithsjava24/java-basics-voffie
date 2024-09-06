package org.example;

import java.util.*;

record PriceData(String hour, int price) {
}

public class App {
    static List<PriceData> data = new ArrayList<>() {{
        add(new PriceData("00-01", 0));
        add(new PriceData("01-02", 0));
        add(new PriceData("02-03", 0));
        add(new PriceData("03-04", 0));
        add(new PriceData("04-05", 0));
        add(new PriceData("05-06", 0));
        add(new PriceData("06-07", 0));
        add(new PriceData("07-08", 0));
        add(new PriceData("08-09", 0));
        add(new PriceData("09-10", 0));
        add(new PriceData("10-11", 0));
        add(new PriceData("11-12", 0));
        add(new PriceData("12-13", 0));
        add(new PriceData("13-14", 0));
        add(new PriceData("14-15", 0));
        add(new PriceData("15-16", 0));
        add(new PriceData("16-17", 0));
        add(new PriceData("17-18", 0));
        add(new PriceData("18-19", 0));
        add(new PriceData("19-20", 0));
        add(new PriceData("20-21", 0));
        add(new PriceData("21-22", 0));
        add(new PriceData("22-23", 0));
        add(new PriceData("23-24", 0));
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
        data.replaceAll(priceData -> new PriceData(priceData.hour(), scanner.nextInt()));
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
                """, lowest.hour(), lowest.price(), highest.hour(), highest.price(), average);
    }

    public static void sortData() {
        List<PriceData> sorted = new ArrayList<>(data);
        sorted.sort(Comparator.comparingInt(PriceData::price).reversed());
        for (PriceData data : sorted) {
            System.out.print(data.hour() + " " + data.price() + " öre\n");
        }
    }

    public static void bestCharge() {
        List<PriceData> sorted = new ArrayList<>(data);
        sorted.sort(Comparator.comparingInt(PriceData::price));
        float sum = sorted.stream().limit(4).mapToInt(PriceData::price).sum();
        float average = sum / 4f;
        System.out.printf("""
                Påbörja laddning klockan %s
                Medelpris 4h: %.1f öre/kWh
                """, sorted.getFirst().hour().substring(0, 2), average);
    }

    public static void visualizeData() {
        final int MAX = Collections.max(data, Comparator.comparingInt(PriceData::price)).price();
        final int MIN = Collections.min(data, Comparator.comparingInt(PriceData::price)).price();
        final int ROW_COUNT = 6;
        final int COLUMN_COUNT = data.size();
        final float DIFFERENCE = (MAX - MIN) / (ROW_COUNT - 1f);

        for (int i = ROW_COUNT; i > 0; i--) {
            StringBuilder output = new StringBuilder();
            int lowerBound = (i == 1) ? MIN : MAX - (ROW_COUNT - i) * DIFFERENCE;

            if (i == ROW_COUNT) {
                output.append("   ").append(MAX).append("|  ");
            } else if (i == 1) {
                output.append(" ").append(MIN).append("|  ");
            } else {
                output.append("   |  ");
            }

            for (int j = 0; j < COLUMN_COUNT; j++) {
                int currentPrice = data.get(j).price();
                if (currentPrice >= lowerBound) {
                    output.append("x  ");
                } else {
                    output.append("   ");
                }
            }
            System.out.print(output + "\n");
        }

        System.out.print("   |------------------------------------------------------------------------\n");
        System.out.print("   | 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23\n");
    }
}

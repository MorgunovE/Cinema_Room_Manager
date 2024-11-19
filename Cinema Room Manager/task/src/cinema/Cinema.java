package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        // Initialize the seating arrangement
        char[][] seats = new char[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seats[i][j] = 'S';
            }
        }

        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(rows, seatsPerRow);

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                printSeatingArrangement(seats);
            } else if (choice == 2) {
                while (true) {
                    System.out.println("Enter a row number:");
                    int chosenRow = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int chosenSeat = scanner.nextInt();

                    if (chosenRow < 1 || chosenRow > rows || chosenSeat < 1 || chosenSeat > seatsPerRow) {
                        System.out.println("Wrong input!");
                    } else if (seats[chosenRow - 1][chosenSeat - 1] == 'B') {
                        System.out.println("That ticket has already been purchased!");
                    } else {
                        int ticketPrice = calculateTicketPrice(rows, seatsPerRow, chosenRow);
                        currentIncome += ticketPrice;
                        purchasedTickets++;
                        seats[chosenRow - 1][chosenSeat - 1] = 'B';
                        System.out.println("Ticket price: $" + ticketPrice);
                        break;
                    }
                }
            } else if (choice == 3) {
                printStatistics(purchasedTickets, rows, seatsPerRow, currentIncome, totalIncome);
            } else if (choice == 0) {
                break;
            }
        }
    }

    private static void printSeatingArrangement(char[][] seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int calculateTicketPrice(int rows, int seatsPerRow, int chosenRow) {
        int totalSeats = rows * seatsPerRow;
        if (totalSeats <= 60) {
            return 10;
        } else {
            int frontHalfRows = rows / 2;
            if (chosenRow <= frontHalfRows) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    private static int calculateTotalIncome(int rows, int seatsPerRow) {
        int totalSeats = rows * seatsPerRow;
        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            int frontHalfRows = rows / 2;
            int backHalfRows = rows - frontHalfRows;
            return (frontHalfRows * seatsPerRow * 10) + (backHalfRows * seatsPerRow * 8);
        }
    }

    private static void printStatistics(int purchasedTickets, int rows, int seatsPerRow, int currentIncome, int totalIncome) {
        double percentage = (double) purchasedTickets / (rows * seatsPerRow) * 100;
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
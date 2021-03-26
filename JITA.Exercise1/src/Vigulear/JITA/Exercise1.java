/*
Task:
You are in charge of the cake for a child's birthday.
You have decided the cake will have one candle for each year of their total age.
They will only be able to blow out the tallest of the candles.
Count how many candles are tallest.
 */

package Vigulear.JITA;

import java.util.Scanner;

public class Exercise1 {

    // Function to find the tallest candle
    public static int tallestCandle(int[] candles) {
        int tallestCandle = candles[0];

        for (int candle: candles) {
            if (candle > tallestCandle)
                tallestCandle = candle;
        }
        return tallestCandle;
    }

    public static int birthdayCakeCandles(int[] candles) {
        boolean flag = false;

        // A loop with array initialization and control for correct length of candles provided
        while (!flag) {

            // Try-catch block to verify whether a correct candle length is provided
            try {
                Scanner input = new Scanner(System.in);

                for (int counter = 0; counter < candles.length; counter++) {
                    candles[counter] = input.nextInt();

                    // 1 <= candles[i] <= 10^3
                    if (candles[counter] < 1 || candles[counter] > Math.pow(10, 3)) {
                        throw new Exception();
                    }
                }
                flag = true;
            }
            catch (Exception exception) {
                System.out.println("\nInvalid candle length!\nPlease try again... \n");
            }
        }

        int numberOfTallestCandles = 0;

        // Counting the number of the tallest candles
        for (int candle: candles) {
            if (candle == tallestCandle(candles))
                numberOfTallestCandles++;
        }
        return numberOfTallestCandles;
    }

    public static void main(String[] args) {

        // This flag is used to control input until a correct amount of candles is provided
        boolean flag = false;

        // a loop to control input until a correct amount of candles is provided
        while (!flag) {

            // Try-catch block to verify whether a correct amount of candles is provided
            try {
                Scanner input = new Scanner(System.in);
                int n = input.nextInt();

                // 1 <= n <= 10^3
                if (n < 1 || n > Math.pow(10, 3))
                    throw new Exception();

                // An array of candles
                int[] candles = new int[n];
                System.out.println(birthdayCakeCandles(candles));

                // If provided n complies, flag changes and loop terminates
                flag = true;
            }
            catch (Exception exception) {
                System.out.println("\nInvalid amount of candles!\nPlease try again... \n");
            }
        }
    }
}

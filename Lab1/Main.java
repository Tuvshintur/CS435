package mum.edu.cs.lab1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    //Create a new array consisting of even numbers only. Then use nested loops to solve the problem using
    //the newly created array of even numbers only.
    public static int algorithmOne(int[] numbers) {
        int maxDistance = Integer.MIN_VALUE;
        List<Integer> evens = new ArrayList<>();
        for (int number : numbers) {
            if (number % 2 == 0) {
                evens.add(number);
            }
        }

        for (int i = 0; i < evens.size() - 1; i++) {
            for (int j = 0; j < evens.size(); j++) {
                int distance = Math.abs(evens.get(i) - evens.get(j));
                if (maxDistance < distance) {
                    maxDistance = distance;
                }
            }
        }

        return maxDistance;
    }

    //Use a nested loop to solve the problem without creating an extra array.
    public static int algorithmTwo(int[] numbers) {
        int maxDistance = Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] % 2 == 1) continue;
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[j] % 2 == 1) continue;
                int distance = Math.abs(numbers[i] - numbers[j]);
                if (maxDistance < distance) {
                    maxDistance = distance;
                }
            }
        }

        return maxDistance;
    }

    //Use one loop. Find max and min of even integers. Compute max – min.
    public static int algorithmThree(int[] numbers) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (number % 2 == 0) {
                if (min > number) {
                    min = number;
                }
                if (max < number) {
                    max = number;
                }
            }
        }
        return max - min;
    }

    //Use Streams to find the max and min. Compute max – min
    public static int algorithmFour(int[] numbers) {
        IntSummaryStatistics evens = Arrays.stream(numbers).filter(number -> number % 2 == 0).summaryStatistics();
        return evens.getMax() - evens.getMin();
    }

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter out = new PrintWriter("results.txt");

        int[] lenghtArray = new int[]{10000, 20000, 30000, 40000};

        for (int length : lenghtArray) {
            int[] numbers = new int[length];

            Random r = new Random();
            for (int i = 0; i < length; i++) {
                numbers[i] = r.nextInt(length * 4);
            }

//            System.out.println(Arrays.toString(numbers));

            long startTime = System.currentTimeMillis();
            algorithmOne(numbers);
            long execTime1 = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            algorithmTwo(numbers);
            long execTime2 = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            algorithmThree(numbers);
            long execTime3 = System.currentTimeMillis() - startTime;

            startTime = System.currentTimeMillis();
            algorithmFour(numbers);
            long execTime4 = System.currentTimeMillis() - startTime;

            out.println(String.format("%s %s %s %s %s", length, execTime1, execTime2, execTime3, execTime4));
        }
        out.close();
    }

}

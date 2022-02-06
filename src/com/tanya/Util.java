package com.tanya;

import java.util.Random;
import java.util.Scanner;

public class Util {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();
    public static final double MIN_FILE_SIZE = 500.0;
    public static final double MAX_FILE_SIZE = 5000.0;
    public static final int MAX_THREAD_SLEEP = 2000;

    public static double randomDouble() {
        double r = Util.RANDOM.nextDouble();
        return (MAX_FILE_SIZE + (MAX_FILE_SIZE - MIN_FILE_SIZE) * r);
    }

    public static double downloadSpeed() {
        return randomDouble();
    }

    public static int downloadDelay() {
        return RANDOM.nextInt(MAX_THREAD_SLEEP);
    }

}

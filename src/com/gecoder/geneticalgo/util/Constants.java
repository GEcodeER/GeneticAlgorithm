package com.gecoder.geneticalgo.util;

public class Constants {

    public static final int VERTICAL_HORIZONTAL_COST = 10;
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLUMNS = 10;

    public static final int NUMBER_OF_ITERATIONS = 100000;
    public static final int SIZE_OF_TABU_QUEUE = 400;
    public static final int NUM_OF_VALUES = 200;

    public static final int MAX_TEMPERATURE = 100;
    public static final int MIN_TEMPERATURE = 1;
    public static final double COOLING_RATE = 0.02;

    public static final int[] SOLUTION_SEQUENCE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
            , 12, 13, 14, 15};
    public static final double CROSSOVER_RATE = 0.05;
    public static final double MUTATION_RATE = 0.015;
    public static final int TOURNAMENT_SIZE = 5;
    public static final int CHROMOSOME_LENGTH = 16;
    public static final int MAX_FITNESS = 16;
    public static final int SIMULATION_LENGTH = 1000;

    public static final int GENE_LENGTH = 10;
}

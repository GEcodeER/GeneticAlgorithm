package com.gecoder.geneticalgo;

import com.gecoder.geneticalgo.algoelements.FunctionCoord;
import com.gecoder.geneticalgo.repos.FunctionCoordRepository;
import com.gecoder.geneticalgo.util.Constants;

public class MainFive {

    public static void main(String[] args) {

        FunctionCoord.setFunction("f(x) = sin(x)*((x-2)^2) + 3");
        FunctionCoord.setInterval(new int[] {0, 10});
        GeneticAlgorithm<FunctionCoordRepository, FunctionCoord> geneticAlgorithmCoord =
                new GeneticAlgorithm<>();

        FunctionCoordRepository function = new FunctionCoordRepository(100);
        function.initialize();
        System.out.println("Current fittest: " + function.getFittest().toString());
        int generationCounter = 0;
        while(generationCounter< Constants.SIMULATION_LENGTH) {
            ++generationCounter;
            function = geneticAlgorithmCoord.evolvePopulation(function);
            System.out.println("GENERATION No." + generationCounter + " fittest: " + function.getFittest().toString());
        }

        System.out.println("SOLUTION FOUND!!! It took " + generationCounter + " " +
                "generations to find the optimal solution!");
        System.out.println("The fittest individual is: " + function.getFittest().toString());
    }
}

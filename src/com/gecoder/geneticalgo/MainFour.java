package com.gecoder.geneticalgo;

import com.gecoder.geneticalgo.algoelements.Individual;
import com.gecoder.geneticalgo.repos.PopulationRepository;
import com.gecoder.geneticalgo.util.Constants;

public class MainFour {

    public static void main(String[] args) {

        GeneticAlgorithm<PopulationRepository, Individual> geneticAlgorithmPop =
                new GeneticAlgorithm<>();

        PopulationRepository population = new PopulationRepository(100);
        population.initialize();
        System.out.println("Current fittest: " + population.getFittest().toString());
        int generationCounter = 0;
        while(population.getFittest().getFitness() != Constants.MAX_FITNESS) {
            ++generationCounter;
            population = geneticAlgorithmPop.evolvePopulation(population);
            System.out.println("GENERATION No." + generationCounter + " fittest: " + population.getFittest().toString());
        }

        System.out.println("SOLUTION FOUND!!! It took " + generationCounter + " " +
                "generations to find the optimal solution!");
        System.out.println("The fittest individual is: " + population.getFittest().toString());
    }
}

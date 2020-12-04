package com.gecoder.geneticalgo.algoelements;

import com.gecoder.geneticalgo.util.Constants;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Individual extends GeneticAlgoElement<Individual, Integer> {

    private Integer[] genes;
    private Integer fitness;
    private Random randomGenerator;

    public Individual() {
        super(16);
        genes = new Integer[Constants.CHROMOSOME_LENGTH];
        randomGenerator = new Random();
        fitness = 0;
    }

    @Override
    public Individual generate() {

        IntStream.range(0, genes.length).forEach(geneIndex -> genes[geneIndex] = randomGenerator.nextInt(Constants.CHROMOSOME_LENGTH-1));

        return this;
    }

    @Override
    public Integer getFitness() {
        if(fitness.equals(0))
            IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(idx -> {
                if(genes[idx]==Constants.SOLUTION_SEQUENCE[idx])
                    fitness++;
            });
        return fitness;
    }

    @Override
    public Individual newInstance() {
        return new Individual();
    }

    @Override
    public Integer getGene(int index) {
        return genes[index];
    }

    @Override
    public Individual setGene(int index, Integer gene) {
        genes[index] = gene;
        fitness = 0;
        return this;
    }

    @Override
    public String toString() {
        return "Individual -> " + Arrays.toString(genes);
    }
}

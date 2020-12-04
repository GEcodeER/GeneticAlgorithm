package com.gecoder.geneticalgo.repos;

import com.gecoder.geneticalgo.algoelements.Individual;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class PopulationRepository extends GeneticAlgoRepository<Individual> {

    public PopulationRepository(int poolSize) {
        super(poolSize);
    }

    @Override
    public GeneticAlgoRepository getInstance(int size) {
        return new PopulationRepository(size);
    }

    public void initialize() {
        IntStream.range(0, size()).forEach(index -> geneticPool.set(index, new Individual().generate()));
    }

    public void saveGeneticElement(int index, Individual newIndividual) {
        geneticPool.set(index, newIndividual);
    }

    @Override
    public Individual getFittest() {
        return geneticPool.stream().filter(Objects::nonNull).max(Comparator.comparing(Individual::getFitness)).orElse(geneticPool.get(0));
    }

    public int size() {
        return super.size;
    }

    @Override
    public Individual getAtIndex(int index) {
        return this.geneticPool.get(index);
    }
}

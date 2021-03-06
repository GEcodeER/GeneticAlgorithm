package com.gecoder.geneticalgo.repos;

import com.gecoder.geneticalgo.algoelements.FunctionCoord;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class FunctionCoordRepository extends GeneticAlgoRepository<FunctionCoord> {

    public FunctionCoordRepository(int poolSize) {
        super(poolSize);
    }

    /** Initialize a random pool of function coordinates (FunctionCoord) */
    public void initialize() {
        IntStream.range(0, super.size).forEach(index -> saveGeneticElement(index, new FunctionCoord().generate()));
    }


    @Override
    public GeneticAlgoRepository getInstance(int size) {
        return new FunctionCoordRepository(super.size);
    }

    /**
     * Returns the fittest of the pool comparing the fitness method of the genetic
     * element class (FunctionCoord)
     * @return The fittest element of the pool
     */
    @Override
    public FunctionCoord getFittest() {
        return geneticPool.stream().filter(Objects::nonNull).max(Comparator.comparing(FunctionCoord::getFitness)).orElse(geneticPool.get(0));
    }

    @Override
    public int size() {
        return super.size;
    }

    @Override
    public FunctionCoord getAtIndex(int index) {
        return geneticPool.get(index);
    }

    @Override
    public void saveGeneticElement(int index, FunctionCoord geneticElement) {
        geneticPool.set(index, geneticElement);
    }
}

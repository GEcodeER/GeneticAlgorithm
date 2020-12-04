package com.gecoder.geneticalgo.repos;

import com.gecoder.geneticalgo.algoelements.FunctionCoord;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class FunctionCoordRepository extends GeneticAlgoRepository<FunctionCoord> {

    public FunctionCoordRepository(int poolSize) {
        super(poolSize);
    }

    public void initialize() {
        IntStream.range(0, super.size).forEach(index -> saveGeneticElement(index, new FunctionCoord().generate()));
    }

    @Override
    public GeneticAlgoRepository getInstance(int size) {
        return new FunctionCoordRepository(super.size);
    }

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

package com.gecoder.geneticalgo.algoelements;

import com.gecoder.geneticalgo.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.mariuszgromada.math.mxparser.Function;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class FunctionCoord extends GeneticAlgoElement<FunctionCoord, Double> {

    private BitSet genes;
    private Double fitness;
    private double[] coords;

    @Setter @Getter
    private static int[] interval;

    private static Function function;

    public FunctionCoord() {
        super(2);
        fitness = 0.0;
        genes = new BitSet(Constants.CHROMOSOME_LENGTH);
        coords = new double[2];
    }

    @Override
    public FunctionCoord generate() {
        IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(geneIndex -> genes.set(geneIndex, (ThreadLocalRandom.current().nextInt(2) == 1)));
        coords[0] = genesToDouble();
        return this;
    }

    @Override
    public Integer getGene(int index) {
        return genes.get(index) ? 1 : 0;
    }

    @Override
    public FunctionCoord setGene(int index, Integer gene) {
        genes.set(index, gene.equals(1));
        return this;
    }

    @Override
    public Double getFitness() {
        if(fitness==0.0) {
            coords[1] = function.calculate(coords[0]);
            fitness = coords[1];
        }
        return fitness;
    }

    @Override
    public FunctionCoord newInstance() {
        return new FunctionCoord();
    }

    private double genesToDouble() {
        return IntStream.range(0, Constants.GENE_LENGTH).mapToDouble(index -> genes.get(index) ? Math.pow(2, Constants.GENE_LENGTH-index-1) : 0).sum() / 102.4f;
    }

    public static void setFunction(String function) {
        FunctionCoord.function = new Function(function);
    }

    @Override
    public String toString() {
        return "Coordinate -> Gene: " + Arrays.toString(IntStream.range(0,
                Constants.GENE_LENGTH).mapToLong(index -> genes.get(index) ? 1 : 0).toArray()) +
                " (x:" + genesToDouble() +
                ";y:" + function.calculate(genesToDouble()) + ")";
    }
}

package com.gecoder.geneticalgo.algoelements;

import com.gecoder.geneticalgo.util.Constants;
import lombok.Getter;
import lombok.Setter;
import org.mariuszgromada.math.mxparser.Function;

import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Genetic element of a function coordinate. It has a gene (a BitSet) that corresponds to a double value (y-axis) of
 * an evaluated function. The better the gene the closer it is to a global minimum/maximum.
 * The fitness of the gene is the one parameter used to compare different genetic elements and pull out th ebest one
 * of them
 * @author me
 */
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

    /**
     * Initialize the current FunctionCoord with a random x value. It so generates
     * random bits of the FunctionCoord's gene
     * @return FunctionCoord the object itself
     */
    @Override
    public FunctionCoord generate() {
        IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(geneIndex -> genes.set(geneIndex, (ThreadLocalRandom.current().nextInt(2) == 1)));
        coords[0] = genesToDouble();
        return this;
    }

    /**
     * Gets the bit at the specific gene index
     * @param index
     * @return bit (0 or 1)
     */
    @Override
    public Integer getGene(int index) {
        return genes.get(index) ? 1 : 0;
    }

    /**
     * Sets the bit at the specified index to either 1 or 0
     * @param index
     * @param gene The bit (either 0 or 1)
     * @return FunctionCoord the object itself (used for chaining)
     */
    @Override
    public FunctionCoord setGene(int index, Integer gene) {
        genes.set(index, gene.equals(1));
        return this;
    }

    /**
     * Returns the fitness of the FunctionCoord, which in this case is the value of the
     * specified function at the given x.
     * @return fitness
     */
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

    /**
     * Converts the gene (which is a bitset) into a double
     * @return the decoded double
     */
    private double genesToDouble() {
        return IntStream.range(0, Constants.GENE_LENGTH).mapToDouble(index -> genes.get(index) ? Math.pow(2, Constants.GENE_LENGTH-index-1) : 0).sum() / 102.4f;
    }

    /**
     * Sets the function on which the y-axis values should be calculated
     * @param function
     */
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

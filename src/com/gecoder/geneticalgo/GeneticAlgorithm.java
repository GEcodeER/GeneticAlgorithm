package com.gecoder.geneticalgo;
import com.gecoder.geneticalgo.algoelements.GeneticAlgoElement;
import com.gecoder.geneticalgo.repos.GeneticAlgoRepository;
import com.gecoder.geneticalgo.util.Constants;

import java.util.Random;
import java.util.stream.IntStream;

public class GeneticAlgorithm<T extends GeneticAlgoRepository,
        K extends GeneticAlgoElement> {

    private Random randomGenerator;

    public GeneticAlgorithm() {
        randomGenerator = new Random();
    }

    /**
     * The core of the Genetic Algorithm: it evolves the given population into a new
     * generation of population. This is done by doing corossovers and mutations to the
     * given population
     * @param geneticPool The initial population that has to be evolved
     * @return The new evolved population
     */
    @SuppressWarnings("unchecked")
    public T evolvePopulation(T geneticPool) {

        T evolvedPool = (T) geneticPool.getInstance(geneticPool.size());

        IntStream.range(0, geneticPool.size()).forEach(indx -> {
            //Choosing the parents
            K firstGeneticElem = randomSelection(geneticPool);
            K secondGeneticElem = randomSelection(geneticPool);
            //New born from the crossover
            K newBorn = crossOver(firstGeneticElem, secondGeneticElem);

            evolvedPool.saveGeneticElement(indx, newBorn);

            //Mutating the newborn child just insert
            mutate((K) evolvedPool.getAtIndex(indx));
        });

        return evolvedPool;
    }

    /**
     * Mutates a genetic item based on the probability to get mutated from the
     * constants field MUTATION_RATE. The mutation is a single bit mutation: bits will
     * be mutated one by one
     * @param geneticItem The genetic item to mutate
     */
    private void mutate(K geneticItem) {

        IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(geneIndex -> {
            if(Math.random() <= Constants.MUTATION_RATE)
                geneticItem.setGene(geneIndex,
                        randomGenerator.nextInt(geneticItem.getRandomRangeGene()));
        });

    }

    /**
     * Makes a crossover between two genetic elements and giving 'birth' to a new
     * genetic element using parts of genes from both its 'parents'
     * @param geneticElement First parent
     * @param otherGeneticElement Second parent
     * @return The new-born child
     */
    @SuppressWarnings("unchecked")
    private K crossOver(K geneticElement, K otherGeneticElement) {

        K currentCrossover = (K) geneticElement.newInstance();
        IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(geneIndex -> {
            if(Math.random() <= Constants.CROSSOVER_RATE)
                currentCrossover.setGene(geneIndex, geneticElement.getGene(geneIndex));
            else
                currentCrossover.setGene(geneIndex,
                        otherGeneticElement.getGene(geneIndex));
        });

        return currentCrossover;
    }

    /**
     * Randomly select a pool of TOURNAMENT_SIZE (Constants) size and picks the fittest
     * of them. This is used to have higher probability of making a crossover between
     * two strong (fit) genes
     * @param geneticPool The population that is being evolved
     * @return The fittest of the randomly selected pool
     */
    @SuppressWarnings("unchecked")
    private K randomSelection(T geneticPool) {

        T tempPool = (T) geneticPool.getInstance(geneticPool.size());

        IntStream.range(0, Constants.TOURNAMENT_SIZE).forEach(index -> tempPool.saveGeneticElement(index,
                geneticPool.getAtIndex(randomGenerator.nextInt(geneticPool.size()))));

        return (K) tempPool.getFittest();
    }
}

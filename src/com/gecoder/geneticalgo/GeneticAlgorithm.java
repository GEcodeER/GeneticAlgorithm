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

    @SuppressWarnings("unchecked")
    public T evolvePopulation(T geneticPool) {

        T evolvedPool = (T) geneticPool.getInstance(geneticPool.size());

        IntStream.range(0, geneticPool.size()).forEach(indx -> {
            K firstGeneticElem = randomSelection(geneticPool);
            K secondGeneticElem = randomSelection(geneticPool);
            K newBorn = crossOver(firstGeneticElem, secondGeneticElem);

            evolvedPool.saveGeneticElement(indx, newBorn);
            mutate((K) evolvedPool.getAtIndex(indx));
        });

        return evolvedPool;
    }

    private void mutate(K geneticItem) {

        IntStream.range(0, Constants.CHROMOSOME_LENGTH).forEach(geneIndex -> {
            if(Math.random() <= Constants.MUTATION_RATE)
                geneticItem.setGene(geneIndex,
                        randomGenerator.nextInt(geneticItem.getRandomRangeGene()));
        });

    }

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

    @SuppressWarnings("unchecked")
    private K randomSelection(T geneticPool) {

        T tempPool = (T) geneticPool.getInstance(geneticPool.size());

        IntStream.range(0, Constants.TOURNAMENT_SIZE).forEach(index -> tempPool.saveGeneticElement(index,
                geneticPool.getAtIndex(randomGenerator.nextInt(geneticPool.size()))));

        return (K) tempPool.getFittest();
    }
}

package com.gecoder.geneticalgo.algoelements;

import lombok.Getter;

public abstract class GeneticAlgoElement<T, G> {

    @Getter
    private int randomRangeGene;

    public GeneticAlgoElement(int randomRangeGene) {
        this.randomRangeGene = randomRangeGene;
    }

    public abstract T generate();

    public abstract Integer getGene(int index);
    public abstract T setGene(int index, Integer gene);
    public abstract G getFitness();
    public abstract T newInstance();
}

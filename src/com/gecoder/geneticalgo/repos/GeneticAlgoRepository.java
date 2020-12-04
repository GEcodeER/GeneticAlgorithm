package com.gecoder.geneticalgo.repos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GeneticAlgoRepository<T> {

    protected List<T> geneticPool;
    protected Integer size;

    public GeneticAlgoRepository(int poolSize) {
        geneticPool = new ArrayList<>(Collections.nCopies(poolSize, null));
        size = poolSize;
    }

    public abstract GeneticAlgoRepository getInstance(int size);
    public abstract T getFittest();
    public abstract int size();
    public abstract T getAtIndex(int index);
    public abstract void saveGeneticElement(int index, T geneticElement);
}

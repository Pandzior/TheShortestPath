package com.marcin.jedrusiak.model;

public class Neighbor {

    private int index;
    private int weight;

    public Neighbor() {
    }

    public Neighbor(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

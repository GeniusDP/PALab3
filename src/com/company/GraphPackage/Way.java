package com.company.GraphPackage;

import java.util.ArrayList;
import java.util.List;

public class Way implements Comparable<Way>{
    private int cost;
    private List<Integer> vertexes;
    {
        cost = 0;
        vertexes = null;
    }
    public Way(int cost) {
        this.cost = cost;
        vertexes = new ArrayList<>();
    }

    public int getCost() {
        return cost;
    }

    public void addNewVertex(int newVertex, int value){
        vertexes.add(newVertex);
        if(vertexes.size() > 0)
            cost += value;
    }

    @Override
    public String toString() {
        return "Way{" +
                "cost=" + cost +
                ", vertexes=" + vertexes +
                '}';
    }

    @Override
    public int compareTo(Way o) throws NullPointerException{
        if(this.cost == o.cost)
            return 0;
        return this.cost - o.cost < 0 ? -1 : 1;
    }
}

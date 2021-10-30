package com.company;
import com.company.GraphPackage.Graph;
import com.company.GraphPackage.Way;

import java.io.FileNotFoundException;

import static com.company.Constants.INF;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
            try {
                graph.readFrom("input.txt");
            }catch(FileNotFoundException e){
                System.out.println("This file was not found!\n" + e);
            }
        Way theBestWay = new Way(INF);
        for(int i=1; i<=1000; i++){
            Way currBestWay = graph.iterate();
            //updating the answer
            if(theBestWay.compareTo(currBestWay) == -1){
                theBestWay = currBestWay;
            }
            if(i % 20 == 0){
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("iteration " + i);
                System.out.println("the best way now is " + theBestWay);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        }
    }
}

package com.company;
import com.company.GraphPackage.Graph;
import com.company.GraphPackage.Way;

import java.io.FileNotFoundException;

import static com.company.Constants.INF;

/*
5 4
1 2 38
1 3 47
1 4 50
1 5 45
2 3 46
2 4 16
2 5 27
3 4 8
3 5 10
4 5 42
 */

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
            try {
                graph.readFrom("input.txt");
            }catch(FileNotFoundException e){
                System.out.println("This file was not found!\n" + e);
            }
        //graph.print();
        Way theBestWay = new Way(INF);
        long timer = System.currentTimeMillis();
        for(int i=1; i<=1000; i++){                                                                            //set to 1000
            Way currBestWay = graph.iterate(2, 3, 0.3);
            //updating the answer
            if(theBestWay.compareTo(currBestWay) > 0){
                theBestWay = currBestWay;
                System.err.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<{==");
                System.err.println("iteration " + i);
                System.err.println("the best way now is " + theBestWay);
                System.err.println("==}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
            if(i % 20 == 0){
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<{==");
                System.out.println("iteration " + i);
                System.out.println("the best way now is " + theBestWay);
                System.out.println("==}>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        }
        System.out.println("THE BEST WAY IS: " + theBestWay);
        System.out.println("Program worked for " + (System.currentTimeMillis() - timer)/1000. + " sec");
    }
}

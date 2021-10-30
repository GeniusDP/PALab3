package com.company.GraphPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Graph {
    private Colony colony;
    private int[][] dist;
    private int[][] tay;
    public void readFrom(String filePath) throws FileNotFoundException {
        int numberOfVertexes;
        int numberOfBeatles;
        Scanner in = new Scanner(new File(filePath));
        numberOfVertexes = in.nextInt();
        numberOfBeatles = in.nextInt();

        dist = new int[numberOfVertexes][numberOfVertexes];
        tay = new int[numberOfVertexes][numberOfVertexes];

        while(in.hasNextInt()){
            int fromVert, toVert, price;
            fromVert = in.nextInt();
            toVert = in.nextInt();
            price = in.nextInt();
            //to make 0-indexation
            fromVert--;
            toVert--;
            dist[fromVert][toVert] = dist[toVert][fromVert] = price;
            colony = new Colony(numberOfBeatles);
        }
        //making tay array. gives start values [1..3]
        for(int i = 0; i < numberOfVertexes; i++){
            for(int j = i+1; j<numberOfVertexes; j++){
                tay[i][j] = tay[j][i] = (abs(new Random().nextInt()))%3 + 1;
            }
        }
        //making new colony
        colony = new Colony(numberOfBeatles);

        in.close();
    }



    /*
    *This is main method.
    * It makes another new iteration,
    * changes tay array
    * and returns the best way after this iteration.
     */
    public Way iterate(int alpha, int beta, double p) {

        List<Way> allTheWays = new ArrayList<>();
        for(int subIt = 0; subIt < this.colony.getNumberOfBeatles(); subIt++){//making subiterations for each beatle

            Way theWayOfCurrentBeatle = new Way(0);                 //the current way
            int startVertex = (abs(new Random().nextInt())) % dist.length;
            Set<Integer> usedVertexes = new HashSet<>();
            int currentVertex = startVertex;

            for(int v = 0; v < dist.length; v++){//loop for vertex to checkout
                theWayOfCurrentBeatle.addNewVertex(currentVertex, (v==0) ? 0 : dist[theWayOfCurrentBeatle.getLastVertexInWay()][currentVertex]);
                usedVertexes.add(currentVertex);
                List<Integer> availableVertexes = new ArrayList<>();

                for(int j = 0; j < dist.length; j++){//loop for vertexes to add into available
                    if(!usedVertexes.contains(j) && dist[currentVertex][j]>0){
                        availableVertexes.add(j);
                    }
                }

                //calculating P-function of available vertex

                    List<Double> probabilityOfAvailableVertex = new ArrayList<>();

                        double summary = 0;//summary of all (etha[i][k]^beta * tay[i][k]^alpha)
                        for(int toVertex: availableVertexes){
                            double etha = 1. / dist[currentVertex][toVertex];
                            summary += pow(etha, beta) * pow(tay[currentVertex][toVertex]+.0, alpha);
                        }
                    //probability calculating for every available vertex
                    for (int toVertex : availableVertexes) {
                        double etha = 1. / dist[currentVertex][toVertex];
                        double P = pow(etha, beta) * pow(tay[currentVertex][toVertex] + .0, alpha) / summary;
                        probabilityOfAvailableVertex.add(P);
                    }
                    if(probabilityOfAvailableVertex.size()>0) {
                        double randomZone = Math.random();
                        double currSum = 0;
                        int i = 0;
                        while( !(currSum < randomZone && currSum + probabilityOfAvailableVertex.get(i) > randomZone)
                                && i < probabilityOfAvailableVertex.size() ){
                            currSum += probabilityOfAvailableVertex.get(i);
                            i++;
                        }
                        i = Math.min(i, probabilityOfAvailableVertex.size()-1);//0.1 + 0.1 + 0.1 != 0.3 problems with accuracy may be
                        currentVertex = availableVertexes.get(i);

                        //for greedy beatle
                       /* double minProb = Collections.min(probabilityOfAvailableVertex);
                        int minInProbabilityIndex = probabilityOfAvailableVertex.indexOf(minProb);
                        currentVertex = availableVertexes.get(minInProbabilityIndex);*/
                    }
            }
            theWayOfCurrentBeatle.addNewVertex(startVertex, dist[theWayOfCurrentBeatle.getLastVertexInWay()][startVertex]);//start vertex is also the last
            allTheWays.add(theWayOfCurrentBeatle);

            //changing tay
            int deltaTay = dist.length * 150 / theWayOfCurrentBeatle.getCost();
            List<Integer> vertexesOfCurrentWay = theWayOfCurrentBeatle.getVertexes();//needed to calculate tay difference;
            for(int i=0; i<vertexesOfCurrentWay.size()-1; i++){
                int _tay = tay[vertexesOfCurrentWay.get(i)][vertexesOfCurrentWay.get(i+1)];
                tay[vertexesOfCurrentWay.get(i)][vertexesOfCurrentWay.get(i+1)] = (int)((1-p) * _tay + deltaTay);
            }
        }

        return Collections.min(allTheWays);
    }

    public void print(){
        for(int i=0; i<dist.length; i++){
            for(int j=0; j<dist[i].length; j++){
                System.out.format("%4d", dist[i][j]);
            }
            System.out.println("");
        }
    }

    public void printTay(){
        for(int i=0; i<tay.length; i++){
            for(int j=0; j<tay[i].length; j++){
                System.out.format("%4d", tay[i][j]);
            }
            System.out.println("");
        }
    }
}

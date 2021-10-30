package com.company.GraphPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Graph {
    private Colony colony;
    private int[][] dist;
    private int[][] tay;

    public void readFrom(String filePath) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filePath));
        
        in.close();
    }

    public Way iterate() {

    }
}

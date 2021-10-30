package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {
    public static void main(String[] args){
        try (PrintWriter pw = new PrintWriter("input.txt")) {
            int n = 200;
            int m = 45;
            pw.println(n + " " + m);
            for(int i = 1; i <= n; i++){
                for(int j = i + 1; j <= n; j++){
                    pw.println(i + " " + j + " " + ((Math.abs(new Random().nextInt())) % 50 + 1) );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

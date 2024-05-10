package me.takinrom.ml;

import me.takinrom.math.Vector;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        int n = 500;
        int dimension;
        if (args.length < 1) {
            dimension = 2;
        } else {
            dimension = Integer.parseInt(args[0]);
        }
        Vector[] data = new Vector[n];
        double[] delta = new double[dimension];
        for (int i = 0; i < n; i++) {
            double[] tmp = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                tmp[j] = random.nextDouble(1, 1000);
            }
            data[i] = new Vector(tmp);
            if (random.nextBoolean()) {
                for (int j = 0; j < dimension; j++) {
                    delta[j] = 600;
                }
                data[i].add(new Vector(delta));
            }
        }


        System.out.println(n);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        Kmeans asdf = new Kmeans(data, 2);
        asdf.run();
    }
}

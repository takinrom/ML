package me.takinrom.ml;

import me.takinrom.math.Vector;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        int n = 2000;
        int dimension = 2;
        Vector[] data = new Vector[n];
        double[] delta = new double[dimension];
        for (int i = 0; i < n; i++) {
            double[] tmp = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                tmp[j] = random.nextDouble(1, 1000);
            }
            data[i] = new Vector(tmp);
            if (random.nextBoolean() || random.nextBoolean()) {
                for (int j = 0; j < dimension; j++) {
                    delta[j] = random.nextInt(1, 2000) - 1000;
                }
                data[i].add(new Vector(delta));
            }
        }


        System.out.println(n);
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        Kmeans asdf = new Kmeans(data, 6);
        asdf.run();
    }
}

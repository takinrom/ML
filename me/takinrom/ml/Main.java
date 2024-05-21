package me.takinrom.ml;

import me.takinrom.math.Vector;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        int n = 1000;
        int dimension = 2;
        int alg;
        if (args.length < 1) {
            printHelp();
            return;
        } else {
            if (args[0].contentEquals("Kmeans")) {
                alg = 1;
            } else if (args[0].contentEquals("DBSCAN")) {
                alg = 2;
            } else {
                printHelp();
                System.out.println("ERROR: Algorithm must be Kmeans or DBSCAN");
                return;
            }
            if (args.length > 1) {
                try {
                    dimension = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    printHelp();
                    System.out.println("ERROR: Dimension must be an integer");
                    return;
                }
            }
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
        for (Vector vector : data) {
            System.out.println(vector);
        }
        switch (alg) {
            case 1:
                Kmeans asdf = new Kmeans(data, 2);
                boolean isChanged = true;
                while (isChanged) {
                    isChanged = asdf.step();
                    System.out.print(Arrays.toString(asdf.getCenters()) + " : ");
                    System.out.println(Arrays.toString(asdf.getMap()));
                }
            case 2:
                DBSCAN zxcv  = new DBSCAN(data, 8.5, 3);
                zxcv.run();
                System.out.println(Arrays.toString(zxcv.getClusterMap()));
        }
    }

    private static void printHelp() {
        System.out.println("""
                Usage: java me.takinrom.ml.Main <Algorithm> [Dimension]
                Example: java me.takinrom.ml.Main Kmeans 3

                Algorithm: Kmeans or DBSCAN

                Dimension: [int] Dimension of test data (default: 2)
                """);
    }
}

package me.takinrom.ml;

import me.takinrom.math.Vector;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int alg;
        int k = -1;
        int dimension = -1;
        int minPts = -1;
        double eps = -1;
        if (args.length < 2) {
            printHelp();
            return;
        }
        try {
            dimension = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect dimension");
        }
        if (args[1].contentEquals("Kmeans")) {
            alg = 0;
            if (args.length < 3) {
                printHelp();
                return;
            }
            try {
                k = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect k");
                return;
            }
        } else if (args[1].contentEquals("DBSCAN")) {
            alg = 1;
            if (args.length < 4) {
                printHelp();
                return;
            }
            try {
                eps = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect eps");
                return;
            }
            try {
                minPts = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect minPts");
                return;
            }
        } else {
            System.out.println("Incorrect algorithm name");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int data_size = Integer.parseInt(scanner.nextLine().strip());

        String[] splited;
        Vector[] data = new Vector[data_size];
        for (int j = 0; j < data_size; j++) {
            if (scanner.hasNext()) {
                splited = scanner.nextLine().split(",");
                if (splited.length != dimension + 1) {
                    System.out.println("Incorrect data dimension");
                    return;
                }
                double[] vec = new double[dimension];
                for (int i = 0; i < dimension; i++) {
                    vec[i] = Double.parseDouble(splited[i]);
                }
                data[j] = new Vector(vec);
            } else {
                System.out.println("Incorrect data length");
                return;
            }
        }
        scanner.close();

        if (alg == 0) {
            Kmeans kmeans = new Kmeans(data, k);
            kmeans.run();
            System.out.println(Arrays.toString(kmeans.getMap()));
        } else {
            DBSCAN dbscan = new DBSCAN(data, eps, minPts);
            dbscan.run();
            System.out.println(Arrays.toString(dbscan.getClusterMap()));
        }
    }

    private static void printHelp() {
        System.out.println("""
                Usage: java me.takinrom.ml.Main <Dimension> <Algorithm> [Algorithm params]
                Example: java me.takinrom.ml.Main 3 Kmeans 2

                Algorithm: Kmeans or DBSCAN
                
                -Kmeans params:
                    K: number of clusters
                Usage: java me.takinrom.ml.Main <Dimension> Kmeans <K>

                -DBSCAN params:
                    eps: radius of a neighborhood
                    minPts: minimum number of points for the core point
                Usage: java me.takinrom.ml.Main <Dimension> DBSCAN <eps> <minPts>
                """);
    }
}

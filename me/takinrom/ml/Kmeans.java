package me.takinrom.ml;

import me.takinrom.math.Vector;

import java.util.Random;

/**
 * Kmeans algorithm realization
 */
public class Kmeans {
    private final int k;
    private final Vector[] data;
    private final int dataSize;
    private final Vector[] centers;
    private final int[] map;
    private final int[] quantities;

    public Kmeans(Vector[] data, int k) {
        if (data.length < 1) {
            throw new Error("Data length must be >= 1");
        }
        if (k < 1) {
            throw new Error("K must be >= 1");
        }
        this.k = k;
        int n = data.length;
        Vector[] builder = new Vector[n];

        int dimension = 0;
        for (int i = 0; i < n; i++) {
            double[] vec = data[i].getVec();
            if (vec.length > dimension) {
                dimension = vec.length;
            }
        }
        for (int i = 0; i < n; i++) {
            double[] vec = data[i].getVec();
            builder[i] = new Vector(vec);
            builder[i].setSite(dimension);
        }
        this.data = builder;

        centers = new Vector[k];
        for (int i = 0; i < k; i++) {
            centers[i] = new Vector(dimension);
        }

        map = new int[n];
        quantities = new int[k];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int rand = random.nextInt(0, k);
            quantities[rand]++;
            map[i] = rand;
        }

        dataSize = n;
    }

    public Kmeans(Vector[] data) {
        this(data, 2);
    }

    private void calcCenters() {
        for (int i = 0; i < k; i++) {
            centers[i].fill(0);
        }
        for (int i = 0; i < dataSize; i++) {
            centers[map[i]].add(data[i]);
        }
        for (int i = 0; i < k; i++) {
            if (quantities[i] > 0) {
                centers[i].divide(quantities[i]);
            }
        }
    }

    private boolean calcMap() {
        for (int i = 0; i < k; i++) {
            quantities[i] = 0;
        }
        boolean isChanged = false;
        for (int i = 0; i < dataSize; i++) {
            int min = 0;
            double min_distance = Double.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                double distance = Vector.distance(centers[j], data[i]);
                if (distance < min_distance) {
                    min = j;
                    min_distance = distance;
                }
            }
            if (map[i] != min) {
                isChanged = true;
                map[i] = min;
            }
            quantities[min]++;
        }

        return isChanged;
    }

    public boolean step() {
        return this.step(1);
    }

    public boolean step(int stepCnt) {
        boolean isChanged = true;
        for (int i = 0; i < stepCnt; i++) {
            calcCenters();
            isChanged = calcMap();
        }
        return isChanged;
    }

    public void run() {
        boolean isChanged = true;
        while (isChanged) {
            calcCenters();
            isChanged = calcMap();
        }
    }

    public int[] getMap() {
        return map;
    }

    public Vector[] getCenters() {
        return centers;
    }

    public Vector[] getData() {
        return data;
    }
}

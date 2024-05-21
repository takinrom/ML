package me.takinrom.ml;

import me.takinrom.math.Vector;
import me.takinrom.util.LinkedList;


/**
 * DBSCAN algorithm realization
 */
public class DBSCAN {
    private final Vector[] data;
    private final double eps;
    private final int minPts;
    private final int[] map;
    /*
    map doc:
    0 - not yet determined(default)
    1 - core points
    2 - reachable points
    3 - outliers
     */
    private final int[] clusterMap;
    /*
    result map doc:
    -1 - outliers
    0 - not yet determined(default)
    other positive numbers is numbers of clusters
     */
    private LinkedList<Integer>[] index;

    public DBSCAN(Vector[] data, double eps, int minPts) {
        int n = data.length;
        Vector[] builder = new Vector[n];
        for (int i = 0; i < n; i++) {
            builder[i] = new Vector(data[i].getVec());
        }
        this.data = builder;

        this.eps = eps;
        this.minPts = minPts;

        map = new int[n];
        clusterMap = new int[n];
    }

    private void indexData() {
        int n = data.length;
        index = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            index[i] = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                if (Vector.distance(data[i], data[j]) < eps && i != j) {
                    index[i].add(j);
                }
            }
        }
    }

    public void run() {
        indexData();
        int n = data.length;
        int C = 0;
        for (int i = 0; i < n; i++) {
            if (map[i] != 0) continue;
            if (index[i].getSize() < minPts) {
                map[i] = 3;
                clusterMap[i] = -1;
                continue;
            }
            map[i] = 1;
            clusterMap[i] = ++C;
            LinkedList<Integer> points = index[i];
            for (int j : points) {
                if (map[j] == 3) {
                    map[j] = 2;
                    clusterMap[j] = C;
                }
                if (map[j] != 0) continue;
                map[j] = 1;
                clusterMap[j] = C;
                if (index[j].getSize() > minPts) {
                    points.concat(index[j]);
                }
            }
        }
    }

    public int[] getMap() {
        return map;
    }

    public int[] getClusterMap() {
        return clusterMap;
    }

    public Vector[] getData() {
        return data;
    }
}

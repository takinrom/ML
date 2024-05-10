package me.takinrom.math;

import java.util.Arrays;

public class Vector {
    private double[] vec;

    public Vector(int size) {
        vec = new double[size];
    }

    public Vector(double[] vec) {
        this.vec = Arrays.copyOf(vec, vec.length);
    }

    public Vector() {
        this(2);
    }

    public double[] getVec() {
        return vec;
    }

    public int getSize() {
        return vec.length;
    }

    public void add(Vector v) {
        double[] v_vec = v.getVec();
        int n = Math.min(v_vec.length, vec.length);

        for (int i = 0; i < n; i++) {
            vec[i] += v_vec[i];
        }
    }

    public static Vector sum(Vector v) {
        return new Vector(v.getVec());
    }

    public static Vector sum(Vector a, Vector b) {
        double[] a_vec, b_vec, tmp;
        a_vec = a.getVec();
        b_vec = b.getVec();
        if (a_vec.length > b_vec.length) {
            tmp = a_vec;
            a_vec = b_vec;
            b_vec = tmp;
        }

        int A = a_vec.length;
        int B = b_vec.length;
        tmp = new double[B];
        for (int i = 0; i < A; i++) {
            tmp[i] = a_vec[i] + b_vec[i];
        }
        for (int i = A; i < B; i++) {
            tmp[i] = b_vec[i];
        }
        return new Vector(tmp);
    }

    public static Vector sum(Vector... vectors ) {
        int maxSize = 0;
        int n = vectors.length;
        for (int i = 0; i < n; i++) {
            int size = vectors[i].getSize();
            if (size > maxSize) {
                maxSize = size;
            }
        }

        double[] res = new double[maxSize];

        double[] t;
        for (int i = 0; i < n; i++) {
            t = vectors[i].getVec();
            for (int j = 0; j < t.length; j++) {
                res[j] += t[j];
            }
        }

        return new Vector(res);
    }

    public void divide(double k) {
        int n = vec.length;
        for (int i = 0; i < n; i++) {
            vec[i] /= k;
        }
    }

    public void multiply(double k) {
        int n = vec.length;
        for (int i = 0; i < n; i++) {
            vec[i] *= k;
        }
    }

    public void fill(double item) {
        int n = vec.length;
        for (int i = 0; i < n; i++) {
            vec[i] = item;
        }
    }

    public static double distance(Vector a, Vector b) {
        double[] vec_a = a.getVec();
        double[] vec_b = b.getVec();
        double[] tmp;
        if (vec_a.length > vec_b.length) {
            tmp = vec_a;
            vec_a = vec_b;
            vec_b = tmp;
        }
        int A = vec_a.length;
        int B = vec_b.length;

        double sum = 0;
        for (int i = 0; i < A; i++) {
            sum += Math.abs(vec_a[i] - vec_b[i]);
        }
        for (int i = A; i < B; i++) {
            sum += Math.abs(vec_b[i]);
        }

        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        int n = vec.length;
        for (int i = 0; i < n - 1; i++) {
            builder.append(vec[i]);
            builder.append(", ");
        }
        builder.append(vec[n - 1]);
        builder.append(")");

        return builder.toString();
    }
}

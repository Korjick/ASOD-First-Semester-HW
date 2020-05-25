package Karatsuba;

import Queue.Queue;
import sun.security.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class KaratsubaMultiplyer {

    private static boolean[] x;
    private static boolean[] y;

    private static boolean[] xl;
    private static boolean[] xr;
    private static boolean[] yl;
    private static boolean[] yr;
    private static int n;

    public static int multiply(int a, int b) {
        n = Math.max(Integer.toBinaryString(a).length(), Integer.toBinaryString(b).length());

        x = intToBooleanArr(a, n);
        y = intToBooleanArr(b, n);

        return inner_multiply();
    }

    private static int inner_multiply() {

        if (n == 1) {
            return booleanArrToInt(booleansArrMultiply(x, y));
        }

        xl = new boolean[n - (n / 2)];
        xr = new boolean[n / 2];
        System.arraycopy(x, 0, xl, 0, n - (n / 2));
        System.arraycopy(x, n - (n / 2), xr, 0, n / 2);

        yl = new boolean[n - (n / 2)];
        yr = new boolean[n / 2];
        System.arraycopy(y, 0, yl, 0, n - (n / 2));
        System.arraycopy(y, n - (n / 2), yr, 0, n / 2);

        boolean[] p1 = booleansArrMultiply(xl, yl);
        boolean[] p2 = booleansArrMultiply(xr, yr);
        boolean[] p3 = booleansArrMultiply(booleanArrSum(xl, xr), booleanArrSum(yl, yr));

        return booleanArrToInt(p1) * (int) Math.pow(2, 2 * (n / 2))
                + (booleanArrToInt(p3) - booleanArrToInt(p1) - booleanArrToInt(p2)) * (int) Math.pow(2, (n/2))
                + booleanArrToInt(p2);
    }

    private static boolean[] booleansArrMultiply(boolean[] a, boolean[] b) {
        int res = 0;

        if (a.length < b.length) {
            for (int i = a.length - 1; i >= 0; i--) {
                boolean[] test = new boolean[b.length];
                int k = (a.length - 1 - i);
                System.arraycopy(b, 0, test, 0, b.length);

                for (int j = 0; j < test.length; j++) test[j] = test[j] & a[i];
                res |= booleanArrToInt(test) << k;
            }
        } else {
            for (int i = b.length - 1; i >= 0; i--) {
                boolean[] test = new boolean[a.length];
                int k = (b.length - 1 - i);
                System.arraycopy(a, 0, test, 0, a.length);

                for (int j = 0; j < test.length; j++) test[j] = test[j] & b[i];
                res |= booleanArrToInt(test) << k;
            }
        }

        return intToBooleanArr(res, Integer.toBinaryString(res).length());
    }

    private static boolean[] booleanArrSum(boolean[] a, boolean[] b) {
        int res = booleanArrToInt(a) + booleanArrToInt(b);
        return intToBooleanArr(res, Integer.toBinaryString(res).length());
    }

    private static int booleanArrToInt(boolean[] a) {
        int sum = 0;
        for (boolean b : a) {
            sum = (sum << 1) + (b ? 1 : 0);
        }

        return sum;
    }

    private static boolean[] intToBooleanArr(int a, int size){
        boolean[] res = new boolean[size];

        for (int i = res.length - 1; i >= 0; i--) {
            res[res.length - 1 - i] = (a & (1 << i)) != 0;
        }

        return res;
    }
}

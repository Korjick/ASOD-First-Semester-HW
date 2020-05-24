package Karatsuba;

import Queue.Queue;

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

        String aBinary = Integer.toBinaryString(a), bBinary = Integer.toBinaryString(b);
        n = Math.max(aBinary.length(), bBinary.length());

        x = new boolean[n];
        y = new boolean[n];

        int k = 0;
        for (int i = aBinary.length() - 1; i >= 0; i--) {
            x[x.length - 1 - k] = aBinary.charAt(i) == '1';
            k++;
        }

        k = 0;
        for (int i = bBinary.length() - 1; i >= 0; i--) {
            y[y.length - 1 - k] = bBinary.charAt(i) == '1';
            k++;
        }

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

    public static boolean[] booleansArrMultiply(boolean[] a, boolean[] b) {
        ArrayList<Integer> arr = new ArrayList<>(2 * n);
        for (int i = 0; i < 2 * n; i++) arr.add(0);

        if (a.length < b.length) {
            for (int i = a.length - 1; i >= 0; i--) {
                boolean[] test = new boolean[b.length];
                int k = arr.size() - 1 - (a.length - 1 - i);
                System.arraycopy(b, 0, test, 0, b.length);

                for (int j = 0; j < test.length; j++) test[j] = test[j] & a[i];
                for (int j = test.length - 1; j >= 0; j--) {
                    if(test[j]) arr.set(k, arr.get(k) + 1);
                    k--;
                }
            }
        } else {
            for (int i = b.length - 1; i >= 0; i--) {
                boolean[] test = new boolean[a.length];
                int k = arr.size() - 1 - (b.length - 1 - i);
                System.arraycopy(a, 0, test, 0, a.length);

                for (int j = 0; j < test.length; j++) test[j] = test[j] & b[i];
                for (int j = test.length - 1; j >= 0; j--) {
                    if(test[j]) arr.set(k, arr.get(k) + 1);
                    k--;
                }
            }
        }

        for (int i = arr.size() - 1; i > 0; i--) {
            if(arr.get(i) > 1){
                int k = arr.get(i);
                arr.set(i, k % 2);
                arr.set(i - 1, arr.get(i - 1) + (k / 2));
            }
        }

        int idx = arr.indexOf(1);
        boolean[] result = new boolean[arr.size() - idx];
        for (int i = idx; i < arr.size(); i++) {
            result[i - idx] = arr.get(i) == 1;
        }
        return result;
    }

    public static boolean[] booleanArrSum(boolean[] a, boolean[] b) {
        return intToBooleanArr(booleanArrToInt(a) + booleanArrToInt(b));
    }

    public static int booleanArrToInt(boolean[] a) {
        int sum = 0, k = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i]) sum = sum + (int) Math.pow(2, k);
            k++;
        }
        return sum;
    }

    private static boolean[] intToBooleanArr(int a){
        String aBinary = Integer.toBinaryString(a);

        boolean[] res = new boolean[aBinary.length()];

        int k = 0;
        for (int i = aBinary.length() - 1; i >= 0; i--) {
            res[res.length - 1 - k] = aBinary.charAt(i) == '1';
            k++;
        }

        return res;
    }
}

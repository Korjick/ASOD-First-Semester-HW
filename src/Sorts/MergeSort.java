package Sorts;

public class MergeSort {

    public static int[] mergeSortRec(int[] a, int left, int right) {
        if (left + 1 >= right)
            return a;

        int mid = (left + right) / 2;
        mergeSortRec(a, left, mid);
        mergeSortRec(a, mid, right);
        a = merge(a, left, mid, right);

        return a;
    }

    public static int[] mergeSortIter(int[] a) {
        for (int i = 1; i < a.length; i *= 2)
            for (int j = 0; j < a.length - i; j += 2 * i)
                a = merge(a, j, j + i, Math.min(j + 2 * i, a.length));

        return a;
    }

    private static int[] merge(int[] a, int left, int mid, int right) {
        int i1 = 0, i2 = 0;
        int[] result = new int[right - left + 1];

        while (left + i1 < mid && mid + i2 < right) {
            if (a[left + i1] < a[mid + i2]) {
                result[i1 + i2] = a[left + i1];
                i1 += 1;
            } else {
                result[i1 + i2] = a[mid + i2];
                i2 += 1;
            }
        }

        while (left + i1 < mid) {
            result[i1 + i2] = a[left + i1];
            i1 += 1;
        }

        while (mid + i2 < right) {
            result[i1 + i2] = a[mid + i2];
            i2 += 1;
        }

        for (int i = 0; i < i1 + i2; i++)
            a[left + i] = result[i];

        return a;
    }
}

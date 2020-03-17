package Sets;

import java.util.HashMap;
import java.util.Map;

public class MultiSet<T> {
    private T[] arr;
    private final int BASE_CAPACITY = 10;
    private final float ARRAY_EXPANSION = 1.5f;
    private int HEAD;

    private HashMap<T, Integer> count;

    public MultiSet() {
        arr = (T[]) new Object[BASE_CAPACITY];
        count = new HashMap<>();
        HEAD = 0;
    }

    public MultiSet(int length) {
        arr = (T[]) new Object[length];
        count = new HashMap<>();
        HEAD = 0;
    }

    public void add(T el) {
        if (HEAD == arr.length) arr = positiveArrayResize();
        for (int i = 0; i < HEAD; i++) {
            if (arr[i].equals(el)){
                increaseCount(count, el);
                return;
            }
        }
        arr[HEAD] = el;
        count.put(el, 1);
        HEAD++;
    }

    public boolean contains(T el){
        for (int i = 0; i < HEAD; i++) {
            if(arr[i].equals(el)) return true;
        }
        return false;
    }

    public int size(){
        int total = 0;
        for (Map.Entry<T, Integer> el:
                count.entrySet()) {
            total += el.getValue();
        }
        return total;
    }

    public void delete(T el){
        for (int i = 0; i < HEAD; i++) {
            if(arr[i] == el){
                arr = negativeArrayResize(i);
                HEAD--;
                count.remove(el);
            }
        }
    }

    public MultiSet merge(MultiSet multiSet){

        MultiSet returnMultiSet = new MultiSet(arr.length);
        for (int i = 0; i < HEAD; i++) {
            returnMultiSet.add(arr[i]);
            returnMultiSet.count.put(arr[i], count.get(arr[i]));
        }

        boolean check;
        for (int i = 0; i < multiSet.arr.length; i++) {
            check = false;
            for (int j = 0; j < HEAD; j++) {
                if(arr[j].equals(multiSet.arr[i])) check = true;
            }
            if(!check){
                returnMultiSet.add(multiSet.arr[i]);
                returnMultiSet.count.put(multiSet.arr[i], multiSet.count.get(multiSet.arr[i]));
            }
        }

        return returnMultiSet;
    }

    private void increaseCount(HashMap<T, Integer> map, T key) {
        int returnCount = map.get(key);
        map.put(key, returnCount + 1);
    }

    private T[] negativeArrayResize(int i) {
        T[] returnArray = (T[]) new Object[arr.length - 1];
        System.arraycopy(arr, 0, returnArray, 0, i);
        System.arraycopy(arr, i, returnArray, i+1, HEAD - i);
        return returnArray;
    }

    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int) (arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, 0, returnArray, 0, HEAD);
        return returnArray;
    }

    public Object[] getArray() {
        T[] returnArray = (T[]) new Object[size()];
        int i = 0;
        for (Map.Entry<T, Integer> el:
                count.entrySet()) {
            for (int j = 0; j < el.getValue(); j++) {
                returnArray[i] = el.getKey();
                i++;
            }
        }
        return returnArray;
    }
}

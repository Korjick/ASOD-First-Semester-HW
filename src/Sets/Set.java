package Sets;

public class Set<T> {
    private T[] arr;
    private final int BASE_CAPACITY = 10;
    private final float ARRAY_EXPANSION = 1.5f;
    private int HEAD;

    public Set(){
        arr = (T[]) new Object[BASE_CAPACITY];
        HEAD = 0;
    }

    public Set(int length){
        arr = (T[]) new Object[length];
        HEAD = 0;
    }

    public boolean add(T el){
        if(HEAD == arr.length) arr = positiveArrayResize();
        for (int i = 0; i < HEAD; i++) {
            if(arr[i].equals(el)) return false;
        }
        arr[HEAD] = el;
        HEAD++;
        return true;
    }

    public boolean contains(T el){
        for (int i = 0; i < HEAD; i++) {
            if(arr[i].equals(el)) return true;
        }
        return false;
    }

    public int size(){
        return HEAD;
    }

    public void delete(T el){
        for (int i = 0; i < HEAD; i++) {
            if(arr[i] == el){
                arr = negativeArrayResize(i);
                HEAD--;
            }
        }
    }

    public Set merge(Set set){
        T[] returnArray = (T[]) set.getArray();
        Set returnSet = new Set(arr.length);
        for (int i = 0; i < HEAD; i++) {
            returnSet.add(arr[i]);
        }

        boolean check;
        for (int i = 0; i < returnArray.length; i++) {
            check = false;
            for (int j = 0; j < HEAD; j++) {
                if(arr[j].equals(returnArray[i])) check = true;
            }
            if(!check) returnSet.add(returnArray[i]);
        }

        return returnSet;
    }

    private T[] negativeArrayResize(int i) {
        T[] returnArray = (T[]) new Object[arr.length - 1];
        System.arraycopy(arr, 0, returnArray, 0, i);
        System.arraycopy(arr, i, returnArray, i+1, HEAD - i);
        return returnArray;
    }

    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int)(arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, 0, returnArray, 0, HEAD);
        return returnArray;
    }

    public Object[] getArray() {
        T[] returnArray = (T[]) new Object[HEAD];
        System.arraycopy(arr, 0, returnArray, 0, HEAD);
        return returnArray;
    }
}

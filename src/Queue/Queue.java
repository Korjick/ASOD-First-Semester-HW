package Queue;

import java.util.EmptyStackException;

public class Queue<T> {
    private T[] arr;
    private final int BASE_CAPACITY = 10;
    private final float ARRAY_EXPANSION = 1.5f;
    private int elementCount;
    private int HEAD;

    public Queue(){
        arr = (T[]) new Object[BASE_CAPACITY];
        elementCount = 0;
        HEAD = 0;
    }

    public Queue(int length){
        arr = (T[]) new Object[length];
        elementCount = 0;
        HEAD = 0;
    }

    public void add(T num) {
        if(elementCount == arr.length) arr = positiveArrayResize();
        arr[elementCount] = num;
        elementCount++;
    }

    public T pop() throws EmptyStackException {
        if(elementCount < HEAD) throw new EmptyStackException();
        if(HEAD == (int) (elementCount / ARRAY_EXPANSION)) arr = negativeArrayResize();
        T returned = peek();
        HEAD++;
        return returned;
    }

    public T peek() {
        return arr[HEAD];
    }

    public int size() {
        return elementCount - HEAD;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean contains(T num) {
        for (int i = HEAD; i < elementCount; i++) if (num.equals(arr[i])) return true;
        return false;
    }

    public Object[] getArray() {
        T[] returnArray = (T[]) new Object[elementCount - HEAD];
        System.arraycopy(arr, HEAD, returnArray, 0, elementCount - HEAD);
        return returnArray;
    }

    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int)(arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, HEAD, returnArray, 0, elementCount - HEAD);
        return returnArray;
    }

    private T[] negativeArrayResize() {
        T[] returnArray = (T[]) new Object[arr.length];
        System.arraycopy(arr, HEAD, returnArray, 0, elementCount - HEAD);
        elementCount -= HEAD;
        HEAD = 0;
        return returnArray;
    }
}
package Stack;

import java.util.EmptyStackException;

public class Stack<T> {

    private final int BASE_CAPACITY = 10;
    private final float ARRAY_EXPANSION = 1.5f;
    private T[] arr;
    private int elementCount;

    public Stack() {
        arr = (T[]) new Object[BASE_CAPACITY];
        elementCount = 0;
    }

    public Stack(int length) {
        arr = (T[]) new Object[length];
        elementCount = 0;
    }

    public void add(T num) {
        if(elementCount == arr.length) arr = positiveArrayResize();
        arr[elementCount] = num;
        elementCount++;
    }

    public T pop() throws EmptyStackException{
        if(elementCount == 0) throw new EmptyStackException();
        T returned = peek();
        elementCount--;
        return returned;
    }

    public T peek() {
        return arr[elementCount - 1];
    }

    public int size() {
        return elementCount;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean contains(T num) {
        for (int i = 0; i < elementCount; i++) if (num.equals(arr[i])) return true;
        return false;
    }

    public Object[] getArray() {
        T[] returnArray = (T[]) new Object[elementCount];
        System.arraycopy(arr, 0, returnArray, 0, elementCount);
        return returnArray;
    }

    private T[] positiveArrayResize() {
        T[] returnArray = (T[]) new Object[(int)(arr.length * ARRAY_EXPANSION)];
        System.arraycopy(arr, 0, returnArray, 0, elementCount);
        return returnArray;
    }
}

package LinkedList;

public class LinkedList<T> {

    private class Element {
        private T value;
        private Element next;

        public Element(T value) {
            this.value = value;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public Element getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }

    private Element first, last;
    private int elementCount;

    public LinkedList() {
        elementCount = 0;
    }

    public boolean add(T t) {
        Element el = new Element(t);
        if (elementCount == 0) {
            first = el;
            last = first;
        } else {
            last.setNext(el);
            last = el;
        }
        elementCount++;
        return true;
    }

    public boolean addFirst(T t) {
        Element el = new Element(t);
        if (elementCount == 0) {
            first = el;
            last = first;
        } else {
            el.setNext(first);
            first = el;
        }
        elementCount++;
        return true;
    }

    public boolean addAfter(int k, T t) {
        if (k < 0 || k > elementCount) throw new IndexOutOfBoundsException();
        Element el = first;
        for (int i = 0; i < k; i++) el = el.getNext();

        Element swapEl = el.getNext();
        el.setNext(new Element(t));
        el.getNext().setNext(swapEl);

        if (swapEl == null) last = el.getNext();
        return true;
    }

    public T get(int k) {
        if (k < 0 || k > elementCount) throw new IndexOutOfBoundsException();
        Element el = first;
        for (int i = 0; i < k; i++) el = el.getNext();

        return el.getValue();
    }

    public void remove(int k) {
        if (k < 0 || k > elementCount) throw new IndexOutOfBoundsException();
        if(elementCount == 1){
            first = null;
            elementCount--;
            return;
        }

        Element el = first;
        for (int i = 0; i < k - 1; i++) el = el.getNext();

        el.setNext(el.getNext().getNext());
        if (el.getNext() == null) last = el;
        elementCount--;
    }

    public void remove(T delEl) {
        Element el = first;
        boolean check = false;

        if (el.getValue() == delEl) {
            first = el.getNext();
            elementCount--;
            return;
        }

        for (int i = 0; i < elementCount - 1; i++) {
            if (el.getNext().getValue() == delEl) {
                check = true;
                break;
            }
            el = el.getNext();
        }

        if (check) {
            el.setNext(el.getNext().getNext());
            elementCount--;
        }
        if (el.getNext() == null) last = el;
    }

    public LinkedList<T> merge(LinkedList<T> list) {
        LinkedList newList = new LinkedList();
        Element el = first;
        newList.add(el.getValue());
        for (int i = 0; i < elementCount - 1; i++) {
            el = el.getNext();
            newList.add(el.getValue());
        }

        el = list.first;
        newList.add(el.getValue());
        for (int i = 0; i < list.size() - 1; i++) {
            el = el.getNext();
            newList.add(el.getValue());
        }

        return newList;
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public T getLast() {
        return last.getValue();
    }

    public int size() {
        return elementCount;
    }

}

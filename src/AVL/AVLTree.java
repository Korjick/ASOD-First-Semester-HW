package AVL;

public class AVLTree {

    public Node insert(Node e, int k) {
        if (e == null) return new Node(k);
        if (k < e.getKey())
            e.setLeft(insert(e.getLeft(), k));
        else
            e.setRight(insert(e.getRight(), k));
        return balance(e);
    }

    public Node remove(Node e, int k) {
        if (e == null) return null;

        if (k < e.getKey())
            e.setLeft(remove(e.getLeft(), k));
        else if (k > e.getKey())
            e.setRight(remove(e.getRight(), k));
        else {
            Node q = e.getLeft();
            Node r = e.getRight();

            if (r == null) return q;
            Node min = findMin(r);
            min.setRight(removeMin(r));
            min.setLeft(q);
            return balance(min);
        }
        return balance(e);
    }

    public Node balance(Node e) {
        fixHeight(e);
        if (bFactor(e) == 2) {
            if (bFactor(e.getRight()) < 0)
                e.setRight(rotateRight(e.getRight()));
            return rotateLeft(e);
        }
        if (bFactor(e) == -2) {
            if (bFactor(e.getLeft()) > 0)
                e.setLeft(rotateLeft(e.getLeft()));
            return rotateRight(e);
        }
        return e;
    }

    private Node rotateLeft(Node e) {
        Node q = e.getRight();
        e.setRight(q.getLeft());
        q.setLeft(e);
        fixHeight(e);
        fixHeight(q);
        return q;
    }

    private Node rotateRight(Node e) {
        Node q = e.getLeft();
        e.setLeft(q.getRight());
        q.setRight(e);
        fixHeight(e);
        fixHeight(q);
        return q;
    }

    private int nodeHeight(Node e) {
        return e != null ? e.getHeight() : 0;
    }

    private int bFactor(Node e) {
        return nodeHeight(e.getRight()) - nodeHeight(e.getLeft());
    }

    private void fixHeight(Node e) {
        int hl = nodeHeight(e.getLeft());
        int hr = nodeHeight(e.getRight());
        e.setHeight(Math.max(hl, hr) + 1);
    }

    private Node findMin(Node e) {
        return e.getLeft() != null ? findMin(e.getLeft()) : e;
    }

    private Node removeMin(Node e) {
        if (e.getLeft() == null)
            return e.getRight();
        e.setLeft(removeMin(e.getLeft()));
        return balance(e);
    }
}

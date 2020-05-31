package AVL;

public class Node {
    private int key, height;
    private Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
        height = 1;
    }

    public Node insert(Node e, int k) {
        if (e == null) return new Node(k);
        if (k < e.key)
            e.left = insert(e.left, k);
        else
            e.right = insert(e.right, k);
        return balance(e);
    }

    public Node remove(Node e, int k) {
        if (e == null) return null;

        if (k < e.key)
            e.left = remove(e.left, k);
        else if (k > e.key)
            e.right = remove(e.right, k);
        else {
            Node q = e.left;
            Node r = e.right;

            if (r == null) return q;
            Node min = findMin(r);
            min.right = removeMin(r);
            min.left = q;
            return balance(min);
        }
        return balance(e);
    }

    public Node balance(Node e) {
        fixHeight(e);
        if (bFactor(e) == 2) {
            if (bFactor(e.right) < 0)
                e.right = rotateRight(e.right);
            return rotateLeft(e);
        }
        if (bFactor(e) == -2) {
            if (bFactor(e.left) > 0)
                e.left = rotateLeft(e.left);
            return rotateRight(e);
        }
        return e;
    }

    private Node rotateLeft(Node e) {
        Node q = e.right;
        e.right = q.left;
        q.left = e;
        fixHeight(e);
        fixHeight(q);
        return q;
    }

    private Node rotateRight(Node e) {
        Node q = e.left;
        e.left = q.right;
        q.right = e;
        fixHeight(e);
        fixHeight(q);
        return q;
    }

    private int nodeHeight(Node e) {
        return e != null ? e.height : 0;
    }

    private int bFactor(Node e) {
        return nodeHeight(e.right) - nodeHeight(e.left);
    }

    private void fixHeight(Node e) {
        int hl = nodeHeight(e.left);
        int hr = nodeHeight(e.right);
        this.height = Math.max(hl, hr) + 1;
    }

    private Node findMin(Node e) {
        return e.left != null ? findMin(e.left) : e;
    }

    private Node removeMin(Node e) {
        if (e.left == null)
            return e.right;
        e.left = removeMin(e.left);
        return balance(e);
    }
}

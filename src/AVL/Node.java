package AVL;

public class Node {
    private int key, height;
    private Node left, right;

    public Node(int key) {
        this.key = key;
        left = right = null;
        height = 1;
    }

    public int getHeight() {
        return height;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

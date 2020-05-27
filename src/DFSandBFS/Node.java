package DFSandBFS;

public class Node {
    private Node left, right;
    private int value;

    public Node(Node left, Node right, int value){
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }
}

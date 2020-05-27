package DFSandBFS;

public class Main {
    public static void main(String[] args) {
        Node n4 = new Node(null, null, 4);
        Node n5 = new Node(null, null, 5);
        Node n6 = new Node(null, null, 6);
        Node n7 = new Node(null, null, 7);

        Node n2 = new Node(n4, n5, 2);
        Node n3 = new Node(n6, n7, 3);
        Node n1 = new Node(n2, n3, 1);

        Graph.dfs(n1);
        Graph.bfs(n1);
    }
}

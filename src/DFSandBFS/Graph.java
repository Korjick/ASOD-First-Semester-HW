package DFSandBFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class Graph {
    public static void dfs(Node node) {
        if(node == null) return;
        System.out.print(node.getValue() + " ");

        dfs(node.getLeft());
        dfs(node.getRight());
    }

    public static void bfs(Node node){
        if(node == null) return;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node e = queue.remove();
            System.out.print(e.getValue() + " ");
            if(e.getLeft() != null) queue.add(e.getLeft());
            if(e.getRight() != null) queue.add(e.getRight());
        }
    }
}

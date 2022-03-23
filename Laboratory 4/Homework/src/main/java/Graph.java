import java.util.*;
import java.lang.*;

/**
 * implements a <code>Graph</code>-like structure
 */
public class Graph {
    private final int vertexNumber;
    public Edge[] edge;

    /**
     * creates a graph with <code>vertices</code> vertices and <code>edges</code> edges
     *
     * @param vertices number of vertices
     * @param edges number of edges
     */
    public Graph(int vertices, int edges) {
        this.vertexNumber = vertices;
        this.edge = new Edge[edges];
        for (int i = 0; i < edges; ++i)
            this.edge[i] = new Edge();
    }

    public int findRoot(vertexSubset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent
                    = findRoot(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    /**
     * does union between sets based on ranks
     */
    public void setUnion(vertexSubset[] vertexSubsets, int firstVertex, int secondVertex) {
        int firstRoot = findRoot(vertexSubsets, firstVertex);
        int secondRoot = findRoot(vertexSubsets, secondVertex);

        if (vertexSubsets[firstRoot].rank < vertexSubsets[secondRoot].rank)
            vertexSubsets[firstRoot].parent = secondRoot;
        else if (vertexSubsets[firstRoot].rank > vertexSubsets[secondRoot].rank)
            vertexSubsets[secondRoot].parent = firstRoot;
        else {
            vertexSubsets[secondRoot].parent = firstRoot;
            vertexSubsets[firstRoot].rank++;
        }
    }

    /**
     * main function to construct MST
     * <code>result</code> will store resultant MST
     * <code>int e</code> is an index variable used for <code>result</code>
     * will sort edges non-decreasingly by weight
     */
    public void KruskalMST() {
        Edge[] result = new Edge[vertexNumber];
        int e = 0;

        for (int i = 0; i < vertexNumber; i++)
            result[i] = new Edge();

        Arrays.sort(edge);

        vertexSubset[] vertexSubsets = new vertexSubset[vertexNumber];
        for (int i = 0; i < vertexNumber; ++i)
            vertexSubsets[i] = new vertexSubset();

        for (int v = 0; v < vertexNumber; ++v) {
            vertexSubsets[v].parent = v;
            vertexSubsets[v].rank = 0;
        }

        int i = 0;
        while (e < vertexNumber/2) {
            Edge nextEdge = edge[i++];
            int firstRoot = findRoot(vertexSubsets, nextEdge.src);
            int secondRoot = findRoot(vertexSubsets, nextEdge.dest);
            if (firstRoot != secondRoot) {
                result[e++] = nextEdge;
                setUnion(vertexSubsets, firstRoot, secondRoot);
            }
        }

        // print MST and make it look nice
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -> "
                    + result[i].dest
                    + " : " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }

    /**
     * represents a <code>Graph</code>'s edge
     */
    public static class Edge implements Comparable<Edge> {
        public int src;
        public int dest;
        public int weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    /**
     * represents a subset of vertices
     */
    static class vertexSubset {
        int parent, rank;
    }

}
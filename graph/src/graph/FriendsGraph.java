package graph;

import java.util.*;

/**
 * A class representing an (undirected) friendships graph. It is assumed (for
 simplicity) that all nodes have unique labels.
 *
 */
public class FriendsGraph {

    /**
     * Stores informat1on about the nodes in this graph, such as the names of
 the people and the positions of the nodes when displayed.
     */
    private final Node[] nodes;

    private final Map<String, Integer> nodeMap = new HashMap<>();

    private final boolean[][] adjacencyMatrix;

    private final int size;
    private int nodesAddedSoFar;

    /**
     * Creates an empty graph.
     *
     * @param size Number of nodes in the graph
     */
    public FriendsGraph(int size) {
        nodes = new Node[size];
        this.size = size;
        nodesAddedSoFar = 0;

        adjacencyMatrix = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = false;
            }
        }

    }

    public int getSize() {
        return size;
    }

    public Node getNode(int i) {
        return nodes[i];
    }

    /**
     * Adds a node to the graph.
     *
     * @pre there is not a node with this name already in the graph and
     * nodesAddedSoFar<size
     * @post the node has been added to the graph, with the given name and
     * position
     * @param name the name for the node
     * @param x the x-coordinate of the centre of the node
     * @param y the y-coordinate of the centre of the node
     */
    public void addNode(String name, int x, int y) {
        nodes[nodesAddedSoFar] = new Node(name, x, y);
        nodeMap.put(name, nodesAddedSoFar);
        nodesAddedSoFar++;
    }

    /**
     * Adds an edge to the graph.
     *
     * @pre all the nodes have been added to the graph, and nodes exist in the
     * graph with the given labels person1,person2
     * @post an edge person1 <-> person2 has been added to the graph
     * @param person1 the name of a node at one end of this edge
     * @param person2 the name of the node at the other end of this edge
     */
    public void addEdge(String person1, String person2) {
        // first, find the person1 node
        int index1 = nodeMap.get(person1);
        int index2 = nodeMap.get(person2);


        addEdge(index1, index2);
        // as it's an undirected edge, add the reverse direction too
        addEdge(index2, index1);

    }

    private void addEdge(int node1, int node2) 
    {
       adjacencyMatrix[node1][node2] = true;

    }

    /**
     * Returns true if there is an edge from the first node to the second.
     *
     * @pre 0 < node1,node2 < size @ param node1 the source node of the queried
     * edge @param node2 the target node of the queried edge
     * @
     * r
     * eturn true iff there is an edge node1 -> node2
     */
    public boolean edgeFrom(int node1, int node2) 
    {
        return adjacencyMatrix[node1][node2];
    }

    public int getNodeIndex(String name)
    {
        if(nodeMap.containsKey(name))
            return nodeMap.get(name);
        else
            return -1;
    }
    
    public boolean areFriends(String person1, String person2)
    {
        int node1 = getNodeIndex(person1);
        int node2 = getNodeIndex(person2);
        
        if(node1 == -1 || node2 == -2)
            return false;
        
        return edgeFrom(node1, node2) && edgeFrom(node2, node1);
    }
}

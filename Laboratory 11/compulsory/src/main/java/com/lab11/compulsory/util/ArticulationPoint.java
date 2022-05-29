package com.lab11.compulsory.util;

import java.util.*;

public class ArticulationPoint {

    int time = 0;
    List<Integer> articulationResult = new ArrayList<>();

    public List<Integer> findArticulationPoints(Graph graph) {
        int vertices = graph.vertices;

        Map<Integer, Integer> visitTime = new HashMap<>();
        Map<Integer, Integer> lowTime = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        List<Boolean> visited = new ArrayList<>();
        for (int index = 0; index < vertices; index++)
            visited.add(false);

        for (int i = 0; i < vertices; i++) {
            if (!visited.get(i)) DFS(graph, i, visitTime, lowTime, visited, parent);
        }

        if (articulationResult.size() > 0) {
            return articulationResult;
        } else {
            return null;
        }

    }

    public void DFS(Graph graph, int currentVertex, Map<Integer, Integer> discoveryTime, Map<Integer, Integer> lowTime, List<Boolean> visited, Map<Integer, Integer> parent) {

        List<LinkedList<Integer>> adjList = graph.adjList;
        int childCount = 0;
        boolean canArticulationPoint = false;
        visited.set(currentVertex, true);
        discoveryTime.put(currentVertex, time);
        lowTime.put(currentVertex, time);
        time++;
        for (int i = 0; i < adjList.get(currentVertex).size(); i++) {
            int adjacentVertex = adjList.get(currentVertex).get(i);

            if (!visited.get(adjacentVertex)) {
                parent.put(adjacentVertex, currentVertex);
                childCount++;
                DFS(graph, adjacentVertex, discoveryTime, lowTime, visited, parent);

                if (discoveryTime.get(currentVertex) <= lowTime.get(adjacentVertex)) {
                    canArticulationPoint = true;
                } else {
                    int currLowTime = lowTime.get(currentVertex);
                    lowTime.put(currentVertex, Math.min(currLowTime, lowTime.get(adjacentVertex)));
                }
            } else {
                int currLowTime = lowTime.get(currentVertex);
                lowTime.put(currentVertex, Math.min(currLowTime, discoveryTime.get(adjacentVertex)));
            }
        }
        if ((parent.get(currentVertex) == null && childCount > 1) || (parent.get(currentVertex) != null && canArticulationPoint)) {
            articulationResult.add(currentVertex);
        }
    }

    public static class Graph {
        int vertices;
        List<LinkedList<Integer>> adjList = new ArrayList<>();

        public Graph(int vertices) {
            this.vertices = vertices;
            for (int i = 0; i < vertices; i++) {
                adjList.add(new LinkedList<>());
            }
        }

        public void addEdge(int source, int destination) {
            adjList.get(source).addFirst(destination);
        }
    }
}
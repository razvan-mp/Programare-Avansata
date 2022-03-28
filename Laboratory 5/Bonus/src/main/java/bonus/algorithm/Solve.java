package bonus.algorithm;

import bonus.model.base.Catalog;
import bonus.utilitary.Utils;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashSet;
import java.util.Set;

public class Solve {
    public static void solveProblem(Catalog catalog) {
        if (checkConnectedGraph(catalog)) {
            Graph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

            Set<String> firstPartition = new HashSet<>(catalog.getItemList().get(0).getIdentifiers());
            Set<String> secondParition = new HashSet<>(Utils.conceptList);

            for (var vertex : firstPartition) {
                graph.addVertex(vertex);
            }

            for (var vertex : secondParition) {
                graph.addVertex(vertex);
            }

            for (var item : catalog.getItemList())
                for (var concept : item.getConcepts())
                    graph.addEdge(item.getId(), concept);

            HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> matcher;
            matcher = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, firstPartition, secondParition);

            System.out.println(matcher.getMatching());
        }
        else {
            System.out.println("Graph won't be connected since not all concepts are present.");
        }
    }

    private static boolean checkConnectedGraph(Catalog catalog) {
        Set<String> existingConcepts = new HashSet<>();

        for (var item : catalog.getItemList()) {
            existingConcepts.addAll(item.getConcepts());
        }

        Utils.conceptList.forEach(existingConcepts::remove);
        return existingConcepts.isEmpty();
    }
}

/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import java.util.*;

public class Network {
    private final List<Node> nodeList;

    public Network() {
        this.nodeList = new ArrayList<>();
    }

    public String addNode(Node v) {
        for (Node node : nodeList) {
            if (node.name.equals(v.getName()))
                return "There already is a node with the name: " + node.name + "! Node will not be added.";
            if (node.address.equals(v.getAddress()))
                return "There already is a node with the same MAC address! Node will not be added.";
            if (node.getIP() != null)
                if (node.getIP().equals(v.getIP()))
                    return "There already is a node with the same IP address! Node will not be added.";
        }
        this.nodeList.add(v);
        return "Node " + v.getName() + " added successfully.";
    }

    public void sortByAddress() {
        nodeList.sort((o1, o2) -> {
            if (o1.getIP() != null && o2.getIP() != null)
                return o1.getIP().compareTo(o2.getIP());
            return (Objects.equals(o1.getIP(), o2.getIP())) ? 0 : (o1.getIP() == null ? 1 : -1);
        });
    }

    public void displayIdentifiable() {
        // sort nodeList such that nulls are last and the list is ascending
        sortByAddress();

        int index = 0;
        while (nodeList.get(index).getIP() != null && index < nodeList.size() - 1) {
            System.out.println(nodeList.get(index).getName() + " -> " + nodeList.get(index).getIP());
            index++;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        nodeList.sort(Comparator.comparing(Node::getName));

        StringBuilder sb = new StringBuilder();
        sb.append("\nNetwork and time costs: \n");
        for (Node node : nodeList) {
            for (Map.Entry<String, Integer> entry : node.timeCost.entrySet()) {
                sb.append(node.name).append(" -- ").append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
            }
        }
        return sb.toString();
    }

    private void printSolution(int[] dist, int src) {
        System.out.println("Node\t\tDistance from v" + (src + 1));
        for (int index = 0; index < nodeList.size(); index++) {
            String key = "v" + (index + 1);
            if (nodeList.get(index).getClass().isInstance(new Computer()) || nodeList.get(index).getClass().isInstance(new Router()))
                    System.out.println(key + "\t\t\t" + dist[index]);
        }
    }

    private int minDistance(int[] dist, Boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int index = 0; index < nodeList.size(); index++) {
            if (!sptSet[index] && dist[index] <= min) {
                min = dist[index];
                minIndex = index;
            }
        }
        return minIndex;
    }

    private void dijkstraHelper(int[][] graph, int src) {
        int[] dist = new int[nodeList.size()];
        Boolean[] sptSet = new Boolean[nodeList.size()];

        for (int i = 0; i < nodeList.size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < nodeList.size() - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < nodeList.size(); v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        for (int index = 0; index < dist.length; index++) {
            if (dist[index] == Integer.MAX_VALUE)
                dist[index] = -1;
        }
        printSolution(dist, src);
    }

    public void dijkstra() {
        int[][] adjacency = new int[nodeList.size()][nodeList.size()];

        // init matrix
        for (int i = 0; i < nodeList.size(); i++) {
            Arrays.fill(adjacency[i], 0);
        }

        for (int index = 0; index < nodeList.size(); index++) {
            var costs = nodeList.get(index).timeCost;
            for (var pair : costs.entrySet()) {
                adjacency[index][getIndex(pair.getKey())] = pair.getValue();
            }
        }
        System.out.println("Adjacency matrix: ");
        printAdjMatrix(adjacency);

        for (Node node : nodeList)
            if (node.getClass().isInstance(new Computer()) || node.getClass().isInstance(new Router()))
                dijkstraHelper(adjacency, nodeList.indexOf(node));
    }

    private void printAdjMatrix(int[][] adjacency) {
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                System.out.printf("%-4d ", adjacency[i][j]);
            }
            System.out.println();
        }
    }

    private int getIndex(String name) {
        for (var node : this.nodeList) {
            if (name.equals(node.getName())) {
                return nodeList.indexOf(node);
            }
        }
        return -1;
    }
}

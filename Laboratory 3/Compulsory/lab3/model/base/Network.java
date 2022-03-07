/**
 * @author Răzvan Morcov-Pahoncea
 * @version
 */

package lab3.model.base;

import java.util.ArrayList;
import java.util.List;

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
            if (node.getIP() != null && !node.getIP().equals("no IP"))
                if (node.getIP().equals(v.getIP()))
                    return "There already is a node with the same IP address! Node will not be added.";
        }
        this.nodeList.add(v);
        return "Node " + v.getName() + " added successfully.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNetwork: \n");
        for (Node node : nodeList) {
            sb.append("Name: ").append(node.getName()).append(" | Location: ").append(node.getLocation());
            sb.append(" | Address: ").append(node.getAddress()).append(" | IP: ").append(node.getIP());
            sb.append(" | Storage: ").append(node.getStorage()).append("\n");
        }
        return sb.toString();
    }
}

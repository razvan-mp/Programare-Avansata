import db.UserDAO;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PropertyGenerator {
    public static void generate() throws SQLException {
        SimpleGraph<Integer, DefaultEdge> graph = new SimpleGraph(DefaultEdge.class);

        List<List<Integer>> friendships = UserDAO.getFriendships();

        for (int index = 0; index < Objects.requireNonNull(friendships).size(); index++) {
            graph.addVertex(index + 1);
        }

        for (int index_1 = 0; index_1 < friendships.size(); index_1++) {
            for (int index_2 = 0; index_2 < friendships.get(index_1).size(); index_2++) {
                graph.addEdge(index_1 + 1, friendships.get(index_1).get(index_2) + 1);
            }
        }

        BronKerboschCliqueFinder<Integer, DefaultEdge> bronKerboschCliqueFinder = new BronKerboschCliqueFinder<>(graph);
        Iterator<Set<Integer>> friendGroups = bronKerboschCliqueFinder.getAllMaximalCliques().iterator();

        System.out.println("The social network has the following groups of friends: ");
        while (friendGroups.hasNext()) {
            Set<Integer> friendGroup = friendGroups.next();
            Iterator<Integer> friendIterator = friendGroup.iterator();
            System.out.print("Group of " + friendGroup.size() + ": ");
            while (friendIterator.hasNext())
                System.out.print(UserDAO.findById(friendIterator.next()) + " ");
            System.out.println();
        }

        System.out.println();

        double numberOfUsers = graph.vertexSet().size();
        double friendshipNumber = graph.edgeSet().size();
        double density = (friendshipNumber / ((numberOfUsers * (numberOfUsers - 1)) / 2)) * 100;

        System.out.println("The social network's density is: " + density + "%");
        System.out.println();

        for (int index_1 = 0; index_1 < friendships.size(); index_1++)
            System.out.println("User " + UserDAO.findById(index_1 + 1) + " has " + graph.degreeOf(index_1 + 1) + " friends.");
    }
}

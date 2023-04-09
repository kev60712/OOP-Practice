import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class RelationshipGraphImpl implements RelationshipGrpah{

    private Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    /** Example Relationship.txt
     A: B C D
     B: A D E
     C: A E G K M
     D: A B K P
     E: B C J K L
     F: Z
     Q: S
     */
    public RelationshipGraphImpl(String script) {
        String[] line = script.split("\n");
        for (String s : line) {
            String[] str = s.split(": ");
            String name1 = str[0];
            String[] friends = str[1].split(" ");
            for (String friend : friends) {
                graph.addVertex(name1);
                graph.addVertex(friend);
                graph.addEdge(name1, friend);
            }
        }
        System.out.println(graph.toString());
    }

    @Override
    public boolean hasConnection(String name1, String name2) {
        // Use DijkstraShortestPath to find the shortest path
        DijkstraShortestPath<String, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> path = dijkstraShortestPath.getPath(name1, name2);
        if (path == null) { // No path found
            return false;
        }
        System.out.println("Path from " + name1 + " to " + name2 + ": " + path.getVertexList());
        return true;
    }
}

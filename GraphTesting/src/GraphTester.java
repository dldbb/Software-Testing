import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.Set;
import org.junit.Test;
public class GraphTester {
    // tests for method "addEdge" in class "Graph"
    @Test public void tae0() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    @Test public void tae1() {
        Graph g = new Graph(2);
        g.addEdge(-1, 1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, false], [false, false]]");
    }

    @Test public void tae2() {
        Graph g = new Graph(2);
        g.addEdge(0, -1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, false], [false, false]]");
    }

    @Test public void tae3() {
        Graph g = new Graph(2);
        g.addEdge(2, 1);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, false], [false, false]]");
    }

    @Test public void tae4() {
        Graph g = new Graph(2);
        g.addEdge(0, 3);
        System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, false], [false, false]]");
    }
    // your tests for method "addEdge" in class "Graph" go here
    // you must provide at least 4 test methods;
    // each test method has at least 1 invocation of addEdge;
    // each test method creates exactly 1 graph
    // each test method creates a unique graph w.r.t. "equals" method
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    // implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework
    // ...
    // tests for method "reachable" in class "Graph"
    @Test public void tr0() {
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }
    //0 -> 0

    @Test public void tr1() {
        Graph g = new Graph(2);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        Set<Integer> targets = new TreeSet<>();
        targets.add(1);
        assertTrue(!g.reachable(nodes, targets));
    }
    //0 & 1

    @Test public void tr2() {
        Graph g = new Graph(2);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        Set<Integer> targets = new TreeSet<>();
        targets.add(1);
        g.addEdge(0, 1);
        assertTrue(g.reachable(nodes, targets));
    }
    //0 -> 1

    @Test public void tr3() {
        Graph g = new Graph(2);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        Set<Integer> targets = new TreeSet<>();
        targets.add(1);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        assertTrue(g.reachable(nodes, targets));
    }
    //0 -> 1 -> 0

    @Test public void tr4() {
        Graph g = new Graph(2);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(-1);
        Set<Integer> targets = new TreeSet<>();
        targets.add(1);
        assertTrue(!g.reachable(nodes, targets));
    }
    //-1 & 1

    @Test public void tr5() {
        Graph g = new Graph(2);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        Set<Integer> targets = new TreeSet<>();
        targets.add(2);
        assertTrue(!g.reachable(nodes, targets));
    }
    //0 & 2

    @Test public void tr6() {
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        Set<Integer> targets = new TreeSet<>();
        assertTrue(g.reachable(nodes, targets));
    }
    //0 & null

    // your tests for method "reachable" in class "Graph" go here
    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your
    // implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework
    // ...
}

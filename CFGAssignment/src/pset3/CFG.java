package pset3;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;

import java.util.*;
//import com.sun.org.apache.bcel.classfile.JavaClass;
//import org.apache.bcel.classfile.Method;
public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();
    public static class Node {
        int position;
        Method method;
        JavaClass clazz;
        Node(int p, Method m, JavaClass c) {
            position = p;
            method = m;
            clazz = c;
        }
        public Method getMethod() {
            return method;
        }
        public JavaClass getClazz() {
            return clazz;
        }
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node n = (Node)o;
            return (position == n.position) && method.equals(n.method) && clazz.equals(n.clazz);
        }
        public int hashCode() {
            return position + method.hashCode() + clazz.hashCode();
        }
        public String toString() {
            return clazz.getClassName() + '.' + method.getName() + method.getSignature() + ": " + position;
        }
    }

    public void addNode(int p, Method m, JavaClass c) {
        addNode(new Node(p, m, c));
    }
    private void addNode(Node n) {
        nodes.add(n);
        Set<Node> nbrs = edges.get(n);
        if (nbrs == null) {
            nbrs = new HashSet<Node>();
            edges.put(n, nbrs);
        }
    }
    public void addEdge(int p1, Method m1, JavaClass c1, int p2, Method m2, JavaClass c2) {
        Node n1 = new Node(p1, m1, c1);
        Node n2 = new Node(p2, m2, c2);
        addNode(n1);
        addNode(n2);
        Set<Node> nbrs = edges.get(n1);
        nbrs.add(n2);
        edges.put(n1, nbrs);
    }
    public void addEdge(int p1, int p2, Method m, JavaClass c) {
        addEdge(p1, m, c, p2, m, c);
    }
    public String toString() {
        return nodes.size() + " nodes\n" + "nodes: " + nodes + '\n' + "edges: " + edges;
    }
    public boolean isReachable(String methodFrom, String clazzFrom,
                               String methodTo, String clazzTo) {
        // you will implement this method in Question 2.2
        Set<Node> visited = new HashSet<Node>();
        boolean validArguments = checkArgumentsExist(methodFrom, clazzFrom, methodTo, clazzTo);
        if(!validArguments) return validArguments;
        Node start = getTargetNode(methodFrom, clazzFrom);
        if(start == null){
            return false;
        }
        Node target = getTargetNode(methodTo, clazzTo);
        if(target == null){
           return false;
        }

        boolean result = recursiveSearch(visited, start, target); //TODO
        return result;
    }
    private boolean checkArgumentsExist(String methodFrom, String clazzFrom,
                                        String methodTo, String clazzTo) {
        boolean methodFromExists = false;
        boolean methodToExists = false;
        boolean clazzFromExists = false;
        boolean clazzToExists = false;

        Iterator<Node> i1 = nodes.iterator();
        while(i1.hasNext()){
            Node n = i1.next();
            if(n.getMethod().getName().equals(methodFrom)) {
                methodFromExists = true;
                break;

            }
        }
        if(!methodFromExists) return false;

        Iterator<Node> i2 = nodes.iterator();
        while(i2.hasNext()){
            Node n = i2.next();
            if(n.getMethod().getName().equals(methodTo))  {
                methodToExists = true;
                break;
            }
        }
        if(!methodToExists) return false;

        Iterator<Node> i3 = nodes.iterator();
        while(i3.hasNext()){
            Node n = i3.next();
            if(n.getClazz().getClassName().equals(clazzFrom)){
                clazzFromExists = true;
                break;
            }
        }
        if(!clazzFromExists) return false;

        Iterator<Node> i4 = nodes.iterator();
        while(i4.hasNext()){
            Node n = i4.next();
            if(n.getClazz().getClassName().equals(clazzTo)){
                clazzToExists = true;
                break;
            }
        }
        if(!clazzToExists) return false;
        return true;
    }
    private Node getTargetNode(String methodTo, String clazzTo) {
        Node target = null;
        Iterator<Node> i2 = nodes.iterator();
        while(i2.hasNext()){
            Node n = i2.next();
            if(n.getMethod().getName().equals(methodTo) && n.getClazz().getClassName().equals(clazzTo) && n.position == 0){
                target = n;
                return target;
            }
        }
        return target;
    }
    private boolean recursiveSearch(Set<Node> visited, Node start, Node target){
        boolean result = false;
        if(visited == null){
            return false;
        }
        if(start == null){
            return false;
        }
        if(target == null){
            return false;
        }

        if(!visited.contains(start)){
            visited.add(start);
            String startMethodName = start.getMethod().getName();
            String targetMethodName = target.getMethod().getName();
            if(startMethodName.equals(targetMethodName)){
                return true;
            }
            Set<Node> neighbors = edges.get(start);
            Iterator<Node> i = neighbors.iterator();
            while(i.hasNext()){
                Node n = i.next();
                result = recursiveSearch(visited, n, target);
                if(result) return result;
            }
        }
        return result;
    }
}


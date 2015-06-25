package structures;

import java.util.List;
import java.util.ArrayList;


/**
 *VIOLATES LISKOV PRINCIPLE - any important property of a type should also hold for its subtype.
 *
 */
public class OpenNode<T extends Comparable<T> > extends Node<T>{
    
    private List<Node<T> > edges = new ArrayList<Node<T> >();
    public OpenNode(T item){
        super(item);
    }

    public void addNode(Node<T> node){
        edges.add(node);
    }

    public boolean hasNode(Node<T> node){
        /*for(Node<T> n : edges){
            if(n.equals(node))
                return true;
        }
        return false;*/
        return edges.contains(node);
    }
}


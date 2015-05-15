package structures; 

public class Node<T extends Comparable<T>>{
    private T item;
    private Node<T> left;
    private Node<T> right;

    /**
     * Constructor creates new node with null
     * childe nodes.
     */
    Node(T item){
        left = null;
        right = null;
        this.item = item;
    }

    public void setRight(Node<T> node){
        right = node;
    }
    
    public void setLeft(Node<T> node){
        left = node;
    }

    public void setItem(T item){
        this.item = item;
    }
    
    public T getItem(){
        return item;
    }

    public Node<T> getRight(){
        return right;
    }

    public Node<T> getLeft(){
        return left;
    }
    
    public String toString(){
        return getItem().toString();
    }
}

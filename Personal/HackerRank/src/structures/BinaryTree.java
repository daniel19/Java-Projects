package structures;

public class BinaryTree<T extends Comparable<T>>{
    Node<T> root;    
    
   public  BinaryTree(){
        root = null;
    }
   
   public void insert(T value, Node<T> root){
       if( root == null){
           this.root = new Node<T>(value);
       }else if(value.compareTo(root.getItem()) < 0){
           //less than
           if(root.getLeft() == null){
               root.setLeft(new Node<T>(value));
           }else{
               insert(value, root.getLeft());
           }

       }else if(value.compareTo(root.getItem()) > 0){
           //greater than
           if(root.getRight() == null){
               root.setRight(new Node<T>(value));
           }else{
               insert(value, root.getRight());
           }
       }else{
           //duplicate found ignore
       }
       
   }

   public void preorderPrint(Node<T> root){
       if(root != null){
           System.out.println(root.toString());
           preorderPrint(root.getLeft());
           preorderPrint(root.getRight());
       }
   } 
   
   public void postorderPrint(Node<T> root){
       if(root != null){
           postorderPrint(root.getLeft());
           postorderPrint(root.getRight());
           System.out.println(root.toString());
       }
   } 

   public void inorderPrint(Node<T> root){
       if(root != null){
           inorderPrint(root.getLeft());
           System.out.println(root.toString());
           inorderPrint(root.getRight());
       }
   }
   
   public Node<T> getRoot(){
       return root;
   }
}

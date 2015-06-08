package structures;

public class BinaryTree<T extends Comparable<T>>{
    private Node<T> root;    
  
   /**
    *Constructor of BinaryTree<T>.
    *Sets root to null
    */  
   public  BinaryTree(){
        root = null;
   }

   /**
    *Inserts new data if no duplicate is found. (recursive) 
    *Creates a new node for data at available places in the tree.
    *Operation begins at the root node.
    *@param T value - value to be inserted.
    *@param Node root - parent of new node value.
    */   
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
   
   /**
    * Searches tree for value starting at the root node. 
    * @param T value - object to find in tree.
    * @param Node root - parent node of tree.
    * @return boolean value if value is present or not in the tree.
    */
   public boolean searchTree(T value, Node<T> root){
       if(root != null){
           if(value.compareTo(root.getItem()) == 0){
               //value is in the tree
               return true;
            }else if (value.compareTo(root.getItem()) < 0){
               return searchTree(value, root.getLeft());
            }else{
               return searchTree(value, root.getRight());
            }    
       }
       return false;
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
    
   public Node<T> getLeftMostNode(Node<T> root){
       if(root != null){
           if(root.getLeft() != null){
               return getLeftMostNode(root.getLeft());
           }else{
               return root;
           }
       }
       return null;
   }
   
   public Node<T> getRightMostNode(Node<T> root){
       if(root != null){
           if(root.getRight() != null){
               return getRightMostNode(root.getRight());
           }else{
               return root;
           }
       }
       return null;
   }
}

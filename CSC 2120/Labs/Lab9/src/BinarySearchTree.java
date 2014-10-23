public class BinarySearchTree extends BinaryTreeBasis implements SearchTreeInterface
{ 
   private int size;

   public BinarySearchTree()
   {
      super();
   }

   public KeyedItem retrieve(Comparable searchKey) 
   {
       return retrieveItem(getRootNode(), searchKey);
   }    

   public void insert(KeyedItem item) throws TreeException
   {
      size++;
      setRootNode(insertItem(getRootNode(), item));
      assert validateBSTProperty() : "Not valid BST after " + item.getKey() + " inserted"; 
   }  

   public void delete(Comparable searchKey) throws TreeException 
   {
      size--;
      setRootNode(deleteItem(getRootNode(), searchKey));
      assert validateBSTProperty() : "Not valid BST after " + searchKey + " deleted."; 
   }  

   protected KeyedItem retrieveItem(TreeNode tNode, Comparable searchKey)
   {
      //disallow duplicates so that you always know which object to retrieve (or delete)
      //you could return a list with all duplicate search keys (but delete is still a problem)
      KeyedItem treeItem;

      if (tNode == null) 
      {
         treeItem = null;
      }
      else 
      {
         KeyedItem nodeItem = (KeyedItem) tNode.getItem();
         int comparison = searchKey.compareTo(nodeItem.getKey());

         if (comparison == 0) 
         {
            // item is in the root of some subtree
            treeItem = nodeItem;
         }
         else if (comparison < 0) 
         {
            // search the left subtree
            treeItem = retrieveItem(tNode.getLeft(), searchKey);
         }
         else  
         { 
            // search the right subtree
            treeItem = retrieveItem(tNode.getRight(), searchKey);
         }  
      }  
      //assert treeItem != null || !treeItem.getKey().equals(searchKey) : searchKey + " Not found."; 
      return treeItem;
   }  

   protected TreeNode insertItem(TreeNode tNode, KeyedItem item) throws TreeException
   {

      if (tNode == null) 
      {
         // position of insertion found; insert after leaf
         // create a new node
         tNode = new TreeNode(item);
         return tNode;
      }  

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = item.getKey().compareTo(nodeItem.getKey());

      // search for the insertion position
      if (comparison == 0)
      {
         throw new TreeException ("Cannot add duplicate.");
      }
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = insertItem(tNode.getLeft(), item);
         tNode.setLeft(subtree);
      }
      else 
      { 
         // search the right subtree
         subtree = insertItem(tNode.getRight(), item);
         tNode.setRight(subtree);
      }  

      return tNode;
   }  

   protected TreeNode deleteItem(TreeNode tNode, Comparable searchKey) throws TreeException
   {

      if (tNode == null) 
      {
         throw new TreeException("Item not found");
      }

      TreeNode subtree;
      KeyedItem nodeItem = (KeyedItem)tNode.getItem();
      int comparison = searchKey.compareTo(nodeItem.getKey());

      if (comparison == 0) 
      {
         // item is in the root of some subtree
         tNode = deleteNode(tNode);  // delete the item
      }
      // else search for the item
      else if (comparison < 0) 
      {
         // search the left subtree
         subtree = deleteItem(tNode.getLeft(), searchKey);
         tNode.setLeft(subtree);
      }
      else 
      { 
         // search the right subtree
         subtree = deleteItem(tNode.getRight(), searchKey);
         tNode.setRight(subtree);
      } 

      return tNode;
   }  

   protected TreeNode deleteNode(TreeNode tNode) 
   {
      // Algorithm note: There are four cases to consider:
      //   1. The tNode is a leaf.
      //   2. The tNode has no left child.
      //   3. The tNode has no right child.
      //   4. The tNode has two children.
      // Calls: findLeftmost and deleteLeftmost

      // test for a leaf --  this test is taken care of by the next two
      if ((tNode.getLeft() == null) && (tNode.getRight() == null)) 
      {
         return null;
      }  

      // test for no left child
      else if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      } 

      // test for no right child
      else if (tNode.getRight() == null) 
      {
         return tNode.getLeft();
      } 

      // there are two children:
      // retrieve and delete the inorder successor
      else 
      {
         KeyedItem replacementItem = findLeftmost(tNode.getRight());
         tNode.setItem(replacementItem);
         TreeNode subtree = deleteLeftmost(tNode.getRight());
         tNode.setRight(subtree);
         return tNode;
      } 
   }  

   protected KeyedItem findLeftmost(TreeNode tNode)  
   {
      if (tNode.getLeft() == null) 
      {
         return (KeyedItem)tNode.getItem();
      }
      else 
      {
         return findLeftmost(tNode.getLeft());
      }  
   } 

   protected TreeNode deleteLeftmost(TreeNode tNode) 
   {
      if (tNode.getLeft() == null) 
      {
         return tNode.getRight();
      }
      else 
      {
         TreeNode subtree = deleteLeftmost(tNode.getLeft());
         tNode.setLeft(subtree);
         return tNode;
      }  
   } 

   protected TreeNode rotateLeft(TreeNode tNode)
   {
      TreeNode right = tNode.getRight();
      TreeNode rightleft = right.getLeft();

      tNode.setRight(rightleft);
      right.setLeft(tNode);

      return right;
   }

   protected TreeNode rotateRight(TreeNode tNode)
   {
      TreeNode left = tNode.getLeft();
      TreeNode leftright = left.getRight();

      tNode.setLeft(leftright);
      left.setRight(tNode);

      return left;
   }

   public int height(){
        return getHeight(getRootNode());
   }
   protected int getHeight(TreeNode t){
        if(t == null)return 0;
        return 1 + Math.max(getHeight(t.getLeft()), getHeight(t.getRight()));
   }

   public boolean validateBSTProperty(){
        TreeIterator it = iterator();
        it.setInorder();
        boolean valid = true;

        if(!it.hasNext()) return true;

        KeyedItem item1 = (KeyedItem) it.next();
        while(it.hasNext()){
            KeyedItem item2 = (KeyedItem) it.next();

            if(item1.getKey().compareTo(item2.getKey())> 0){
                valid = false;
                break;
            }

            item1 = item2;
        }
        return valid;
   }
   public int size(){return size;}
   protected int computeSize(){
       TreeIterator it = iterator();
       it.setInorder();
       int i=0;
       for(; it.hasNext(); ++i)it.next();
       return i;

   }
   public boolean validateSize(){ return size() == computeSize(); }

   public boolean isBalanced(){
       return isBalanced(getRootNode()); 
   }

   protected boolean isBalanced(TreeNode tNode){
          if (tNode == null)
          {
             return true;
          }

          boolean balanced = isBalanced(tNode.getLeft());
          if (!balanced) return balanced;
          balanced = isBalanced(tNode.getRight());
          if (!balanced) return balanced;

          int leftHeight = getHeight(tNode.getLeft());
          int rightHeight = getHeight(tNode.getRight());
          if (Math.abs(leftHeight - rightHeight) > 1)
          {
             balanced = false;
          }

          return balanced;
   }
} 

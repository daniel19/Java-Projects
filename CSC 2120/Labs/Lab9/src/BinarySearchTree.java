/**
 * BinarySearchTree class creates binary search trees that abides
 *  the binary tree property.
 *  @author Daniel Brown
 */
public class BinarySearchTree extends BinaryTreeBasis implements SearchTreeInterface
{ 
    /**
     * Size variable holds total number of nodes in BST.
     */
    private int size;
   
   /**
    * BST Constructor that calls parent constructor BinaryTreeBasis
    */ 
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

   /**
    * Called by retrieve() to navigate the BST.
    * Preconditions: TreeNode and Comparable key to navigate the BST.
    * Postconditions: KeyedItem returned that matches the search key.
    */
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
    /**
     *Called by insert() to place KeyedItem into BST.
     *Preconditions: TreeNode and KeyedItem to be placed into BST.
     *Postconditions: Treenode Inserted into BST.
     */ 
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
        insertDuplicate();
      }
      else if (comparison < 0) 
      {
         tNode = insertLeft(tNode, item);
        
      }
      else 
      { 
         tNode = insertRight(tNode, item);
      }  

      return tNode;
   }  
    /**
     * Called by insertItem(); method decomp.
     * Preconditions: Duplicate item found.
     * Postconditions: Thrown TreeException
     */
   protected void insertDuplicate() throws TreeException{
      throw new TreeException ("Cannot add duplicate.");
   }
    /**
     * Called by insertItem(); method decomp.
     * Preconditions: Takes a TreeNode and KeyedItem to be inserted on the left size of a node.
     * Postconditions: inserted node.
     */
   protected TreeNode insertLeft(TreeNode t, KeyedItem item){
         // search the left subtree
         TreeNode subtree = insertItem(t.getLeft(), item);
         //TreeNode subtree = insertItem(t.getRight(), item);
         t.setLeft(subtree);
         return t;
   }
   /**
     * Called by insertItem(); method decomp.
     * Preconditions: Takes a TreeNode and KeyedItem to be inserted on the right size of a node.
     * Postconditions: inserted node.
     */
   protected TreeNode insertRight(TreeNode t, KeyedItem item){
         // search the right subtree
         TreeNode subtree = insertItem(t.getRight(), item);
         //TreeNode subtree = insertItem(t.getLeft(), item);
         t.setRight(subtree);
         return t; 
   }
   /**
     * Called by delete().
     * Preconditions: Takes a TreeNode and KeyedItem to be deleted.
     * Postconditions: Deleted node.
     */
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
         tNode = deleteLeft(tNode, searchKey);
      }
      else 
      { 
         // search the right subtree
         tNode = deleteRight(tNode, searchKey);
      } 

      return tNode;
   }  
   /**
     * Called by deleteItem(); method decomp.
     * Preconditions: Takes a TreeNode and KeyedItem to be deleted.
     * Postconditions: Deleted node.
     */
   protected TreeNode deleteLeft(TreeNode tNode, Comparable sk)
   {
      TreeNode subtree = deleteItem(tNode.getLeft(), sk);
      tNode.setLeft(subtree);
      return tNode;
   }
    /**
     * Called by deleteItem(); method decomp.
     * Preconditions: Takes a TreeNode and KeyedItem to be deleted.
     * Postconditions: Deleted node.
     */
   protected TreeNode deleteRight(TreeNode tNode, Comparable sk)
   {
      TreeNode subtree = deleteItem(tNode.getRight(), sk);
      tNode.setRight(subtree);
      return tNode;
   }
   /**
     * Called by deleteItem(); method decomp.
     * Preconditions: Takes a TreeNode and KeyedItem to be deleted.
     * Postconditions: Deleted node.
     */ 
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
    /**
     *Finds the left most leaf in a BST.
     *Preconditions: TreeNode 
     *Postconditions: KeyedItem
     */
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
    /**
     *Finds the left most leaf in a BST and deletes it.
     *Preconditions: TreeNode 
     *Postconditions: Deleted TreeNode
     */
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
    /**
     *Rotates the specified TreeNode to the left.
     *Preconditions: TreeNode 
     *Postconditions: New BST based off of rotation properties.
     */
   protected TreeNode rotateLeft(TreeNode tNode)
   {
      TreeNode right = tNode.getRight();
      TreeNode rightleft = right.getLeft();

      tNode.setRight(rightleft);
      right.setLeft(tNode);

      return right;
   }
    /**
     *Rotates the specified TreeNode to the right.
     *Preconditions: TreeNode 
     *Postconditions: New BST based off of rotation properties.
     */
   protected TreeNode rotateRight(TreeNode tNode)
   {
      TreeNode left = tNode.getLeft();
      TreeNode leftright = left.getRight();

      tNode.setLeft(leftright);
      left.setRight(tNode);

      return left;
   }
    /**
     * Returns the height of the BST.
     * Preconditions: None
     * Postconditions: integer height of BST.
     */
   public int height(){
        return getHeight(getRootNode());
   }
   /**
    * Helper method to return height of BST.
    * Preconditions: TreeNode to keep postion
    * Postconditions: integer of BST height.
    */
   protected int getHeight(TreeNode t){
        if(t == null)return 0;
        return 1 + Math.max(getHeight(t.getLeft()), getHeight(t.getRight()));
   }
    /**
     *Determines if BST is valid according to properties.
     *Preconditions: none
     *Postconditions: Boolean return of valid BST Property.
     */
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
   /**
    * Helper method to determine the size of the BST.
    * Preconditions none
    * Postconditions integer with size of BST.
    */
   protected int computeSize(){
       TreeIterator it = iterator();
       it.setInorder();
       int i=0;
       for(; it.hasNext(); ++i)it.next();
       return i;

   }
   /**
    * Determines if the instance variable matches the actual size of the BST.
    * Preconditions: none
    * Postconditions: boolean of the tested condition.
    */ 
   public boolean validateSize(){ return size() == computeSize(); }
    /**
     * Determines if the BST is balanced according to BST property.
     * Preconditions: none;
     * Postconditions: Boolean of condition
     */
   public boolean isBalanced(){
       return isBalanced(getRootNode()); 
   }
    /**
     * Helper method to determine if the BST is balanced according to BST property.
     * Preconditions: TreeNode position.
     * Postconditions: Boolean of condition
     */
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

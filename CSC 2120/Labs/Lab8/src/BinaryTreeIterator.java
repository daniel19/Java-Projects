import queue.*;

class BinaryTreeIterator implements TreeIterator
{
   private TreeNode root;  
   private QueueInterface queue;       

   public BinaryTreeIterator(TreeNode root) 
   {
      this.root = root;
      queue = new QueueLinked();
   } 

   public boolean hasNext() 
   {
      return !queue.isEmpty();
   }  

   public Object next() throws TreeException
   {
      try 
      {
         return queue.dequeue();
      }  
      catch (QueueException e) 
      {
         throw new TreeException("End of tree traversal.");
      }  
   }  

   public void remove() throws UnsupportedOperationException 
   {
      throw new UnsupportedOperationException();
   }  

   public void setPreorder() 
   {
      queue.dequeueAll();
      preorder(root);
   } 

   public void setInorder() 
   {
      queue.dequeueAll();
      inorder(root);
   }  

   public void setPostorder() 
   {
      queue.dequeueAll();
      postorder(root);
   }   

   public void setLevelorder()
   {
      if (root == null)
         return;  //Don't do anything if the root node is null

      QueueInterface newQueue = new QueueLinked<TreeNode>();
      newQueue.enqueue(root);  //Add the root to the new queue
      queue.dequeueAll();  //Clear the instance queue
      TreeNode currItem = null;  //Set currItem to null for the loop

      while(!newQueue.isEmpty())
      {
         currItem = (TreeNode) newQueue.dequeue();  //Get the current item
         queue.enqueue(currItem.getItem());  //Add it to the instance queue

         if (currItem.getLeft() != null)
            newQueue.enqueue(currItem.getLeft());  //Add the left subtree to the local queue
         if (currItem.getRight() != null)
            newQueue.enqueue(currItem.getRight());  //Add the right subtree to the local queue
      }
   }

   private void preorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         queue.enqueue(tNode.getItem());
         preorder(tNode.getLeft());
         preorder(tNode.getRight());
      } 
   } 

   private void inorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         inorder(tNode.getLeft());
         queue.enqueue(tNode.getItem());
         inorder(tNode.getRight());
      }
   }  

   private void postorder(TreeNode tNode) 
   {
      if (tNode != null) 
      {  
         postorder(tNode.getLeft());
         postorder(tNode.getRight());
         queue.enqueue(tNode.getItem());
      } 
   } 
}  

public class AdaptableBinarySearchTree extends BinarySearchTree
{
	public AdaptableBinarySearchTree()
	{
		super();
	}

	public KeyedItem retrieve(Comparable searchKey)
	{
		try
		{
			return (KeyedItem) retrieveItemAdapt(getRootNode(), searchKey).getItem();
		}
		catch (TreeException it)
		{
			return null;
		}
	}

	private TreeNode retrieveItemAdapt(TreeNode tNode, Comparable searchKey) throws TreeException
	{
		if (tNode == null)  //If the tree node is not in the tree
			throw new TreeException("Item not found.");

		KeyedItem newKeyedItem = (KeyedItem) tNode.getItem();
		int compareInt = (newKeyedItem.getKey()).compareTo(searchKey);  //Compare the key to the item passed in

		//If the item is to the left tNode
		if (compareInt < 0)
		{
			return retrieveItemAdapt(super.rotateRight(tNode), searchKey);
		}
		//If the item is to the right of tNode
		else if (compareInt > 0)
			return retrieveItemAdapt(super.rotateLeft(tNode), searchKey);
		//Found the item location
		else
		{
			return tNode;
		}
	}
}

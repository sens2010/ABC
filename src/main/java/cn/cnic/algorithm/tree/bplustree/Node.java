package cn.cnic.algorithm.tree.bplustree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class Node<T extends Comparable<T>>
{
	private static int NODESIZE = 10;
	private static int NODEBOTTOM = 5;
	private static int BORROWCOUNT=1;
	public static int getNodeSize()
	{
		return NODESIZE;
	}
	
	public static void setNodeSize(int nodesize)
	{
		NODESIZE = nodesize;
	}
	public static int getNodeBottom()
	{
		return NODEBOTTOM;
	}
	
	public static void setNodeBottom(int nodebottom)
	{
		NODEBOTTOM = nodebottom;
	}
	
	public static void setBorrowCount(int borrowcount)
	{
		BORROWCOUNT = borrowcount;
	}
	public static int getBorrowCount()
	{
		return BORROWCOUNT;
	}
	
	private T ancientKey;
	
	public T getAcientKey()
	{
		return this.ancientKey;
	}
	
	public void setAcientKey(T ancientkey)
	{
		this.ancientKey= ancientkey;
	}
	
	private List<T> keylist = Lists.newLinkedList();
	
	public List<T> getKeyList()
	{
		return this.keylist;
	}
	
	public void setKeyList(List<T> keylist)
	{
		this.keylist.clear();
		this.keylist.addAll(keylist);
	}
	
	Value findOne(T search){return null;};
	
	//left node will borrow or lend some node the right. 
	//if number >0 is lend, left<-right;
	//otherwise, left->right, and the number will match the count
	public abstract T borrowNode(Node<T> right, int number, T key);
	
	
	
	List<Value> findAll(T search)
	{
		return null;
	}
	
	Node<T> addNode(T key)
	{
		return addNode(key, null);
	}
	
	abstract Node<T> addNode(T key, Value value);
	
	public int insertNode(T key)
	{
		int end = keylist.size() - 1;
		int result = 0;
		if (end >= 0 && keylist.get(end).compareTo(key) > 0)
		{
			result = insert(0, end, key);
		} else
		{
			if (end >= 0)
			{
				result = end + 1;
			}
			keylist.add(key);
		}
		/*System.out.println("插入位置：" + result);
		System.out.print("插入结果");
		for (T i : keylist)
		{
			System.out.print(i + "    ");
		}
		System.out.println();*/
		return result;
	}
	
	public int insert(int start, int end, T key)
	{
		int mid = (start + end + 1) / 2;
		if (keylist.get(mid).compareTo(key) == 0 || start == end)
		{
			if (keylist.get(mid).compareTo(key) < 0)
			{
				keylist.add(mid + 1, key);
				return mid + 1;
			} else
			{
				keylist.add(mid, key);
				return mid;
			}
		} else if (keylist.get(mid).compareTo(key) > 0)
		{
			return insert(start, mid - 1, key);
		} else
		{
			return insert(mid + 1, end, key);
		}
	}
	
	public int search(int start, int end, T key)
	{
		System.out.println("start:"+start+", end:"+end+", key:"+key);
		for(T k : this.getKeyList())
		{
			System.out.print(k.toString()+"\t");
		}
		System.out.println();
		int mid = (start + end + 1) / 2;
		if (start >= end|| this.getKeyList().get(mid).compareTo(key) == 0)
		{
			if (start >end||(start == end && this.getKeyList().get(mid).compareTo(key) != 0))
			{
				return -1;
			} 
			else
			{
				return mid;
			}
		} else if (this.getKeyList().get(mid).compareTo(key) > 0)
		{
			return search(start, mid - 1, key);
		} else
		{
			return search(mid + 1, end, key);
		}
	}
	
	public abstract void visitAll();
	public abstract void visitAllWithValue();
	
	
	public abstract Node<T> removeNode(T key);
	
	boolean updateOne(T node, Value value)
	{
		return null != null;
	}
	
	boolean updateAll(T node, Value value)
	{
		return null != null;
		
	}
	
	public int findNode(T key)
	{
		List<T> keylist = this.getKeyList();
		int i = 0;
		System.err.println("size:"+keylist.size());
		
		while (i<keylist.size()&&keylist.get(i).compareTo(key) <= 0)
		{
			System.err.println(keylist.get(i));
			i++;
		}
		return i;
		
	}
	
	abstract public void visitByBFV();
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}
	
}

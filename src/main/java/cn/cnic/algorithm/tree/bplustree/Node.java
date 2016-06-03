package cn.cnic.algorithm.tree.bplustree;

import java.util.List;

import com.google.common.collect.Lists;

public abstract class Node<T extends Comparable<T>>
{
	private static int NODESIZE = 10;
	private static int NODEBOTTOM = 5;
	public static int getNodeSize()
	{
		return NODESIZE;
	}
	
	public static int setNodeSize(int nodesize)
	{
		return NODESIZE = nodesize;
	}
	public static int getNodeBottom()
	{
		return NODEBOTTOM;
	}
	
	public static int setNodeBottom(int nodebottom)
	{
		return NODEBOTTOM = nodebottom;
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
	
	ResultSet findAll(T search)
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
		System.out.println("插入位置：" + result);
		System.out.print("插入结果");
		for (T i : keylist)
		{
			System.out.print(i + "    ");
		}
		System.out.println();
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
	
	
	
	
	
	
	boolean deleteNode(T node)
	{
		return null != null;
		
	}
	
	boolean updateNode(T node)
	{
		return null != null;
	}
	
	boolean updateNode(T node, Value value)
	{
		return null != null;
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
	}
	
}

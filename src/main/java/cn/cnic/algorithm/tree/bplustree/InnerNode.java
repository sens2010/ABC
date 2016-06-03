package cn.cnic.algorithm.tree.bplustree;

import java.util.List;

import com.google.common.collect.Lists;

public class InnerNode<T extends Comparable<T>> extends Node<T>
{
	
	private List<Node<T>> indexlist = Lists.newLinkedList();
	
	public List<Node<T>> getIndexList()
	{
		return this.indexlist;
	}
	
	public void setIndexList(List<Node<T>> indexes)
	{
		this.indexlist.clear();
		this.indexlist.addAll(indexes);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		
	}
	
	@Override
	Value findOne(T key)
	{
		return null;
	}
	
	@Override
	Node<T> addNode(T key, Value value)
	{
		System.out.println("root size2:"+getKeyList().size());
		int pos = findNode(key);
		Node<T> newnode = this.getIndexList().get(pos).addNode(key, value);
		if (newnode != null)
		{
			if (pos < this.getKeyList().size())
			{
				this.getKeyList().add(pos, newnode.getAcientKey());
				this.getIndexList().add(pos + 1, newnode);
			} else
			{
				this.getKeyList().add(newnode.getAcientKey());
				this.getIndexList().add(newnode);
			}
		}
		if (this.getKeyList().size() <= Node.getNodeSize())
		{
			return null;
		} else
		{
			List<T> keys = this.getKeyList();
			List<Node<T>> indexes = this.getIndexList();
			
			InnerNode<T> inner = new InnerNode<T>();
			
			inner.setKeyList(keys.subList((keys.size() + 1) / 2 + 1,
					keys.size() - 1));
			inner.setIndexList(indexes.subList((keys.size() + 1) / 2 + 1,
					keys.size()));
			
			inner.setAcientKey(keys.get((keys.size() + 1) / 2));
			keys.subList((keys.size() + 1) / 2 + 1,
					keys.size() - 1).clear();
			indexes.subList((keys.size() + 1) / 2 + 1,
					keys.size()).clear();
			
			return inner;
		}
	}
	
	int findNode(T key)
	{
		System.out.println("root size3:"+getKeyList().size());
		List<T> keylist = this.getKeyList();
		int i = 0;
		System.out.println(keylist.get(i));
		while (i<keylist.size()&&keylist.get(i).compareTo(key) < 0)
		{
			i++;
		}
		
			return i;
		
	}
}

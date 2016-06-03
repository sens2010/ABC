package cn.cnic.algorithm.tree.bplustree;

import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class Leaf<T extends Comparable<T>> extends Node<T>
{
	
	private List<Value> datalist = Lists.newLinkedList();
	private Leaf<T> nextNode = null;
	public List<Value> getDataList()
	{
		return this.datalist;
	}
	
	public void setDataList(List<Value> datalist)
	{
		this.datalist.clear();
		this.datalist.addAll(datalist);
	}
	
	public Leaf<T> next()
	{
		return this.nextNode;
	}
	
	public void setNext(Leaf<T> next)
	{
		this.nextNode = next;
	}
	
	Node<T> addNode(T key, Value value)
	{
		int index = insertNode(key);
		if (index < 0 || index == this.getKeyList().size())
		{
			datalist.add(value);
		} else
		{
			datalist.add(index, value);
		}
		if (this.getKeyList().size() <= Node.getNodeSize())
		{
			return null;
		} 
		else
		{
			List<T> keys= this.getKeyList();
			List<Value> values = this.getDataList();
			
			Leaf<T> leaf = new Leaf<T>();
			
			for(int i=(keys.size()+1)/2;i<=keys.size()-1;i++)
			{
				leaf.getKeyList().add(keys.get(i));
				leaf.getDataList().add(values.get(i));
			}
			keys.subList((keys.size()+1)/2, keys.size()-1).clear();
			values.subList((keys.size()+1)/2, keys.size()-1).clear();
			
			leaf.setNext(this.next());
			this.setNext(leaf);
			leaf.setAcientKey(leaf.getKeyList().get(0));
			System.out.println("split! ak："+this.getAcientKey());
			System.out.println("split!");
			return leaf;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Leaf<Integer> leaf = new Leaf<Integer>();
		// ResultSet rs = new ResultSet();
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			Integer key = sc.nextInt();
			leaf.insertNode(key);
			
		}
	}
	
	
}

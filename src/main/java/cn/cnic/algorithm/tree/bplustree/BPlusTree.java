package cn.cnic.algorithm.tree.bplustree;
import java.util.List;

import cn.cnic.algorithm.common.StringFunnel;
import com.google.common.hash.BloomFilter;
public class BPlusTree<T extends Comparable<T>> {
	BloomFilter<T> bloomfilter;
	
	Node<T> root;
	
	public Node<T> getRoot()
	{
		return this.root;
	}
	public void setRoot(Node<T> root)
	{
		this.root = root;
	}
	public BPlusTree()
	{
		root =  new Leaf<T>();
	}
	
	public void addNode(T key)
	{
		root.addNode(key,null);
	}
	
	public void addNode(T key, Value value)
	{
		Node<T> newnode= root.addNode(key, value);
		if(newnode!=null)
		{
			InnerNode<T> temp = new InnerNode<T>();
			temp.getKeyList().add(newnode.getAcientKey());
			temp.getIndexList().add(root);
			temp.getIndexList().add(newnode);
			root = temp;		
		}
	}
	
	public boolean removeNode(T key)
	{
		Node<T> newnode= root.removeNode(key);
		if(newnode==null)
		{
			return true;
		}
		else if(newnode.getKeyList().size()==0)
		{
			return false;
		}
		else
		{
			if(newnode instanceof InnerNode&& newnode.getKeyList().size()==1)
			{
				Node<T> left = ((InnerNode<T>)newnode).getIndexList().get(0);
				Node<T> right = ((InnerNode<T>)newnode).getIndexList().get(1);
				left.borrowNode(right, right.getKeyList().size());
				root = left;
			}
			return true;
		}
	}
	
	public Value find(T key)
	{
		return root.findOne(key);
	}
	public List<Value> findAll(T key)
	{
		return root.findAll(key);
	}
	
	public boolean update(T key, Value value)
	{
		return root.updateOne(key, value);
	}
	
	public boolean updateAll(T key, Value value)
	{
		return root.updateAll(key, value);
	}
	
	public void visitAll()
	{
		this.getRoot().visitAll();
	}
	public void visitAllWithValue()
	{
		this.getRoot().visitAllWithValue();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BloomFilter<String> bf = BloomFilter.create(new StringFunnel(),10000);
		bf.put("124");
		System.out.println(bf.mightContain("124"));
	}

}

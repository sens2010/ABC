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
		root.addNode(key,new Value(""));
	}
	
	public void addNode(T key, Value value)
	{
		Node<T> newnode= root.addNode(key, value);
		if(newnode!=null)
		{
			//System.err.println("newroot!");
			InnerNode<T> temp = new InnerNode<T>();
			temp.getKeyList().add(newnode.getAcientKey());
			temp.getIndexList().add(root);
			temp.getIndexList().add(newnode);
			root = temp;
			//for(T t:root.getKeyList())
			//System.err.print(t);
			//System.err.println();
			//for(T t:((InnerNode<T>)root).getIndexList().get(0).getKeyList())
				//System.err.print(t+"\t");
				//System.err.println();
				//for(T t:((InnerNode<T>)root).getIndexList().get(1).getKeyList())
					//System.err.print(t+"\t");
					//System.err.println();
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
			if(newnode instanceof InnerNode&&((InnerNode<T>)newnode).getIndexList().size()==1)
			{
				this.visitByBFV();
				root = ((InnerNode<T>)newnode).getIndexList().get(0);
				this.visitByBFV();
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
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
	
	public void visitByBFV()
	{
		this.getRoot().visitByBFV();
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

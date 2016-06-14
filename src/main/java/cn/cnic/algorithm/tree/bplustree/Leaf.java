package cn.cnic.algorithm.tree.bplustree;

import java.util.List;
import com.google.common.collect.Lists;

public class Leaf<T extends Comparable<T>> extends Node<T>
{
	
	private List<Value> datalist = Lists.newLinkedList();
	private Leaf<T> nextNode = null;
	private Leaf<T> frontNode = null;
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
	
	public Leaf<T> front()
	{
		return this.frontNode;
	}
	
	public void setFront(Leaf<T> front)
	{
		this.frontNode  = front;
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
			
			List<T> subkeys = keys.subList((keys.size()+1)/2, keys.size());
			List<Value> subvalues = values.subList((keys.size()+1)/2, keys.size());
			
			for(T k:subkeys)
			{
				leaf.getKeyList().add(k);
			}
			for(Value v:subvalues)
			{
				leaf.getDataList().add(v);
			}
			subkeys.clear();
			subvalues.clear();
			
			leaf.setNext(this.next());
			leaf.setFront(this);
			this.setNext(leaf);
			leaf.setAcientKey(leaf.getKeyList().get(0));
			return leaf;
		}
	}
	
	@Override
	public Node<T> removeNode(T key)
	{
		int index = search(key);
		System.err.println("index:"+index);
		if(index==-1)
		{
			return new Leaf<T>();
		}
		else
		{
			this.getKeyList().remove(index);
			this.getDataList().remove(index);
			if(this.getKeyList().size()>Node.getNodeBottom())
			{
				return null;
			}
			else
			{
				return this;
			}
		}
	}
	
	public int search(T key)
	{
		return search(0,this.getKeyList().size()-1,key);
	}
	

	public T borrowNode(Node<T> right, int number)
	{
		if(number>0)
		{
			for(int i=0;i<number;i++)
			{
				this.getKeyList().add(right.getKeyList().get(i));
				((Leaf<T>)this).getDataList().add(((Leaf<T>)right).getDataList().get(i));
			}
			right.getKeyList().subList(0, number).clear();
			((Leaf<T>)right).getDataList().subList(0, number).clear();
			System.out.println("***************");
			for(T t : this.getKeyList())
			{
				System.out.print(t+"\t");
			}
			System.out.println();
			System.out.println("***************");
			for(T t : right.getKeyList())
			{
				System.out.print(t+"\t");
			}
			System.out.println();
			System.out.println("size:"+right.getKeyList().size());
			System.out.println("***************");
			return right.getKeyList().size()>0?right.getKeyList().get(0):null;
		}
		else if(number<0)
		{
			int lsize = this.getKeyList().size();
			number*=-1;
			for(int i=0;i<number;i++)
			{
				right.getKeyList().add(0,this.getKeyList().get(lsize-i-1));
				((Leaf<T>)right).getDataList().add(0,((Leaf<T>)this).getDataList().get(lsize-i-1));
			}
			this.getKeyList().subList(lsize-number, lsize).clear();
			((Leaf<T>)this).getDataList().subList(lsize-number, lsize).clear();
			 return right.getKeyList().size()>0?null:right.getKeyList().get(0);
		}
		else
		{
			return null;
		}
		
	}
	
	public Value findOne(T key)
	{
		int pos = findNode(key);
		if(this.getKeyList().size()==0||this.getKeyList().get(pos).compareTo(key)!=0)
			return null;
		else
			return this.getDataList().get(pos);
	}
	
	public List<Value> findAll(T key)
	{
		int pos = findNode(key);
		if(this.getKeyList().get(pos).compareTo(key)!=0)
		{
			return null;
		}
		else
		{
			List<Value> results = Lists.newLinkedList();
			do
			{
				while(pos<this.getDataList().size()&&this.getKeyList().get(pos).compareTo(key)==0)
				{
					results.add(this.getDataList().get(pos));
					pos++;
				}
				if(pos<=this.getDataList().size())
				{
					break;
				}
				pos=0;
			}while(this.next()!=null);
			return results;
		}
	}
	
	public boolean updateOne(T key, Value value)
	{
		int pos = findNode(key);
		if(this.getKeyList().get(pos).compareTo(key)!=0)
		{
			return false;
		}
		else
		{
			this.getDataList().set(pos, value);
			return true;
		}
	}
	
	public boolean updateAll(T key,Value value)
	{
		int pos = findNode(key);
		if(this.getKeyList().get(pos).compareTo(key)!=0)
			return false;
		else
		{
			do
			{
				while(pos<this.getDataList().size()&&this.getKeyList().get(pos).compareTo(key)==0)
				{
					this.getDataList().set(pos,value);
					pos++;
				}
				if(pos<=this.getDataList().size())
				{
					break;
				}
				pos=0;
			}while(this.next()!=null);
			return true;
		}
	}
	public void visitAll()
	{
		for(T key:this.getKeyList())
		{
			System.out.print(key+"\t");
		}
		System.out.println();
		if(this.nextNode!=null)
		this.next().visitAll();
	}
	
	public void visitAllWithValue()
	{
		List<T> list = this.getKeyList();
		for(int i=0;i<list.size();i++)
		{
			String value ="";
			try
			{
				value=this.getDataList().get(i).getText().toString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.print(list.get(i)+":"+value+"\t");
		}
		/*for(T key:this.getKeyList())
		{
			System.out.print(key+":"+this);
		}*/
		System.out.println();
		if(this.nextNode!=null)
		this.next().visitAllWithValue();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	}
	
	
}

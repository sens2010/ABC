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
	Node<T> addNode(T key, Value value)
	{
		int pos = findNode(key);
		Node<T> newnode = this.getIndexList().get(pos).addNode(key, value);
		if (newnode != null)
		{
			if (pos < this.getKeyList().size())
			{
				this.getKeyList().add(pos, newnode.getAcientKey());
				this.getIndexList().add(pos + 1, newnode);
			} 
			else
			{
				this.getKeyList().add(newnode.getAcientKey());
				this.getIndexList().add(newnode);
			}
		}
		if (this.getKeyList().size() <= Node.getNodeSize())
		{
			return null;
		} 
		else
		{
			List<T> keys = this.getKeyList();
			List<Node<T>> indexes = this.getIndexList();
			
			InnerNode<T> inner = new InnerNode<T>();
			
			List<T> subkeys = keys.subList((keys.size() + 1) / 2 + 1,keys.size());
			List<Node<T>> subindexes = indexes.subList((keys.size() + 1) / 2 + 1,indexes.size());
			for(T k:subkeys)
			{
				inner.getKeyList().add(k);
			}
			for(Node<T> si:subindexes)
			{
				inner.getIndexList().add(si);
			}
			
			inner.setAcientKey(keys.get((keys.size() + 1) / 2));
			keys.subList((keys.size() + 1) / 2,
					keys.size()).clear();
			indexes.subList((keys.size() + 1) / 2 + 1,
					keys.size()).clear();
			
			return inner;
		}
	}
	
	public Node<T> removeNode(T key)
	{
		int pos = findNode(key);
		Node<T> newnode = this.getIndexList().get(pos).removeNode(key);
		if(newnode==null)
		{
			return null;
		}
		else
		{
			if(newnode.getKeyList().size()==0)
			{
				return newnode;
			}
			else
			{
				if(pos==0)
				{
					Node<T> right = this.getIndexList().get(pos+1);
					if(right.getKeyList().size()>(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = newnode.borrowNode(right, Node.getBorrowCount());
						this.getKeyList().set(pos,flag);
					}
					else
					{
						newnode.borrowNode(right, right.getKeyList().size());
						this.getKeyList().remove(0);
						this.getKeyList().remove(1);
					}
				}
				else if(pos<=(this.getKeyList().size()-1))
				{
					Node<T> right = this.getIndexList().get(pos+1);
					Node<T> left = this.getIndexList().get(pos-1);
					if(right.getKeyList().size()>(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = newnode.borrowNode(right, Node.getBorrowCount());
						this.getKeyList().set(pos,flag);
					}
					else if(left.getKeyList().size()>(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = left.borrowNode(newnode, Node.getBorrowCount()*-1);
						this.getKeyList().set(pos-1,flag);
					}
					else
					{
						newnode.borrowNode(right, right.getKeyList().size());
						this.getKeyList().remove(pos);
						this.getKeyList().remove(pos+1);
					}
				}
				else
				{
					Node<T> left = this.getIndexList().get(pos-1);
					if(left.getKeyList().size()>(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = left.borrowNode(newnode, Node.getBorrowCount()*-1);
						this.getKeyList().set(pos-1,flag);
					}
					else
					{
						left.borrowNode(newnode, newnode.getKeyList().size());
						this.getKeyList().remove(pos);
						this.getKeyList().remove(pos+1);
					}
				}
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
	}
	
	public Value findOne(T key)
	{
		int pos = findNode(key);
		return this.getIndexList().get(pos).findOne(key);
	}
	public List<Value> findAll(T key)
	{
		int pos = findNode(key);
		return this.getIndexList().get(pos).findAll(key);
	}
	
	public boolean updateOne(T key,Value value)
	{
		int pos = findNode(key);
		return this.getIndexList().get(pos).updateOne(key,value);
	}
	public boolean updateAll(T key,Value value)
	{
		int pos = findNode(key);
		return this.getIndexList().get(pos).updateAll(key,value);
	}
	
	
	public T borrowNode(Node<T> right, int number)
	{
		if(number>0)
		{
			for(int i=0;i<number;i++)
			{
				this.getKeyList().add(right.getKeyList().get(i));
				((InnerNode<T>)this).getIndexList().add(((InnerNode<T>)right).getIndexList().get(i));
			}
			right.getKeyList().subList(0, number).clear();
			((InnerNode<T>)right).getIndexList().subList(0, number).clear();
			return right.getKeyList().size()>0?right.getKeyList().get(0):null;
		}
		else if(number<0)
		{
			number*=-1;
			for(int i=0;i<number;i++)
			{
				right.getKeyList().add(this.getKeyList().get(i));
				((InnerNode<T>)right).getIndexList().add(((InnerNode<T>)this).getIndexList().get(i));
			}
			this.getKeyList().subList(0, number).clear();
			((InnerNode<T>)this).getIndexList().subList(0, number).clear();
			return right.getKeyList().size()>0?null:right.getKeyList().get(0);
		}
		else
		{
			return null;
		}
	}
	

	
	public void visitAll()
	{
		this.getIndexList().get(0).visitAll();
	}
	public void visitAllWithValue()
	{
		this.getIndexList().get(0).visitAllWithValue();
	}
	
}

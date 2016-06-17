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
			//System.err.println("*************");
			//System.out.println("size:"+this.getKeyList()+",condition:"+(this.getKeyList().size() <= Node.getNodeSize()));
			List<T> keys = this.getKeyList();
			List<Node<T>> indexes = this.getIndexList();
			
			InnerNode<T> inner = new InnerNode<T>();
			
			List<T> subkeys = keys.subList((keys.size() + 1) / 2,keys.size());
			List<Node<T>> subindexes = indexes.subList((keys.size() + 1) / 2,indexes.size());
			for(T k:subkeys)
			{
				inner.getKeyList().add(k);
			}
			for(Node<T> si:subindexes)
			{
				inner.getIndexList().add(si);
			}
			
			inner.setAcientKey(keys.get((keys.size() + 1) / 2-1));
			keys.subList((keys.size() + 1) / 2-1,
					keys.size()).clear();
			indexes.subList((indexes.size() + 1) / 2,
					indexes.size()).clear();
			//System.err.println("indexes size:"+indexes.size());
			return inner;
		}
	}
	
	public Node<T> removeNode(T key)
	{
		int pos = findNode(key);
		/*if(pos==5)
		{
			for(T t:this.getKeyList())
			{
				System.out.println(t);
			}
			
			Node<T> n = this.getIndexList().get(5);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
			n = this.getIndexList().get(4);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
			n = this.getIndexList().get(0);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
			System.out.println("****1*****");
			n = this.getIndexList().get(1);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
			System.out.println("****2*****");
			n = this.getIndexList().get(2);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
			System.out.println("****3*****");
			n = this.getIndexList().get(3);
			for(T t:n.getKeyList())
			{
				System.out.println(t);
			}
		}*/
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
					T keyflag = this.getKeyList().get(pos);
					//System.out.println("right_size:"+right.getKeyList().size()+",compare:"+(Node.getNodeBottom()+Node.getBorrowCount()));
					if(right.getKeyList().size()>=(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = newnode.borrowNode(right, Node.getBorrowCount(),keyflag);
						this.getKeyList().set(pos,flag);
						
					}
					else
					{
						newnode.borrowNode(right, right.getKeyList().size(),keyflag);
						this.getKeyList().remove(0);
						this.getIndexList().remove(1);
					}
				}
				else if(pos<=(this.getKeyList().size()-1))
				{
					Node<T> right = this.getIndexList().get(pos+1);
					Node<T> left = this.getIndexList().get(pos-1);
					T keyflag ;
					if(right.getKeyList().size()>=(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						keyflag = this.getKeyList().get(pos);
						T flag = newnode.borrowNode(right, Node.getBorrowCount(),keyflag);
						this.getKeyList().set(pos,flag);
					}
					else if(left.getKeyList().size()>=(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						keyflag = this.getKeyList().get(pos-1);
						T flag = left.borrowNode(newnode, Node.getBorrowCount()*-1, keyflag);
						this.getKeyList().set(pos-1,flag);
					}
					else
					{
						keyflag = this.getKeyList().get(pos);
						newnode.borrowNode(right, right.getKeyList().size(),keyflag);
						this.getKeyList().remove(pos);
						this.getIndexList().remove(pos+1);
					}
				}
				else
				{
					Node<T> left = this.getIndexList().get(pos-1);
					T keyflag = this.getKeyList().get(pos-1);
					if(left.getKeyList().size()>=(Node.getNodeBottom()+Node.getBorrowCount()))
					{
						T flag = left.borrowNode(newnode, Node.getBorrowCount()*-1,keyflag);
						this.getKeyList().set(pos-1,flag);
					}
					else
					{
						left.borrowNode(newnode, newnode.getKeyList().size(),keyflag);
						this.getKeyList().remove(pos-1);
						this.getIndexList().remove(pos);
					}
				}
				
				//System.out.println("inner:size:"+this.getKeyList().size());
				/*for(T t:this.getKeyList())
				{
					System.out.print(t+"\t");
				}*/
				//System.out.println();
				if(this.getKeyList().size()>=Node.getNodeBottom())
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
	
	
	public T borrowNode(Node<T> right, int number, T key)
	{
		T keypoint;
		if(number>0)
		{
			this.getKeyList().add(key);
			for(int i=0;i<number-1;i++)
			{
				this.getKeyList().add(right.getKeyList().get(i));
				((InnerNode<T>)this).getIndexList().add(((InnerNode<T>)right).getIndexList().get(i));
			}
			((InnerNode<T>)this).getIndexList().add(((InnerNode<T>)right).getIndexList().get(number-1));
			if(number==right.getKeyList().size())
			{
				keypoint = null;
				this.getKeyList().add(right.getKeyList().get(number-1));
				this.getIndexList().add(((InnerNode<T>)right).getIndexList().get(number));
				((InnerNode<T>)right).getIndexList().subList(0, number+1).clear();
			}
			else
			{
				keypoint = right.getKeyList().get(number-1);	
				((InnerNode<T>)right).getIndexList().subList(0, number).clear();
			}
			
			right.getKeyList().subList(0, number).clear();
			
			//return right.getKeyList().size()>0?right.getKeyList().get(0):null;
			return keypoint;
		}
		else if(number<0)
		{
			number*=-1;
			right.getKeyList().add(key);
			for(int i=0;i<number-1;i++)
			{
				right.getKeyList().add(this.getKeyList().get(i));
				((InnerNode<T>)right).getIndexList().add(((InnerNode<T>)this).getIndexList().get(i));
			}
			keypoint = this.getKeyList().get(number-1);
			this.getKeyList().subList(0, number).clear();
			((InnerNode<T>)this).getIndexList().subList(0, number).clear();
			//return right.getKeyList().size()>0?null:right.getKeyList().get(0);
			return keypoint;
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
	
	public void visitByBFV()
	{
		for(T t:this.getKeyList())
		{
			System.out.print(t+"\t");
		}
		System.out.println();
		System.out.println("*******************");
		
		for(Node<T> n:this.getIndexList())
		{
			System.out.println("?????????????????????");
			n.visitByBFV();
			
		}
	}
	
}

package cn.cnic.algorithm.tree.bplustree;
import java.util.List;

import com.google.common.collect.Lists;

public abstract class Node<T> {

	
	 List<T> data = Lists.newLinkedList();

	 abstract Value findOne(T search);
	 ResultSet findAll(T search)
	 {
			return null;
	 }
	 boolean addNode(T node)
	 {
			return null != null;		 
	 }
	 boolean addNode(T node, Value value)
	 {
			return null != null;
			 
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

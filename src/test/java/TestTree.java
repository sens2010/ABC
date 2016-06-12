import org.junit.Test;

import cn.cnic.algorithm.tree.bplustree.BPlusTree;
import cn.cnic.algorithm.tree.bplustree.Value;


public class TestTree {
  
	
	public void testAddTree()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,null);
		}
		bpt.addNode(3,null);
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		bpt.visitAll();
	}
	
	public void testAddTreeWithValue()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			Value v = new Value(i+"");
			System.err.println(v.getText());
			bpt.addNode(i,v);
		}
		bpt.addNode(3,new Value("30"));
		bpt.addNode(3,new Value("31"));
		bpt.addNode(3,new Value("32"));
		bpt.addNode(3,new Value("33"));
		bpt.addNode(3,new Value("34"));
		bpt.addNode(3,new Value("35"));
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		bpt.visitAllWithValue();
	}
	
	public void testAddSplit()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++)
		{
			bpt.addNode(i,null);
		}
		bpt.addNode(3,null);
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		bpt.visitAll();
	}
	
	
	public void testDelete()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,null);
		}
		bpt.removeNode(3);
		bpt.visitAll();
		System.out.println("******************************");
		bpt.removeNode(4);
		bpt.visitAll();
		System.out.println("******************************");
		bpt.removeNode(41);
		bpt.visitAll();
		System.out.println("耗时："+(System.currentTimeMillis()-start));
	}
	
	@Test 
	public void testDeleteWithValue()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,new Value(i+""));
		}
		bpt.removeNode(3);
		bpt.visitAllWithValue();
		System.out.println("******************************");
		bpt.removeNode(4);
		bpt.visitAllWithValue();
		System.out.println("******************************");
		bpt.removeNode(41);
		bpt.visitAllWithValue();
		System.out.println("耗时："+(System.currentTimeMillis()-start));
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

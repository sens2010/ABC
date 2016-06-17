import org.junit.Test;

import cn.cnic.algorithm.tree.bplustree.BPlusTree;
import cn.cnic.algorithm.tree.bplustree.InnerNode;
import cn.cnic.algorithm.tree.bplustree.Node;
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
		
		System.out.println("*********split line**********");
		InnerNode<Integer> in = (InnerNode<Integer>)(bpt.getRoot());
		for(Integer i: in.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		System.err.println(in.findNode(35));
		System.err.println(in.findNode(37));
		System.out.println("*********split line**********");
		Node<Integer> node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.out.println();
		node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
	}
	
	//@Test
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
	
	
	public void testAddTreeWithoutValue()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			///Value v = new Value(i+"");
			//System.err.println(v.getText());
			bpt.addNode(i,null);
		}
		bpt.addNode(3,new Value("30"));
		bpt.addNode(3,new Value("31"));
		bpt.addNode(3,new Value("32"));
		bpt.addNode(3,new Value("33"));
		bpt.addNode(3,new Value("34"));
		bpt.addNode(3,new Value("35"));
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		bpt.visitAllWithValue();
		bpt.visitAll();
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
		for(int i=0;i<200;i++)
		{
			bpt.addNode(i,null);
		}
		/*bpt.removeNode(3);
		bpt.visitAll();*/
		/*System.out.println("******************************");
		bpt.removeNode(4);
		bpt.visitAll();
		System.out.println("******************************");
		bpt.removeNode(41);
		bpt.visitAll();*/
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		System.err.println("*********split line**********");
		InnerNode<Integer> in = (InnerNode<Integer>)(bpt.getRoot());
		for(Integer i: in.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		System.err.println("*********split line**********");
		Node<Integer> node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bpt.removeNode(3);//测试首行节点
		bpt.removeNode(4);//测试删除合并
		bpt.removeNode(17);//测试中间节点删除是否正常
		bpt.removeNode(45);//测试中间内部节点是否删除正常
		bpt.removeNode(44);//测试中间内部节点合并是否正常
		bpt.removeNode(90);//测试中间首个节点合并是否删除正常
		bpt.removeNode(99);//测试最后节点删除是否正常
		bpt.removeNode(91);bpt.removeNode(93);bpt.removeNode(92);bpt.removeNode(94);//测试最后节点删除是否正常
		System.out.println(bpt.removeNode(98));//测试最后一行合并后是否正常删除
		System.out.println(bpt.removeNode(98));//测试删除不存在节点
		bpt.visitAll();
		((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
		
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("******************************");
		//bpt.removeNode(17);
		bpt.visitAll();
		((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
		
		System.err.println("*********split line**********");
		in = (InnerNode<Integer>)(bpt.getRoot());
		for(Integer i: in.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		System.err.println("*********split line**********");
		node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(1);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

	public void testDeleteWithDegress()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
	
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,null);
		}

		bpt.visitByBFV();
		bpt.removeNode(30);//测试首行节点
		bpt.visitByBFV();
		bpt.removeNode(4);//测试删除合并
		bpt.visitByBFV();
		bpt.removeNode(17);//测试中间节点删除是否正常
		bpt.removeNode(45);//测试中间内部节点是否删除正常
		bpt.removeNode(44);//测试中间内部节点合并是否正常
		bpt.visitByBFV();
		bpt.removeNode(90);//测试中间首个节点合并是否删除正常
		bpt.visitByBFV();
		bpt.removeNode(99);//测试最后节点删除是否正常
		bpt.visitByBFV();
		bpt.removeNode(91);bpt.removeNode(93);bpt.removeNode(92);bpt.removeNode(94);//测试最后节点删除是否正常
		System.out.println("###########################");
		bpt.visitByBFV();
		System.out.println(bpt.removeNode(98));//测试最后一行合并后是否正常删除
		bpt.visitByBFV();
		System.out.println(bpt.removeNode(98));//测试删除不存在节点
		bpt.visitByBFV();
		for(int i=0;i<69;i++)//测试删除后树的退化
		{
			bpt.visitByBFV();
			bpt.removeNode(i);
		}
		for(int i=0;i<50;i+=2)//测试删除后树的退化
		{
			bpt.removeNode(i);
		}

		bpt.visitAll();
		
		
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("******************************");
		//bpt.removeNode(17);
		bpt.visitAll();
		((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		
		System.err.println("*********split line**********");
		InnerNode<Integer> in = (InnerNode<Integer>)(bpt.getRoot());
		for(Integer i: in.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		System.err.println("*********split line**********");
		Node<Integer> node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		node  = ((InnerNode<Integer>)(bpt.getRoot())).getIndexList().get(0);
		for(Integer i: node.getKeyList())
		{
			System.err.print(i+"\t");
		}
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//bpt.visitAll();
		bpt.visitByBFV();
		for(int i=0;i<89;i++)//测试删除后树的退化
		{
			bpt.visitByBFV();
			bpt.removeNode(i);
		}
		bpt.visitByBFV();
	}
	
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
		bpt.removeNode(41);
		bpt.visitAllWithValue();
		System.out.println("******************************");
		bpt.removeNode(99);
		bpt.visitAllWithValue();
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		
		InnerNode<Integer> in = (InnerNode<Integer>)(bpt.getRoot());
		for(Integer i: in.getKeyList())
		{
			System.err.print(i+"\t");
		}
		System.err.println();
		System.err.println(in.findNode(99));
		
		
	}
	
	public void testDeleteWithValueWithRoot()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		
		long start = System.currentTimeMillis();
		for(int i=0;i<11;i++)
		{
			bpt.addNode(i,new Value(i+""));
		}
		bpt.visitAllWithValue();
		bpt.removeNode(10);
		bpt.visitAllWithValue();
		System.out.println("耗时："+(System.currentTimeMillis()-start));
	}
	
	
	public void testBFV()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,new Value(i+1));
			bpt.visitByBFV();
		}	
		
	}
	
	//@Test
	public void testFindTree()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,new Value(i+1));
		}	
		bpt.visitByBFV();
		System.out.println(bpt.find(0).getText());
		System.out.println(bpt.find(5).getText());
		System.out.println(bpt.find(6).getText());
		System.out.println(bpt.find(29).getText());
		System.out.println(bpt.find(30).getText());
		System.out.println(bpt.find(36).getText());
		System.out.println(bpt.find(90).getText());
		System.out.println(bpt.find(99).getText());
	}
	
	@Test
	public void testUpdateTree()
	{
		BPlusTree<Integer> bpt = new BPlusTree<Integer>();
		for(int i=0;i<100;i++)
		{
			bpt.addNode(i,new Value(i+1));
		}	
		bpt.visitByBFV();
		System.out.println(bpt.find(0).getText());
		System.out.println(bpt.find(5).getText());
		System.out.println(bpt.find(6).getText());
		System.out.println(bpt.find(29).getText());
		System.out.println(bpt.find(30).getText());
		System.out.println(bpt.find(36).getText());
		System.out.println(bpt.find(90).getText());
		System.out.println(bpt.find(99).getText());
		
		System.out.println(bpt.update(0,new Value("2")));
		System.out.println(bpt.update(5,new Value("7")));
		System.out.println(bpt.update(6,new Value("8")));
		System.out.println(bpt.update(29,new Value("31")));
		System.out.println(bpt.update(30,new Value("30")));
		System.out.println(bpt.update(36,new Value("37")));
		System.out.println(bpt.update(90,new Value("90")));
		System.out.println(bpt.update(99,new Value("99")));
		
		
		System.out.println(bpt.find(0).getText());
		System.out.println(bpt.find(5).getText());
		System.out.println(bpt.find(6).getText());
		System.out.println(bpt.find(29).getText());
		System.out.println(bpt.find(30).getText());
		System.out.println(bpt.find(36).getText());
		System.out.println(bpt.find(90).getText());
		System.out.println(bpt.find(99).getText());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

package cn.cnic.algorithm.tree.bplustree;
import cn.cnic.algorithm.common.StringFunnel;
import com.google.common.hash.BloomFilter;
public class BPlusTree<T extends Comparable<T>> {
	BloomFilter<T> bloomfilter;
	
	Node<T> root;
	
	
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
		System.out.println("root size1:"+root.getKeyList().size());
		Node<T> newnode= root.addNode(key, value);
		
		if(newnode!=null)
		{
			InnerNode<T> temp = new InnerNode<T>();
			temp.getKeyList().add(newnode.getAcientKey());
			temp.getIndexList().add(root);
			System.out.println("root size:"+root.getKeyList().size());
			temp.getIndexList().add(newnode);
			System.out.println("new size:"+newnode.getKeyList().size());
			System.out.println("temp size:"+temp.getKeyList().size());
			root = temp;
			System.out.println("root size:"+root.getKeyList().size());
			System.out.println("root size4:"+root.getKeyList().get(0));
			System.out.println("temp size4:"+temp.getKeyList().get(0));
			
		}
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

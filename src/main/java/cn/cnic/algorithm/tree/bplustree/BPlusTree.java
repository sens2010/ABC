package cn.cnic.algorithm.tree.bplustree;
import cn.cnic.algorithm.common.StringFunnel;
import com.google.common.hash.BloomFilter;
public class BPlusTree<T> {
	BloomFilter<T> bloomfilter;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BloomFilter<String> bf = BloomFilter.create(new StringFunnel(),10000);
		bf.put("124");
		System.out.println(bf.mightContain("124"));
	}

}

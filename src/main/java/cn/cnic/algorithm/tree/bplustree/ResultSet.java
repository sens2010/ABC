package cn.cnic.algorithm.tree.bplustree;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class ResultSet {
	
	private List<Integer> keylist = Lists.newLinkedList();
	public ResultSet add(int key)
	{
		int end = keylist.size()-1;
		int result = 0;
		if(end>=0&&keylist.get(end)>key)
		{
			result = insert(0,end,key);
		}
		else
		{
			if(end>=0)
			{
				result=end+1;
			}
			keylist.add(key);
		}
		System.out.println("插入位置："+result);
		System.out.print("插入结果");
		for(int i:keylist)
		{
			System.out.print(i+"    ");
		}
		System.out.println();
		return this;
	}
	public int insert(int start, int end, int key)
	{
		int mid = (start+end+1)/2;
		if(keylist.get(mid)==key||start==end)
		{
			if(keylist.get(mid)<=key)
			{
				keylist.add(mid+1,key);
				return mid+1;
			}
			else
			{
				keylist.add(mid,key);
				return mid;
			}
		}
		else if(keylist.get(mid)>key)
		{
			return insert(start, mid-1, key);
		}
		else
		{
			return insert(mid+1,end, key);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ResultSet rs = new ResultSet();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			 
			int key = sc.nextInt();
			rs.add(key);
		
		}
		//sc.close();
		
	}

}

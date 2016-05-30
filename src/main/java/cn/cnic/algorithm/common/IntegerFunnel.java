package cn.cnic.algorithm.common;

import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class IntegerFunnel implements Funnel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	public void funnel(Integer from, PrimitiveSink into) {
		into.putInt(from);
	}

}

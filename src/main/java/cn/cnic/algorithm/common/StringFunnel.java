package cn.cnic.algorithm.common;

import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class StringFunnel implements Funnel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

	public void funnel(String from, PrimitiveSink into) {
		into.putUnencodedChars(from);
		
	}

}

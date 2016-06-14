package cn.cnic.algorithm.tree.bplustree;


public class Value {

	private Object text;
	public  Value()
	{
	}
	
	public Value(Object text)
	{
		this.text=text;
	}
	
	public Object getText()
	{
		return this.text==null?"":this.text;
	}
	
	public void setText(Object text)
	{
		this.text = text;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//IntNode.java
//
//Author: SHAI PE'ER   Email: shaip86@gmail.com     Date:08.05.2013

public class IntNode
{
	private int[] value = new int[2];
	private IntNode next;
	
	public IntNode(int x, int y, IntNode n)
	{
		value[0] = x;
		value[1] = y;
		next = n;
	}
	
	public void setValue(int[] val)
	{ 
		value[0] = val[0];
		value[1] = val[1];
	}
	
	public void setX(int x)		   { value[0] = x; }
	
	public void setY(int y) 	   { value[1] = y; }
	
	public void setNext(IntNode n) { next = n;     }
	
	public int[] getValue()        { return value; }
	
	public IntNode getNext()       { return next;  }
}
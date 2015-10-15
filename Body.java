//Body.java
//
//Author: SHAI PE'ER   Email: shaip86@gmail.com     Date:08.05.2013

public class Body 
{
	
	public IntNode head;  //snake tail
	
	//initialize head.
	public Body()
	{
		head = null;
	}
	
	//add snake head by x and y
	public void add(int x, int y)
	{
		IntNode newElement = new IntNode(x, y, head);
		
		if (head == null)
			head = newElement;
		else
		{
			IntNode p;
			for(p = head; p.getNext()!=null; p = p.getNext());
			
			IntNode temp = new IntNode(x,y, null);
			p.setNext(temp);

		}
	}
	
	//remove snake tail
	public void remove()
	{
		head = head.getNext();
	}
	
	public IntNode getSnakeTail()
	{
		return head;
	}
	
	//print snake coordinates
	public String toString()
	{
		String snakeString = "";
		
		for(IntNode pointer = head ; pointer!=null ; pointer = pointer.getNext())
			snakeString += "("+ pointer.getValue()[0] +","+ pointer.getValue()[1]+"), ";
		
		return snakeString;
	}
	
}

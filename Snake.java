//Snake.java
//
//Author: SHAI PE'ER   Email: shaip86@gmail.com     Date:08.05.2013

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Snake 
{
	private Body b;	//Creates the body of the snake
	
	//Location and Movement
	private int _x, _y;  			//the snake head location
	private final int _step = 10;   //the step size for each frame;
	private int _stepX, _stepY;  	//the steps to add by any direction, Changes by KeyPress
	private int _lastMove; 			//remember the last move to block moving backwards  
	
	//Snake Links
	private int _linksToAdd; //Gets how many links to add
	
	//Apple
	private int[] _appleLoc = new int[2];   	//Apple location
	private final int _appleSize = 40;			//Apple size 
	private Boolean _eaten;  					//Check if the apple have been eaten
	private final int MAX_APPLE_LOCATION = 550; //Apple location borders
	private final int MIN_APPLE_LOCATION = 20;  //For the random location
	
	//Images
	private Image imgSnake;
	private Image imgApple;
	
	//Score Counter
	private int _score = 0;
	private final int SCORE_BONUS = 10; //score for every apple eat
	
	public Snake()
	{
		//Snake Body Draw
		ImageIcon ii = new ImageIcon("C:/snakeBody.PNG");
		imgSnake = ii.getImage();
		
		//Apple Draw
		ImageIcon iii = new ImageIcon("C:/apple.PNG");
		imgApple = iii.getImage();
		
		//Snake
		b = new Body();
		_linksToAdd = 6; 	//first body length
		_x = 200;		 	//starting snake location X
		_y = 200;		 	//starting snake location Y
		_stepX = _step;  	//first move direction
		_stepY = 0;		 	//first move direction
		
		//Apple
		_appleLoc[0] = 200; //first apple location X
		_appleLoc[1] = 150;	//first apple location Y
	}
	
	
	//The snake movement behavior 
	public void move()
	{
		_x += _stepX*2;
		_y += _stepY*2;
		
		addHeadLink(_x,_y); //add link to move forward
		
		//remove the last link every step
		if (_linksToAdd <= 0)
			removeLastLink();
		else
			_linksToAdd--;
		
		//screen rotation - if the snake gets to the end of the screen, coming back from the other
		if (_x <= 0)        {_x = 698;}
		else if (_x >= 698) {_x = 1;  }
		if (_y <= 0)        {_y = 658;}
		else if(_y >= 659)  {_y = 1;  }
			
		System.out.println("x: "+_x +"   y: "+ _y +"   <<MOVE>> ");
	}
	
	//Add Head Link
	public void addHeadLink(int x, int y) { b.add(x, y); }
	
	//Remove Last Link
	public void removeLastLink() { b.remove();}
	
	//Add Apple
	public void addApple()
	{
		_eaten = isAppleBeenEaten();
		
		if (_eaten)
		{
			_linksToAdd += 5;
			generateRandomApple();
		}
		scoreUpdate();
	}
	
	
	//check if the snake collide itself
	public Boolean collision()
	{
		for(IntNode p = b.getSnakeTail(); p.getNext()!=null; p = p.getNext())
			if (p.getValue()[0] == _x && p.getValue()[1] == _y)
					return true;
		
		return false;
	}
	
	//return the snake head
	private IntNode snakeHead()
	{
		IntNode p;
		for(p = b.getSnakeTail(); p.getNext()!=null; p = p.getNext());
		return p;
	}
	
	//Return if an apple been eaten
	private Boolean isAppleBeenEaten()
	{
		IntNode snakeHead = snakeHead();
		
		if ((snakeHead.getValue()[0]+ _step/2 >= _appleLoc[0] &&
			 snakeHead.getValue()[0]+ _step/2 <= _appleLoc[0]+_appleSize) &&
			(snakeHead.getValue()[1]+ _step/2 >= _appleLoc[1] && 
			 snakeHead.getValue()[1]+ _step/2 <= _appleLoc[1]+_appleSize))
					{System.out.println("<<<<<<<<<<<<<<<<< appleBeenEaten >>>>>>>>>>>>>>>>>>>>>>");
					return true;}
		else
			return false;
	}
	
	//Generate new Random Apple
	private void generateRandomApple()
	{
		Boolean randomApple = true;
		while (randomApple)
		{
			_appleLoc[0] = (int)(  ( Math.random() * (MAX_APPLE_LOCATION - MIN_APPLE_LOCATION) ) + MIN_APPLE_LOCATION  );
			_appleLoc[1] = (int)(  ( Math.random() * (MAX_APPLE_LOCATION - MIN_APPLE_LOCATION) ) + MIN_APPLE_LOCATION  );
			
			for(IntNode p = b.getSnakeTail(); p.getNext()!=null; p = p.getNext())
				if (p.getValue()[0] == _appleLoc[0] && p.getValue()[1] == _appleLoc[1])
					randomApple = true;
			
			randomApple = false;
		}
	}
	
	//update the score
	private void scoreUpdate()
	{
		if (_eaten)
			_score += SCORE_BONUS;
	}
	
	//  >>>>   Getters   <<< 
	public int getAppleSize()	 { return _appleSize; }
	
	public int[] getAppleLoc()   { return _appleLoc;  }
	
	public Image getSnakeImage() { return imgSnake;   }
	
	public Image grtAppleImage() { return imgApple;   }
	
	public int getScore()        { return _score;     }
	
	public Body getBody()    	 { return b;    	  }
	
	
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT && _lastMove != KeyEvent.VK_RIGHT)
		{
			_stepX = -1*_step;
			_stepY = 0;
			
			System.out.println("_stepX: "+_stepX +"   _stepY: "+ _stepY +"   >>LEFT");
		}
		else if (key == KeyEvent.VK_RIGHT && _lastMove != KeyEvent.VK_LEFT)
		{
			_stepX = _step;
			_stepY = 0;
			//_x += _step;
			System.out.println("_stepX: "+_stepX +"   _stepY: "+ _stepY +"   >>RIGHT");
		}
		else if (key == KeyEvent.VK_UP && _lastMove != KeyEvent.VK_DOWN)
		{
			_stepX = 0;
			_stepY = -1*_step;
			
			System.out.println("_stepX: "+_stepX +"   _stepY: "+ _stepY +"   >>UP");
		}
		else if (key == KeyEvent.VK_DOWN && _lastMove != KeyEvent.VK_UP)
		{
			_stepX = 0;
			_stepY = _step;
			
			System.out.println("_stepX: "+_stepX +"   _stepY: "+ _stepY +"   >>DOWN");
		}
		
		else if (key == KeyEvent.VK_1)  //for check if the snake coordinates are ok
		{
			System.out.println(b);
		}
		
		_lastMove = key;
	}
	
}

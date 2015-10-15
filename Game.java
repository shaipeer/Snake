//Game.java
//
//Author: SHAI PE'ER   Email: shaip86@gmail.com     Date:08.05.2013

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;


public class Game extends JPanel implements ActionListener
{
	//Snake
	private Snake _s; //Snake object
	private Color hColor = Color.GREEN; //Snake Head Color
	
	//Game
	private Timer _time; 				//Timer object
	private int _frameSize;				//get the frame size
	private JLabel _l; 					//score label
	private Boolean _gameOver = false;
	
	//Images
	private Image img;
	private Image img2;
	
	
	public Game(int frameSize)
	{
		_s = new Snake();
		_frameSize = frameSize; //get the frame size of the game
		
		//Key Listener
		addKeyListener(new AL());
		setFocusable(true);
		
		//BackGround
		ImageIcon bgImg = new ImageIcon("C:/BG.PNG");
		img = bgImg.getImage(); 
		
		//GameOver
		ImageIcon gameOver = new ImageIcon("C:/gameOver.PNG");
		img2 = gameOver.getImage(); 
		
		//Score
		_l = new JLabel();
		add(_l);
		setLayout(new GridLayout(1, 8));
		add(empty());	add(empty());	add(empty());
		add(empty());	add(empty());	add(_l);
		
		//Timer
		_time = new Timer (100, this);
		_time.start();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		_s.move();
		repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
			Graphics2D g2d =(Graphics2D) g;
			
			//BackGraound Draw
			g2d.drawImage(img, 0, 0, _frameSize, _frameSize , null);
			
			//Score Draw
			_l.setText("SCORE: " + _s.getScore());
			_l.setFont(new Font("arial", 24, 24));
			
			//Snake Body Draw - draw by LinkedList
			IntNode p;
			for(p = _s.getBody().head ; p.getNext()!=null ; p = p.getNext())
				g2d.drawImage(_s.getSnakeImage(), p.getValue()[0], p.getValue()[1], 20, 20, null);
			
			//Snake head draw
			g.setColor(hColor);
			g.fillOval(p.getValue()[0], p.getValue()[1], 20, 20);
			
			//Apple Draw
			g2d.drawImage(_s.grtAppleImage(), _s.getAppleLoc()[0], _s.getAppleLoc()[1], _s.getAppleSize(), _s.getAppleSize(), null);
			_s.addApple();	//add apple
			
			// GAME OVER
			if (_s.collision())
				_gameOver = true;
			if (_gameOver)
				g2d.drawImage(img2, 0, 0, _frameSize, _frameSize, null);
	}
	
	
	private class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			_s.keyPressed(e);
		}
	}
	
	
	protected JComponent score() { return _l; }
	
	protected JComponent empty() 
	{
		JPanel emptyPanel = new JPanel();
	    return emptyPanel;
	}
	
	
}

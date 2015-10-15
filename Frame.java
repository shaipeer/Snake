//Frame.java
//
//Author: SHAI PE'ER   Email: shaip86@gmail.com     Date:08.05.2013


import javax.swing.JFrame;


public class Frame {

	public static void main(String[] args) 
	{
		int frameSize = 700;
		int controlPanelSize = 170;
		
		JFrame frame = new JFrame("SNAKE");
		frame.add(new Game(frameSize));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameSize+controlPanelSize,frameSize);
		frame.setVisible(true);
		
	}

}

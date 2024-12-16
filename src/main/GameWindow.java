package main;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

import Utilz.LoadSave;

public class GameWindow {
  private JFrame frame;	
  private Image icon=new ImageIcon(this.getClass().getResource("/Icon.png")).getImage();
  public GameWindow(GamePanel MainPanel){
	frame=new JFrame("Space game");
	frame.setResizable(false);
	frame.add(MainPanel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.pack();
	frame.setIconImage(icon);
	frame.setVisible(true); 
	frame.setLocationRelativeTo(null);
	frame.addWindowFocusListener(new WindowFocusListener() {

		@Override
		public void windowGainedFocus(WindowEvent e) {
			
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			MainPanel.getGame().WindowFocusLost();
			
		}
	});
  }
}


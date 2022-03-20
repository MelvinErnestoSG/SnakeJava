package snakeframe;

import javax.swing.JFrame;
//import javax.swing.JRootPane;

import gamepanel.GamePanel;
import lobbypanel.LobbyPanel;

public class SnakeFrame extends JFrame
{
	/**
	 * Melvin Ernesto Santana Garcia
	 */
	private static final long serialVersionUID = 1L;
	LobbyPanel lobbyPanel = new LobbyPanel(this);
	GamePanel gamePanel = new GamePanel(null);
	private boolean addedLeaderboard = false;
	
	public SnakeFrame()
	{
		/*this.setUndecorated(true); 
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);*/
        this.add(lobbyPanel);
        this.setTitle("Java Snakey"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		this.add(gamePanel);
    }
	
	public void SwitchToGamePanel()
	{
		gamePanel.setVisible(true);
		gamePanel.requestFocus();
		gamePanel.startGame();
	}
		
	public void SwitchToLeaderboardPanel()
	{
		if(addedLeaderboard)
		{
			lobbyPanel.setVisible(false);
		}
		else
		{
			lobbyPanel.setVisible(false);
			gamePanel.setVisible(false);
			addedLeaderboard = true;
		}
	}
}
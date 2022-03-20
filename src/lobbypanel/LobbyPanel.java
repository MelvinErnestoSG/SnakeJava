package lobbypanel;

import gamepanel.GamePanel;
import snakeframe.SnakeFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LobbyPanel extends JPanel
{
	/**
	 * Melvin Ernesto Santana Garcia
	 */
	private static final long serialVersionUID = 1L;
	public static final String TITLE_MESSAGE = "Java Snake";
    public static final String CREATOR_MESSAGE = "Created by @Melvin Ernesto Santana Garcia in 2022";
    public static final Font TITLE_FONT = new Font("Playball", 0 , 62);
    public static final Font MENU_FONT = new Font("Arial", 0 , 28);
    public static final Font CREATOR_FONT = new Font("Arial", 0 , 14);
    public static final int SCREEN_WIDTH = GamePanel.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    public static final String[] MENU_ITEMS = {"Play","Exit"};
    private int selectedMenuItem = 0;
	SnakeFrame parentFrame;

    public LobbyPanel(JFrame frame)
    {
		parentFrame = (SnakeFrame) frame;
        this.addKeyListener((KeyListener) new MyKeyAdapter());
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        //Lo hacemos focusable para que tome teclas
        this.setFocusable(true);
		this.requestFocus();
    }

    public void paintComponent(Graphics g)
    {
        //Tenemos que llamar al paintComponent() de super para no perder 
    	//el background color seteado en JPanel.
        super.paintComponent(g);
        DrawTitle(g);
        DrawMenu(g);
        DrawCreator(g);
    }

    private void DrawTitle(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(TITLE_FONT);
        FontMetrics metrics = g.getFontMetrics();
        int x = (SCREEN_WIDTH - metrics.stringWidth(TITLE_MESSAGE)) / 2;
        int y = metrics.getHeight() + 100;
        g.drawString(TITLE_MESSAGE, x, y);
    }

    private void DrawMenu(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(MENU_FONT);
        FontMetrics metrics = g.getFontMetrics();
        for(int i = 0; i < MENU_ITEMS.length; i++)
        {
            int x = (SCREEN_WIDTH - metrics.stringWidth(MENU_ITEMS[i])) / 2; //Horizontal center
            int y = metrics.getHeight() + 300 + (i * (metrics.getHeight() + 20));
            g.drawString(MENU_ITEMS[i], x, y);
            
            if (selectedMenuItem == i)
            {
                DrawTriangle(x - 30, y - 20, g);
            }
        }
    }

    private void DrawTriangle(int x, int y, Graphics g)
    {
        g.setColor(Color.white);
        int[] xPoints = {x, x + 20, x};
        int[] yPoints = {y, y+10, y+20};
        g.fillPolygon(xPoints, yPoints, 3);
    }

    private void DrawCreator(Graphics g)
    {
        g.setColor(Color.white);
        g.setFont(CREATOR_FONT);

        FontMetrics metrics = g.getFontMetrics();
        int x = SCREEN_WIDTH - metrics.stringWidth(CREATOR_MESSAGE) - 10;
        int y = SCREEN_WIDTH - metrics.getHeight();

        g.drawString(CREATOR_MESSAGE, x, y);
    }

    private class MyKeyAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    DecrementMenu();
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    IncrementMenu();
                    repaint();
                    break;
                case KeyEvent.VK_ENTER:
                    switchPanels();
					break;
            }
        }
    }

    private void switchPanels()
    {
        switch(selectedMenuItem)
        {
            case 0:
                parentFrame.SwitchToGamePanel();
                break;
             case 1:
                System.exit(0);
                break;
        }
    }

    private void IncrementMenu()
    {
        //Siempre escribir las clases y los metodos pensando en expansion. 
    	//Mientras menos toquemos el source code mejor
        int lastItemIndex = MENU_ITEMS.length - 1;
        
        if(selectedMenuItem < lastItemIndex)
        {
            selectedMenuItem++;
        }
        else
        {
            selectedMenuItem = 0;
        }
    }

    private void DecrementMenu()
    {
        int lastItemIndex = MENU_ITEMS.length - 1;
        
        if(selectedMenuItem > 0)
        {
            selectedMenuItem--;
        }
        else
        {
            selectedMenuItem = lastItemIndex;
        }
    }

    public int getSelectedMenuItem()
    {
        return selectedMenuItem;
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 20;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
      static int x = 240 ;
      static int y = 240 ;
      public static int[] monstersX = new int[Main.numberOfMonsters]; 
      public static int[] monstersY = new int[Main.numberOfMonsters];
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
      
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

      public static void islemler(int id) {
            
            int xLerFarki,yLerFarki ;
            int xtmp = x - monstersX[id];
            int ytmp = y - monstersY[id];
            xLerFarki = Math.abs(xtmp) ;
            yLerFarki = Math.abs(ytmp) ;

            if(xLerFarki>=yLerFarki){
                  // o zaman once y'leri 0 la...
                  if(ytmp<0)
                        monstersY[id] = monstersY[id] - 10 ;
                  else 
                        monstersY[id] = monstersY[id] + 10 ;

                        if(yLerFarki <=20){
                              if(xtmp<0)
                              monstersX[id] = monstersX[id] - 10 ;
                              else 
                              monstersX[id] = monstersX[id] + 10 ;
                        }
            }
            if(xLerFarki<yLerFarki){
                  if(xtmp<0)
                        monstersX[id] = monstersX[id] - 10 ;
                  else 
                        monstersX[id] = monstersX[id] + 10 ;

                  if(xLerFarki <=20){
                        if(ytmp<0)
                        monstersY[id] = monstersY[id] - 10 ;
                        else 
                        monstersY[id] = monstersY[id] + 10 ;
                  }
            }
            
      }

      public static void setTheMonsters(){
            
            for(int i = 0 ; i<Main.xMonsters.size();i++){
                  monstersX[i] = Main.xMonsters.get(i);
            }

            for(int i = 0 ;i<Main.yMonsters.size();i++){
                  monstersY[i] = Main.yMonsters.get(i);
            }
      }

	public void startGame() {
		running = true;
		timer = new Timer(0,this);
		timer.start();
            
	}
      
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
			
                  g.setColor(Color.green);
			
                  g.fillRect(x, y, UNIT_SIZE, UNIT_SIZE);
                  
                  g.setColor(Color.blue);
                  for(int i = 0 ; i<monstersX.length;i++){
                       
                        g.fillRect(monstersX[i], monstersY[i], UNIT_SIZE, UNIT_SIZE);
                  }
                 

		}
		else {
			gameOver();
		}
		
	}

	public void gameOver() {
            Main.gameOver = true ;
		System.exit(0);
	}
      
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
                  checkCollisions();
			
		}
		repaint();
	}

      private void checkCollisions() {
           Components[] components_array = new Components[monstersX.length+1];
           components_array[0] = new Components(GamePanel.x,GamePanel.y);
           for(int i = 1 ; i<components_array.length;i++){
                  components_array[i] = new Components(monstersX[i-1], monstersY[i-1]);
           }

           for(int i = 1 ; i<components_array.length;i++){
                  if(components_array[0].getBounds().intersects(components_array[i].getBounds())){
                        System.out.println("Collision Happened. Game Over.");
                        gameOver();
                  }
           }

      }

      public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
                        if( !(x <= 0))
                              x = x - 10;
                        break;

			case KeyEvent.VK_D:
                        if( !(x >= 480))
                              x = x + 10;
                        break;
                 
			case KeyEvent.VK_W:
                        if( !(y <= 0))
                              y = y - 10;
                        break;     
                 
			case KeyEvent.VK_S:
                        if( !(y >= 480))
                              y = y + 10;
                        break;
                
			}
		}
	}      
}
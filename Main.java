import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class Main extends JFrame {
	
	public static ArrayList<Integer> xMonsters = new ArrayList<>();
	public static ArrayList<Integer> yMonsters = new ArrayList<>();
	public static boolean gameOver = false ;
	public static int sayac = 0 ;

	public static int numberOfMonsters ;
	public Main(){
		this.add(new GamePanel());
		this.setTitle("Kovalamaca Oyunu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args) {
		
		int number_of_monsters = 20 ;//Integer.parseInt(args[0]);
		numberOfMonsters = number_of_monsters ;
		System.out.println(number_of_monsters);
		Main m = new Main();
		
		Main.Monster [] monsters = new Main.Monster[number_of_monsters];
		
		Random r = new Random();
		
		for(int i=0;i<number_of_monsters;i++)
		{
			monsters[i] = m.new Monster(Math.abs(r.nextInt()%500),Math.abs(r.nextInt()%500));		
			
		}
		
		for(int i=0;i<number_of_monsters;i++)
			monsters[i].start();
		
		try {
			for(int i=0;i<number_of_monsters;i++)
				monsters[i].join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public class Monster extends Thread {

		public int id ;
		
		public Monster(int x, int y) {
			this.id = sayac;
			xMonsters.add(x);
			xMonsters.trimToSize();
			yMonsters.add(y);
			yMonsters.trimToSize();
			GamePanel.setTheMonsters();
			sayac++;
		}

		public void run(){
			
			while(gameOver == false){
				
				GamePanel.islemler(id);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Buraya girildi hata var !");
					e.printStackTrace();
				}
			}
		}
	}
	

}

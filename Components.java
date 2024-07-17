import javax.swing.JComponent;

public class Components extends JComponent{
      private int x ;
      private int y ;

      public Components(int newX, int newY){
            this.x = newX ;
            this.y = newY ;
            setBounds(x,y,20,20);
      }

      public int getX(){
            return x ;
      }

      public int getY(){
            return y ;
      }

      public void setX(int newX){
            this.x = newX;
      }

      public void setY(int newY){
            this.y = newY;
      }
      
}

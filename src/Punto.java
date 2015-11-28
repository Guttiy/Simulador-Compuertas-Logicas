
import java.awt.Graphics;
import java.io.Serializable;




public class Punto implements Serializable{
   	private static final long serialVersionUID = 1L;
	public int x;
    public int y;
    public Punto (){}
    public Punto(int _X, int _Y){
        x = _X;
        y = _Y;
    }
    public Punto(String texto){
        String[]campos=texto.split(":");
        x=Integer.parseInt(campos[0]);
        y=Integer.parseInt(campos[1]);
    }
    
    @Override
    public String toString(){
        return x+ ":"+ y;
    }
    
    
    public void dibujar(Graphics g){
       g.drawLine(x, y, x, y);
    }
}

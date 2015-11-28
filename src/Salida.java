
import java.awt.Graphics;


public class Salida extends Punto{
   	private static final long serialVersionUID = 1L;
	public boolean sal, evaluado = false;
    int identif;

    
    public Salida( int _x, int _y, int _identif){
        super(_x,_y);
        identif = _identif;
    }
    
    public Salida(String texto){
        String[] campos=texto.split(";");
        x= Integer.parseInt(campos[0]);
        y= Integer.parseInt(campos[1]);
        sal=Boolean.parseBoolean(campos[2]);
        evaluado=Boolean.parseBoolean(campos[3]);
        identif= Integer.parseInt(campos[4]);
        
    }
    
    public void dibujar(Graphics g) {
        
        g.drawLine(x, y, x+10, y);
        g.drawRect(x+10, y-5, 10, 10);
        if (evaluado==true) {
            char a;
            if (sal==true) {
                a='1';
            } else {
                a='0';
            }
            g.drawString(""+a, x+12, y+5);
        }
    }
    
    @Override
    public String toString(){
        return x+ ";"+ y+";"+sal+";"+evaluado+";"+identif;
    }
}
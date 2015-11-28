
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USUARIO
 */

public class Entrada extends Punto{
    
	private static final long serialVersionUID = 1L;
	public boolean ent;

    public Entrada( int _x, int _y, boolean _ent){
        super(_x,_y);
        ent=_ent;
    }
    public Entrada(String texto){
        String[]campos=texto.split(";");
        x= Integer.parseInt(campos[0]);
        y= Integer.parseInt(campos[1]);
        ent=Boolean.parseBoolean(campos[2]);
    }
    
    
    @Override
    public String toString(){
        return x+ ";"+ y+";"+ent;
    }
    
    public void dibujar(Graphics g) {
        
        g.drawLine(x, y, x-10, y);
        g.drawRect(x-20, y-5, 10, 10);
        if (ent==false) {
            g.drawString("0", x-18, y+5);
        } else {
            g.drawString("1", x-18, y+5);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

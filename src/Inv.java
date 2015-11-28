import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ivab
 */
public class Inv extends Compuertas {
    
    public Inv(int _identificador, Punto _pos){
        super(_identificador, 1, 1, _pos);
        
    }
    
    
    public Inv(String texto){
        String[]campos=texto.split(";");
        identificador=Integer.parseInt(campos[0]);
        Punto p=new Punto (campos[1]);
        pos=p;
        campos[2]=campos[2].substring(1, campos[2].length()-1);
        String[]pines=campos[2].split(" ");
        pines[0]=pines[0].substring(0, pines[0].length()-1);
        for(int i =0; i<2; i++){
            Pin pi = new Pin (pines[i]);
            terminales.add(pi);
        }
    } 
    

    @Override
    public boolean evaluar() {
        boolean a = false, sal = false;
        
        revisar_entradas();
        
        for (Pin p:terminales) {
            if (p.flujo==true) {
                a=p.valor;
            }
        }
        for (Pin p:terminales) {
            if (p.flujo==false) {
                p.valor=logica(a);
                sal=p.valor;
            }
        }
        return sal;
    }
    

    public boolean logica(boolean a) {
        return !a;
    }
    
    @Override
    public void pos_pin(){
        for (Pin pin: terminales) {
            if (pin.flujo==true) {
                pin.pos.x=this.pos.x;
                pin.pos.y=this.pos.y+20;
            } else {
                pin.pos.x=this.pos.x+90;
                pin.pos.y=this.pos.y+20;
            }
        }
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawLine(pos.x, pos.y+20, pos.x+20, pos.y+20);
        g.drawLine(pos.x+75, pos.y+20, pos.x+90, pos.y+20);
        g.drawLine(pos.x+20, pos.y, pos.x+20, pos.y+40);
        g.drawLine(pos.x+20, pos.y, pos.x+65, pos.y+20);
        g.drawLine(pos.x+20, pos.y+40, pos.x+65, pos.y+20);
        g.drawOval(pos.x+65, pos.y+15, 10, 10);
        g.drawString("INV "+identificador, pos.x+23, pos.y+25);
    }

    @Override
    public String toString() {
                return 5+"/<"+identificador+";"+pos+";"+terminales+">";

    }

    @Override
    public boolean logica(boolean a, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}


import java.awt.Graphics;


public class Multiplicador extends Compuertas {
    
    public Multiplicador(int _identificador, Punto p){
        super(_identificador, 1, 2, p);
    }
    
    
    public Multiplicador(String texto){
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
    

    public boolean logica(boolean a) {
      return a;  
    }
    
    
    @Override
    public void pos_pin(){
        int ent=0;
        for (Pin pin: terminales) {
            if (pin.flujo==false) {
                if (ent==0) {
                    pin.pos.x=this.pos.x +90;
                    pin.pos.y=this.pos.y+10;
                    ent=1;
                } else {
                    pin.pos.x=this.pos.x+90;
                    pin.pos.y=this.pos.y+30;
                }
            } else {
                pin.pos.x=this.pos.x;
                pin.pos.y=this.pos.y+20;
            }
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
                p.evaluado=true;
                sal=p.valor;
            }
        }
        return sal;
    }
    
    @Override
    public void dibujar(Graphics g) {
        
        g.drawLine(pos.x, pos.y+20, pos.x+20, pos.y+20);
        
        g.drawLine(pos.x+20, pos.y, pos.x+20, pos.y+40);
        g.drawLine(pos.x+20, pos.y, pos.x+65, pos.y+20);
        g.drawLine(pos.x+20, pos.y+40, pos.x+65, pos.y+20);
        g.drawLine(pos.x+65, pos.y+20, pos.x+75, pos.y+20);
        g.drawLine(pos.x+75, pos.y+10, pos.x+75, pos.y+30);
        g.drawLine(pos.x+75, pos.y+10, pos.x+90, pos.y+10);
        g.drawLine(pos.x+75, pos.y+30, pos.x+90, pos.y+30);
        g.drawString("MUL "+identificador, pos.x+23, pos.y+25);
        
    }


    @Override
    public String toString() {
        return 6+"/<"+identificador+";"+pos+";"+terminales+">";
    }
    

    @Override
    public boolean logica(boolean a, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}

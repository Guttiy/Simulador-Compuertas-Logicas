
import java.awt.Graphics;




public class And extends Compuertas{
    
    public And(int _identificador, Punto p){
        super(_identificador ,2, 1, p);
    } 
    
    public And(String texto){
        String[]campos=texto.split(";");
        identificador=Integer.parseInt(campos[0]);
        Punto p=new Punto (campos[1]);
        pos=p;
        campos[2]=campos[2].substring(1, campos[2].length()-1);
        String[]pines=campos[2].split(" ");
        pines[0]=pines[0].substring(0, pines[0].length()-1);
        pines[1]=pines[1].substring(0, pines[1].length()-1);
        for(int i =0; i<3; i++){
            Pin pi = new Pin (pines[i]);
            terminales.add(pi);
        }
    } 
    
    @Override
    public boolean logica(boolean a, boolean b){
        return a && b;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawLine(pos.x, pos.y+10, pos.x+20, pos.y+10);
        g.drawLine(pos.x, pos.y+30, pos.x+20, pos.y+30);
        g.drawLine(pos.x+20, pos.y, pos.x+20, pos.y+40);
        g.drawLine(pos.x+70, pos.y+20, pos.x+90, pos.y+20);
        g.drawArc(pos.x-25, pos.y, 94, 40, 0, 92);
        g.drawArc(pos.x-25, pos.y, 94, 40, 0, -92);
        g.drawString("AND "+identificador, pos.x+27, pos.y+25);
    }

    @Override
    public String toString() {
    return 1+"/<"+identificador+";"+pos+";"+terminales.toString()+">";
    }
}

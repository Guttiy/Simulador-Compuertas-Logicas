
import java.awt.Graphics;

public class Xor extends Compuertas{
    
    public Xor(int _identificador, Punto _pos ){
        super(_identificador, 2, 1, _pos);
    } 
    
    
    public Xor(String texto){
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
        
        if (a == false && b == false){
            return false;
        }else{
            if(a == false && b == true){
                return true;
            }else{
                if(a == true && b == false){
                    return true;
                }else{
                    if(a == true && b == true){
                        return false;
                    }
                }
            }
        }
        return false;
    }
    

    @Override
    public void dibujar(Graphics g) {
        g.drawLine(pos.x, pos.y+10, pos.x+16, pos.y+10);
        g.drawLine(pos.x, pos.y+30, pos.x+16, pos.y+30);
        g.drawArc(pos.x+20, pos.y, 5, 40, 0, 92);
        g.drawArc(pos.x+20, pos.y, 5, 40, 0, -92);
        g.drawArc(pos.x+13, pos.y, 5, 40, 0, 92);
        g.drawArc(pos.x+13, pos.y, 5, 40, 0, -92);
        g.drawArc(pos.x-20, pos.y, 84, 35, 0, 92);
        g.drawArc(pos.x-20, pos.y+5, 84, 35, 0, -92);
        g.drawLine(pos.x+75, pos.y+20, pos.x+90, pos.y+20);
        g.drawOval(pos.x+65, pos.y+15, 10, 10);
        g.drawString("XOR "+identificador, pos.x+27, pos.y+25);
    }

    @Override
    public String toString() {
        return 4+"/<"+identificador+";"+pos+";"+terminales+">";
    }
}
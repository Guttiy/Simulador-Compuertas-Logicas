
import java.awt.Graphics;
import java.util.ArrayList;



public class Cable {
    int identif;
    Pin term = new Pin(false);
    Pin termi = new Pin(false);
    public Pin[] terminales = { term , termi};
    public ArrayList <Punto> ruta = new ArrayList<Punto>();
    
    public Cable(Punto _ini, Punto _final, int _identif){
        terminales[0].pos.x=_ini.x;
        terminales[0].pos.y=_ini.y;
        terminales[1].pos.x=_final.x;
        terminales[1].pos.y=_final.y;
        identif=_identif;
        for (int i = 0; i < 2; i++) {
            for (Compuertas comp: MyPanel.circuito) {
                for ( Pin p : comp.terminales) {
                    if ((p.pos.x==terminales[i].pos.x) && (p.pos.y==terminales[i].pos.y)) {
                        terminales[i].identificador=p.identificador;
                        terminales[i].flujo=p.flujo;
                        break;
                    }
                }
            }
        }
    }
    
    public Cable(String texto){
        String[]campos=texto.split(";");
        identif = Integer.parseInt(campos[0]);
        terminales[0]=new Pin(campos[1]);
        terminales[1]=new Pin(campos[2]);
    }
    
    
    @Override
    public String toString(){
        return identif+ ";"+ term+";"+termi;
    }
    
    
    public void dibujar(Graphics g) {
        g.drawString("C "+identif , terminales[0].pos.x, terminales[0].pos.y);
        g.drawString("C "+identif , terminales[1].pos.x, terminales[1].pos.y);
        g.drawLine(terminales[0].pos.x, terminales[0].pos.y, terminales[1].pos.x, terminales[1].pos.y);
    }
}

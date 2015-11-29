
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import javax.swing.JPanel;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, Serializable {
    
    
	private static final long serialVersionUID = 1L;
	
	public static ArrayList <Compuertas> circuito=new ArrayList<Compuertas>();
   	public static ArrayList <Cable> cables=new ArrayList<Cable>();
    public static ArrayList <Punto> malla=new ArrayList<Punto>();
    public static ArrayList <Punto> dispon=new ArrayList<Punto>();
    public static ArrayList <Entrada> entradas=new ArrayList<Entrada>();
    public static ArrayList <Salida> salidas=new ArrayList<Salida>();
    
    
   	public boolean dibujando = false; 
    public static int figpaint = 5;
    public final static int AND = 0;
    public final static int NAND = 1;
    public final static int OR = 2;
    public final static int XOR = 3;
    public final static int INV = 4;
    public final static int CABLE = 5;
    public final static int ENTRADA = 6;
    public final static int ENTRADA1 = 7;
    public final static int SALIDA = 8;
    public final static int MULTIPLICADOR = 9;
    public static int contador=0;
    public static int esp_malla=10;
    public int cont_cable=0;
    public int x=0,y=0;
    public Punto inicio;
    
    public MyPanel(){
    }
           
    public void malla(){
        if (!((x==this.getWidth())&&(y==this.getHeight()))) {
            malla.clear();
            for (int i = 5; i < this.getWidth(); i+=esp_malla) {
                for (int j = 5; j <this.getHeight(); j+=esp_malla) {
                    Punto p= new Punto(i, j);
                    malla.add(p);
                }
            }
            x=this.getWidth();
            y=this.getHeight();
            disponible();
        }
        
    }
    
    
    public static void disponible(){
         for (Punto p: malla) {
             Punto pc=new Punto (p.x+esp_malla/2, p.y+esp_malla/2);
             dispon.add(pc);
         }
         for (Compuertas C: circuito) {
             ocupa_com(C);
             
         }
     }
     
     
    public static void ocupa_com(Compuertas C){
          Punto pos = C.pos;
          for (int i = 0; i < 9; i++) {
              for (int j = 0; j < 4; j++) {
                  int _x=pos.x+5+(10*i), _y=pos.y+5+(10*j);
                  for (Punto p:dispon) {
                      if (p.x==_x&&p.y==_y){
                          p.x=0;
                          p.y=0;
                          continue;
                      }
                  }
              }
         }
      }
     
      
    public static boolean ver_dispon(Punto P){
         Punto pos =P;
         pos.x=P.x;
         pos.y=P.y;
         boolean disp=true;
         
          for (int i = 0; i < 9; i++) {
              for (int j = 0; j < 4; j++) {
                  disp=false;
                  int _x=pos.x+5+(10*i), _y=pos.y+5+(10*j);
                  for (Punto p:dispon) {
                      if ((p.x==_x)&&(p.y==_y)){
                          disp=true;
                          continue;
                      } 
                  }
                  if (disp==false) {
                      break;
                  }
              }
              if (disp==false) {
                  break;
              }
         }
         return disp;
     }
    
    
    public static void guardar (String filename){
       try{
           FileWriter archivo = new FileWriter(filename);
           
           archivo.write(""+ circuito.size());
           System.out.println(""+ circuito.size());
           archivo.write("\n");
           for (Compuertas c:circuito) {
               archivo.write(""+c.toString());
               archivo.write("\n");
               System.out.println(""+c.toString());
           }
           archivo.write(""+ cables.size());
           System.out.println(""+cables.size());
           archivo.write("\n");
           for (Cable c:cables) {
               archivo.write(c.toString());
               System.out.println(""+c.toString());
               archivo.write("\n");
           }
           archivo.write(""+ entradas.size());
           archivo.write("\n");
           for (Entrada e: entradas) {
               archivo.write(e.toString());
               archivo.write("\n");
           }
           archivo.write(""+ salidas.size());
           archivo.write("\n");
           for (Salida s: salidas) {
               archivo.write(s.toString());
               archivo.write("\n");
           }
           archivo.close();
       }catch(Exception e){
       }
   }
    
    public static void leer(String nombreArchivo){
        try{
            FileReader lector= new FileReader(nombreArchivo);
            BufferedReader archivo=new BufferedReader (lector);
            int n= Integer.parseInt(archivo.readLine());
            circuito.clear();
            for (int i = 0; i < n; i++) {
                String texto = archivo.readLine();
                String[]campos=texto.split("/");
                int tipo= Integer.parseInt(campos[0]);
                campos[1]=campos[1].substring(1, campos[1].length()-1);
                switch (tipo){
                    case 1:
                        And and = new And(campos[1]);
                        ocupa_com(and);
                        circuito.add(and);
                        break;
                    case 2:
                        Nand nand = new Nand(campos[1]);
                        ocupa_com(nand);
                        circuito.add(nand);
                        break;
                    case 3:
                        Or or = new Or(campos[1]);
                        ocupa_com(or);
                        circuito.add(or);
                        break;
                    case 4:
                        Xor xor = new Xor(campos[1]);
                        ocupa_com(xor);
                        circuito.add(xor);
                        break;
                    case 5:
                        Inv inv = new Inv(campos[1]);
                        ocupa_com(inv);
                        circuito.add(inv);
                        break;
                    case 6:
                        Multiplicador mul= new Multiplicador(campos[1]);
                        ocupa_com(mul);
                        circuito.add(mul);
                        break;
                }
            }
            
            int no= Integer.parseInt(archivo.readLine());
            cables.clear();
            for (int i = 0; i < no; i++) {
                String texto = archivo.readLine();
                cables.add(new Cable(texto));
            }
            int ent= Integer.parseInt(archivo.readLine());
            entradas.clear();
            for (int i = 0; i < ent; i++) {
                entradas.add(new Entrada(archivo.readLine()));
            }
			int sal= Integer.parseInt(archivo.readLine());
            salidas.clear();
            for (int i = 0; i < sal; i++) {
                salidas.add(new Salida(archivo.readLine()));
            }
            
            lector.close();
        }catch(Exception e){
        }
    }
    
    
    public Punto pos_comp(Punto _pos){
        if (_pos.x%esp_malla<esp_malla/2) {
            _pos.x=_pos.x-_pos.x%esp_malla;
        }else{
            _pos.x=_pos.x+(esp_malla-_pos.x%esp_malla);
        }
        if (_pos.y%esp_malla<esp_malla/2) {
            _pos.y=_pos.y-_pos.y%esp_malla;
        }else{
            _pos.y=_pos.y+(esp_malla-_pos.y%esp_malla);
        }
        _pos.x+=5;
        _pos.y+=5;
        return _pos;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.getWidth(),this.getHeight());
        g.setColor(Color.BLACK);
        
        malla();
        
        for (Punto p:malla) {
            p.dibujar(g);
        }
        g.setColor(Color.blue);
        for (Compuertas comp:circuito) {
            comp.dibujar(g);
            for (Pin p: comp.terminales) {
                g.setColor(Color.red);
                p.dibujar(g);
                g.setColor(Color.blue);
            }
        }
        g.setColor(Color.black);
        g.setColor(Color.green);
        for (Cable cable: cables) {
            cable.dibujar(g);
            for (Pin p: cable.terminales) {
                g.setColor(Color.blue);
                p.dibujar(g);
                g.setColor(Color.green);
            }
        }
        g.setColor(Color.black);
        
        g.setColor(Color.red);
        for (Punto p:dispon) {
            p.dibujar(g);
        }
        g.setColor(Color.black);
        
        g.setColor(Color.yellow);
        for (Entrada ent:entradas) {
            ent.dibujar(g);
        }
        g.setColor(Color.black);
        
        g.setColor(Color.blue);
        for (Salida sal:salidas) {
            sal.dibujar(g);
        }
        g.setColor(Color.black);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
    	
    }
    
    @Override
    public  void mousePressed(MouseEvent e){
       dibujando = true;
       Punto ini = new Punto (e.getX(), e.getY());
        inicio=ini;
   }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        dibujando = true;
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (dibujando = true){
            if (e.getModifiers()==16){
                Punto fina = new Punto (e.getX(), e.getY());
                switch (figpaint){
                    case AND:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            And comp= new And (++contador, fina);
                            circuito.add(comp);
                        }
                        break;
                    case NAND:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            Nand comp1= new Nand (++contador, fina);
                            circuito.add(comp1);
                        }
                        break;
                    case OR:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            Or comp2= new Or (++contador, fina);
                            circuito.add(comp2);
                        }
                        break;
                    case XOR:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            Xor comp3= new Xor (++contador, fina);
                            circuito.add(comp3);
                        }
                        break;
                    case INV:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            Inv comp4= new Inv (++contador, fina);
                            circuito.add(comp4);
                        }
                        break;
                    case CABLE:
                        inicio=pos_comp(inicio);
                        
                        fina=pos_comp(fina);
                        int pin1=0, pin2=0;
                        for (Compuertas com:circuito) {
                            for (Pin p:com.terminales) {
                                if (inicio.x==p.pos.x&&inicio.y==p.pos.y) {
                                    pin1=com.identificador;
                                }
                                if (fina.x==p.pos.x&&fina.x==p.pos.x) {
                                    pin2=com.identificador;
                                    
                                }
                            }
                        }
                        if ((pin1!=0)&&(pin2!=0)) {
                            Cable cable = new Cable(inicio, fina, ++cont_cable);
                            cables.add(cable);
                            
                            for (Compuertas com:circuito) {
                                for (Pin p:com.terminales) {
                                    if (inicio.x==p.pos.x&&inicio.y==p.pos.y) {
                                        p.identificador=pin2;
                                    }
                                    if (fina.x==p.pos.x&&fina.x==p.pos.x) {
                                        p.identificador=pin1;
                                        
                                    }
                                }
                            }
                        }
                        break;
                    case    ENTRADA:
                        fina=pos_comp(fina);
                        for (Compuertas com: circuito) {
                            for (Pin p: com.terminales) {
                                if (p.pos.x==fina.x&&p.pos.y==fina.y) {
                                    if (p.flujo==true) {
                                        if (p.evaluado==false){
                                            p.evaluado=true;
                                            p.valor=false;
                                            Entrada ent= new Entrada (fina.x, fina.y, false);
                                            entradas.add(ent);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case ENTRADA1:
                        fina=pos_comp(fina);
                        for (Compuertas com: circuito) {
                            for (Pin p: com.terminales) {
                                if (p.pos.x==fina.x&&p.pos.y==fina.y) {
                                    if (p.flujo==true) {
                                        if (p.evaluado==false){
                                            p.evaluado=true;
                                            p.valor=true;
                                            Entrada ent= new Entrada (fina.x, fina.y, true);
                                            entradas.add(ent);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case SALIDA:
                        fina=pos_comp(fina);
                        for (Compuertas com: circuito) {
                            for (Pin p: com.terminales) {
                                if (p.pos.x==fina.x&&p.pos.y==fina.y) {
                                    if (p.flujo==false) {
                                        Salida sal= new Salida (fina.x, fina.y, com.identificador);
                                        salidas.add(sal);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case MULTIPLICADOR:
                        fina=pos_comp(fina);
                        if (ver_dispon(fina)==true) {
                            Multiplicador comp= new Multiplicador (++contador, fina);
                            circuito.add(comp);
                        break;
                        }
                }
                repaint();
            }
            dibujando = false;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
       
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        
        
        
    } 
}


import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Compuertas{
    
     /**
     *Almacena un entero consecutivo que se encarga de identificar a dicha compuerta.
     */
    protected int identificador;
    
    
    /**
     *Punto que se encarga de guardar la posicion especifica en la que se debe
     * dibujar la compuerta en una ventana grafica.
     * Almacena la posicion de la esquina superior izquierda.
     */
    public Punto pos;
    
    
    /**
     *ArrayList de Pin  que se encarga de almacenar los pines que corresponden
     * a dicha compuenrta.
     */
    public ArrayList <Pin> terminales= new ArrayList();
    
    
    public Compuertas(){}
    
    
    /**
     *Constructor de Compuertas.
     * 
     * @param _identificador Entero que contiene el codigo consecutivo con el que se identifica a la compuerta.
     * @param ent Entero que se encarga de contar la cantidad de entradas que tiene la compuerta.
     * @param sal Entero que se encarga de contar la cantidad de entradas que tiene la compuerta.
     * @param _pos Punto que se encarga de dar la posicion en la que se encuentra la compuerta.
     */
    public Compuertas(int _identificador, int ent, int sal, Punto _pos){
        
        identificador = _identificador;
        pos=_pos;
        for (int i = 0; i < ent; i++) {
            Pin pin = new Pin(true);
            terminales.add(pin);
        }
        for (int i = 0; i < sal; i++) {
            Pin pin = new Pin(false);
            terminales.add(pin);
        }
        pos_pin();
        MyPanel.ocupa_com(this);
    }
    
    
    /**
     * Posiciona los pines de cada compuerta en su respectiva posicion respecto a su ubicacion especifica.
     */
    public  void pos_pin(){
        int ent=0;
        for (Pin pin: terminales) {
            if (pin.flujo==true) {
                if (ent==0) {
                    pin.pos.x=this.pos.x;
                    pin.pos.y=this.pos.y+10;
                    ent=1;
                } else {
                    pin.pos.x=this.pos.x;
                    pin.pos.y=this.pos.y+30;
                }
            } else {
                pin.pos.x=this.pos.x+90;
                pin.pos.y=this.pos.y+20;
            }
        }
    };
    
    
    /**
     *Se encarga de revisar los valores almacenados en los pines, solicitar
     * los faltantes y evaluar dicha compuerta de acuerdo a su respectiva logica. 
     * @return Valor resultante despues de la evaluacion logica de la compuerta.
     */
    public boolean evaluar(){
    
        boolean a = false, b = false, cont = false, sal = false;
        
        revisar_entradas();
        
        for (Pin p:terminales) {
            if (p.flujo==true) {
                if (cont==false) {
                    a=p.valor;
                    cont=true;
                } else {
                    b=p.valor;
                }
            }
        }
        sal=logica(a,b);
        for (Pin p:terminales) {
            if (p.flujo==false) {
                p.valor=sal;
                p.evaluado=true;
            }
        }
        return sal;
    };
    
    /**
     *Se encarga de revisar cada uno de los pines y sus valores, si algun valor
     * no esta evaluado se encarga de evaluar a su respectiva compuerta y
     * asignarle el valor resultante.
     */
    public void revisar_entradas(){
        for (Pin p:this.terminales) {
            if (p.flujo==true) {
                if (p.evaluado==false) {
                    for (Compuertas c : MyPanel.circuito){
                        if (p.identificador==c.identificador) {
                            p.valor=c.evaluar();
                            p.evaluado=true;
                        }
                    }
                }
            }
        }
    }
    
    /**
     *Se encarga de dibujar graficamente a cada una de las compuertas de su
     * respectiva forma.
     * @param g Parametro grafico de java.
     */
    public abstract void dibujar(Graphics g);
    
    
    /**
     *Se encarga de realizar el proceso de logica de cada una de las compuertas
     * que usan dos parametros logicos.
     * @param a Primer paramero logico para la evaluacion.
     * @param b Primer paramero logico para la evaluacion.
     * @return resultado de la evaluacion logica de los parametros de acuerdo
     * a cada condicion logica.
     */
    public abstract boolean logica(boolean a, boolean b);
    
       
}
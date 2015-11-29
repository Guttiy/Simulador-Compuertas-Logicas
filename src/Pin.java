
import java.awt.Graphics;
import java.io.Serializable;


public class Pin implements Serializable{
    
   
	private static final long serialVersionUID = 1L;
	/**
     * La variable flujo se encarga de determinar si dicho pin es de entrada o salida.
     * Entrada = true; Salida = false.
     * La variable valor se encarga de almacenar el dato que entre o salga de dicho pin.
     * La varibla evaluado se encarga de informar si el dato que esta almacenado
     * en valor es real o es el dato por default.
     * 
     */
    public boolean flujo, valor, evaluado;
    /**
     *El entero identifocador se engarga de almacenar el dato de a quien esta
     * conectado dicho pin.
     */
    public int identificador;
    /**
     *El Punto pos se encarga de almacenar el dato de posicion de dicho pin
     * respecto a la posicion especifica de la compuerta a la que pertenece.
     */
    public Punto pos;
    
    
    /**
     *Constructor de Pin.
     * @param _flujo Se encarga de almacenar el sentido en el que funciona dicho
     * pin.
     */
    public Pin(boolean _flujo){
        this.pos = new Punto (0,0);
        this.identificador = 0;
        this.evaluado = false;
        this.valor = false;
        flujo = _flujo;
    }
    public Pin(String texto){
        String[]campos=texto.split(",");
        Punto _pos = new Punto (campos[4]);
        pos=_pos;
        identificador = Integer.parseInt(campos[3]);
        evaluado = Boolean.parseBoolean(campos[2]);
        valor = Boolean.parseBoolean(campos[1]);
        flujo = Boolean.parseBoolean(campos[0]);
    }
    
    
    /**
     *Se encarga de dibujar el pin en una panatalla grafica teniendo en cuenta
     * la posicion almacenada en Punto pos.
     * @param g
     */
    public void dibujar(Graphics g) {
        g.drawOval(pos.x-1, pos.y-1, 2, 2);
    }
    
    
    @Override
    public String toString(){
        return  flujo+","+ valor+","+ evaluado+","+identificador+","+pos.toString();
    }
}

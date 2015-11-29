

public abstract class Simulador {
    
    
    /**
     *Si verif_con y verif_cable no encuentran errores entonces se llama 
     * a evaluar para cada una de las compuertas conectadas a salidas. 
     */
    public static void Simular(){
        if ((verif_con()&&verif_cable())==true) {
            for (Salida sal:MyPanel.salidas) {
                if (sal.evaluado==false) {
                    for (Compuertas com: MyPanel.circuito) {
                        if (sal.identif==com.identificador) {
                            sal.sal=com.evaluar();
                            sal.evaluado=true;
                            break;
                        }
                    }    
                }
            }
        }
    }
    
    
    /**
     *Se encagarga de verificar que cada una de las compuertas tenga todos sus 
     * terminales conectados, a un cable o a una entrada o a una salida.
     * @return true si la compuerta cumple con las conecciones;false si no.
     */
    public static boolean verif_con(){
        boolean aprobado=true;
        for (Compuertas com:MyPanel.circuito) {
            boolean verific= false;
            for (Pin p: com.terminales) {
                if (p.identificador!=0){
                    verific= true;
                    continue;
                }
                if (p.evaluado==true){
                    verific=true;
                    continue;
                }
                for (Salida sal:MyPanel.salidas) {
                    if (sal.identif==com.identificador) {
                        verific=true;
                        break;
                    }
                }
            }
            if (verific==false){
                System.out.println("Error: falta coneccion en la compuerta "+com.identificador+".");
                aprobado=false;
            }
        }
        return aprobado;
    }
    
    
    /**
     *Se encarga de varificar cada una de las conecciones de los cables;
     * vefirica a quien estan conectados si estan conectados a dos salidas o
     * entradas, si tienen la coneccion en el mismo pin.
     * @return true Si todas las conecciones estan correctas, false si no.
     */
    public static boolean verif_cable(){
        boolean aprobado=true;
        for (Cable C: MyPanel.cables) {
            for (Pin p: C.terminales) {
                for (Cable Ca:MyPanel.cables) {
                    for (Pin pin: Ca.terminales) {
                        if ((C.identif!=Ca.identif) && (p.pos.x==pin.pos.x&&p.pos.y==pin.pos.y)){
                            aprobado=false;
                            System.out.println("Error: El cable " + C.identif +
                                    " esta conectado en el mismo Pin que el cable " +Ca.identif + ".");
                            continue;
                        }
                    }
                }
            }
            if ((C.terminales[0].pos.x==C.terminales[1].pos.x) && (C.terminales[0].pos.y==C.terminales[1].pos.y)) {
                aprobado=false;
                System.out.println("Error: El cable "+C.identif+" tiene sus dos terminales en el mismo nodo.");
            }
            if (C.terminales[0].flujo==C.terminales[1].flujo){
                aprobado =false;
                if (C.terminales[0].flujo==true){
                    System.out.println("El cable "+C.identif+" esta conectado a dos entradas.");
                }else{
                    System.out.println("El cable "+C.identif+" esta conectado a dos salidas.");}
            }
        }
        return aprobado;
    }
    
    
}

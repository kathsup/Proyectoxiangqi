package juegochino;

public class elefante extends piezas {

    public elefante(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }

    @Override
    public String getNombre(){
    return "Elefante";
    }
    
    @Override
 public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
 
     // moverse exactamente 2 en diagonal
        int diferenciaX = Math.abs(nuevaX - x);
        int diferenciaY = Math.abs(nuevaY - y);

        // 1. Verificar que el movimiento es exactamente de 2 en diagonal
        if (diferenciaX == 2 && diferenciaY == 2) {

            // 2. Verificar que no cruza el río
            if (rojo && nuevaX > 4) { 
                return false;
            }
            if (!rojo && nuevaX < 5) { 
                return false;
            }

            // 3. Verificar que el punto intermedio este vacío
            int intermedioX = (x + nuevaX) / 2;
            int intermedioY = (y + nuevaY) / 2;
            if (tablero[intermedioX][intermedioY] != null) {
                return false; // El elefante no puede saltar sobre piezas
            }

            // Si pasa todas las verificaciones, el movimiento es válido
            return true;
        }

        return false; // Cualquier otro movimiento es inválido
    }
 }   
    
    
    
    


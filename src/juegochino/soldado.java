package juegochino;

public class soldado extends piezas{

    public soldado(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    
    if (!rojo) {
        // Movimiento hacia adelante en el propio campo (eje Y negativo)
        if (nuevaX == x && nuevaY == y - 1) {
            return true;
        }
        // Movimiento lateral cuando está en el campo enemigo
        else if (y < 5 && Math.abs(nuevaX - x) == 1 && nuevaY == y) {
            return true;
        }
    } else {
        // Movimiento hacia adelante en el propio campo (eje Y positivo)
        if (nuevaX == x && nuevaY == y + 1) {
            return true;
        }
        // Movimiento lateral cuando está en el campo enemigo
        else if (y > 4 && Math.abs(nuevaX - x) == 1 && nuevaY == y) {
            return true;
        }
    }
        return false; // Si no se cumple ninguna de las condiciones, el movimiento no es válido
    }
    }


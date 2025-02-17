package juegochino;

public class general extends piezas{

    public general(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }

    @Override
    public String getNombre(){
    return "Rey";
    }
    
    
    @Override
public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
    // Obtener posición actual
    int actualX = this.getX();
    int actualY = this.getY();

    // Verificar que el movimiento es exactamente horizontal o vertical
    if (Math.abs(nuevaX - actualX) + Math.abs(nuevaY - actualY) != 1) {
        return false;  
    }

    // Verificar si el rey está dentro de su palacio
    if (this.esRojo()) {
        // Verificar si el nuevo movimiento está dentro del palacio rojo
        if (nuevaX < 0 || nuevaX > 2 || nuevaY < 3 || nuevaY > 5) {
            return false;  
        }
    } else {
       // palacio negro
        if (nuevaX < 7 || nuevaX > 9 || nuevaY < 3 || nuevaY > 5) {
            return false;  
        }
    }

    // Verificar que no haya una visión directa con el otro rey
    piezas destino = tablero[nuevaX][nuevaY];
    if (destino != null && destino instanceof general) {
        // Si hay un rey enemigo en la misma columna, verificar que haya piezas entre ellos
        if (actualY == nuevaY) {
            int minY = Math.min(actualX, nuevaX);
            int maxY = Math.max(actualX, nuevaX);
            // Verificar que haya al menos una pieza en la columna
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[i][nuevaY] != null) {
                    return true;  // Si hay una pieza entre ellos, es válido
                }
            }
            return false;  
        }
    }

    
    if (destino != null && destino.esRojo() == this.esRojo()) {
        return false;  
    }

    // Si se cumplen todas las reglas, el movimiento es válido
    return true;
}
    
    
    
    
}

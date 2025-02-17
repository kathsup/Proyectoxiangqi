package juegochino;

public class carroGuerra extends piezas{

    public carroGuerra(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    @Override
    public String getNombre(){
    return "Torre";
    }
    
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    //  Verificar que el movimiento es recto - horizontal o vertical
        if (x != nuevaX && y != nuevaY) {
            return false; //  no puede moverse en diagonal
        }

        //  Verificar si hay obstáculos en el camino 
        if (x == nuevaX) {
            // Movimiento horizontal
            int minY = Math.min(y, nuevaY);
            int maxY = Math.max(y, nuevaY);
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[x][i] != null) {
                    return false; // Hay una pieza en el camino
                }
            }
        } else if (y == nuevaY) {
            // Movimiento vertical
            int minX = Math.min(x, nuevaX);
            int maxX = Math.max(x, nuevaX);
            for (int i = minX + 1; i < maxX; i++) {
                if (tablero[i][y] != null) {
                    return false; // Hay una pieza en el camino
                }
            }
        }

        // Si pasa todas las verificaciones, el movimiento es valido
        return true;
    
    }
}

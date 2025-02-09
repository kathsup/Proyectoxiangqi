package juegochino;

public class canon extends piezas {

    public canon(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    if (x != nuevaX && y != nuevaY) {
            return false; // El Cañón no puede moverse en diagonal
        }

       
        if (tablero[nuevaX][nuevaY] == null) {
            
            return esCaminoDespejado(x, y, nuevaX, nuevaY, tablero);
        } else {
            
            return esCapturaValida(x, y, nuevaX, nuevaY, tablero);
        }
    }

    // Método para verificar si el camino está completamente despejado
    private boolean esCaminoDespejado(int x, int y, int nuevaX, int nuevaY, piezas[][] tablero) {
        if (x == nuevaX) {
            
            int minY = Math.min(y, nuevaY);
            int maxY = Math.max(y, nuevaY);
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[x][i] != null) {
                    return false; // Hay una pieza en el camino
                }
            }
        } else if (y == nuevaY) {
            
            int minX = Math.min(x, nuevaX);
            int maxX = Math.max(x, nuevaX);
            for (int i = minX + 1; i < maxX; i++) {
                if (tablero[i][y] != null) {
                    return false; // Hay una pieza en el camino
                }
            }
        }
        return true; // Camino despejado
    }

    // Método para verificar si la captura es válida debe haber una pieza intermedia
    private boolean esCapturaValida(int x, int y, int nuevaX, int nuevaY, piezas[][] tablero) {
        int contadorPlataformas = 0;

        if (x == nuevaX) {
            // Movimiento horizontal
            int minY = Math.min(y, nuevaY);
            int maxY = Math.max(y, nuevaY);
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[x][i] != null) {
                    contadorPlataformas++;
                }
            }
        } else if (y == nuevaY) {
            // Movimiento vertical,
            int minX = Math.min(x, nuevaX);
            int maxX = Math.max(x, nuevaX);
            for (int i = minX + 1; i < maxX; i++) {
                if (tablero[i][y] != null) {
                    contadorPlataformas++;
                }
            }
        }

        // El Cañón solo puede capturar si hay exactamente una pieza intermedia 
        return contadorPlataformas == 1;
    }
    
    
    
    
}

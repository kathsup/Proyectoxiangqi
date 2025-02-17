package juegochino;

public class canon extends piezas {

    public canon(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    @Override
    public String getNombre(){
    return "Cañon";
    }
    
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    if (x != nuevaX && y != nuevaY) {
            return false; // movimiento en diagonal no permitido
        }
        if (tablero[nuevaX][nuevaY] == null) {
            return esCaminoDespejado(x, y, nuevaX, nuevaY, tablero);
        } else {
            return esCapturaValida(x, y, nuevaX, nuevaY, tablero);
        }
    }

    // verificar si el camino esta completamente despejado
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

    // verificar si la captura es posible debe haber una pieza intermedia
    private boolean esCapturaValida(int x, int y, int nuevaX, int nuevaY, piezas[][] tablero) {
        int contadorPlataformas = 0;

        if (x == nuevaX) {
            //  horizontal
            int minY = Math.min(y, nuevaY);
            int maxY = Math.max(y, nuevaY);
            for (int i = minY + 1; i < maxY; i++) {
                if (tablero[x][i] != null) {
                    contadorPlataformas++;
                }
            }
        } else if (y == nuevaY) {
            //  vertical,
            int minX = Math.min(x, nuevaX);
            int maxX = Math.max(x, nuevaX);
            for (int i = minX + 1; i < maxX; i++) {
                if (tablero[i][y] != null) {
                    contadorPlataformas++;
                }
            }
        }

        //  solo puede capturar si UNA  pieza intermedia 
        return contadorPlataformas == 1;
    }
    
    
    
    
}

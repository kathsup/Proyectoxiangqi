package juegochino;

public class general extends piezas{

    public general(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }

    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    
        int diferenciaX = Math.abs(nuevaX - x);
        int diferenciaY = Math.abs(nuevaY - y);
        
        //1. verificar que se mueva una en vertical u horizonta. 
        if((diferenciaX==1 && diferenciaY==0) || (diferenciaY==1&& diferenciaX==0)){
        
        //2. verificar que no salga del palacio
          if(rojo && nuevaX < 0 || nuevaX > 2 || nuevaY < 3 || nuevaY > 5){
          return false; 
          } 
          else if(!rojo && nuevaX < 7 || nuevaX > 9 || nuevaY < 3 || nuevaY > 5){
          return false;
          }
          
          //3. verifica que los generales no tengan visión directa 
          if (visionDirecta(nuevaX, nuevaY, tablero)) {
            return false; // No puede moverse si tiene visión directa con el otro General
        }

        // Si pasa todas las verificaciones, el movimiento es valido
        return true;
    }
        
        return false;
        
    }
     
     private boolean visionDirecta(int nuevaX, int nuevaY, piezas[][] tablero) {
        // Verificar si ambos generales están en la misma columna
        if (nuevaY == y) {
            // Buscar el otro general en la misma columna
            for (int fila = Math.min(nuevaX, x) + 1; fila < Math.max(nuevaX, x); fila++) {
                if (tablero[fila][y] != null) {
                    return false; // Hay una pieza entre los generales, no hay visión directa
                }
            }

            // Verificar si hay un General oponente en la misma columna sin piezas entre ellos
            for (int fila = 0; fila < tablero.length; fila++) {
                if (fila != nuevaX && tablero[fila][nuevaY] instanceof general) {
                    return true; // Hay visión directa
                }
            }
        }

        return false; // No hay visión directa con el otro General
    }
    
    
    
    
}

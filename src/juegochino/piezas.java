package juegochino;

public abstract class piezas {
  
//atributos
protected int x; 
protected int y ; 
protected boolean rojo; 

//constructor 

public piezas(int x, int y, boolean rojo) {
this.x = x;
this.y = y;
this.rojo = rojo;
   }

//metodo abstracto 
public abstract boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero);

// getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    
    //metodos
    public void setPosicion(int x, int y ){
    this.x=x;
    this.y=y;
    }
    
    public boolean esRojo (){
    return rojo;
    }
    
    
    
    
    
    
    
    
    
}

package juegochino;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class jugador {
    
    //atributos 
   private String username;
    private String password;
    private int puntos;
    private Calendar fechaIngreso;
    private boolean activo;
    private String[] logPartidas; 
    
    public jugador(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.fechaIngreso = Calendar.getInstance(); 
        this.activo = true;
        this.logPartidas = new String[10]; 
    }
    
    
    public String getFechaIngresoFormateada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formato de día/mes/año
        return sdf.format(fechaIngreso.getTime());
    }

    @Override
    public String toString() {
        return "Jugador: " + username;
               
    }
    
    // Getters y setters 
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Método para agregar un log de partida.
    public void agregarLogPartida(String resultado) {
        for (int i = 0; i < logPartidas.length; i++) {
            if (logPartidas[i] == null) {
                logPartidas[i] = resultado;
                break;
            }
        }
    }
    
}

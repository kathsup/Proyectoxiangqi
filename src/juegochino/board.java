package juegochino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class board extends JFrame {

    public JPanel panel1, panel2;
    private JButton[][] botones;
    private final juego juego;
    private piezas piezaSeleccionada = null; // para almacenar la pieza seleccionada
    private int filaOrigen, colOrigen;

    private jugador jugadorRojo; // El jugador logueado es el rojo
    private jugador jugadorNegro; // El jugador oponente será el negro
    private gestionJugadores gestorDeJugadores; // Para gestionar los jugadores
    
    
    private JTextArea infoJugadores; // Muestra la info de los jugadores
    private JTextArea infoMovimientos; // Muestra los movimientos del juego
    
    public board(jugador jugadorLogueado, gestionJugadores gestor) {
        this.setSize(712, 550);
        setTitle("XIANGQI");
        setLocationRelativeTo(null);      //pone la pantalla centrada
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.jugadorRojo = jugadorLogueado; // El jugador logueado es el rojo
        this.gestorDeJugadores = gestor; // Asignar el gestor de jugadores
        juego = new juego(this,jugadorRojo, jugadorNegro);
        iniciarComponentes();
        juego.mostrarTurno();

        infoJugadores.append("Jugador Rojo: " + jugadorRojo.getUsername() + "\n");
       
    }

    private void iniciarComponentes() {
        iniciarpanel1();
        iniciarpanel2();
        juego.inicializarTablero();
        crearBotones();
        colocarPiezas();
        colocarBtnRetiro();
        crearJComboBoxJugadores();
        colocarInfoPaneles();
    }

    private void iniciarpanel1() {
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 712, 550); // Panel principal de 712x506
        panel1.setLayout(null); // Layout libre para colocar componentes manualmente
        this.getContentPane().add(panel1);
    }

    private void iniciarpanel2() {
        panel2 = new JPanel();
        panel2.setBounds(0, 5, 516, 500);
        panel2.setLayout(new GridLayout(10, 9)); // Grid de 10x9 para los botones
        panel1.add(panel2);
    }

    private void crearBotones() {
        botones = new JButton[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                //crearlos
                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(80, 80)); // Establece el tamaño de los botones

                //color
                if ((i + j) % 2 == 0) {
                    // Casilla morada
                    botones[i][j].setBackground(Color.decode("#cd99f7"));
                } else {
                    // Casilla rosa     
                    botones[i][j].setBackground(Color.decode("#f7a3f5"));
                }
                //actions
                final int fila = i;
                final int col = j;
                botones[i][j].addActionListener(e -> manejarClick(fila, col));
                panel2.add(botones[i][j]);
            }
        }
    }

    private void colocarBtnRetiro() {

        JButton retiro = new JButton("Retirarse");//O usar set text 
        retiro.setBounds(533, 60, 135, 30);
        retiro.setForeground(Color.decode("#ac58f5"));
        retiro.setFont(new Font("Tahoma", Font.BOLD, 18));
        retiro.setBackground(Color.decode("#00d9ce"));
        panel1.add(retiro);

        ActionListener r = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                manejarRetiro();
            }
        };
        retiro.addActionListener(r);

    }

    private void manejarRetiro() {

        int retirarse = JOptionPane.showConfirmDialog(this, "Seguro deseas retirarte?", "confirmar retiro", JOptionPane.YES_NO_OPTION);
        //Yes option=0 y no_option =1
        if (retirarse == JOptionPane.YES_OPTION) {
            juego.retirarse();
        }

    }
    
    private void crearJComboBoxJugadores() {
        // Crear el JComboBox para seleccionar el jugador oponente (jugador negro)
        JComboBox<jugador> listaJugadores = new JComboBox<>();
        listaJugadores.setBounds(533, 100, 135, 30);

        // Llenar el JComboBox con los jugadores disponibles (excluyendo al jugador rojo)
        for (jugador j : gestorDeJugadores.jugadores) {
            if (j != null && !j.equals(jugadorRojo)) {
                listaJugadores.addItem(j);
            }
        }

        // Añadir la lista al panel
        panel1.add(listaJugadores);

        // Manejar la selección del oponente
         listaJugadores.addActionListener(e -> {
        if (jugadorNegro == null) { // Solo permitir seleccionar una vez
            jugadorNegro = (jugador) listaJugadores.getSelectedItem();
            // Mostrar el jugador negro en el JTextArea de información
            infoJugadores.append("Jugador Negro: " + jugadorNegro.getUsername() + "\n");
            // Desactivar el JComboBox para evitar cambios de jugador
            listaJugadores.setEnabled(false);
        }
    });
    }

    private void colocarInfoPaneles() {
        // JTextArea para mostrar la info de los jugadores
        infoJugadores = new JTextArea();
        infoJugadores.setBounds(533, 150, 150, 100);
        infoJugadores.setEditable(false);
        panel1.add(infoJugadores);

        
    }
    
    public void mostrarEnInfoMovimientos(String mensaje) {
        // JTextArea para mostrar los movimientos del juego
        infoMovimientos = new JTextArea();
        infoMovimientos.setBounds(533, 260, 150, 200);
        infoMovimientos.setEditable(false);
        panel1.add(infoMovimientos);
        infoMovimientos.append(mensaje + "\n"); // Agregar el mensaje al JTextArea
    }

    private void colocarPiezas() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                piezas pieza = juego.getPieza(i, j); // Obtener la pieza lógica
                if (pieza != null) {
                    if (pieza.esRojo()) {
                        // Piezas rojas
                        if (pieza instanceof carroGuerra) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/carroR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof caballo) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/caballoR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof elefante) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/elefanteR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof oficial) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/oficialR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof general) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/generalR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof canon) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/cañonR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof soldado) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/peonR.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }

                    } else {
                        // Piezas negras
                        if (pieza instanceof carroGuerra) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/carroN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof caballo) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/caballoN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof elefante) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/elefanteN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof oficial) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/OficialN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof general) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/generalN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof canon) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/cañonN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        } else if (pieza instanceof soldado) {
                            botones[i][j].setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/peonN.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                        }
                    }
                } else {
                    botones[i][j].setIcon(null); // Si no hay pieza, deja la casilla vacía
                }
            }
        }

    }

    public void manejarClick(int fila, int col) {
        //juego.mostrarTurno();
        piezas piezaClicada = juego.getPieza(fila, col); // Obtener la pieza en la casilla clicada
        //juego.mostrarTurno();
        
        if (jugadorNegro == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona al jugador negro antes de realizar movimientos.", "Jugador Negro no seleccionado", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método si no se ha seleccionado el jugador negro
        }
        
        if (piezaSeleccionada == null) {
            // Si no hay pieza seleccionada, selecciona la pieza
            if (piezaClicada != null) {
                piezaSeleccionada = piezaClicada;
                filaOrigen = fila;
                colOrigen = col;
                infoMovimientos.append("Pieza seleccionada en: " + fila + ", " + col + "\n");
            }
        } else {
            // Si ya hay una pieza seleccionada, intenta moverla
            if (juego.moverPieza(filaOrigen, colOrigen, fila, col)) {
                // movimiento valido, actualizar la visualización
                colocarPiezas(); // Actualizar los íconos
            } //else {
            // System.out.println("Movimiento no válido.");
            // }
            piezaSeleccionada = null; // Reiniciar la selección
        }
    }
    
    public void cerrar() {
        dispose(); // Cierra la ventana del tablero
        // jugador nuevoJugador = gestorDeJugadores.buscarJugador(usser); // Obtener el jugador recién creado
         new principal(gestorDeJugadores, jugadorRojo).setVisible(true); 
    }

    public static void main(String[] args) {
        // Crear el gestor de jugadores y algunos jugadores ficticios para probar
        gestionJugadores gestorDeJugadores = new gestionJugadores();
        
        // Agregar algunos jugadores al sistema (esto sería parte de tu lógica real)
        gestorDeJugadores.crearJugador("sandra", "12345");
        gestorDeJugadores.crearJugador("patricia", "12345");
        gestorDeJugadores.crearJugador("carla", "12345");

        // Simular login del jugador rojo
        jugador jugadorRojo = gestorDeJugadores.logIn("carla", "12345");
        if (jugadorRojo != null) {
            // Si el login es exitoso, se inicia el tablero con el jugador rojo
            // Al crear el tablero, se pasará el jugador logueado (jugador rojo) y el gestor de jugadores
            board tablero = new board(jugadorRojo, gestorDeJugadores);
            tablero.setVisible(true); // Mostrar el tablero
        } else {
            System.out.println("Error en el login");
        }
    }
}

//COSAS A REVISAR 
//Movimientos de cada pieza, captura de cada pieza 

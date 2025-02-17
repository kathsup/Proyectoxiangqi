package juegochino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class board extends JFrame {

    public JPanel panel1, panel2;
    private JButton[][] botones;
    public juego juego;
    private piezas piezaSeleccionada = null; // para la pieza seleccionada
    private int filaOrigen, colOrigen;

    private jugador jugadorRojo; 
    private jugador jugadorNegro; 
    private gestionJugadores gestorDeJugadores; // Para gestionar los jugadores

    private JTextArea infoJugadores; 
    private JTextArea infoMovimientos; 
    JScrollPane scrollPane;
    
    
    public board(jugador jugadorLogueado, gestionJugadores gestor) {
        this.setSize(712, 550);
        setTitle("XIANGQI");
        setLocationRelativeTo(null);      //pone la pantalla centrada
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.jugadorRojo = jugadorLogueado;
        this.gestorDeJugadores = gestor; // Asignar el gestor de jugadores
        infoMovimientos = new JTextArea();
        scrollPane = new JScrollPane(infoMovimientos);
        iniciarComponentes();
        infoJugadores.append("Jugador Rojo: " + jugadorRojo.getUsername() + "\n");
       
    }

    private void iniciarComponentes() {
        iniciarpanel1();
        iniciarpanel2();
        crearJComboBoxJugadores();
        colocarInfoPaneles();
    }

    private void iniciarpanel1() {
        panel1 = new JPanel();
        panel1.setBackground(Color.decode("#F7F0E0"));
        panel1.setBounds(0, 0, 712, 550); 
        panel1.setLayout(null); 
        this.getContentPane().add(panel1);
    }

    private void iniciarpanel2() {
        panel2 = new JPanel();
        panel2.setBounds(0, 5, 516, 500);
        panel2.setLayout(new GridLayout(10, 9)); 
        panel1.add(panel2);
    }

    private void crearBotones() {
        botones = new JButton[10][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                //crearlos
                botones[i][j] = new JButton();
                botones[i][j].setPreferredSize(new Dimension(80, 80)); //tamaño de los botones

                //poner el rio
                if (i == 5) {
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, Color.decode("#a0a0a0"))); // Borde superior 
                }

                //palacio: 
                if ((i == 0 || i == 1 || i == 2) && j == 3) {
                    int top = (i == 0) ? 4 : 0; 
                    int bottom = (i == 2) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(top, 4, bottom, 0, Color.decode("#a0a0a0"))); 
                } 
                else if (i == 2 && (j == 3 || j == 4 || j == 5)) {
                    int left = (j == 3) ? 4 : 0; 
                    int right = (j == 5) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(0, left, 4, right, Color.decode("#a0a0a0"))); 
                } 
                else if ((i == 0 || i == 1 || i == 2) && j == 5) {
                    int top = (i == 0) ? 4 : 0; 
                    int bottom = (i == 2) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(top, 0, bottom, 4, Color.decode("#a0a0a0"))); 
                } 
                else if (i == 0 && (j == 3 || j == 4 || j == 5)) {
                    int right = (j == 5) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(4, 0, 0, right, Color.decode("#a0a0a0"))); 
                }
                
                //palacio de abajo
                else if ((i == 7 || i == 8 || i == 9) && j == 3) {
                    int top = (i == 7) ? 4 : 0; 
                    int bottom = (i == 9) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(top, 4, bottom, 0, Color.decode("#a0a0a0"))); 
                } 
                else if (i == 9 && (j == 3 || j == 4 || j == 5)) {
                    int left = (j == 3) ? 4 : 0; 
                    int right = (j == 5) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(0, left, 4, right, Color.decode("#a0a0a0"))); 
                } 
                else if ((i == 7 || i == 8 || i == 9) && j == 5) {
                    int top = (i == 7) ? 4 : 0; 
                    int bottom = (i == 9) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(top, 0, bottom, 4, Color.decode("#a0a0a0"))); 
                } 
                else if (i == 7 && (j == 3 || j == 4 || j == 5)) {
                    int right = (j == 5) ? 4 : 0; 
                    botones[i][j].setBorder(BorderFactory.createMatteBorder(4, 0, 0, right, Color.decode("#a0a0a0"))); 
                }

                    //color
                    if ((i + j) % 2 == 0) {
                        // Casilla beige
                        botones[i][j].setBackground(Color.decode("#f7f4eb"));//#F7F0E0
                    } else {
                        // Casilla rosa     
                        botones[i][j].setBackground(Color.decode("#f2bdde"));
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
        retiro.setForeground(Color.decode("#F7F0E0"));
        retiro.setFont(new Font("Tahoma", Font.BOLD, 18));
        retiro.setBackground(Color.decode("#D23B42"));
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
    //JComboBox para seleccionar el jugador oponente 
    JComboBox<jugador> listaJugadores = new JComboBox<>();
    listaJugadores.setBounds(533, 100, 135, 30);

    // Llenar el JComboBox con los jugadores disponibles no al jugador rojo
    for (jugador j : gestorDeJugadores.jugadores) {
        if (j != null && !j.equals(jugadorRojo)) {
            listaJugadores.addItem(j);
        }
    }

    if (listaJugadores.getItemCount() == 0) {
        JOptionPane.showMessageDialog(this, "No hay jugadores disponibles.", "Sin Oponentes", JOptionPane.WARNING_MESSAGE);
        cerrar(); 
        return; 
    }
    
    // Añadir la lista al panel
    panel1.add(listaJugadores);

    //  la selección del oponente
    listaJugadores.addActionListener(e -> {
        jugadorNegro = (jugador) listaJugadores.getSelectedItem();
        if (jugadorNegro != null) {
            infoJugadores.append("Jugador Negro: " + jugadorNegro.getUsername() + "\n");
            // luego de elegirlo se desactiva para que no cambien al jugador 
            listaJugadores.setEnabled(false);

            // Crear el objeto juego después de seleccionar el jugador negro, porque si no da error porque jujador negro era null
            this.juego = new juego(this, jugadorRojo, jugadorNegro);
            this.juego.inicializarTablero();
            this.juego.mostrarTurno();
            
            crearBotones(); 
            colocarPiezas();
            colocarBtnRetiro();
        }
    });
}

    private void colocarInfoPaneles() {
        //mostrar los jugadores
        infoJugadores = new JTextArea();
        infoJugadores.setBounds(533, 150, 150, 100);
        infoJugadores.setEditable(false);
        panel1.add(infoJugadores);
    }
    
    public void mostrarEnInfoMovimientos(String mensaje) {
        // area para mostrar movimientos y etc

        infoMovimientos.setEditable(false);
        infoMovimientos.setLineWrap(true);
        infoMovimientos.setWrapStyleWord(true);
        scrollPane.setBounds(533, 260, 150, 200); 
        panel1.add(scrollPane);
        infoMovimientos.append(mensaje + "\n\n"); 

        // el panel se actualiza
        infoMovimientos.revalidate();
        infoMovimientos.repaint();
    }
    
    private void colocarPiezas() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                piezas pieza = juego.getPieza(i, j); // Obtener la pieza 
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
        
        piezas piezaClicada = juego.getPieza(fila, col); // Obtener la pieza en la casilla seleccionada
        
        
        if (jugadorNegro == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona al jugador negro antes de realizar movimientos.", "Jugador Negro no seleccionado", JOptionPane.WARNING_MESSAGE);
            return; // Salir si no se ha seleccionado el jugador negro
        }
        
        if (piezaSeleccionada == null) {
            // Si no hay pieza seleccionada, se selecciona la pieza
            if (piezaClicada != null) {
                piezaSeleccionada = piezaClicada;
                filaOrigen = fila;
                colOrigen = col;
                infoMovimientos.append("Pieza seleccionada en: " + fila + ", " + col + "\n");
            }
        } else {
            // Si ya hay una pieza seleccionada, se intenta moverla
            if (juego.moverPieza(filaOrigen, colOrigen, fila, col)) {
                // movimiento valido, actualizar el tablero
                colocarPiezas(); // Actualizar los íconos
            } 
            piezaSeleccionada = null; // Reiniciar la selección
        }
    }
    
    public void cerrar() {
        dispose(); 
         new principal(gestorDeJugadores, jugadorRojo).setVisible(true); 
    }

}


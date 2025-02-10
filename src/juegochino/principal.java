package juegochino;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class principal extends JFrame {
private CardLayout cardLayout;
    private JPanel panelCentral;
    private jugador jugador;
    private gestionJugadores gestorJugadores;
    private juego j; 
    JPanel panelDerecho;
    private JTable rankingTable;

    public principal(gestionJugadores gestorJugadores, jugador jugador) {
        this.jugador = jugador;
        this.gestorJugadores = gestorJugadores;
        setTitle("Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
        setVisible(true); 
    }

    private void iniciarComponentes() {
       primerPanel();

    }
    
    public void primerPanel(){
    
     cardLayout = new CardLayout();
        panelCentral = new JPanel();
        panelCentral.setLayout(cardLayout);
        
        JPanel jugar = new JPanel();//crear panel inicial con la parte de jugar y los botones de ajustes y log out
        jugar.setLayout(null);//para poder usar setbounds y moverlo a donde yo quiera
        JButton comenzarP = new JButton("comenzar partida");//boton para comenzar el juego
        comenzarP.setBounds(325, 250, 147, 47);//lugar donde lo quiero colocar
        jugar.add(comenzarP);//agregar el boton al panel de jugar
        panelCentral.add(jugar, "Jugar");//agregar el panel de jugar a el panel central 
        getContentPane().add(panelCentral);//hacerlo visible 
        
       comenzarP.addActionListener(e -> {
    // Verificar si hay jugadores disponibles (excluyendo al jugador rojo)
    boolean hayOponentes = false;
    for (jugador j : gestorJugadores.jugadores) {
        if (j != null && !j.equals(jugador)) {
            hayOponentes = true;
            break;
        }
    }

    if (!hayOponentes) {
        JOptionPane.showMessageDialog(this, "No hay jugadores disponibles. Regresando al menú principal.", "Sin Oponentes", JOptionPane.WARNING_MESSAGE);
        return; // Salir del método sin abrir el tablero
    }

    // Si hay oponentes, crear el tablero
    dispose(); // Cerrar la ventana actual
    board tablero = new board(jugador, gestorJugadores); // Crea el tablero con los jugadores
    tablero.setVisible(true); // Haz visible el tablero
});
        
        //agregar los botones de ajustes 
        JButton logout = new JButton("L");
        logout.setBounds(680, 470, 50, 50);
        jugar.add(logout);
        
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el menú principal
                new inicio(gestorJugadores).setVisible(true); // Abre el menú de inicio 
            }
        });
        
        JButton JBajustes = new JButton("A");
        JBajustes.setBounds(680, 400, 50, 50);
        jugar.add(JBajustes);
        segundoPanel();
        JBajustes.addActionListener(e -> cardLayout.show(panelCentral, "ajustes"));
        
    
    
    }
    
    public void segundoPanel(){
    
    //segundo jpanel
        JPanel JPajustes = new JPanel(new BorderLayout());//panel ajustes donde estan todos los botones 
        JSplitPane splitPane = new JSplitPane();//lo que lo divide 
        splitPane.setDividerLocation(100);//el tamaño de separacion que tendran
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1)); // GridLayout para organizar botones
        JButton btnReportes = new JButton("Reportes");
        JButton btnMiCuenta = new JButton("Mi Cuenta");
        JButton regresar = new JButton("Regresar");
        panelIzquierdo.add(btnReportes);
        panelIzquierdo.add(btnMiCuenta);
        panelIzquierdo.add(regresar);

        panelDerecho = new JPanel(new CardLayout());//lo que sale al lado derecho - paneles
        
        //subpanel reportes 
        JPanel reportesSubPanel = new JPanel(new GridLayout(2, 1));
        JButton btnRanking = new JButton("Ranking");
        JButton btnUltimosLogs = new JButton("Últimos Logs");
        reportesSubPanel.add(btnRanking);
        reportesSubPanel.add(btnUltimosLogs);
        panelDerecho.add(reportesSubPanel, "Reportes");// Añadir subpaneles al panel derecho

        // Subopciones de "Mi Cuenta"
        JPanel miCuentaSubPanel = new JPanel(new GridLayout(3, 1));
        JButton btnVerInformacion = new JButton("Ver mis datos");
        JButton btnCambiarContrasena = new JButton("Cambiar Contraseña");
        JButton btnLogoutCuenta = new JButton("borrar cuenta");
        miCuentaSubPanel.add(btnVerInformacion);
        miCuentaSubPanel.add(btnCambiarContrasena);
        miCuentaSubPanel.add(btnLogoutCuenta);
        panelDerecho.add(miCuentaSubPanel, "Mi Cuenta");// Añadir subpaneles al panel derecho
        
        // Paneles específicos para las subopciones de reportes 
        ranking();
        ultimosLogs();
        
        //paneles de subopciones mi cuenta 
        cambiarPassword();
        misDatos();
        borrarCuenta();
        
        // Acción para cambiar entre reportes y mi cuenta
        btnReportes.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Reportes");
        });

        btnMiCuenta.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Mi Cuenta");
        });

        // Acciones para las subopciones de "Reportes"
        btnRanking.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Ranking");
        });

        btnUltimosLogs.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Últimos Logs");
        });

        regresar.addActionListener(e -> {
            cardLayout.show(panelCentral, "Jugar");
        });

        // Acciones para las subopciones de "Mi Cuenta"
        btnCambiarContrasena.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Cambiar Contraseña");
        });

        btnVerInformacion.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "verDatos");
        });
        
        btnLogoutCuenta.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "borrar");
        });

       
        // Configurar el JSplitPane con los paneles izquierdo y derecho
        splitPane.setLeftComponent(panelIzquierdo);
        splitPane.setRightComponent(panelDerecho);

        // Añadir el JSplitPane al panel de ajustes
        JPajustes.add(splitPane, BorderLayout.CENTER);

        panelCentral.add(JPajustes, "ajustes");
    
    }
    
    public void ranking(){
    JPanel rankingPanel = new JPanel();
    panelDerecho.add(rankingPanel, "Ranking");
    
    gestorJugadores.ordenarJugadoresPorPuntos();

        // Definir las columnas de la tabla
        String[] columnNames = {"Posición", "Username", "Puntos"};

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar el modelo de la tabla con los jugadores ordenados
        for (int i = 0; i < gestorJugadores.numjugadores; i++) {
            if (gestorJugadores.jugadores[i] != null) {
                // Agregar una fila por cada jugador
                Object[] row = {
                    i + 1, // Posición
                    gestorJugadores.jugadores[i].getUsername(), // Username
                    gestorJugadores.jugadores[i].getPuntos() // Puntos
                };
                model.addRow(row);
            }
    
    }
        
        
        // Crear la tabla con el modelo
        rankingTable = new JTable(model);
        rankingTable.setFillsViewportHeight(true);

        // Mostrar la tabla dentro del panel
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        //panelDerecho.removeAll(); // Limpiar el panel antes de agregar el JTable
        rankingPanel.add(scrollPane, BorderLayout.CENTER);
        //panelDerecho.revalidate(); // Actualizar la interfaz
        //panelDerecho.repaint();  
    }
    
        
    public void ultimosLogs(){
    JPanel ultimosLogsPanel = new JPanel();
        ultimosLogsPanel.add(new JLabel("Aquí están los Últimos Logs"));
          // Crear un JTextArea para mostrar los logs
    JTextArea textAreaLogs = new JTextArea(10, 30); // 10 filas, 30 columnas
    textAreaLogs.setEditable(false); // Los logs no serán editables por el usuario

    // Obtener los logs del jugador
    String[] logs = jugador.getLogPartidas();

    // Recorrer los logs y añadirlos al JTextArea
    for (String log : logs) {
        if (log != null) {
            textAreaLogs.append(log + "\n");
        }
    }

    // Colocar el JTextArea dentro de un JScrollPane para permitir desplazamiento
    JScrollPane scrollPane = new JScrollPane(textAreaLogs);

    // Añadir el scrollPane al panel
    ultimosLogsPanel.add(scrollPane, BorderLayout.CENTER);
    panelDerecho.add(ultimosLogsPanel, "Últimos Logs");
    }
    
    public void cambiarPassword(){
    JPanel cambiarContrasenaPanel = new JPanel();
    cambiarContrasenaPanel.setLayout(null);
    
    JLabel etiqueta = new JLabel("Cambiar Password");
      cambiarContrasenaPanel.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(250,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.MAGENTA);//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,18));
    
    JLabel vieja = new JLabel("Actual password:");
      cambiarContrasenaPanel.add(vieja);//agregar etiqueta al panel
      vieja.setBounds(200,180,300,50);//tamaño y posición de la etiqueta
      vieja.setForeground(Color.BLACK);//ponerle color a las letras 
      vieja.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      JTextField v = new JTextField();
      cambiarContrasenaPanel.add(v);
      v.setBounds(200, 220, 290, 37);
  
      
      JLabel nueva = new JLabel("New password:");
      cambiarContrasenaPanel.add(nueva);//agregar etiqueta al panel
      nueva.setBounds(200,260,300,50);//tamaño y posición de la etiqueta
      nueva.setForeground(Color.BLACK);//ponerle color a las letras 
      nueva.setFont(new Font("Tahoma",Font.BOLD,18));
      
      JTextField n = new JTextField();
      cambiarContrasenaPanel.add(n);
      n.setBounds(200, 300, 290, 37);
      
      JButton cambiar = new JButton("CAMBIAR");
      cambiar.setBounds(240, 400, 180, 46);
      cambiarContrasenaPanel.add(cambiar);
      
        cambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String vieja = v.getText();
                String nueva = n.getText();

                boolean cambiarC = gestorJugadores.cambiarP(jugador, vieja, nueva);

                if (cambiarC) {
                    JOptionPane.showMessageDialog(null, "Password cambiada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    CardLayout cl = (CardLayout) panelDerecho.getLayout();
                    cl.show(panelDerecho, "Mi Cuenta");
                } else {
                    //muestra las opciones de error 
                }

            }
        });
      
    panelDerecho.add(cambiarContrasenaPanel, "Cambiar Contraseña");
    }
    
    public void misDatos(){
    JPanel verDatos = new JPanel();
        verDatos.setLayout(null);
        JLabel LD = new JLabel("MIS DATOS");
        LD.setBounds(100, 10, 100, 50);
        verDatos.add(LD);
        JTextArea info = new JTextArea();
        info.setBounds(50, 50, 500, 500);
        info.setText(jugador.mostrarInfo());
        verDatos.add(info);
     panelDerecho.add(verDatos, "verDatos");
    }
  
    public void borrarCuenta(){
    JPanel borrar = new JPanel();
    borrar.setLayout(null);
  
    JLabel etiqueta = new JLabel("BORRAR CUENTA");
      borrar.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(250,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.MAGENTA);//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,18));
    
    JLabel contra = new JLabel("Password:");
      borrar.add(contra);//agregar etiqueta al panel
      contra.setBounds(200,180,300,50);//tamaño y posición de la etiqueta
      contra.setForeground(Color.BLACK);//ponerle color a las letras 
      contra.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      JTextField c = new JTextField();
      borrar.add(c);
      c.setBounds(200, 220, 290, 37);
    
    JButton cambiar = new JButton("BORRAR CUENTA");
      cambiar.setBounds(260, 300, 180, 46);
      borrar.add(cambiar);
      
        cambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = c.getText();
                int confirmar = JOptionPane.showConfirmDialog(null, "Seguro deseas borrar tu cuenta?", "confirmar borrar", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
            boolean borrado = gestorJugadores.eliminarCuenta(jugador, password);

            if (borrado) {
                JOptionPane.showMessageDialog(null, "Se borró la cuenta correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cierra el menú principal
                new inicio(gestorJugadores).setVisible(true); // Abre el menú de inicio
            } else {
                //las otras opciones
            }
        } else if (confirmar == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "No se borró la cuenta.", "Información", JOptionPane.INFORMATION_MESSAGE);
            CardLayout cl = (CardLayout) panelDerecho.getLayout();
            cl.show(panelDerecho, "Mi Cuenta");
        }

            }
        });
    
    
    panelDerecho.add(borrar, "borrar");
    
    
    }
    
    
    
    
}


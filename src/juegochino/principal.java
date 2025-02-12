package juegochino;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
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
    FondoPanel fondo = new FondoPanel();//ACTIVEFONDO

    public principal(gestionJugadores gestorJugadores, jugador jugador) {
        this.jugador = jugador;
        this.gestorJugadores = gestorJugadores;
        setTitle("Menú Principal");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setContentPane(fondo);//FONDO 
        iniciarComponentes();
        setVisible(true); 
    }

    private void iniciarComponentes() {
        cardLayout = new CardLayout();
        panelCentral = new JPanel();
        panelCentral.setLayout(cardLayout);
        panelCentral.setOpaque(false); // Fondo transparente para que se vea el fondo

        fondo.setLayout(new BorderLayout()); // Asegurar un buen layout para agregar componentes
        fondo.add(panelCentral, BorderLayout.CENTER); // Agregar el panel central al fondo
        
        primerPanel(); // Inicializa el primer panel (jugar)
        segundoPanel(); // Inicializa el segundo panel (ajustes)
    }

    public void primerPanel() {
        JPanel jugar = new JPanel();
        jugar.setLayout(null); // Para usar setBounds
        jugar.setOpaque(false); // Fondo transparente

        // Botón para comenzar el juego
        JButton comenzarP = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/INICIAR .png")).getImage()));
        comenzarP.setBounds(325, 250, 147, 47); 
        jugar.add(comenzarP); // Agregar al panel jugar
        
        // Acción del botón comenzar
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

        // Botón de ajustes
        JButton JBajustes = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/ajustes.png")).getImage()));
        JBajustes.setBounds(680, 400, 50, 50);
        JBajustes.setContentAreaFilled(false); // Elimina el relleno del botón
        JBajustes.setBorderPainted(false);     // Elimina el borde del botón
        JBajustes.setFocusPainted(false);      // Elimina el foco visual

        // Hacer que el área de clic sea redonda
        JBajustes.setOpaque(false); // Asegura que el fondo sea transparente
        JBajustes.setBounds(680, 400, 50, 50);
        jugar.add(JBajustes); 

        // Acción del botón ajustes
        JBajustes.addActionListener(e -> cardLayout.show(panelCentral, "ajustes"));

        // Botón de logout
        JButton logout = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/imagenes/salir.png")).getImage()));
        logout.setBounds(680, 470, 50, 50);
        logout.setBounds(680, 470, 50, 50);
        logout.setContentAreaFilled(false); // Elimina el relleno del botón
        logout.setBorderPainted(false);     // Elimina el borde del botón
        logout.setFocusPainted(false);      // Elimina el foco visual

        // Hacer que el área de clic sea redonda
        logout.setOpaque(false);
        jugar.add(logout);

        // Acción del botón logout
        logout.addActionListener(e -> {
            dispose(); 
            new inicio(gestorJugadores).setVisible(true);
        });

        panelCentral.add(jugar, "Jugar"); // Agregar el panel jugar al CardLayout
    }

    public void segundoPanel() {
        JPanel JPajustes = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(100);

        // Panel izquierdo con opciones
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1));
        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBackground(Color.decode("#f7f4eb"));
        btnReportes.setFont(new Font("Tahoma",Font.BOLD,12));
        btnReportes.setForeground(Color.decode("#80584d"));
        
        JButton btnMiCuenta = new JButton("Mi Cuenta");
        btnMiCuenta.setBackground(Color.decode("#f7f4eb"));
        btnMiCuenta.setFont(new Font("Tahoma",Font.BOLD,12));
        btnMiCuenta.setForeground(Color.decode("#80584d"));
        
        JButton regresar = new JButton("Regresar");
        regresar.setBackground(Color.decode("#f7f4eb"));
        regresar.setFont(new Font("Tahoma",Font.BOLD,12));
        regresar.setForeground(Color.decode("#80584d"));
        
        panelIzquierdo.add(btnReportes);
        panelIzquierdo.add(btnMiCuenta);
        panelIzquierdo.add(regresar);

        // Panel derecho con subopciones
        panelDerecho = new JPanel(new CardLayout());
        JPanel reportesSubPanel = new JPanel(new GridLayout(2, 1));
        
        JButton btnRanking = new JButton("Ranking");
        btnRanking.setBackground(Color.decode("#f7f4eb"));
        btnRanking.setFont(new Font("Tahoma",Font.BOLD,24));
        btnRanking.setForeground(Color.decode("#80584d"));
        
        JButton btnUltimosLogs = new JButton("Últimos Logs");
        btnUltimosLogs.setBackground(Color.decode("#f7f4eb"));
        btnUltimosLogs.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnUltimosLogs.setForeground(Color.decode("#80584d"));
        
        reportesSubPanel.add(btnRanking);
        reportesSubPanel.add(btnUltimosLogs);
        panelDerecho.add(reportesSubPanel, "Reportes");
        

        JPanel miCuentaSubPanel = new JPanel(new GridLayout(3, 1));
        JButton btnVerInformacion = new JButton("Ver mis datos");
        btnVerInformacion.setBackground(Color.decode("#f7f4eb"));
        btnVerInformacion.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnVerInformacion.setForeground(Color.decode("#80584d"));
        
        JButton btnCambiarContrasena = new JButton("Cambiar Contraseña");
        btnCambiarContrasena.setBackground(Color.decode("#f7f4eb"));
        btnCambiarContrasena.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnCambiarContrasena.setForeground(Color.decode("#80584d"));
        
        JButton btnLogoutCuenta = new JButton("Borrar cuenta");
        btnLogoutCuenta.setBackground(Color.decode("#f7f4eb"));
        btnLogoutCuenta.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnLogoutCuenta.setForeground(Color.decode("#80584d"));
        
        miCuentaSubPanel.add(btnVerInformacion);
        miCuentaSubPanel.add(btnCambiarContrasena);
        miCuentaSubPanel.add(btnLogoutCuenta);
        panelDerecho.add(miCuentaSubPanel, "Mi Cuenta");

        // Acciones de los botones
        btnReportes.addActionListener(e -> ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, "Reportes"));
        btnMiCuenta.addActionListener(e -> ((CardLayout) panelDerecho.getLayout()).show(panelDerecho, "Mi Cuenta"));
        regresar.addActionListener(e -> cardLayout.show(panelCentral, "Jugar"));

        splitPane.setLeftComponent(panelIzquierdo);
        splitPane.setRightComponent(panelDerecho);

        JPajustes.add(splitPane, BorderLayout.CENTER);
        
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
    rankingPanel.setBackground(Color.decode("#f7f4eb"));
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
    
        
    public void ultimosLogs() {
        JPanel ultimosLogsPanel = new JPanel();
        ultimosLogsPanel.setBackground(Color.decode("#f7f0e0"));
        
        JLabel titleLabel = new JLabel("10 Últimos Logs");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        ultimosLogsPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Crear un JTextArea para mostrar los logs
        JTextArea textAreaLogs = new JTextArea(30, 70); // 10 filas, 30 columnas
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
     cambiarContrasenaPanel.setBackground(Color.decode("#f7f0e0"));
    cambiarContrasenaPanel.setLayout(null);
    
    JLabel etiqueta = new JLabel("Cambiar Password");
      cambiarContrasenaPanel.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(250,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.decode("#c7326b"));//ponerle color a las letras 
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
        cambiar.setBounds(250, 400, 180, 46);
        cambiar.setForeground(Color.WHITE);
        cambiar.setFont(new Font("Tahoma", Font.BOLD, 18));
        cambiar.setBackground(Color.decode("#a1162b"));
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
    verDatos.setBackground(Color.decode("#f7f0e0"));
        verDatos.setLayout(null);
        JLabel LD = new JLabel("MIS DATOS");
        LD.setFont(new Font("Tahoma",Font.BOLD,19));
        LD.setBounds(100, 10, 200, 50);
        verDatos.add(LD);
        JTextArea info = new JTextArea();
        info.setBounds(50, 50, 500, 400);
        info.setText(jugador.mostrarInfo());
        verDatos.add(info);
     panelDerecho.add(verDatos, "verDatos");
    }
  
    public void borrarCuenta(){
    JPanel borrar = new JPanel();
    borrar.setBackground(Color.decode("#f7f0e0"));
    borrar.setLayout(null);
  
    JLabel etiqueta = new JLabel("BORRAR CUENTA");
      borrar.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(250,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.decode("#c7326b"));//ponerle color a las letras 
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
      cambiar.setBounds(260, 300, 150, 45);
        cambiar.setForeground(Color.WHITE);
        cambiar.setFont(new Font("Tahoma", Font.BOLD, 12));
        cambiar.setBackground(Color.decode("#a1162b"));
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
    
    class FondoPanel extends JPanel{//ACTIVARFONDO
  
      private Image imagen; 
      
      @Override
      public void paintComponent(Graphics g){
          super.paintComponent(g);
          imagen = new ImageIcon(getClass().getResource("/imagenes/principal.jpg")).getImage();
          g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
          
      
      }
  }
    
    
}


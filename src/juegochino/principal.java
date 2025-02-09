package juegochino;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class principal extends JFrame {
private CardLayout cardLayout;
    private JPanel panelCentral;
    private jugador jugador;
    private gestionJugadores gestorJugadores;
    private juego j; 

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
        cardLayout = new CardLayout();
        panelCentral = new JPanel();
        panelCentral.setLayout(cardLayout);
        
        JPanel jugar = new JPanel();//crear panel inicial con la parte de jugar y los botones de ajustes y log out
        jugar.setLayout(null);//para poder usar setbounds y moverlo a donde yo quiera
        JButton comenzarP = new JButton("comenzar partida");//boton para comenzar el juego que desplegaria la lista de los jugadores
        comenzarP.setBounds(325, 250, 147, 47);//lugar donde lo quiero colocar
        jugar.add(comenzarP);//agregar el boton al panel de jugar
        panelCentral.add(jugar, "Jugar");//agregar el panel de jugar a el panel central 
        getContentPane().add(panelCentral);//hacerlo visible 
        
        comenzarP.addActionListener(e -> {
        // Cerrar el menú principal
        dispose();

        // Abrir el tablero
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
                new inicio(gestorJugadores).setVisible(true); // Abre el menú de login nuevamente
            }
        });
        
        JButton JBajustes = new JButton("A");
        JBajustes.setBounds(680, 400, 50, 50);
        jugar.add(JBajustes);
        
        //segundo jpanel
        JPanel JPajustes = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(100);
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1)); // GridLayout para organizar botones
        JButton btnReportes = new JButton("Reportes");
        JButton btnMiCuenta = new JButton("Mi Cuenta");
        JButton regresar = new JButton("Regresar");
        panelIzquierdo.add(btnReportes);
        panelIzquierdo.add(btnMiCuenta);
        panelIzquierdo.add(regresar);

        JPanel panelDerecho = new JPanel(new CardLayout());
        
        JPanel reportesSubPanel = new JPanel(new GridLayout(2, 1));
        JButton btnRanking = new JButton("Ranking");
        JButton btnUltimosLogs = new JButton("Últimos Logs");
        
        reportesSubPanel.add(btnRanking);
        reportesSubPanel.add(btnUltimosLogs);
        

        // Subopciones de "Mi Cuenta"
        JPanel miCuentaSubPanel = new JPanel(new GridLayout(3, 1));
        JButton btnVerInformacion = new JButton("Ver mis datos");
        JButton btnCambiarContrasena = new JButton("Cambiar Contraseña");
        JButton btnLogoutCuenta = new JButton("cerrar cuenta");
        miCuentaSubPanel.add(btnVerInformacion);
        miCuentaSubPanel.add(btnCambiarContrasena);
        miCuentaSubPanel.add(btnLogoutCuenta);
        
       

        // Paneles específicos para las subopciones
        JPanel rankingPanel = new JPanel();
        rankingPanel.add(new JLabel("Aquí está el Ranking"));
        JPanel ultimosLogsPanel = new JPanel();
        ultimosLogsPanel.add(new JLabel("Aquí están los Últimos Logs"));
        JPanel cambiarContrasenaPanel = new JPanel();
        cambiarContrasenaPanel.add(new JLabel("Aquí puedes cambiar tu Contraseña"));
        
        JPanel verDatos = new JPanel();
        verDatos.setLayout(null);
        JLabel LD = new JLabel("MIS DATOS");
        LD.setBounds(100, 10, 100, 50);
        verDatos.add(LD);
        JTextArea info = new JTextArea();
        info.setBounds(50, 50, 500, 500);
        info.setText(jugador.mostrarInfo());
        verDatos.add(info);
        

        // Añadir subpaneles al panel derecho
        panelDerecho.add(reportesSubPanel, "Reportes");
        panelDerecho.add(miCuentaSubPanel, "Mi Cuenta");
        panelDerecho.add(rankingPanel, "Ranking");
        panelDerecho.add(ultimosLogsPanel, "Últimos Logs");
        panelDerecho.add(cambiarContrasenaPanel, "Cambiar Contraseña");
        panelDerecho.add(verDatos, "verDatos");
        
        

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
            cl.show(panelDerecho, "Cerrar cuenta");
        });

       
        // Configurar el JSplitPane con los paneles izquierdo y derecho
        splitPane.setLeftComponent(panelIzquierdo);
        splitPane.setRightComponent(panelDerecho);

        // Añadir el JSplitPane al panel de ajustes
        JPajustes.add(splitPane, BorderLayout.CENTER);

        panelCentral.add(JPajustes, "ajustes");

        JBajustes.addActionListener(e -> cardLayout.show(panelCentral, "ajustes"));


        
    }
    
  
}

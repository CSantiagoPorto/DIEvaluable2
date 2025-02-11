import javax.swing.*;

import BaseDatos.GestionBD;
import model.Alumno;
import model.Profesor;
import model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JComboBox<String> cbCargo;
    private JButton logo;
    private GestionBD db;

    public Login() {
    
        db = new GestionBD();
        setResizable(false);
        getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
        getContentPane().setBackground(new Color(176, 224, 230));
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/logo.png")));
        setTitle("Login");
        setSize(512, 274);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        userLabel.setBounds(30, 30, 80, 25);
        getContentPane().add(userLabel);

        userField = new JTextField();
        userField.setBounds(120, 30, 200, 25);
        getContentPane().add(userField);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(30, 70, 100, 25);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 200, 25);
        getContentPane().add(passwordField);

        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cargoLabel.setBounds(30, 110, 80, 25);
        getContentPane().add(cargoLabel);

        cbCargo = new JComboBox<>(new String[]{"Seleccione", "Alumno", "Profesor"});
        cbCargo.setFont(new Font("Verdana", Font.PLAIN, 14));
        cbCargo.setBounds(120, 110, 200, 25);
        getContentPane().add(cbCargo);

        logo = new JButton("");
        logo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logo.png")));
        logo.setBounds(356, 30, 113, 79);
        getContentPane().add(logo);

        JButton loginbtn = new JButton("Iniciar Sesión");
        loginbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginbtn.setBounds(181, 160, 150, 30);
        getContentPane().add(loginbtn);

        JButton limpiarbtn = new JButton("Limpiar");
        limpiarbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        limpiarbtn.setBounds(30, 200, 100, 30);
        getContentPane().add(limpiarbtn);

        JButton salirbtn = new JButton("Salir");
        salirbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        salirbtn.setBounds(389, 200, 80, 30);
        getContentPane().add(salirbtn);

        JButton btnRegistrar = new JButton("Registrese");
        btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegistrar.setForeground(Color.BLUE);
        btnRegistrar.setBounds(206, 200, 100, 30);
        getContentPane().add(btnRegistrar);

        loginbtn.addActionListener(e -> validarLogin());
        btnRegistrar.addActionListener(e -> new Registro().setVisible(true));
        limpiarbtn.addActionListener(e -> {
            userField.setText("");
            passwordField.setText("");
            cbCargo.setSelectedIndex(0);
        });
        salirbtn.addActionListener(e -> System.exit(0));
    }

    private void validarLogin() {
        String cargo = (String) cbCargo.getSelectedItem();
        String user = userField.getText();
        String password = new String(passwordField.getPassword());

        if ("Alumno".equals(cargo)) {
            try {
                ResultSet rs = db.buscarAlumno(user, password);
                if (rs != null && rs.next()) {
                    Alumno alumno = new Alumno(
                        rs.getString("dni_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("pass")
                    );
                    new VentanaAlumno(alumno).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else if ("Profesor".equals(cargo)) {
            try {
                ResultSet rs = db.buscarProfesor(user, password);
                if (rs != null && rs.next()) {
                    Profesor profesor = new Profesor(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("direccion"),
                        rs.getString("pass"),
                        rs.getString("dni_profesor")
                    );
                    new VentanaProfesor(profesor).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cargo.");
        }
    }
}

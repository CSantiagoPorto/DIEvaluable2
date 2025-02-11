import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

public class Registro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfApellido;
    private JTextField tfNombre;
    private JTextField tfContraseña;
    private JComboBox<String> comboBoxSexo;
    private JCalendar calendar;
    private Date fecha;
    private JTextField tfDni;

    public Registro() {
        setResizable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(10, 68, 70, 13);
        contentPane.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        tfNombre.setBounds(90, 65, 96, 19);
        contentPane.add(tfNombre);
        tfNombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        lblApellido.setBounds(10, 112, 70, 13);
        contentPane.add(lblApellido);

        tfApellido = new JTextField();
        tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        tfApellido.setBounds(90, 109, 96, 19);
        contentPane.add(tfApellido);
        tfApellido.setColumns(10);

        JLabel lblContraseña = new JLabel("Contraseña");
        lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        lblContraseña.setBounds(10, 162, 70, 13);
        contentPane.add(lblContraseña);

        tfContraseña = new JTextField();
        tfContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        tfContraseña.setBounds(90, 159, 96, 19);
        contentPane.add(tfContraseña);
        tfContraseña.setColumns(10);

        JLabel lblFecha = new JLabel("Fecha nacimiento");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        lblFecha.setBounds(196, 68, 120, 13);
        contentPane.add(lblFecha);

        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSexo.setBounds(196, 112, 45, 13);
        contentPane.add(lblSexo);

        comboBoxSexo = new JComboBox<>();
        comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        comboBoxSexo.setBounds(315, 108, 96, 21);
        comboBoxSexo.addItem("Seleccione");
        comboBoxSexo.addItem("Masculino");
        comboBoxSexo.addItem("Femenino");
        contentPane.add(comboBoxSexo);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        btnLimpiar.setBounds(40, 213, 96, 21);
        contentPane.add(btnLimpiar);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        btnRegistro.setBounds(185, 213, 111, 21);
        contentPane.add(btnRegistro);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        btnCerrar.setBounds(341, 213, 85, 21);
        contentPane.add(btnCerrar);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(315, 62, 96, 19);
        contentPane.add(dateChooser);
        
        tfDni = new JTextField();
        tfDni.setBounds(315, 156, 96, 19);
        contentPane.add(tfDni);
        tfDni.setColumns(10);
        
        JLabel lblDni = new JLabel("DNI");
        lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDni.setBounds(196, 152, 100, 27);
        contentPane.add(lblDni);

        // Evento para registrar usuario
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = tfNombre.getText();
                String apellido = tfApellido.getText();
                String contraseña = tfContraseña.getText();

                if (dateChooser.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha de nacimiento.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNacimiento = sdf.format(dateChooser.getDate());
                String sexo = (String) comboBoxSexo.getSelectedItem();

                if (nombre.isEmpty() || apellido.isEmpty() || contraseña.isEmpty() || fechaNacimiento.isEmpty() || "Seleccione".equals(sexo)) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,  
                        "Usuario registrado con éxito:\n" + 
                        "Nombre: " + nombre + "\n" + 
                        "Apellido: " + apellido + "\n" + 
                        "Contraseña: " + contraseña + "\n" + 
                        "Fecha de nacimiento: " + fechaNacimiento + "\n" + 
                        "Sexo: " + sexo,
                        "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfNombre.setText("");
                tfApellido.setText("");
                tfContraseña.setText("");
                dateChooser.setDate(null); 
                comboBoxSexo.setSelectedIndex(0);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

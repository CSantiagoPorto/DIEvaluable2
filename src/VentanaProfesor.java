import javax.swing.*;

import BaseDatos.GestionBD;
import model.Alumno;
import model.Profesor;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class VentanaProfesor extends JFrame implements ActionListener, ItemListener {
    private JComboBox<String> ModulosComboBox;
    private JComboBox<String> AlumnosComboBox;
    private JTextField NotaTextField;
    private JButton Guardarbtn;
    private JButton CerrarSesionbtn;
    private GestionBD db;

    // ArrayList con los alumnos
    ArrayList<Alumno> listaAlumnos = new ArrayList<>();
	private Profesor profesor;

    public VentanaProfesor(Profesor profesor) {
    	this.profesor = profesor;
    	
    	db= new GestionBD();
        
        setResizable(false);
        getContentPane().setBackground(new Color(176, 224, 230));
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProfesor.class.getResource("/imagenes/logo.png")));
        setTitle("Gestión de Notas - Profesor");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);



        
        JLabel moduloLabel = new JLabel("Módulo:");
        moduloLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        moduloLabel.setBounds(30, 30, 80, 25);
        getContentPane().add(moduloLabel);

        ModulosComboBox = new JComboBox<>();
       
        
        ModulosComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ModulosComboBox.setBounds(105, 30, 180, 25);
        getContentPane().add(ModulosComboBox);

        
        JLabel AlumnosLabel = new JLabel("Alumno:");
        AlumnosLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        AlumnosLabel.setBounds(30, 70, 80, 25);
        getContentPane().add(AlumnosLabel);

        
        AlumnosComboBox = new JComboBox<>();
        AlumnosComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        AlumnosComboBox.setBounds(105, 70, 180, 25);
        getContentPane().add(AlumnosComboBox);

        
        JLabel NotaLabel = new JLabel("Nota:");
        NotaLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        NotaLabel.setBounds(90, 120, 43, 25);
        getContentPane().add(NotaLabel);

        NotaTextField = new JTextField();
        NotaTextField.setBounds(143, 120, 106, 25);
        getContentPane().add(NotaTextField);

        // Botón para guardar la nota
        Guardarbtn = new JButton("Guardar");
        Guardarbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        Guardarbtn.setBounds(84, 180, 100, 25);
        getContentPane().add(Guardarbtn);

        Guardarbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarNota();
            }
        });

      
        ModulosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   actualizarAlumnos();// Llamamos a actualizar alumnos
                //CAMBIAR A DB
            }
        });

        // Botón para cerrar sesión
        CerrarSesionbtn = new JButton("Cerrar Sesión");
        CerrarSesionbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        CerrarSesionbtn.setBounds(194, 180, 120, 25);
        getContentPane().add(CerrarSesionbtn);

        CerrarSesionbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

      //  actualizarAlumnos();
    }

    /* Método para actualizar la lista de alumnos 
    private void actualizarAlumnos() {
        AlumnosComboBox.removeAllItems();
        for (Alumno alumno : listaAlumnos) {
            AlumnosComboBox.addItem(alumno.getNombre());
        }
    }*/

   
    private void guardarNota() {//ESTO HAY QUE CAMBIARLO DE CLASE
    	//AHORA NECESITAMOS PASÁRSELA A LA BD-> pARA GUARDAR NECESITO UN MÉTODO BOOLEAN QUE HAGA UNA INSERCION
    	//creo la query en GestionDB--> un objeto Statement--> voy a necesitar un executeUpdate para hacer el INSERT
    	//Luego aquí sólo llamo al método 
        String moduloSelec = (String) ModulosComboBox.getSelectedItem();
        String alumnoSelec = (String) AlumnosComboBox.getSelectedItem();
        String notaIngresada = NotaTextField.getText();

        double nota;
        try {
            nota = Double.parseDouble(notaIngresada);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La nota no es válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        JOptionPane.showMessageDialog(this, "Nota guardada:\n" + "Módulo: " + moduloSelec + "\n" + "Alumno: " + alumnoSelec + "\n" + "Nota: " + nota);
    }
    
    public void cargarModulos() {
		try {
			ResultSet rs= db.BuscarTodosAsignaturas();
			while (rs.next()) {
				ModulosComboBox.addItem(rs.getString("DENOMINACION"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

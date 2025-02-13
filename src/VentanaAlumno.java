import javax.swing.*;

import BaseDatos.GestionBD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Alumno;

public class VentanaAlumno extends JFrame {
    private Alumno alumno;
    private JComboBox<String> cbAsignatura;
    
    private JTextField tfNota;
    private GestionBD bd;

    public VentanaAlumno(Alumno alumno) {
        this.alumno = alumno;
        
        
       bd = new GestionBD();//Inicializo la BD

        setTitle("Ventana Alumno - " + alumno.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);

        
        JLabel welcomeLabel = new JLabel("Bienvenido, " + alumno.getNombre());
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        welcomeLabel.setBounds(100, 20, 250, 25);
        getContentPane().add(welcomeLabel);

        
        JLabel lblAsignatura = new JLabel("Asignaturas");
        lblAsignatura.setBounds(50, 60, 150, 25);
        getContentPane().add(lblAsignatura);

        cbAsignatura = new JComboBox<>();
        cbAsignatura.setBounds(200, 60, 150, 25);
        getContentPane().add(cbAsignatura);
        cbAsignatura.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if(e.getStateChange()==ItemEvent.SELECTED) {
        			String asignaturaSelect= (String) cbAsignatura.getSelectedItem();
        			mostrarNota(asignaturaSelect);
        		}
        	}
        });
        

       
        JLabel lblNota = new JLabel("Nota:");
        lblNota.setBounds(50, 100, 150, 25);
        getContentPane().add(lblNota);

        tfNota = new JTextField();
        tfNota.setBounds(200, 100, 150, 25);
        tfNota.setEditable(false);
        getContentPane().add(tfNota);

        
        JButton cerrarSesionBtn = new JButton("Cerrar Sesión");
        cerrarSesionBtn.setBounds(140, 200, 120, 30);
        getContentPane().add(cerrarSesionBtn);

        cerrarSesionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

        System.out.println(" Llamando a mostrarAsignaturas() para el alumno: " + alumno.getDni_alumno());
        mostrarAsignaturas();

       
       
    }

    private void mostrarAsignaturas() {
    	try {
    		boolean todoBien=true;
			ResultSet rs= bd.mostrarNotasAlumno(alumno.getDni_alumno());
			cbAsignatura.removeAllItems();
			while(rs.next()) {
				String asignatura= rs.getString("denominacion");
				System.out.println("agregando al cbB la asignatura:"+ asignatura);
				cbAsignatura.addItem(asignatura);
			}
			
			
			if(!todoBien) {
				System.out.println("No hay asignaturas en el comboBox");
			}
			cbAsignatura.revalidate();
	        cbAsignatura.repaint();
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(cbAsignatura, "No se pudieron cargar las asignaturas");
		}
    	
    }
    

    private void mostrarNota(String asignaturaNombre) {
    	
    	//Hay que hacer un método que me permita buscar la asignatura por el nombre
    	//Porque en el combo sale el nombre
    	//Buscar al alumno para ese nombre y extraer la nota
   
    	
    	String asignatura= bd.obtenerIdAsignaturaPorNombre(asignaturaNombre);
    	if(asignatura==null) {
    		System.out.println("No estoy encontrando la asignatura");
    		return;
    	}
    	
    	try {
			ResultSet rs = bd.notaAlumnoAsignatura(alumno.getDni_alumno(), asignatura);
			if(rs.next()) {
				tfNota.setText(rs.getString("calificacion"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(cbAsignatura, "Algo falló en mostrar nota");
		}
    }
}

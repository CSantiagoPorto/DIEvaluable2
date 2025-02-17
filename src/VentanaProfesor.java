import javax.swing.*;

import BaseDatos.GestionBD;
import model.Alumno;
import model.Profesor;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class VentanaProfesor extends JFrame implements ActionListener, ItemListener {
    private JComboBox<String> cbModulos;
    private JComboBox<String> cbAlumnos;
    private JTextField tfNota;
    private JButton btGuardar;
    private JButton btnSalir;
    private GestionBD db;

    ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    private Profesor profesor;

    public VentanaProfesor(Profesor profesor) {
        this.profesor = profesor;
        
        db = new GestionBD();
        setResizable(false);
        getContentPane().setBackground(new Color(176, 224, 230));
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProfesor.class.getResource("/imagenes/logo.png")));
        setTitle("Gestión de Notas - Profesor");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panelTítulo = new JPanel();
        panelTítulo.setBackground(new Color(100, 149, 237));
        panelTítulo.setPreferredSize(new Dimension(500, 60));
        panelTítulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel tituloLabel = new JLabel("Gestión de Notas - Profesor");
        tituloLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        tituloLabel.setForeground(Color.WHITE);
        panelTítulo.add(tituloLabel);

        getContentPane().add(panelTítulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(3, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lbModulo = new JLabel("Módulo:");
        lbModulo.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelCentral.add(lbModulo);

        cbModulos = new JComboBox<>();
        panelCentral.add(cbModulos);

        JLabel lbAlumnos = new JLabel("Alumno:");
        lbAlumnos.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelCentral.add(lbAlumnos);

        cbAlumnos = new JComboBox<>();
      
        //Hacemos un evento de cambio 
        cbAlumnos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String alumnoSeleccionado = (String) cbAlumnos.getSelectedItem();
                    String asignaturaSeleccionada = (String) cbModulos.getSelectedItem();//Devuelve el módulo que el usuario ha seleccionado
                    if (alumnoSeleccionado == null || asignaturaSeleccionada == null) {
                        System.out.println("Alumno o asignatura no seleccionados.");
                        return;
                    }

                    //System.out.println("Alumno seleccionado: " + alumnoSeleccionado);
                    String dniAlumno = obtenerDniAlumno(alumnoSeleccionado);


                    if (dniAlumno == null) {
                        System.out.println("No se encontró el DNI del alumno.");
                        return;
                    }

                    mostrarNota(dniAlumno, asignaturaSeleccionada);
                }
            }
        });

        mostrarModulos(profesor.getDni_profesor() );
        panelCentral.add(cbAlumnos);

        JLabel lbNota = new JLabel("Nota:");
        lbNota.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelCentral.add(lbNota);

        tfNota = new JTextField();
        panelCentral.add(tfNota);

        getContentPane().add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(230, 240, 250));
        panelBotones.setPreferredSize(new Dimension(500, 60));
        panelBotones.setLayout(new FlowLayout());

        btGuardar = new JButton("Guardar");
        btGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panelBotones.add(btGuardar);

        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panelBotones.add(btnSalir);

        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        btGuardar.addActionListener(e -> guardarNota());
        btnSalir.addActionListener(e -> {
            new VentanaInicio().setVisible(true);
            dispose();
        });
        
        cbModulos.addItemListener(this);//Hay que iniciar
        
    }




    public void mostrarModulos(String dniProfesor) {
    	
    	try {
			ResultSet rs = db.modulosProfesor(dniProfesor);
			cbModulos.removeAllItems(); 
			while(rs.next()) {
				String modulo = rs.getString("denominacion");
				//System.out.println("Módulo encontrado: " + modulo);
				cbModulos.addItem(modulo); ;//Necesito el valor que está en la columna
				//cbModulos.addItem(modulo);Aquí se seleeciona la asignatura
				//rs es un Resulset, por lo tanto tiene la consulta y getString me devuelve el registro que tiene guardado el valor que hemos obtenido de la consulta
			}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(btGuardar, "No carga los módulos");
		}
    	
    	
    }
    //Este método DETECTA el cambio
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String nombreAsignatura = (String) cbModulos.getSelectedItem();
            //System.out.println("Asignatura seleccionada: " + nombreAsignatura);

            String idAsignatura = db.obtenerIdAsignaturaPorNombre(nombreAsignatura);
            if (idAsignatura == null) {
               // System.out.println("No se encontró un ID para la asignatura: " + nombreAsignatura);
                JOptionPane.showMessageDialog(this, "No se encontró la asignatura seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                cbAlumnos.removeAllItems();
                return;
            }

          //  System.out.println("ID de la asignatura: " + idAsignatura);
            mostrarAlumnos(idAsignatura);

        }
    }
    public void mostrarAlumnos(String idAsignatura) {
        try {
           
            System.out.println("ID Asignatura recibido: " + idAsignatura);
            cbAlumnos.removeAllItems();

            ResultSet rs = db.obtenerAlumnoAsignatura(idAsignatura);

            while (rs.next()) {
                String nombreA = rs.getString("nombre") + " " + rs.getString("apellidos");
              //  System.out.println("Alumno encontrado: " + nombreA);
                cbAlumnos.addItem(nombreA);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los alumnos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private String obtenerDniAlumno(String nombreCompleto) {
        try {
            ResultSet rs = db.obtenerDniPorNombre(nombreCompleto);
            if (rs.next()) {
                return rs.getString("dni_alumno");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void mostrarNota(String dniAlumno, String nombreAsignatura) {
    	try {
    		//El combo me devuelve el nmbre, necesito obtener el id, para poder enseñar la nota
    		String idAsignatura=db.obtenerIdAsignaturaPorNombre(nombreAsignatura);
    		if(idAsignatura == null) {
    			 System.out.println("Error: No se encontró ID para la asignatura " + nombreAsignatura);
    	            tfNota.setText(""); 
    	            return;
    		}
			ResultSet rs= db.notaAlumnoAsignatura(dniAlumno, idAsignatura) ;
			if(rs.next()) {
				String nota=(rs.getString("calificacion"));
				 tfNota.setText(nota);
				 System.out.println("Nota encontrada: " + nota);
			}else {tfNota.setText("");
			 System.out.println("No se encontró nota para este alumno en esta asignatura.");
			
				}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(tfNota, "Error al mostrar la nota");
		}
    }
    public void guardarNota() {
        String asignaturaSelec = (String) cbModulos.getSelectedItem();//Obtenemos el NOMBRE
        String alumnoSelec = (String) cbAlumnos.getSelectedItem();//Obtenemos el nombre del alumno
        String notaIngresada = tfNota.getText();//La nota del text

      

      //Obtenemos el ID  a partir del nombre
        String idAsignatura = db.obtenerIdAsignaturaPorNombre(asignaturaSelec);
     

        String dniAlumno = obtenerDniAlumno(alumnoSelec);
        if (dniAlumno == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el alumno seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

     
        double nota;
        try {
            nota = Double.parseDouble(notaIngresada);//En la BD es un VARCHAR
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La nota no es válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean guardado = db.ponerNota(dniAlumno, idAsignatura, notaIngresada);
            if (guardado) {
                JOptionPane.showMessageDialog(this, "Nota actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar la nota", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

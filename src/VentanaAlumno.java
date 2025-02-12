import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import BaseDatos.GestionBD;
import model.Alumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAlumno extends JFrame {
	private Alumno alumno;
	private GestionBD db;
	private JTable tablaNotas;
    public VentanaAlumno(Alumno alumno) {
        setTitle("Vista Alumno - " + alumno.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Bienvenido, " + alumno.getNombre());
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        welcomeLabel.setBounds(100, 20, 250, 25);
        add(welcomeLabel);
        //Tabla de notas que se rellena con el módulo y la puntuación
        String[] columnNames = {"Asignatura", "Nota"};
        Object[][] data = new Object[alumno.getAsignatura().size()][2];
        for (int i = 0; i < alumno.getAsignatura().size(); i++) {
            data[i][0] = alumno.getAsignatura().get(i);
            data[i][1] = alumno.getNotas().get(i);
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 300, 100);
        add(scrollPane);

        JButton cerrarSesionBtn = new JButton("Cerrar Sesión");
        cerrarSesionBtn.setBounds(140, 200, 120, 30);
        add(cerrarSesionBtn);

        cerrarSesionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
    }
    private void mostrarNotas(String dniAlumno) {
    	try {
			ResultSet rs = db.obtenerNotasAlumno(dniAlumno);
			int filas = 0;
			while (rs.next()) {
			    filas++;
			}//mientras el puntero detecte otra fila la cuenta

			
			rs.beforeFirst();// LLevamos arriba otra vez el puntero
			 Object[][] data = new Object[filas][2];//Usamos las filas que nos devuelve, las columnas son conocidas
	            int i = 0;
	            while (rs.next()) {
	                data[i][0] = rs.getString("DENOMINACION");
	                data[i][1] = rs.getDouble("NOTA");
	                i++;
	                

	            }String[] columnNames = {"Asignatura", "Nota"};
                tablaNotas.setModel(new DefaultTableModel(data, columnNames));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
}

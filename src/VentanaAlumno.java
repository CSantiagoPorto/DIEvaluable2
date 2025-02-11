import javax.swing.*;

import model.Alumno;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAlumno extends JFrame {
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
}

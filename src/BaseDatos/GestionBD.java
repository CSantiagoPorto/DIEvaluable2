package BaseDatos;
 
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;
 
import javax.naming.spi.DirStateFactory.Result;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
 
public class GestionBD {

	private Conexion conexion=new Conexion();

	private Connection con;

	private Statement st;

	private ResultSet resultado;
	
	//Obtener datos--> ResulSet
    //Guardar datos boolean
	
	
	
	public ResultSet mostrarNotasAlumno(String dniAlumno) throws SQLException {
		con= conexion.getConexion();
		String query= "SELECT asignaturas.denominacion, notas.calificacion " + "FROM notas " + "JOIN asignaturas ON notas.id_asignatura= asignaturas.id_asignatura "+ "WHERE notas.dni = '" +  dniAlumno + "'";
	//Selecciona la denominación y la calificación desde sus tablas
		//Se unen las tablas 
		//Devuelve los resultados para un alumno con un dni dado
		st = (Statement) con.createStatement();
		return st.executeQuery(query);
		
		//retorna un objeto ResulSet, con sus dos columnas y tantas filas como resultados positivos a dni
	}
	
	//Necesito un método que me de una nota para x alumno en x asignatura
	//Con este método saco el nombre del comboBox, y busco su id por su denominación
	public String obtenerIdAsignaturaPorNombre(String nombreModulo) {
	    try {
	        con = conexion.getConexion();
	        String query = "SELECT id_asignatura FROM asignaturas WHERE denominacion = '" + nombreModulo + "'";
	        st = (Statement) con.createStatement();
	        ResultSet rs = st.executeQuery(query);//Ejecuta y almacena la consulta

	        if (rs.next()) {//Itera
	            return rs.getString("id_asignatura");//Devuelve resultado
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	public ResultSet notaAlumnoAsignatura(String dniAlumno, String idAsignatura) throws SQLException {
		
		
	    con = conexion.getConexion();
	    String query= "SELECT calificacion FROM notas WHERE dni =  '"+dniAlumno+ "' AND ID_ASIGNATURA = '"+ idAsignatura+ "'";
	    
	    
	    st = (Statement) con.createStatement();
	    return st.executeQuery(query);
	}

	

	
	public ResultSet modulosProfesor(String dniProfesor) throws SQLException {
	    con = conexion.getConexion();
	    String query = "SELECT id_asignatura, denominacion FROM asignaturas WHERE dni_profesor = '" + dniProfesor + "'";

	    st = (Statement) con.createStatement();
	    return st.executeQuery(query);
	}
//Ahora lo llamamos desde VentanaProfesor [cargarModulos()]para que cargue
	
	public ResultSet obtenerAlumnoAsignatura(String idAsignatura) throws SQLException {
	    con = conexion.getConexion();
	    
	    
	    String query = "SELECT a.dni_alumno, a.nombre, a.apellidos FROM alumnos a " +
	                   "INNER JOIN matriculas m ON a.dni_alumno = m.dni_alumno " +
	                   "WHERE m.id_asignatura = '" + idAsignatura + "'";
	    
	    st = (Statement) con.createStatement();
	    return st.executeQuery(query);
	}


	
	public ResultSet obtenerNotasAlumno(String dniAlumno) throws SQLException {
	    con = conexion.getConexion();
  
	    String query = "SELECT asignaturas.DENOMINACION, notas.NOTA FROM notas " +
	                 "JOIN asignaturas ON notas.ID_ASIGNATURA = asignaturas.ID_ASIGNATURA " +
	                 "WHERE notas.DNI_ALUMNO = '" + dniAlumno + "'";
	    st = (Statement) con.createStatement();
	    st.close(); 
        con.close(); 
	    return st.executeQuery(query);
	    
	}

	//Como hemos puesto como Varchar el id_Nota necesito generarlo
	public String generarIdNota(String dniAlumno, String idAsignatura) {
	    return "N" + dniAlumno + idAsignatura + System.currentTimeMillis();
	}

	public boolean ponerNota(String dniAlumno, String idAsignatura, String nota) throws SQLException {
	    boolean insertado = false;
	    con = conexion.getConexion(); 
	    try {
	    	// System.out.println("Intentando poner nota. DNI: " + dniAlumno + ", ID Asignatura: " + idAsignatura + ", Nota: " + nota);
	    String existe="SELECT * FROM notas WHERE dni = '"+ dniAlumno +"' AND ID_ASIGNATURA= '"+ idAsignatura + "'";
	   
	    //System.out.println("Está leyendo la consulta?: " + existe);
	    st=(Statement) con.createStatement();
	    ResultSet rs = st.executeQuery(existe);
	    
	    //Si ya existe necesito hacer un UPDATE
	    if(rs.next()) {
	    	rs.close();
	    String actualizar= "UPDATE notas SET calificacion= '"+nota+ "' WHERE dni = '"+dniAlumno+ "' AND id_asignatura = '"+ idAsignatura + "'";
	    //System.out.println("el update es : " + actualizar);
	        st = (Statement) con.createStatement(); 

	        int confirmar = st.executeUpdate(actualizar); 

	        if (confirmar == 1) {
	            insertado = true;
	            System.out.println("Nota actualizada correctamente.");
	        }
	    }else {
            
            String idNota = generarIdNota(dniAlumno, idAsignatura);

          
            String guardar = "INSERT INTO notas (id_nota, dni, id_asignatura, calificacion) VALUES ('" + idNota + "', '" + dniAlumno + "', '" + idAsignatura + "', '" + nota + "')";
          
            System.out.println("Ejecutando INSERT: " + guardar);
            
            int confirmar = st.executeUpdate(guardar);
            if (confirmar == 1) {
                insertado = true;
                System.out.println("Nueva nota insertada.");
            }
        }	//rs.close();
	        st.close(); 
	        con.close(); 

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return insertado; 
	}

	


	public ResultSet obtenerDniPorNombre(String nombreCompleto) throws SQLException {
	    con = conexion.getConexion();
	    
	    String query = "SELECT dni_alumno FROM alumnos WHERE CONCAT(nombre, ' ', apellidos) = '" + nombreCompleto + "' LIMIT 1";

	    st = (Statement) con.createStatement();
	    return st.executeQuery(query);
	}

	public ResultSet buscarAlumno(String nombre, String password) throws SQLException {
	    con = conexion.getConexion();
	    String sql = "SELECT * FROM alumnos WHERE NOMBRE='" + nombre + "' AND PASS='" + password + "'";
	    try {
	        st = (Statement) con.createStatement();
	        return st.executeQuery(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	
	
	public ResultSet buscarProfesor(String nombre, String password) throws SQLException {
	    con = conexion.getConexion();
	    String sql = "SELECT * FROM profesores WHERE NOMBRE='" + nombre + "' AND PASS='" + password + "'"; 
	    try {
	        st = (Statement) con.createStatement();
	        return st.executeQuery(sql); 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; 
	}

	

}
 
 
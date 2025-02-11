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
	
	
	
	
	//COMPROBAR
	public ResultSet obtenerNotasAlumno(String dniAlumno) throws SQLException {
	    con = conexion.getConexion();
	    //Obtener datos--> ResulSet
	    //Guardar datos boolean
	    
	    //NECESITO INNER JOIN PORQUE TENGO QUE RELACIONAR LAS 2 COLUMNAS
	    String query = "SELECT modulos.DENOMINACION, notas.NOTA FROM notas " +
	                 "JOIN modulos ON notas.ID_MODULO = modulos.ID_ASIGNATURA " +
	                 "WHERE notas.DNI_ALUMNO = '" + dniAlumno + "'";
	    st = (Statement) con.createStatement();
	    st.close(); 
        con.close(); 
	    return st.executeQuery(query);
	    
	}
//COMPROBAR
	public boolean ponerNota(String dniAlumno, String idModulo, String nota) throws SQLException {
	    boolean insertado = false;
	    con = conexion.getConexion(); 
	    String sql = "INSERT INTO notas (DNI_ALUMNO, ID_MODULO, NOTA) VALUES ('" + dniAlumno + "', '" + idModulo + "', '" + nota + "')";

	    try {
	        st = (Statement) con.createStatement(); 

	        int confirmar = st.executeUpdate(sql); 

	        if (confirmar == 1) {
	            insertado = true;
	        }

	        st.close(); 
	        con.close(); 

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return insertado; 
	}

	
	//REVISADO
	public boolean insertarAlumno(String dni, String nombre, String apellidos, String direccion, String pass) throws SQLException {

		boolean insertado = false;

		con=conexion.getConexion();

		String sql="insert into alumnos (DNI,NOMBRE,APELLIDOS,DIRECCION,PASS) values ('"+dni+"','"+nombre+"','"+apellidos+"','"+direccion+"','"+pass+"')";

		try {

			st=(Statement) con.createStatement();

			int confirmar = st.executeUpdate(sql);

			if (confirmar == 1){

				insertado = true;

			}

			st.close();

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return insertado;

	}
	//REVISADO
	public boolean insertarProfesor(String dni, String nombre, String apellidos, String direccion, String pass) throws SQLException {

		boolean insertado = false;

		con=conexion.getConexion();

		String sql="insert into profesores (DNI,NOMBRE,APELLIDOS,DIRECCION,PASS) values ('"+dni+"','"+nombre+"','"+apellidos+"','"+direccion+"','"+pass+"')";

		try {

			st=(Statement) con.createStatement();

			int confirmar = st.executeUpdate(sql);

			if (confirmar == 1){

				insertado = true;

			}

			st.close();

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return insertado;

	}

	
	
	
	//revisado y adaptado
	public boolean insertarAsignatura(String id_asignatura,  String denominacion, String horas, String descripcion) throws SQLException {

		boolean insertado = false;

		con=conexion.getConexion();

		String sql="insert into asignaturas (ID_ASIGNATURA,DENOMINACION,HORAS,DESCRIPCION) values ('"+id_asignatura+"','"+denominacion+"','"+horas+"','"+descripcion+"')";

		try {

			st=(Statement) con.createStatement();

			int confirmar = st.executeUpdate(sql);

			if (confirmar == 1){

				insertado = true;

			}

			st.close();

			con.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return insertado;

	}
	//REVISADO
	public int modificarAlumno(String dni_alumno, String nombre, String apellidos, String direccion, String pass) throws SQLException {

		int confirmar=0;

		con=conexion.getConexion();

		String sql="update alumnos set DNI='"+dni_alumno+"', NOMBRE='"+nombre+"', APELLIDOS='"+apellidos+"', DIRECCION='"+direccion+"', PASS='"+pass+"' where DNI="+dni_alumno+"";

		try {

			st=(Statement) con.createStatement();

			confirmar=st.executeUpdate(sql);

			st.close();

			con.close();

		}  catch (SQLException e) {

			e.printStackTrace();

		}

		return confirmar;

	}
//REVISADO
	public int modificarProfesor(String dni_profesor, String nombre, String apellidos, String direccion, String pass) throws SQLException {

		int confirmar=0;

		con=conexion.getConexion();

		String sql="update profesores set DNI='"+dni_profesor+"', NOMBRE='"+nombre+"', APELLIDOS='"+apellidos+"', DIRECCION='"+direccion+"', PASS='"+pass+"' where DNI="+dni_profesor+"";

		try {

			st=(Statement) con.createStatement();

			confirmar=st.executeUpdate(sql);

			st.close();

			con.close();

		}  catch (SQLException e) {

			e.printStackTrace();

		}

		return confirmar;

	}
 

	//REVISADO
	public int eliminarAlumno(String dni) throws SQLException {
		int confirmar=0;
		con=conexion.getConexion();
		String sql="delete from alumnos where DNI="+dni;
		try {
			st=(Statement) con.createStatement();
			confirmar=st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confirmar;
	}
	//REVISADO
	public int eliminarProfesor(String dni_profesor) throws SQLException {
		int confirmar=0;
		con=conexion.getConexion();
		String sql="delete from profesores where DNI="+dni_profesor;
		try {
			st=(Statement) con.createStatement();
			confirmar=st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confirmar;
	}
	

	//revisado y cambiado
	public boolean comprobarAlumno(String dni, String pass) throws SQLException {
		boolean encontrado = false;
		con=conexion.getConexion();
		String sql="SELECT * FROM alumnos WHERE DNI='"+dni+"'and PASS='"+pass+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			while (resultado.next()){
				encontrado = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
	
	//revisado y cambiado---> Te dice si lo encontró o no pero no te devuelve los datos
	public boolean comprobarProfesor(String dni_profesor, String pass) throws SQLException {
		boolean encontrado = false;
		con=conexion.getConexion();
		String sql="SELECT * FROM profesores WHERE DNI='"+dni_profesor+"'and PASS='"+pass+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			while (resultado.next()){
				encontrado = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
	
	
	//Necesitamos que devuelva los datos del alumno. Si es boolean sólo devuelve si ha encontrado o no el alumno
	public ResultSet buscarAlumno(String nombre, String password) throws SQLException {
		
		con=conexion.getConexion();
		String sql="SELECT * FROM alumnos WHERE NOMBRE='"+ nombre +"'AND PASS='"+password+"'";
		try{
			st=(Statement) con.createStatement();
			return st.executeQuery(sql);
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//REVISADO Y ADAPTADO
	public boolean existeProfesor(String dni_profesor) throws SQLException {
		boolean encontrado = false;
		con=conexion.getConexion();
		String sql="SELECT * FROM profesores WHERE DNI='"+dni_profesor+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			while (resultado.next()){
				encontrado = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
	

	//REVISADO
	public boolean buscarAsignatura(String id_asignatura) throws SQLException {
		boolean encontrado = false;
		con=conexion.getConexion();
		String sql="SELECT * FROM modulos WHERE ID_ASIGNATURA='"+id_asignatura+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			while (resultado.next()){
				encontrado = true;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
	//REVISADO
	public ResultSet BuscarAlumno(String dni_alumno) throws SQLException {
		con=conexion.getConexion();
		String sql="SELECT * FROM alumnos WHERE DNI='"+dni_alumno+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	//REVISADO Y ADAPTADO PARA QUE BUSQUE POR NOMBRE
	
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

	
	//REVISADO
	public ResultSet BuscarAsignatura(String id_asignatura) throws SQLException {
		con=conexion.getConexion();
		String sql="SELECT * FROM modulos WHERE CLAVE='"+id_asignatura+"' ";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	//REVISADO
	public ResultSet BuscarTodosAlumnos() throws SQLException {
		con=conexion.getConexion();
		String sql="SELECT * FROM alumnos";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	//revisado
	public ResultSet BuscarTodosProfesores() throws SQLException {
		con=conexion.getConexion();
		String sql="SELECT * FROM profesores";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	public ResultSet BuscarTodosAsignaturas() throws SQLException {
		con=conexion.getConexion();
		String sql="SELECT * FROM modulos";
		try{
			st=(Statement) con.createStatement();
			resultado= st.executeQuery(sql);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
 
 
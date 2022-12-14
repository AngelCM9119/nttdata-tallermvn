package com.nttdata.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {      
    	App app = new App();
        app.consulta();
    }
    
    /**
     * Conexión a la base de datos MySQL ejecuta una consulta
     */
    private void consulta()
    {
    	try
    	{
    		// cargar el driver
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		// crear la conexión
	    	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nttdata_hibernate_taller1","root","root");			
	    	Statement statement = (Statement) conexion.createStatement();
			
	    	// ejecutar la consulta
			ResultSet result = statement.executeQuery(
					"SELECT e.name, s.name "+
					"FROM nttdata_t1mysql_employees e LEFT JOIN nttdata_t1mysql_services s ON s.id_service = e.id_service "+
					"ORDER BY e.name ASC"
			);
			
			// mostrar el resultado de la consulta
			System.out.printf("%-30s%s\n", "EMPLEADO", "SERVICIO");
			System.out.println("----------------------------------------");
			
			while(result.next())
			{
				String employee = result.getString(1);
				String service = result.getString(2);
				System.out.printf("%-30s%s\n",employee, service);
			}
			
			// cerrar las conexiones abiertas
			result.close();
			statement.close();
			conexion.close();
    	} catch(SQLException e)
    	{
    		System.out.println("ERROR: "+e.getMessage());
    	} catch (ClassNotFoundException e)
    	{
    		System.out.println("ERROR: "+e.getMessage());
		}
    }
}

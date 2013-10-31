package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import daointerfaces.DALException;


public class Connector
{
	static {try {
		new Connector();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	private static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	private static Connection conn;
	private static Statement stm;
	private static Pattern pForeign = Pattern.compile("REFERENCES `([^`]*)` \\(`([^`]*)`\\)");

	private Connector(String server, int port, String database,
			String username, String password)
					throws InstantiationException, IllegalAccessException,
					ClassNotFoundException, SQLException {
		conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database,
				username, password);
		stm		= conn.createStatement();
	}

	public Connector() throws InstantiationException, IllegalAccessException,
	ClassNotFoundException, SQLException {
		this(Constant.server, Constant.port, Constant.database,
				Constant.username, Constant.password);
	}

	public static ResultSet doQuery(String cmd) throws DALException {
		try { return stm.executeQuery(cmd); }
		catch (SQLException e) { throw new DALException(e); }
	}

	public static int doUpdate(String cmd) throws DALException {
		try { return stm.executeUpdate(cmd); }
		catch (SQLException e) {
			Matcher m = pForeign.matcher(e.getMessage());
			if (m.find()) {
				throw new DALException("The specified " + m.group(2) + " does not exist in " + m.group(1) + " , the action could not be carried out.");
			} else {
				throw new DALException(e); 
			} 
		}
	}
}
package ch.weinetz.jraptor.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class SqliteHandler {
	// Fields
	private final Path pathToDatabase;
	private Connection connection;
	private Logger logger;
	private ResultSet result;
	
	// Constructor
	public SqliteHandler(String pathToDatabase) {
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		Path databasePath = Paths.get(pathToDatabase).toAbsolutePath();
		this.pathToDatabase = workingDirectory.relativize(databasePath);
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		connectToDatabase();
	}
	public SqliteHandler(Path pathToDatabase) {
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		this.pathToDatabase = workingDirectory.relativize(pathToDatabase);
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		connectToDatabase();
	}
	public SqliteHandler(File databaseFile) {
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		Path databasePath = databaseFile.toPath();
		this.pathToDatabase = workingDirectory.relativize(databasePath);
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		connectToDatabase();
	}
	
	// Getters
	public String getRelativePath() {
		return pathToDatabase.toString();
	}
	public String getAbsolutePath() {
		return pathToDatabase.toAbsolutePath().toString();
	}
	public Connection getConnection() {
		return connection;
	}
	public ResultSet getResult() {
		return result;
	}
	public Set<String> getDataTables() {
		return listTables(new String[]{"TABLE"});
	}
	public List<String> getColumnNames(String tableName) {
		if (listTables(null).contains(tableName)) {
			return listColumns(tableName);
		} else {
			throw new RuntimeException(String.format("Table %s does not exist in database %s.", tableName, getRelativePath()));
		}
	}
	
	// Public methods
	public ResultSet executeSql(PreparedStatement sqlStatement) {
		result = null;
		execute(sqlStatement);
		return result;
	}
	public ResultSet executeSql(String sqlStatement) {
		result = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			execute(statement);
			
		} catch (SQLException e) {
			logger.warning("Could not create SQL statement");
			e.printStackTrace();
		}
		return result;
	}
	public ResultSet executeSql(String sqlStatement, String... values) {
		result = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			
			int i = 1;
			for (String value : values) {
				statement.setString(i, value);
			}
			execute(statement);
			
		} catch (SQLException e) {
			logger.warning("Could not create SQL statement");
			e.printStackTrace();
		}
		return result;
	}
	public void closeResultSet() {
		try {
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		try {		
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Private methods
	private void connectToDatabase() {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDatabase.toString());			
		} catch (SQLException e) {
			logger.severe(String.format("Connection to SQLITE database \"%s\" failed", getAbsolutePath()));
			e.printStackTrace();
		}
	}
	private void execute(PreparedStatement sqlStatement) {
		try {
			result = (sqlStatement.execute()) ? sqlStatement.getResultSet() : null;
		} catch (SQLException e) {
			logger.warning("Could not execute SQL statement");
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
	}
	private Set<String> listTables(String[] tableTypes) {
		Set<String> tables = new HashSet<>();
		try {
			ResultSet dataTables = connection.getMetaData().getTables(null, null, "%", tableTypes);
			while (dataTables.next()) {
				tables.add(dataTables.getString("TABLE_NAME"));
			}
			dataTables.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	private List<String> listColumns(String table) {
		List<String> columns = new LinkedList<>();
		try {
			ResultSet tableData = executeSql(String.format("SELECT * FROM %s LIMIT 1;", table));
			ResultSetMetaData metadata = tableData.getMetaData();
			for (int i = 0; i < metadata.getColumnCount(); i++) {
				columns.add(metadata.getColumnName(i + 1));
			}
			tableData.close();						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columns;
	}
}

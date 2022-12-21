package ch.lugis.jraptor.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	}
	public SqliteHandler(Path pathToDatabase) {
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		this.pathToDatabase = workingDirectory.relativize(pathToDatabase);
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	}
	public SqliteHandler(File databaseFile) {
		Path workingDirectory = Paths.get(".").toAbsolutePath();
		Path databasePath = databaseFile.toPath();
		this.pathToDatabase = workingDirectory.relativize(databasePath);
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
	
	// Public methods
	public void connectToDatabase() {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDatabase.toString());
		} catch (SQLException e) {
			logger.severe(String.format("Connection to SQLITE database \"%s\" failed", getAbsolutePath()));
			e.printStackTrace();
		}
	}
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
	private void execute(PreparedStatement sqlStatement) {
		try {
			result = (sqlStatement.execute()) ? sqlStatement.getResultSet() : null;
		} catch (SQLException e) {
			logger.warning("Could not execute SQL statement");
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
	}
}

package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    private static DataConnection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/quanli_sach";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private ConnectionPool connectionPool;

    private DataConnection() {
        connectionPool = new ConnectionPool();
    }

    public static synchronized DataConnection getInstance() {
        if (instance == null) {
            instance = new DataConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public void releaseConnection(Connection connection) {
        connectionPool.releaseConnection(connection);
    }

    private static class ConnectionPool {
        private static final int MAX_CONNECTIONS = 4;
        private Connection[] connections;
        private boolean[] isConnectionInUse;

        private ConnectionPool() {
            connections = new Connection[MAX_CONNECTIONS];
            isConnectionInUse = new boolean[MAX_CONNECTIONS];
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                try {
                    connections[i] = createNewConnection();
                } catch (SQLException e) {
                    handleSQLException(e);
                }
            }
        }

        private Connection createNewConnection() throws SQLException {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        public synchronized Connection getConnection() throws SQLException {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                if (!isConnectionInUse[i]) {
                    isConnectionInUse[i] = true;
                    return connections[i];
                }
            }
            throw new SQLException("Connection pool full");
        }

        public synchronized void releaseConnection(Connection connection) {
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                if (connections[i] == connection) {
                    isConnectionInUse[i] = false;
                    break;
                }
            }
        }

        private void handleSQLException(SQLException e) {
            e.printStackTrace(); 
        }
    }
}


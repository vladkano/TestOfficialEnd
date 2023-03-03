package sql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBWorker {

    private Connection con;
    private Session session;

    public Session getSession() {
        return session;
    }

    public Connection getCon() {
        return con;
    }

    private static Dotenv dotenv = Dotenv.load();

    public DBWorker() {

        try {
            int nRemotePort = 3306; // remote port number of your database
            final JSch jsch = new JSch();
            jsch.addIdentity(dotenv.get("IDENTITY"));
            session = jsch.getSession(dotenv.get("SSH_USER"), dotenv.get("SSH_HOST"), Integer.parseInt(dotenv.get("SSH_PORT")));
            final Properties config = new Properties();
            config.put(dotenv.get("CONFIG_FIRST"),dotenv.get("CONFIG_SECOND"));
            config.put(dotenv.get("CONFIG_THIRD"),dotenv.get("CONFIG_FOUR"));
            session.setPassword(dotenv.get("PREFERRED_AUTHENTICATIONS"));
            session.setConfig(config);
            session.connect();
            int forwardedPort = session.setPortForwardingL(0, dotenv.get("REMOTE_HOST"), nRemotePort);
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + forwardedPort;
            con = DriverManager.getConnection(url + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

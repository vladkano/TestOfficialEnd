package sql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

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


    public DBWorker() {

        //БОЕВОЙ САЙТ

        try {
            String strSshUser = "poisondrop"; // SSH loging username
            String strSshHost = "77.223.106.149";
            String strRemoteHost = "10.144.0.12"; // hostname or ip of your database server
            int nRemotePort = 3306; // remote port number of your database
            String strDbUser = "rundkvist"; // database loging username
            String strDbPassword = "vCGHIyfx864fhMVtrfvk65"; // database login password

            final JSch jsch = new JSch();
            jsch.addIdentity("C:\\.ssh\\id_rsa");
            session = jsch.getSession(strSshUser, strSshHost, 10022);
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + forwardedPort;
            con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
                    strDbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Сталинград
//        try {
//        String strSshUser = "root"; // SSH loging username
//        String strSshHost = "77.223.123.139";
//        String strRemoteHost = "localhost"; // hostname or ip of your database server
//        int nRemotePort = 3306; // remote port number of your database
//        String strDbUser = "poisondrop"; // database loging username
//        String strDbPassword = "vuVU76hjsdjh3297G"; // database login password
//
//        final JSch jsch = new JSch();
//        session = jsch.getSession(strSshUser, strSshHost, 22);
//        final Properties config = new Properties();
//        config.put("StrictHostKeyChecking", "no");
//        config.put("PreferredAuthentications", "password");
//        session.setPassword("P0VJvF3qSNSW");
//        session.setConfig(config);
//        session.connect();
//        int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//        Class.forName("org.mariadb.jdbc.Driver");
//        String url = "jdbc:mysql://localhost:" + forwardedPort;
//        con = DriverManager.getConnection(url + "/poisondrop", strDbUser,
//                strDbPassword);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }

//
        //Севастополь
//            try {
//                String strSshUser = "support"; // SSH loging username
//                String strSshHost = "77.223.106.150";
//                String strRemoteHost = "localhost"; // hostname or ip of your database server
//                int nRemotePort = 3306; // remote port number of your database
//                String strDbUser = "sevastopol"; // database loging username
//                String strDbPassword = "8jsMOov6wwFf39Qbom"; // database login password
//
//                final JSch jsch = new JSch();
//                jsch.addIdentity("C:\\.ssh\\id_rsa");
//                session = jsch.getSession(strSshUser, strSshHost, 10022);
//                final Properties config = new Properties();
//                config.put("StrictHostKeyChecking", "no");
//                session.setConfig(config);
//                session.connect();
//                int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//                Class.forName("org.mariadb.jdbc.Driver");
//                String url = "jdbc:mysql://localhost:" + forwardedPort;
//                con = DriverManager.getConnection(url + "/sevastopol", strDbUser,
//                        strDbPassword);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


        //Курск
//        try {
//            String strSshUser = "poisondrop"; // SSH loging username
//            String strSshHost = "77.223.123.138";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 3306; // remote port number of your database
//            String strDbUser = "pd_prod_user"; // database loging username
//            String strDbPassword = "cGXIH452yfgYTus@%yt"; // database login password
//
//            final JSch jsch = new JSch();
//
//            session = jsch.getSession(strSshUser, strSshHost, 10022);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            config.put("PreferredAuthentications", "password");
//            session.setPassword("rHCkTLBLRqz7oQICFE");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/new_poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //Тула
//        try {
//            String strSshUser = "root"; // SSH loging username
//            String strSshHost = "77.223.105.214";
//            String strRemoteHost = "localhost"; // hostname or ip of your database server
//            int nRemotePort = 3306; // remote port number of your database
//            String strDbUser = "pd_prod_user"; // database loging username
//            String strDbPassword = "cGXIH452yfgYTus@%yt"; // database login password
//
//            final JSch jsch = new JSch();
//
//            session = jsch.getSession(strSshUser, strSshHost, 10022);
//            final Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            config.put("PreferredAuthentications", "password");
//            session.setPassword("DTs42KDqufVS");
//            session.setConfig(config);
//            session.connect();
//            int forwardedPort = session.setPortForwardingL(0, strRemoteHost, nRemotePort);
//
//            Class.forName("org.mariadb.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:" + forwardedPort;
//            con = DriverManager.getConnection(url + "/new_poisondrop", strDbUser,
//                    strDbPassword);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
    }


}

import java.sql.Connection;

import database.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MySQL db = MySQL.getInstance();
        Connection conn = db.getConnection();
        System.out.println(conn);
    }
}

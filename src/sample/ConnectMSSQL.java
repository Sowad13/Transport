package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectMSSQL {

    public Connection connection;

    public Connection connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=TransportManagement;selectMethod=cursor", "sa", "123456");

//            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement
//                    .executeQuery("SELECT Transport_plate_no FROM Transport");


//            while (resultSet.next()) {
//
//                System.out.println("Transport_plate_no:" +
//                        resultSet.getString("Transport_plate_no"));
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static void main(String[] args) {
//        ConnectMSSQL cnObj = new ConnectMSSQL();
//        cnObj.connectDB();
//    }

}
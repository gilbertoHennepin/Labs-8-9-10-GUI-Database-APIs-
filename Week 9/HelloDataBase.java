package movies;

import java.sql.*;

public class HalloDataBase {
        public static void main(String[] args) throws SQLException {

            String url = "jdbc:sqlite:hello.sqlite";
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            String createTableSQL = "CREATE TABLE cats (name TEXT, age INTEGER);)";
            statement.executeUpdate(createTableSQL);

            String insertDataSQL = "INSERT INTO cats VALUES ('Maru', 10) ";
            statement.executeUpdate(insertDataSQL);

//            String insertDataSQL = "INSERT INTO cats VALUES ('Hello Kitty', 40) ";
//            statement.executeUpdate(insertDataSQL);


            String getAllDataSQL = "SELECT * FROM cats";          // read data
            ResultSet allCats = statement.executeQuery(getAllDataSQL); // returns data to resultSet

            while (allCats.next()) { // calls next set till there's nothing == false
                String name = allCats.getString("name"); // Returns name
                int age = allCats.getInt("age"); // returns age
                System.out.println("Name: " + name + ", || Age " + age + " Years Old ");
            }
        }
    }

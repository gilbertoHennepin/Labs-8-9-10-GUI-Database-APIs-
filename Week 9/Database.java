package movies;

import org.sqlite.core.CorePreparedStatement;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databasePath;

    Database(String databasePath) { // constructor


        // create table, or make sure it us created

        this.databasePath = databasePath;

        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY, name text UNIQUE CHECK(lenghth(name) >= 1), starts INTEGER CHECK(stars >= 0 AND stars <= 5) watched BOOLEAN)");


        } catch (Exception e) {
            System.out.println("error creating movie DB table because " + e);

        }


    }

    public void addNewMovie(Movie movie) {

        String insertSQL = "INSERT INTO movies (name, stars, watched)VALUES (?, ?, ?)"; // preaperd statement

        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {


            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getStarts());
            preparedStatement.setBoolean(3, movie.isWatched());
            preparedStatement.execute();


        } catch (SQLException e) {
            System.out.println("Error adding movie " + movie + "because " + e);
        }
    }

    public List<Movie> getAllMovies() {

        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()
        ) {
            //get all movies
            ResultSet movieResult = statement.executeQuery("SELECT * FROM movies ORDER BY name");

            List<Movie> movies = new ArrayList<>();

            while (movieResult.next()) {
                int id = movieResult.getInt("id");
                String name = movieResult.getString("name");
                int starts = movieResult.getInt("starts");
                boolean watched = movieResult.getBoolean("watched");

                Movie movie = new Movie(id, name, starts, watched);
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            System.out.println("Error retrieving all movies because " + e);
            return null;
        }

    }

    public List<Movie> getMoviesByWatched(boolean watchedStatues) {

        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies WHERE watched = ?")) {

            preparedStatement.setBoolean(1, watchedStatues);
            ResultSet movieResults = preparedStatement.executeQuery();

            List<Movie> movies = new ArrayList<>();


            while (movieResults.next()) {
                int id = movieResults.getInt("id");
                String name = movieResults.getString("name");
                int starts = movieResults.getInt("starts");
                boolean watched = movieResults.getBoolean("watched");
                Movie movie = new Movie(id, name, starts, watched);
                movies.add(movie);
            }

            return movies;

        } catch (SQLException e) {
            System.out.println("Error retrieving all movies because " + e);
            return null;
        }

    }

    public void updateMovie(Movie movie) {
        String sql = "UPDATE movies SET starts = ?, watched = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, movie.getStarts());
            preparedStatement.setBoolean(2, movie.isWatched());
            preparedStatement.setInt(3, movie.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error updating movie " + movie + "because " + e);
        }
    }

    public void deleteMovie(Movie movie) {

        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movies WHERE id = ?")) {

            preparedStatement.setInt(1, movie.getId());
            preparedStatement.execute();

        }catch (SQLException e) {
            System.out.println("Error deleting movie " + movie + "because " + e);
        }
    }
    public List<Movie> searchMovie(String searchTerm) {

        String sql = "SELECT * FROM movies WHERE UPPER(name) LIKE UPPER(?)";

        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1,"%"+  searchTerm + "%");

            ResultSet movieResults = preparedStatement.executeQuery();

            List<Movie> movies = new ArrayList<>();

            while (movieResults.next()) {
                int id = movieResults.getInt("id");
                String name = movieResults.getString("name");
                int starts = movieResults.getInt("starts");
                boolean watched = movieResults.getBoolean("watched");

                Movie movie = new Movie(id, name, starts, watched);
                movies.add(movie);
            }

            return movies;

        }catch (SQLException e) {
            System.out.println("Error searching movie " + searchTerm + "because " + e);
            return null;
        }
    }
}


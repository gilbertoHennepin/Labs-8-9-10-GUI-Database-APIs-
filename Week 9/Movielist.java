package movies;

import java.sql.Connection;
import java.util.List;

import static input.InputUtils.*;

public class MovieList {

    private static final String DB_PATH = "jdbc:sqlite:movie_watchlist.sqlite";
    private static Database database;



    public static void main(String[] args) {
        database = new Database(DB_PATH);
        addNewMovies();
        checkIfWatchedAndRate();
        displayAllMovies();
        deleteWatchedMovies();
        searchMovie();


    }

    public static int getRatingOutOfFive() {
        int rating = positiveIntInput("Please enter a valid rating");
        while (rating < 0 || rating > 5) {
            System.out.println("error, enter a number between 0 and 5 ");
            rating = positiveIntInput("Please enter a valid rating");
        }
        return rating;
    }

    public static String getNonEmptyMovieNames() {
        String name = stringInput("Enter the movie name: ");
        while (name.isEmpty()) {
            System.out.println("error, enter a valid movie name");
            name = stringInput("Enter the movie name: ");
        }
        return name;
    }


    public static void addNewMovies() {
        do {
            String movieName = getNonEmptyMovieNames();
            boolean movieWatched = yesNoInput("Have you seen this movie yet?");
            int movieStars = 0;
            if (movieWatched) {
                movieStars = getRatingOutOfFive();
                movieStars = positiveIntInput("Enter movie stars number");

            }
            Movie movie = new Movie(movieName, movieStars, movieWatched);
            database.addNewMovie(movie);



        } while (yesNoInput("Add a movie to the watchlist?"));
    }
    public static void displayAllMovies() {
        List<Movie> movies = database.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("There are no movies in the database");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    public static void checkIfWatchedAndRate() {
        List<Movie> unwatchedMovies = database.getMoviesByWatched(false);

        for (Movie movie : unwatchedMovies) {
            boolean hasWatched = yesNoInput("Have you watched " + movie.getName() + " Yet?");
            if (hasWatched) {
                int starts =  positiveIntInput("Enter movie stars number " + movie.getName() + "Out of 5 ?");
                movie.setWatched(true);
                movie.setStarts(starts);
                database.updateMovie(movie);
            }
        }
    }

    public static void deleteWatchedMovies() {
        System.out.println("Here are all the movies you have seen");

        List<Movie> watchedMovies = database.getMoviesByWatched(true);

        for (Movie movie : watchedMovies) {
            boolean delete = yesNoInput(" Delete " + movie.getName() + "?");
            if (delete) {
                database.deleteMovie(movie);
            }
        }
    }


    public static void searchMovie() {
        String moveName = stringInput("Enter the movie name");
        List<Movie> movieMatches = database.searchMovie(moveName);

        if (movieMatches.isEmpty()) {
            System.out.println("There are no movies in the database");
        } else {
            for (Movie movie : movieMatches) {
                System.out.println(movie);
            }
        }
    }
}

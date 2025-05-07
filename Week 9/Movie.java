package movies;

public class Movie {

    private int id;
    private String name;
    int starts;
    private boolean watched;

    Movie(String name, int starts, boolean watched) {
        this.name = name;
        this.starts = starts;
        this.watched = watched;
    }

    Movie(int id, String name, int starts, boolean watched) {
        this.id = id;
        this.name = name;
        this.starts = starts;
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "ID " + id + "Movie name= " + name + ", || stars : " + starts + ", || watched = " + watched;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}

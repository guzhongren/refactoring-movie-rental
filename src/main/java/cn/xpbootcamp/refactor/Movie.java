package cn.xpbootcamp.refactor;

public class Movie implements MovieInterface {


    private String title;

    private MovieType movieType;

    Movie(String title, MovieType movieType) {
        this.title = title;
        this.movieType = movieType;
    }

    public String getTitle() {

        return title;
    }

    public MovieType getMovieType() {
        return movieType;
    }
}

package cn.xpbootcamp.refactor;

public class Movie {

//    static final int CAMPUS = 2;
//    static final int HISTORY = 0;
//    static final int NEW_RELEASE = 1;

    private String title;

    private MovieType movieType;

    Movie(String title, MovieType movieType) {
        this.title = title;
        this.movieType = movieType;
    }

    public String getTitle() {

        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }
}

package cn.xpbootcamp.refactor;

public class Rental implements RentalInterface {

    private Movie movie;
    private int rentedDays;

    Rental(Movie movie, int rentedDays) {
        this.movie = movie;
        this.rentedDays = rentedDays;
    }

    public Movie getMovie() {
        return movie;
    }

    public int rentedDays() {
        return rentedDays;
    }

}

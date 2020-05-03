package cn.xpbootcamp.refactor;

import java.util.Enumeration;
import java.util.Vector;

public class Customer implements CustomerInterface {

    static final int NEW_RELEASE_PEER_DAY = 3;
    static final double HISTORY_CONST_OF_OUT_2_DAYS = 1.5;
    static final int HISTORY_BASE_DAYS = 2;
    static final int HISTORY_CONST_IN_BASE_DAYS = 2;

    static final double CAMPUS_CONST_IN_3_DAYS = 1.5;
    static final int CAMPUS_BASE_DAYS = 3;
    static final double CAMPUS_CONST_PEER_DAY = 1.5;

    private String name;
    private Vector<Rental> rentals = new Vector<>();
    private int frequentRenterPoints;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }


    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String statement() {
        double totalAmount = 0d;
        frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        StringBuilder result = generateRentalHeader();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            double thisAmount = getAmount(each);
            getFrequentRenterPoints(each);
            totalAmount = getSingleMovieInfo(totalAmount, result, each, thisAmount);
        }
        return generateRentalInfo(totalAmount, result);
    }

    private StringBuilder generateRentalHeader() {
        return new StringBuilder("Rental Record for " + getName() + "：\n");
    }

    private double getAmount(Rental each) {
        double thisAmount = 0d;
        switch (each.getMovie().getMovieType()) {
            case NEW_RELEASE:
                thisAmount += each.getDaysRented() * NEW_RELEASE_PEER_DAY;
                break;
            case HISTORY:
                thisAmount += HISTORY_CONST_IN_BASE_DAYS;
                if (each.getDaysRented() > HISTORY_BASE_DAYS)
                    thisAmount += (each.getDaysRented() - HISTORY_BASE_DAYS) * HISTORY_CONST_OF_OUT_2_DAYS;
                break;
            case CAMPUS:
                thisAmount += CAMPUS_CONST_IN_3_DAYS;
                int rentedDays = each.getDaysRented();
                if (rentedDays > CAMPUS_BASE_DAYS)
                    thisAmount += (rentedDays - CAMPUS_BASE_DAYS) * CAMPUS_CONST_PEER_DAY;
                break;
            default:
                break;
        }
        return thisAmount;
    }

    private String generateRentalInfo(double totalAmount, StringBuilder result) {
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private double getSingleMovieInfo(double totalAmount, StringBuilder result, Rental each, double thisAmount) {
        result.append("\t")
              .append(each.getMovie().getTitle())
              .append("\t")
              .append(thisAmount).append("\n");
        totalAmount += thisAmount;
        return totalAmount;
    }

    private void getFrequentRenterPoints(Rental each) {
        frequentRenterPoints++;
        if (isNewRelease(each.getMovie()) && each.getDaysRented() > 1)
            frequentRenterPoints++;
    }

    private boolean isNewRelease(Movie movie) {
        return movie.getMovieType() == MovieType.NEW_RELEASE;
    }

}

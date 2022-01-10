package Model;

import Model.Enums.EnumSeats;
import Model.Enums.EnumTheaterType;

// Create a class for managing screening time for each movie.
public class Screening {
    // Fields
    private Theater theater;
    private final ScreeningTime screeningTime;

    // Constructor
    public Screening(EnumTheaterType theaterType, int theaterNumber, ScreeningTime screeningTime) {
        theater = TheaterCreator.TheaterCreator(theaterType, theaterNumber);
        this.screeningTime = screeningTime;

        // Initialize theater seats
        int rows = this.theater.getNumRows();
        int cols = this.theater.getNumCols();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.theater.setSeat(row, col, EnumSeats.AVAILABLE);
            }
        }
    }


    // Getters
    public Theater getTheater() {
        return theater;
    }

    public ScreeningTime getScreeningTime() {
        return screeningTime;
    }


    @Override
    public String toString() {
        return theater.toString() + screeningTime.toString();
    }
}


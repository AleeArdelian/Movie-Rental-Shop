package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Rental extends BaseEntity<Integer> {
    private int clientId;
    private int movieId;
    private String rentalDate;
    private String returnDate;

    public Rental(int cId, int mId){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        LocalDate newLocalDate = localDate.plusWeeks(1);

        this.clientId=cId;
        this.movieId =mId;
        rentalDate=dtf.format(localDate);
        returnDate=dtf.format(newLocalDate);
    }

    public Rental(int cId, int mId, String rentalDate, String returnDate) {
        this.clientId = cId;
        this.movieId = mId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public int getMovieId()
    {
        return movieId;
    }

    public int getClientId(){
        return clientId;
    }

    public String getRentalDate(){
        return rentalDate;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public String toString(){
        //return "Rental id: "+ getId() + " client: " +clientId + " movie: "+movieId+
        //" Rental date: "+ rentalDate + " Return date: "+returnDate;
        return getId() + " " + clientId + " " + movieId + " " + rentalDate + " " + returnDate;
    }

}
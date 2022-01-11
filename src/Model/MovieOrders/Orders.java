package Model.MovieOrders;

import Model.MoviesAndScreenings.Movie;
import Model.MoviesAndScreenings.Movies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Crate a class that holds all user-orders.
public class Orders {
    // Fields
    private final List<UserOrder> allOrders;

    // Constructor
    public Orders(List<UserOrder> orders) {
        this.allOrders = orders;
    }

    // Getters
    public List<UserOrder> getAllOrders() {
        return allOrders;
    }

    public int GetNumOfOrders(){
        return this.allOrders.size();
    }


    // Add a new order to allOrders list
    public void addOrder(UserOrder order) {
        allOrders.add(order);
    }

    // Display all data about each order: User, movie, duration, starting time, theater type and number, bill.
    public void displayOrders() {
        //Traversing list through Iterator
        Iterator itr = this.allOrders.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println();
            System.out.println("------------------------------------------------------------------------");
            System.out.println();
            System.out.println(itr.next());//printing the element and move to next
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

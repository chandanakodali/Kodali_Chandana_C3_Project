import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        Restaurant restaurantToBeFound = null;
        for(Restaurant restaurant: restaurants) {
            if (Objects.nonNull(restaurantName) && restaurant.getName().equalsIgnoreCase(restaurantName)) {
                restaurantToBeFound = restaurant;
            }
        }
        if (Objects.nonNull(restaurantToBeFound))
            return restaurantToBeFound;
        else
            throw new restaurantNotFoundException(restaurantName + " is not found");
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}

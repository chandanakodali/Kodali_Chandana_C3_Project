import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    @Spy
    Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai", LocalTime.parse("10:30:00"), LocalTime.parse("22:00:00"));
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @BeforeEach
    public void setup() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("12:00:00"));
        boolean actualStatusOfTheRestaurant = restaurant.isRestaurantOpen();
        assertTrue(actualStatusOfTheRestaurant);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // When current time is after closing time
        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:01"));
        boolean actualStatusOfTheRestaurantWhenCurrentTimeIsAfterClosingTime = restaurant.isRestaurantOpen();
        assertFalse(actualStatusOfTheRestaurantWhenCurrentTimeIsAfterClosingTime);

        // When current time is before opening time
        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:29:00"));
        boolean actualStatusOfTheRestaurantWhenCurrentTimeIsBeforeClosingTime = restaurant.isRestaurantOpen();
        assertFalse(actualStatusOfTheRestaurantWhenCurrentTimeIsBeforeClosingTime);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_exactly_opening_time(){
        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));
        boolean actualStatusOfTheRestaurant = restaurant.isRestaurantOpen();
        assertTrue(actualStatusOfTheRestaurant);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_exactly_closing_time(){
        when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        boolean actualStatusOfTheRestaurant = restaurant.isRestaurantOpen();
        assertTrue(actualStatusOfTheRestaurant);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
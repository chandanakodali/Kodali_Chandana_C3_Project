import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item item;

    @BeforeEach
    public void setup() {
        item = new Item("Dosa", 65);
    }
    @Test
    void testGetName() {
        String actualName = item.getName();
        assertEquals("Dosa", item.getName());
    }

    @Test
    void testGetPrice() {
        int actualPrice = item.getPrice();
        assertEquals(65, actualPrice);
    }

    @Test
    void testToString() {
        String actualString = item.toString();
        assertEquals("Dosa:65\n", actualString);
    }
}
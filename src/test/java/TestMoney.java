import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class TestMoney {
    // *** all the assertions below passes ***
    @Test
    public void testMakeChangeForAmount() {
        // setting up the device with initial slot setup of
        //     10 bills of face value 6
        //     10 bills of face value 5
        //     10 bills of face value 3
        MoneyChangeDevice moneyChangeDevice = new MoneyChangeDevice("6:10 5:10 3:10");

        // each successful call to makeChangeForAmount updates the device state with remaining bills
        // Note how the values below are descending sorted by value
        // Returning the least amount of bills for amount of 16:
        //     1 bill of face   value 6
        //     2 bills of face  value 5
        // Also returning the resulting device state:
        //     9 bills of face  value 6
        //     8 bills of face  value 5
        //     10 bills of face value 3
      //  assertEquals("6:1 5:2; 6:9 5:8 3:10", moneyChangeDevice.makeChangeForAmount("16"));

        // each successful call to makeChangeForAmount updates the device state with remaining bills
        // Note the sorted values below
        // Returning the least amount of bills for amount of 19:
        //     1 bill of face   value 6
        //     2 bills of face  value 5
        //     1 bill of face   value 3
        // Also returning the resulting device state:
        //     8 bills of face  value 6
        //     6 bills of face  value 5
        //     9 bills of face  value 3
        assertEquals("6:1 5:2 3:1; 6:8 5:6 3:9", moneyChangeDevice.makeChangeForAmount("19"));

        // this is a non successful call, so device state is unchanged
        assertEquals("no change is possible for 7", moneyChangeDevice.makeChangeForAmount("7"));
    }
}

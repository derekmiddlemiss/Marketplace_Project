import org.junit.Before;
import marketplace.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer del;
    private HashMap< String, PaymentMethod > paymentMethods = new HashMap<>();

    @Before
    public void before(){
        del = new Customer( 1, 1, "Del", 10.00, paymentMethods );
    }

    @Test
    public void testGetName(){
        assertEquals( "Del", del.getName() );
    }

}

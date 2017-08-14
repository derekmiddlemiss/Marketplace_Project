import marketplace.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class PaymentMethodTest {

    private PaymentMethod creditCardLowLimit;

    @Before
    public void before() {
        creditCardLowLimit = new PaymentMethod(
                PaymentMethodType.CREDITCARD,
                "Number 0114245566653 Owner Frank Mitty",
                0.0,
                -100.0,
                "1234");
    }

    @Test
    public void testUse__Allowed() {
        assertTrue( creditCardLowLimit.use( 90.0, "1234" ) );
    }

    @Test
    public void testUse__NotAllowedWrongPin() {
        assertFalse( creditCardLowLimit.use( 90.0, "2134" ) );
    }

    @Test
    public void testUse__NotAllowed() {
        assertFalse( creditCardLowLimit.use( 110.0, "1234" ) );
    }

    @Test
    public void testUse__Multiple() {
        assertTrue( creditCardLowLimit.use( 90.0, "1234" ) );
        assertFalse( creditCardLowLimit.use( 20.0, "1234" ) );
    }

    @Test
    public void testCredit__Multiple() {
        assertTrue( creditCardLowLimit.use( 90.0, "1234" ) );
        assertFalse( creditCardLowLimit.use( 20.0, "1234" ) );
        creditCardLowLimit.credit( 50.0 );
        assertTrue( creditCardLowLimit.use( 20.0, "1234" ) );
    }

    @Test
    public void testGetBalance() {
        Double expected = 0.0;
        assertEquals( expected, creditCardLowLimit.getBalance("1234" ) );
    }

    @Test
    public void testGetBalance__WrongPin() {
        assertNull( creditCardLowLimit.getBalance("2134" ) );
    }

    @Test
    public void testAuthorise() {
        assertTrue( creditCardLowLimit.authorise( 10.0, "1234" ) );
    }

    @Test
    public void testAuthorise_NotAuthorised() {
        assertFalse( creditCardLowLimit.authorise( 200.0, "1234" ) );
    }

}

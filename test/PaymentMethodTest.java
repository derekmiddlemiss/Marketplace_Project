import marketplace.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

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
//        debitCard = new PaymentMethod( PaymentMethodType.DEBITCARD,
//                "Number 2454655478321256 Owner Frank Mitty",
//                0.0,
//                -1000.0,
//                "5678");
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

}

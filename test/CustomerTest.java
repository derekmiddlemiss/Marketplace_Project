import org.junit.Before;
import marketplace.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerTest {

    private Customer frank;
    private PaymentMethod creditCard;
    private Product lotr;

    @Before
    public void before(){
        frank = new Customer(
                1,
                1,
                "Frank",
                10.00 );
        creditCard = new PaymentMethod(
                PaymentMethodType.CREDITCARD,
                "Number 1143231267342845",
                0.0,
                -100.0,
                "1234");
        lotr = new Product(
                "LordOfTheRings1149",
                8.99,
                12.99,
                "Unabridged version of the Lord of the Rings by J. R. R. Tolkien ");
    }

    @Test
    public void testGetName(){
        assertEquals( "Frank", frank.getName() );
    }

    @Test
    public void testAddPaymentMethod() {
        frank.addPaymentMethod( creditCard );
        Boolean authorise = frank.authorise( PaymentMethodType.CREDITCARD, 20.00, "1234" );
        assertTrue( authorise );
    }

    @Test
    public void testBuyProduct() {
        frank.addPaymentMethod( creditCard );
        frank.buyProduct( lotr, PaymentMethodType.CREDITCARD, "1234" );
        assertTrue( frank.hasProductWithIdentifier( lotr.getIdentifier() ) );
        Double expectedBalance = -12.99;
        assertEquals( expectedBalance, frank.getBalance(PaymentMethodType.CREDITCARD, "1234" ), 0.01 );
    }

    @Test
    public void testBuyProduct__Null(){
        frank.addPaymentMethod( creditCard );
        frank.buyProduct( null, PaymentMethodType.CREDITCARD, "1234" );
        assertFalse( frank.hasProductWithIdentifier( lotr.getIdentifier() ) );
        Double expectedBalance = 0.0;
        assertEquals( expectedBalance, frank.getBalance(PaymentMethodType.CREDITCARD, "1234" ), 0.01 );
    }

    @Test
    public void testReturnProductWithIdentifier() {
        frank.addPaymentMethod( creditCard );
        frank.buyProduct( lotr, PaymentMethodType.CREDITCARD, "1234" );
        Product returnProduct = frank.returnProductWithIdentifier( lotr.getIdentifier(), PaymentMethodType.CREDITCARD );
        assertEquals( returnProduct, lotr );
        Double expectedBalance = 0.0;
        assertEquals( expectedBalance, frank.getBalance( PaymentMethodType.CREDITCARD, "1234" ), 0.01 );
        assertFalse( frank.hasProductWithIdentifier( lotr.getIdentifier() ) );
    }

    @Test
    public void testReturnProductWithIdentifier__DoesNotHave(){
        frank.addPaymentMethod( creditCard );
        frank.buyProduct( lotr, PaymentMethodType.CREDITCARD, "1234" );
        frank.returnProductWithIdentifier("TalesOfTheCity1122", PaymentMethodType.CREDITCARD );
        Double expectedBalance = -12.99;
        assertEquals( expectedBalance, frank.getBalance( PaymentMethodType.CREDITCARD, "1234" ), 0.01 );
        assertTrue( frank.hasProductWithIdentifier( lotr.getIdentifier() ) );
    }


}

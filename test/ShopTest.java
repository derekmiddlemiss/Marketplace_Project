import marketplace.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShopTest {

    Shop waterstones;
    Product lotr;

    @Before
    public void before(){
        waterstones = new Shop(
                1,
                1,
                "Waterstones",
                500.0,
                0.0,
                0.0);
        lotr = new Product(
                "LordOfTheRings1149",
                8.99,
                12.99,
                "Unabridged version of the Lord of the Rings by J. R. R. Tolkien ");
    }

    @Test
    public void testCheckStockWithIdentifier(){
        assertFalse( waterstones.checkStockWithIdentifier( lotr.getIdentifier() ) );
    }

    @Test
    public void testRestockProduct(){
        waterstones.restockProduct( lotr );
        assertTrue( waterstones.checkStockWithIdentifier( lotr.getIdentifier() ) );
    }

    @Test
    public void testGetTotal(){
        waterstones.restockProduct( lotr );
        Double expectedTotal =  491.01;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }

    @Test
    public void testSellProductWithIdentifier(){
        waterstones.restockProduct( lotr );
        Product product = waterstones.sellProductWithIdentifier( lotr.getIdentifier() );
        assertEquals( lotr, product);
        assertFalse( waterstones.checkStockWithIdentifier( lotr.getIdentifier() ) );
        Double expectedTotal = 504.0;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }

    @Test
    public void testSellProductWithIdentifier__DoesNotHave(){
        waterstones.restockProduct( lotr );
        Product product = waterstones.sellProductWithIdentifier( "LordOfTheRings2424" );
        assertNull( product );
        Double expectedTotal =  491.01;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }

    @Test
    public void testSellProductWithIdentifier__Null(){
        waterstones.restockProduct( lotr );
        Product product = waterstones.sellProductWithIdentifier( null );
        assertNull( product );
        Double expectedTotal =  491.01;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }

    @Test
    public void testRefundProduct(){
        waterstones.restockProduct( lotr );
        waterstones.sellProductWithIdentifier( lotr.getIdentifier() );
        waterstones.refundProduct( lotr );
        Double expectedTotal = 491.01;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }

    @Test
    public void testRefundProduct__Null(){
        waterstones.restockProduct( lotr );
        waterstones.refundProduct( null );
        Double expectedTotal = 491.01;
        assertEquals( expectedTotal, waterstones.getTotal(), 0.01 );
    }
}

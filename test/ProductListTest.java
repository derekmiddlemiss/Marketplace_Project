import marketplace.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductListTest {

    private Product taleTwoCities1;
    private Product taleTwoCities2;
    private ProductList bookStore;

    @Before
    public void before(){
        taleTwoCities1 = new Product( "TaleTwoCities11492", 10.99,
                "Unabridged copy of A Tale of Two Cities by Charles Dickens");
        taleTwoCities2 = new Product( "TaleTwoCities11492", 10.99,
                "Unabridged copy of A Tale of Two Cities by Charles Dickens");
        bookStore = new ProductList();
    }

    @Test
    public void testStoreProduct(){
        bookStore.storeProduct( taleTwoCities1 );
        Integer expected = 1;
        assertEquals( expected, bookStore.numberProductsWithIdentifier( taleTwoCities1.getIdentifier() ) );
    }

    @Test
    public void testFindProduct(){
        bookStore.storeProduct( taleTwoCities1 );
        bookStore.storeProduct( taleTwoCities2 );
        Integer expected = 1;
        assertEquals( expected, bookStore.findProduct( taleTwoCities2 ) );
    }

    @Test
    public void testFindProduct__NotThere(){
        assertNull( bookStore.findProduct( taleTwoCities1 ) );
    }

    @Test
    public void testFetchProduct(){
        bookStore.storeProduct( taleTwoCities1 );
        bookStore.storeProduct( taleTwoCities2 );
        Integer expected = 1;
        assertEquals( expected, bookStore.findProduct( taleTwoCities2 ) );
        expected = 2;
        assertEquals( expected, bookStore.numberProductsWithIdentifier( taleTwoCities1.getIdentifier() ) );
        assertEquals( taleTwoCities1, bookStore.fetchProduct( taleTwoCities1 ) );
        expected = 1;
        assertEquals( expected, bookStore.numberProductsWithIdentifier( taleTwoCities2.getIdentifier() ) );
        expected = 0;
        assertEquals( expected, bookStore.findProduct( taleTwoCities2 ) );
    }

    @Test
    public void testFetchProduct__NoneButIdentifierExistsAsKey(){
        bookStore.storeProduct( taleTwoCities1 );
        assertNull( bookStore.fetchProduct( taleTwoCities2 ));
    }

    @Test
    public void testFetchProduct__None(){
        assertNull( bookStore.fetchProduct( taleTwoCities1 ));
    }

    @Test
    public void testFetchProductWithIdentifier(){
        bookStore.storeProduct( taleTwoCities1 );
        bookStore.storeProduct( taleTwoCities2 );
        assertEquals( taleTwoCities2, bookStore.fetchProductWithIdentifier( taleTwoCities1.getIdentifier() ) );
        Integer expected = 1;
        assertEquals( expected, bookStore.numberProductsWithIdentifier( taleTwoCities1.getIdentifier() ) );
    }

    @Test
    public void testFetchProductWithIdentifier__None(){
        assertNull( bookStore.fetchProductWithIdentifier( taleTwoCities1.getIdentifier() ) );
    }

}

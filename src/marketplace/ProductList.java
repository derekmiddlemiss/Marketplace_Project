package marketplace;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductList {

    private HashMap< String, ArrayList< Product > > store;

    public ProductList(){
        this.store = new HashMap< String, ArrayList< Product > >();
    }

    public void storeProduct( Product product ){
        if ( this.store.get( product.getIdentifier() ) == null ){
            this.store.put( product.getIdentifier(), new ArrayList< Product >() );
        }
        this.store.get( product.getIdentifier() ).add( product );
    }

    public Product inspectProduct( Product product ){
        if ( this.store.get( product.getIdentifier() ) != null ) {
            int index = this.getProductIndex( product );
            return this.store.get( product.getIdentifier() ).get( index );
        } else {
            return null;
        }
    }

    public Product inspectProductWithIdentifier( String productIdentifier ) {
        Integer numberProducts = this.numberProductsWithIdentifier( productIdentifier );
        if ( this.store.get( productIdentifier ) != null ) {
            return this.store.get( productIdentifier ).get( numberProducts - 1);
        } else {
            return null;
        }

    }

    public Boolean hasProductWithIdentifier( String productIdentifier ){
        return ( this.numberProductsWithIdentifier( productIdentifier ) > 0 );
    }

    public Integer numberProductsWithIdentifier( String productIdentifier ){
        if ( this.store.get( productIdentifier ) != null ) {
            return this.store.get(productIdentifier).size();
        } else {
            return 0;
        }
    }

    public Integer getProductIndex( Product product ){
        if ( this.store.get( product.getIdentifier() ) != null ) {
            return this.store.get(product.getIdentifier()).indexOf(product);
        }
        return -1;
    }

    public Product fetchProduct( Product product ){
        if ( this.store.get( product.getIdentifier() ) != null ) {
            int index = this.getProductIndex( product );
            if ( index != -1 ) {
                Product fetchProduct = this.store.get( product.getIdentifier() ).remove(index);
                this.checkDeHash( product.getIdentifier() );
                return fetchProduct;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Product fetchProductWithIdentifier( String productIdentifier ){
        Integer numberProducts = this.numberProductsWithIdentifier( productIdentifier );
        if ( numberProducts >= 1 ){
            Product fetchProduct = this.store.get( productIdentifier ).remove( numberProducts - 1 );
            this.checkDeHash( productIdentifier );
            return fetchProduct;
        } else {
            return null;
        }
    }

    public void checkDeHash( String productIdentifier ){
        if ( this.numberProductsWithIdentifier( productIdentifier ) == 0 ){
            this.store.remove( productIdentifier );
        }
    }


}

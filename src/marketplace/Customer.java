package marketplace;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Transactee {

    private int customerId;
    private Double balance;
    private HashMap< PaymentMethodType, PaymentMethod > paymentMethods;

    public Customer(
            int transacteeId,
            int customerId,
            String name,
            Double balance
    ){
        super(
                transacteeId,
                TransacteeType.CUSTOMER,
                name
        );
        this.customerId = customerId;
        this.paymentMethods = new HashMap<>();
        this.balance = balance;
    }

    public void addPaymentMethod( PaymentMethod method ) {
        this.paymentMethods.put( method.getType(), method );
    }

    public Boolean authorise( PaymentMethodType paymentMethodType, Double amount, String pin ) {
        return this.paymentMethods.get( paymentMethodType ).authorise( amount, pin );
    }

    public Double getBalance( PaymentMethodType paymentMethodType, String pin ) {
        return this.paymentMethods.get( paymentMethodType ).getBalance( pin );
    }

    public void buyProduct( Product product, PaymentMethodType paymentMethodType, String pin ) {
        if ( product != null ) {
            this.productList.storeProduct(product);
            this.paymentMethods.get(paymentMethodType).use(product.getRetailPrice(), pin);
        }
    }

    public Boolean hasProductWithIdentifier( String productIdentifier ) {
        return ( this.productList.numberProductsWithIdentifier( productIdentifier ) > 0 );
    }

    public Product returnProductWithIdentifier( String productIdentifier, PaymentMethodType paymentMethodType ) {
        if ( this.hasProductWithIdentifier( productIdentifier ) ) {
            Product fromStore = this.productList.fetchProductWithIdentifier(productIdentifier);
            this.paymentMethods.get(paymentMethodType).credit(fromStore.getRetailPrice());
            return fromStore;
        } else {
            return null;
        }
    }



}

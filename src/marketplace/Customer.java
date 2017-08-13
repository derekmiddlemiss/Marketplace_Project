package marketplace;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Transactee {

    private int customerId;
    private HashMap< String, PaymentMethod > paymentMethods;

    public Customer(
            int transacteeId,
            int customerId,
            String name,
            Double balance,
            ArrayList< Product > productList,
            HashMap< String, PaymentMethod > paymentMethods
    ){
        super( transacteeId, TransacteeType.CUSTOMER, name, balance );
        this.customerId = customerId;
        this.paymentMethods = paymentMethods;
    }


}

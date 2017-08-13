package marketplace;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Transactee {

    protected int transacteeId;
    protected TransacteeType type;
    protected String name;
    protected Double balance;
    protected ProductList productList;

    protected Transactee( int transacteeId, TransacteeType type, String name, Double balance ){
        this.transacteeId = transacteeId;
        this.type = type;
        this.name = name;
        this.balance = balance;
        this.productList = new ProductList();
    }

    public String getName(){
        return this.name;
    }

}

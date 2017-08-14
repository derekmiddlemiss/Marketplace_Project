package marketplace;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Transactee {

    protected int transacteeID;
    protected TransacteeType type;
    protected String name;
    protected ProductList productList;

    protected Transactee(
            int transacteeID,
            TransacteeType type,
            String name
    ){
        this.transacteeID = transacteeID;
        this.type = type;
        this.name = name;
        this.productList = new ProductList();
    }

    public String getName(){
        return this.name;
    }

}

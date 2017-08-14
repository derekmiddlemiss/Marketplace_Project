package marketplace;

public class Shop extends Transactee {

    private int shopID;
    private Double balance;
    private Double sales;
    private Double refunds;

    public Shop(
            int transacteeID,
            int shopID,
            String name,
            Double balance,
            Double sales,
            Double refunds
    ){
        super(
                transacteeID,
                TransacteeType.SHOP,
                name
        );
        this.shopID = shopID;
        this.balance = balance;
        this.sales = sales;
        this.refunds = refunds;
    }

    public Boolean checkStockWithIdentifier( String productIdentifier ) {
        return ( this.productList.numberProductsWithIdentifier( productIdentifier ) > 0 );
    }

    public Product sellProductWithIdentifier( String productIdentifier ){
        Product fromStore = this.productList.fetchProductWithIdentifier( productIdentifier );
        this.sales += fromStore.getRetailPrice();
        return fromStore;
    }

    public void restockProduct( Product product ) {
        this.productList.storeProduct( product );
        this.balance -= product.getWholesalePrice();
    }

    public void refundProduct( Product product ) {
        this.productList.storeProduct( product );
        this.refunds += product.getRetailPrice();
    }

    public Double getTotal() {
        return this.balance + this.sales - this.refunds;
    }


}

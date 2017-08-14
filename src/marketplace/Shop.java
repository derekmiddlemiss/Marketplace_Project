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
        return this.productList.hasProductWithIdentifier( productIdentifier );
    }

    public Product sellProductWithIdentifier( String productIdentifier ){
        if ( this.checkStockWithIdentifier( productIdentifier ) ) {
            Product fromStore = this.productList.fetchProductWithIdentifier(productIdentifier);
            this.sales += fromStore.getRetailPrice();
            return fromStore;
        } else {
            return null;
        }
    }

    public void restockProduct( Product product ) {
        if ( product != null ) {
            this.productList.storeProduct(product);
            this.balance -= product.getWholesalePrice();
        }
    }

    public void refundProduct( Product product ) {
        if ( product != null ) {
            this.productList.storeProduct(product);
            this.refunds += product.getRetailPrice();
        }
    }

    public Double getTotal() {
        return this.balance + this.sales - this.refunds;
    }


}

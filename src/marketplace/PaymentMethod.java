package marketplace;

public class PaymentMethod {

    private PaymentMethodType type;
    private String details;
    private Double balance;
    private Double limit;
    private int pinHash;

    public PaymentMethod( PaymentMethodType type, String details, Double balance, Double limit, String setupPin ) {
        this.type = type;
        this.details = details;
        this.balance = balance;
        this.limit = limit;
        this.pinHash = getHashCode( setupPin );
    }

    public Boolean use( Double payAmount, String pin ) {
        if ( !checkOverLimit(payAmount) && checkPin( pin ) ) {
            this.balance -= payAmount;
            return true;
        } else {
            return false;
        }
    }

    public void credit( Double creditAmount ) {
        this.balance += creditAmount;
    }

    private Boolean checkOverLimit( Double payAmount ) {
        return ( ( this.balance - payAmount ) < this.limit );
    }

    private int getHashCode( String input ) {
        return input.hashCode();
    }

    private Boolean checkPin( String inputPin ) {
        return ( this.pinHash == getHashCode( inputPin ) );
    }

    public Double getBalance( String pin ) {
        if ( checkPin( pin ) ) {
            return this.balance;
        } else {
            return null;
        }
    }

    public Boolean authorise( Double amount, String pin ) {
        if (checkPin( pin ) ) {
            return !this.checkOverLimit( amount );
        }
        return null;
    }

    public PaymentMethodType getType() {
        return this.type;
    }



}


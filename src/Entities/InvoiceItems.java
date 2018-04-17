package Entities;

public class InvoiceItems {

    /**
     * Entitetsbønne med getters og setters som representerer tabellen InvoiceItems.
     */

    private int invoice;
    private int product;

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}

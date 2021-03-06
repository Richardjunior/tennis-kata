package projet.esiea.model.entitiesReceipt;

import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductQuantity;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product[], Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Offer offer) {
        this.offers.put(offer.getProducts(),offer);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }

}

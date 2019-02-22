package projet.esiea.model.entitiesReceipt;

import projet.esiea.model.entitiesMarket.Product;

public interface SupermarketCatalog {
    void addProduct(Product product, double price);

    double getUnitPrice(Product product);

}

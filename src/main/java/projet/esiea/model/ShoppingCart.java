package projet.esiea.model;

import java.time.chrono.Era;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();


    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product[], Offer> offers, SupermarketCatalog catalog) {

    	Map<Product,Double> items = productQuantities();

    	loop:
			 for (Map.Entry<Product[], Offer> offer : offers.entrySet()) {
				 for (Product p : offer.getKey()) {
					 if (!items.containsKey(p)) {
						 continue loop;
					 }

			 }
		items = offer.getValue().DiscountCalculate(items,catalog);

			 Discount discount= offer.getValue().getDiscount();

			 if(discount != null) {
			 	receipt.addDiscount(discount);

            }

        } }  }



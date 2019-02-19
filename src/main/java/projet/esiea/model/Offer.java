package projet.esiea.model;

import java.util.Map;

public interface Offer {



	Product[] getProducts();
	Discount getDiscount();

	Map<Product,Double> DiscountCalculate(Map<Product,Double> items, SupermarketCatalog catalog);


    }


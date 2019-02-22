package projet.esiea.model.entitiesMarket;

import com.sun.org.apache.regexp.internal.RE;
import projet.esiea.model.alldiscounts.SpecialOfferType;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

public interface Offer {

	Product[] getProducts();
	Discount getDiscount();

	Map<Product, Double> DiscountCalculate(Map<Product, Double> items, SupermarketCatalog catalog);

}


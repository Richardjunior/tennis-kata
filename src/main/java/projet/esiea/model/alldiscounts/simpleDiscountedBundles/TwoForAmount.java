package projet.esiea.model.alldiscounts.simpleDiscountedBundles;

import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

public class TwoForAmount implements Offer {

	private final double argument;
	private Discount discount;
	private Product product;

	public TwoForAmount(Product product, double argument) {
		this.argument = argument;
		this.product = product;
	}

	@Override
	public Product[] getProducts() {
		return new Product[]{product};
	}

	@Override
	public Discount getDiscount() {
		return discount;
	}

	@Override
	public Map<Product, Double> DiscountCalculate(Map<Product, Double> items, SupermarketCatalog catalog) {

		double quantity = items.get(product);
		double priceUnit = catalog.getUnitPrice(product);



		if ((int) quantity >= 2) {
			double total = argument * (int) quantity / 2 + (int) quantity % 2 * priceUnit;
			double discountN = priceUnit * quantity - total;
			discount = new Discount(product, "2 for " + argument, discountN);
		}


		items.put(product, (double) (int) quantity % 2);
		return items;
	}
}

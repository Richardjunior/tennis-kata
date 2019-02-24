package projet.esiea.model.alldiscounts.simpleDiscountedBundles;

import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

public class ThreeForTwo implements Offer {

	public final Product p;
	public final double argument;
	private Discount discount;

	public ThreeForTwo(Product product, double argument) {
		this.argument = argument;
		this.p = product;

	}

	@Override
	public Product[] getProducts() {
		return new Product[]{p};
	}

	@Override
	public Discount getDiscount() {
		return discount;
	}

	@Override
	public Map<Product, Double> DiscountCalculate(Map<Product, Double> items, SupermarketCatalog catalog) {

		double initial_quantity = items.get(p);
		double initial_priceUnit = catalog.getUnitPrice(p);
		int initial_quantity_to_int= (int) initial_quantity;
		int x=initial_quantity_to_int/3;

		double discountAmount = initial_quantity * initial_priceUnit - ((x * 2 * initial_priceUnit) + initial_quantity_to_int % 3 * initial_priceUnit);
		discount = new Discount(p, "3 pour 2", discountAmount);

		items.put(p , (double)initial_quantity_to_int%3);
		return items;
	}
}

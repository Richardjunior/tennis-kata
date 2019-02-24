package projet.esiea.model.alldiscounts.simpleDiscountedBundles;

import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

public class TwoForAmount implements Offer {

	private double argument;
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
		/*
		 * Deux boîtes de tomates cerises au prix de 0,99 €, prix normal de 0,69 € par boîte.
		 * SELON NOS CALCULS, IL S'AGIT DE FAIRE UNE REDUCTION DE 28.26%
		 *
		 * c'est la raison pour laquelle argument prend 28.26%
		 * */
		argument = 28.26D;

		if ((int) quantity >= 2) {

			double total = ((int) quantity * priceUnit * argument) / 100;

			discount = new Discount(product, "2 for " + argument, total);
		}


		items.put(product, (double) (int) quantity % 2);
		return items;
	}
}

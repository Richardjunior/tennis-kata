
package projet.esiea.model.alldiscounts.newFeatureDiscountedBundles;

import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.function.BiFunction;

import java.util.Map;
import java.util.stream.Collectors;

public  class DiscountArticleGroupBundle implements Offer {

	private Discount discount = null;
	public final double argument;
	int numberOfLot = 0;
	double discountT = 0.0D;
	private Map<Product, Integer> products;


	public DiscountArticleGroupBundle(Map<Product, Integer> products, double argument) {
		this.argument = argument;
		this.products = products;
	}

	@Override
	public Product[] getProducts() {
		return products.keySet().toArray(new Product[products.size()]);
	}

	@Override
	public Discount getDiscount() {
		return discount;
	}

	public double applyDiscount(Map<Product, Integer> pro, Map<Product, Double> items, BiFunction<Integer, Double, Double> discountOffer, SupermarketCatalog catalog, int numberOfLot) {
		double discountToApply = 0.0D;
		for (Map.Entry<Product, Integer> productsApply : pro.entrySet()) {

			int quantity_for_discount = productsApply.getValue();
			Product product = productsApply.getKey();

			double quantity = items.get(pro);
			double priceUnit = catalog.getUnitPrice(product);

			discountToApply += discountOffer.apply(quantity_for_discount, priceUnit);

			items.put(product, quantity - quantity_for_discount * numberOfLot);

		}
		return discountToApply;

	}

	@Override
	public Map<Product, Double> DiscountCalculate(Map<Product, Double> items, SupermarketCatalog catalog) {
		//Nous faisons un check sur les quantités
		int validationOfQuantities = 1;
		for (Map.Entry<Product, Integer> product : products.entrySet()) {


			if (product.getValue() > items.get(product.getKey())) {
				validationOfQuantities = -1;
				break;

			}
			switch (validationOfQuantities) {

				case 1:
					for (Map.Entry<Product, Integer> productBoucle2 : products.entrySet()) {
						double quantity = items.get(productBoucle2);
						numberOfLot = (int) quantity;
					}

					BiFunction<Integer, Double, Double> discountOffer = (val1, val2) -> val1 * val2 * numberOfLot * 0.1;

					double discountT = applyDiscount(products, items, discountOffer, catalog, numberOfLot);
					String nameOfProduct = products.keySet().stream().map(Product::getName).collect(Collectors.joining(" & "));
					Product product1 = new Product(nameOfProduct, ProductUnit.Each);
					discount = new Discount(product1, "New discount bundle offer", discountT);

					break;
				case -1:

					break;
			}

		}
		return items;
	}
}

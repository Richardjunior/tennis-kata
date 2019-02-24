package projet.esiea.model.alldiscounts.newFeatureDiscountedBundles;

import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

public class DiscountArticleGroupBundle implements Offer {

	private Discount discount;
	private final double argument;
	private Product product;


	public DiscountArticleGroupBundle(Product product,double argument) {
		this.argument = argument;
		this.product=product;
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
		return null;
	}
}

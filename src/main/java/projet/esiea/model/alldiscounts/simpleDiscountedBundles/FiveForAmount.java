package projet.esiea.model.alldiscounts.simpleDiscountedBundles;


import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.lang.*;
import java.util.Map;

public class FiveForAmount implements Offer {

	public final Product product;
	public final double argument;
	private Discount discount = null;

	public FiveForAmount(Product product, double argument) {
		this.argument = argument;
		this.product = product;
	}


	private double quantity;

	@Override
		public Map<Product, Double> DiscountCalculate (Map<Product, Double> productQuantities, SupermarketCatalog catalog){
			double quantity = productQuantities.get(product);
			double unitPrice = catalog.getUnitPrice(product);
			int quantityAsInt = (int) quantity;
			int valueOf = quantityAsInt / 5;

			if (quantityAsInt >= 5) {
				double discountTotal = (quantity * unitPrice) - ((argument * valueOf) + (quantityAsInt % 5 * unitPrice));
				discount = new Discount(product, 5 + " for " + argument, discountTotal);
			}
			productQuantities.put(product, (double) quantityAsInt % 5);
			return productQuantities;
		}
	public Product[] getProducts(){
		return new Product[] {product};
	}
	public Discount getDiscount(){
		return discount;
	}
}

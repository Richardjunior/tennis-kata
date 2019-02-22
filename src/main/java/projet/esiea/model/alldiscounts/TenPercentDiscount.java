package projet.esiea.model.alldiscounts;


import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Offer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.HashMap;
import java.util.Map;

public class TenPercentDiscount implements Offer {

	public Product p;
	public double argument;
	private Discount discount;


	public TenPercentDiscount(Product p, double argument) {
		this.argument = argument;
		this.p = p;
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
		//nous calculons la réduciton des 10% des produits

		double quantity = items.get(p);
		double priceUnit = catalog.getUnitPrice(p);

		//dans notre cas, l'attribut argument est la quantité de noter produit
		if (argument != quantity) {
			argument = quantity;
		}
		final double  discountTotal = argument * priceUnit * 0.1;
		//Nous l'applicons sur nos produits
		discount = new Discount(p, "10%" + quantity, discountTotal);

		items.remove(p);
		return items;
	}


}

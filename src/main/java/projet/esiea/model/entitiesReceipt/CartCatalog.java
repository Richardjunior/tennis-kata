package projet.esiea.model.entitiesReceipt;


import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;

import java.util.HashMap;
import java.util.Map;

public class CartCatalog implements SupermarketCatalog {

	private Map<String, Product> products = new HashMap<String, Product>();
	private Map<String, Double> prices = new HashMap<String, Double>();

	public CartCatalog(){
		Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
		addProduct(toothbrush, 0.99);
		Product mango = new Product("Mango", ProductUnit.Each);
		addProduct(mango, 1.99);
		Product apples = new Product("Apples", ProductUnit.Kilo);
		addProduct(apples, 1.99);
		Product egg = new Product("Egg", ProductUnit.Kilo);
		addProduct(egg, 3.99);

	}
	public void addProduct(Product product, double price) {
		this.products.put(product.getName(), product);
		this.prices.put(product.getName(), price);
	}
	@Override
	public void removeProduct(String name) {
		products.remove(name);
		prices.remove(name);
	}

	public double getUnitPrice(Product p) {
		return this.prices.get(p.getName());
	}

	@Override
	public Map<String,Product> getProducts() {
		return products;
	}

	@Override
	public Map<String, Double> getPrices() {
		return prices;
	}

}

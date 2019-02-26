package projet.esiea.model;


import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;


import java.util.Map;

public class JsonNodeFactoryTolls {

	private static JsonNodeFactory jnf = JsonNodeFactory.instance;


	public static ObjectNode getProduct(String name, ProductUnit unit, double price) {

		ObjectNode productAndPrice = jnf.objectNode();
		productAndPrice.put("name",name);
		productAndPrice.put("unit",unit.toString());
		productAndPrice.put("price",price);

		return productAndPrice;
	}

	public static ObjectNode getCatalog(SupermarketCatalog catalog) {
		Map<String, Product> products = catalog.getProducts();
		Map<String, Double> prices = catalog.getPrices();

		ObjectNode catalogCard = jnf.objectNode();
		catalogCard.put("Size",prices.size());

		ArrayNode productsArrayNode = catalogCard.putArray("Products");

		for(String name : products.keySet()){
			ObjectNode productObjectNode = getProduct(name,products.get(name).getUnit(),prices.get(name));
			productsArrayNode.add(productObjectNode);
		}

		return catalogCard;
	}
}

package projet.esiea.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static projet.esiea.model.JsonNodeFactoryTolls.getCatalog;
import static projet.esiea.model.JsonNodeFactoryTolls.getProduct;

public class JsonNodeFactoryTollsTest {
	private static JsonNodeFactory jnf = JsonNodeFactory.instance;

	public static ObjectNode getCatalogForTest(SupermarketCatalog catalog) {
		Map<String, Product> products = catalog.getProducts();
		Map<String, Double> prices = catalog.getPrices();

		ObjectNode catalogCard = jnf.objectNode();
		catalogCard.put("Size", prices.size());

		ArrayNode productsArrayNode = catalogCard.putArray("Products");

		for (String name : products.keySet()) {
			ObjectNode productObjectNode = getProduct(name, products.get(name).getUnit(), prices.get(name));
			productsArrayNode.add(productObjectNode);
		}

		return catalogCard;
	}

	@Test
	void testMethodGetProduct() {
		final String name = "Apples";
		final String currentName = "[\"Apples\"]";
		final String currentPrice = "[2.0]";
		final String currentUnit = "[\"Each\"]";
		ProductUnit unit = ProductUnit.Each;
		final double price = 2D;

		JsonNodeFactoryTolls jsonNodeFactoryTolls = new JsonNodeFactoryTolls();

		ObjectNode productAndPrice = jnf.objectNode();
		productAndPrice.put("name", name);
		productAndPrice.put("unit", unit.toString());
		productAndPrice.put("price", price);


		assertThat(getProduct(name, unit, price)).isEqualTo(productAndPrice);
		assertThat(getProduct(name, unit, price).findValues("name").toString()).isEqualTo(currentName);
		assertThat(getProduct(name, unit, price).findValues("price").toString()).isEqualTo(currentPrice);
		assertThat(getProduct(name, unit, price).findValues("unit").toString()).isEqualTo(currentUnit);

	}

	@Test
	void testMethodGetCatalog() {
		final double priceUnit = 2.49D;

		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);
		JsonNodeFactoryTolls jsonNodeFactoryTolls = new JsonNodeFactoryTolls();


		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(bagofrice, priceUnit);

		assertThat(jsonNodeFactoryTolls.getCatalog(catalog)).isEqualTo(getCatalogForTest(catalog));


	}
}

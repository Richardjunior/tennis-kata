package projet.esiea.model.entitiesReceiptTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.CartCatalog;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartCatalogTest {

	@Test
	void addProductTest() {
		double totalPrice = 0.0D;
		CartCatalog cart = new CartCatalog();
		Product toothbrush = new Product("Toothbrush", ProductUnit.Each);
		cart.addProduct(toothbrush, 0.99);
		Product apples = new Product("apples", ProductUnit.Kilo);
		cart.addProduct(apples, 1.99);
		//On vérifie si la taille a bien augmenté lors de l'ajour des produits(d'autres produits sont déjà dans la hashmap products)

		assertThat(cart.getProducts().size()).isEqualTo(5);


		//Nous testons la fonction getPrice()
		for (Map.Entry<String, Double> product : cart.getPrices().entrySet()) {

			totalPrice += product.getValue().doubleValue();
		}
		assertThat(totalPrice).isEqualTo(10.95, within(0.1));


		//Nous testons la fonction get getUnitPrice(Product p)
		assertThat(cart.getUnitPrice(toothbrush)).isEqualTo(0.99D);


		//Nous testons la fonction
		Map<String, Double> price = new HashMap<>();
		price.put("Apples", 1.99);
		price.put("Egg", 3.99);
		price.put("Mango", 1.99);
		price.put("Toothbrush", 0.99);
		price.put("apples", 1.99);
		assertThat(cart.getPrices()).isEqualTo(price);

	}

}

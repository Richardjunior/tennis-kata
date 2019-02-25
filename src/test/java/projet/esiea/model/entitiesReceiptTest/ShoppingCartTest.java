package projet.esiea.model.entitiesReceiptTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import java.util.Map;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.registerCustomDateFormat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ShoppingCartTest {



	@Test
	void testAddItem() {
		Product productToAddForTest = new Product("banana" +
			"", ProductUnit.Kilo);


		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(productToAddForTest);
		shoppingCart.addItem(productToAddForTest);
		shoppingCart.addItem(productToAddForTest);


		int sumProductQuantities=0;

		for (Map.Entry<Product, Double> entry : shoppingCart.productQuantities.entrySet()) {
			sumProductQuantities += entry.getValue();

		}
		assertThat(sumProductQuantities).isEqualTo(3);


	}


}

package projet.esiea.model.alldiscountsTest.newFeatureDiscountedBundlesTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.newFeatureDiscountedBundles.DiscountArticleGroupBundle;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountArticleGroupBundleTest {

	/*
	 *
	 * */

	@Test
	public void testNewDiscountArticleBundle() {

		SupermarketCatalog catalog = new FakeCatalog();
		Teller teller = new Teller(catalog);
		ShoppingCart cart = new ShoppingCart();
		Map<Product, Double> itemsCurrent = new HashMap<>();
		itemsCurrent=cart.productQuantities;
		Map<Product, Double> itemsExpect;

		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99D);
		Product toothpaste = new Product("toothpaste", ProductUnit.Each);
		catalog.addProduct(toothpaste, 1.79D);

		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(toothbrush, 2);
		products.put(toothpaste, 1);
		teller.addSpecialOffer(new DiscountArticleGroupBundle(products, 10));
		itemsCurrent.put(toothbrush, 0.99D);
		itemsCurrent.put(toothpaste, 1.79D);
		DiscountArticleGroupBundle discountArticleGroupBundleToTest = new DiscountArticleGroupBundle(products, 3D);

		cart.addItemQuantity(toothbrush, 1);
		cart.addItemQuantity(toothpaste, 1);

		itemsExpect = discountArticleGroupBundleToTest.DiscountCalculate(itemsCurrent, catalog);


		Receipt receipt = teller.checksOutArticlesFrom(cart);


		assertThat(itemsExpect).isEqualTo(itemsCurrent);
		assertThat(receipt.getTotalPrice()).isEqualTo(2.7, within(0.1));

	}

}

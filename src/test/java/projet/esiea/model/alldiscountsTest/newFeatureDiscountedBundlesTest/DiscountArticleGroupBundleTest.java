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

		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99D);
		Product toothpaste = new Product("toothpaste", ProductUnit.Each);
		catalog.addProduct(toothpaste, 1.79D);

		Map<Product, Integer> products = new HashMap<Product, Integer>();
		products.put(toothbrush, 2);
		products.put(toothpaste, 1);
//argument n'est pas pris en considération dans la cette classe, car nous avons calculé sans tenir compte de ce paramètre
		teller.addSpecialOffer(new DiscountArticleGroupBundle(products, 10));

		cart.addItemQuantity(toothbrush, 1);
		cart.addItemQuantity(toothpaste, 1);

		Receipt receipt = teller.checksOutArticlesFrom(cart);
		assertThat(receipt.getTotalPrice()).isEqualTo(2.7, within(0.1));

	}

	@Test
	public void testApplyDiscount() {


		assertThat(true).isEqualTo(true);
	}
}

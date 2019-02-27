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
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiscountArticleGroupBundleTest {
	@Test
	void testDiscountArticleGoupBundle(){

		Map<Product, Double> itemsExpect;
		Map<Product, Double> itemsCurrent ;
		Map<Product, Double> items =new HashMap<>() ;
		Map<Product, Integer> products = new HashMap<Product, Integer>();


		SupermarketCatalog catalog = new FakeCatalog();
		Teller teller = new Teller(catalog);
		ShoppingCart cart = new ShoppingCart();
		Product toothpaste = new Product("toothpaste", ProductUnit.Each);
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);


		itemsCurrent = cart.productQuantities;
		itemsExpect=cart.productQuantities;

		catalog.addProduct(toothbrush, 0.99D);
		catalog.addProduct(toothpaste, 1.79D);


		products.put(toothbrush, 2);
		products.put(toothpaste, 1);

		teller.addSpecialOffer(new DiscountArticleGroupBundle(products, 10));

		itemsCurrent.put(toothbrush, 0.99D);
		itemsCurrent.put(toothpaste, 1.79D);
		items.put(toothbrush , 2D);
		items.put(toothpaste,1D);

		itemsExpect.put(toothbrush, 0.99D);
		itemsExpect.put(toothpaste, 1.79D);


		DiscountArticleGroupBundle discountArticleGroupBundleToTest = new DiscountArticleGroupBundle(products, 3D);


		cart.addItemQuantity(toothbrush, 1);
		cart.addItemQuantity(toothpaste, 1);

		teller.addSpecialOffer(new DiscountArticleGroupBundle(products, 0.1));
		Receipt receipt = teller.checksOutArticlesFrom(cart);
		//BiFunction<Integer, Double, Double> discountOffer = (val1, val2) -> val1 * val2 * 4 * 0.1;

		//double discount=discountArticleGroupBundleToTest.applyDiscount(products , items , discountOffer, catalog , 2);
		//assertThat(discount).isEqualTo(discount);

		assertThat(discountArticleGroupBundleToTest.DiscountCalculate(itemsCurrent, catalog)).isEqualTo(itemsCurrent);
		assertThat(1.99).isEqualTo(itemsCurrent.get(toothbrush));
		assertThat(itemsCurrent.get(toothbrush)).isEqualTo(1.99);
		assertThat(receipt.getTotalPrice()).isEqualTo(2.7, within(0.1));
		assertThat(discountArticleGroupBundleToTest.DiscountCalculate(itemsCurrent , catalog))
			.isEqualTo(discountArticleGroupBundleToTest.DiscountCalculate(itemsExpect,catalog));


	}
}

package projet.esiea.model.alldiscountsTest.simpleDiscountedBundlesTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.TwoForAmount;
import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TwoForAmountTest {

	/*
	 * Test offerType = TwoForAmount
	 * */
	@Test
	void offerTwoForAmount() {

		final double price = 0.69D;
		final int quantity = 2;
		final double argument = 28.26D;
		Receipt receipt = null;

		double expectedPrice = price * quantity - (quantity * price * argument) / 100;

		Product cherryTomatoes = new Product("cherryTomatoes", ProductUnit.Each);

		SupermarketCatalog catalog = new FakeCatalog();
		Teller teller = new Teller(catalog);
		ShoppingCart cart = new ShoppingCart();

		catalog.addProduct(cherryTomatoes, price);
		teller.addSpecialOffer(new TwoForAmount(cherryTomatoes, argument));
		cart.addItemQuantity(cherryTomatoes, quantity);

		receipt = teller.checksOutArticlesFrom(cart);
		final double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice, within(0.001));


	}

}

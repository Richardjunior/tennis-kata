package projet.esiea.model;

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
/*
		final double price = 0.69D;
		final int quantity = 2;
		final double argument = 2;


		Product cherryTomatoes = new Product("cherryTomatoes", ProductUnit.Each);

		SupermarketCatalog catalog = new FakeCatalog();
		Teller teller = new Teller(catalog);
		ShoppingCart cart = new ShoppingCart();

		catalog.addProduct(cherryTomatoes, price);
		cart.addItemQuantity(cherryTomatoes, quantity);
		teller.addSpecialOffer(new TwoForAmount(cherryTomatoes, argument));



		double total = argument * quantity / 2 + (int) quantity % 2 * price;
		double discountN = price * quantity - total;
		double expectedPrice = 0.99D;

		Discount discountCherryTomatoes = new Discount(cherryTomatoes, "add discount to cherryTomatoes ", discountN);
		cart.addItem(cherryTomatoes);
		Receipt receipt = teller.checksOutArticlesFrom(cart);


		final double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice, within(0.001));
*/
	}

}

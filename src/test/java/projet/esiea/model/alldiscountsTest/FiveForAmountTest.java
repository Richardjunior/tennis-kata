package projet.esiea.model.alldiscountsTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.FiveForAmount;
import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FiveForAmountTest {

	/*
	 * Test offerType =  FiveForAmount
	 * */
	@Test
	void offerFiveForAmount() {

		final double expectedPrice = 7.49D;
		final double price = 1.79D;
		final int quantity = 5;

		Product tubesOfToothpaste = new Product("tubesOfToothpaste", ProductUnit.Each);

		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(tubesOfToothpaste, price);
		Teller teller = new Teller(catalog);

		ShoppingCart cart = new ShoppingCart();

		teller.addSpecialOffer(new FiveForAmount( tubesOfToothpaste ,price * 5 ));
		cart.addItemQuantity(tubesOfToothpaste, quantity);


		Discount discounttubesOfToothpaste = new Discount(tubesOfToothpaste, " add discount to tubesOfToothpaste ", 1.46D);

		Receipt receipt = teller.checksOutArticlesFrom(cart);
		receipt.addDiscount(discounttubesOfToothpaste);
		double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice, within(0.001));

	}
}

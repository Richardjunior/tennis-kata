package projet.esiea.model;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class ShoppintCartTest {


	/*
	Test for offertype = ThreeForTwo
	 */
	@Test
	void offerThreeForTwo() {
		final double expectedPrice = 1.98D;
		SupermarketCatalog catalog = new FakeCatalog();
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99);

		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(toothbrush, 3);

		Teller teller = new Teller(catalog);
		teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0);

		Receipt receipt = teller.checksOutArticlesFrom(cart);

	
			assertThat(receipt.getTotalPrice()).isEqualTo(expectedPrice);


	}

	/*
	 * Test offeType = tenPercentDiscount
	 * */
	@Test
	void offerTenPercentDiscount() {

		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);
		final double priceUnit = 2.49D;
		final int quantity=5;
		final double expectedPrice=(priceUnit*quantity)-(((priceUnit*quantity)*10)/100);



		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(bagofrice, priceUnit);
		Teller teller = new Teller(catalog);

		ShoppingCart cart = new ShoppingCart();
		teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, bagofrice, 10);
		cart.addItemQuantity(bagofrice,quantity);

		Receipt receipt=teller.checksOutArticlesFrom(cart);

		double currentPrice=receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice);


	}




}

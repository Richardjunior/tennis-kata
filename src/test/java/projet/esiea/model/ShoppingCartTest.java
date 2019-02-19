package projet.esiea.model;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.registerCustomDateFormat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ShoppingCartTest {


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
		//teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0);

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
		final int quantity = 5;
		final double expectedPrice = (priceUnit * quantity) - (((priceUnit * quantity) * 10) / 100);


		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(bagofrice, priceUnit);
		Teller teller = new Teller(catalog);

		ShoppingCart cart = new ShoppingCart();
		//teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, bagofrice, 10);
		cart.addItemQuantity(bagofrice, quantity);

		Receipt receipt = teller.checksOutArticlesFrom(cart);

		double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice);


	}

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
		//teller.addSpecialOffer(SpecialOfferType.FiveForAmount, tubesOfToothpaste, price * 5);
		cart.addItemQuantity(tubesOfToothpaste, quantity);


		Discount discounttubesOfToothpaste = new Discount(tubesOfToothpaste, " add discount to tubesOfToothpaste ", 1.46D);

		Receipt receipt = teller.checksOutArticlesFrom(cart);
		receipt.addDiscount(discounttubesOfToothpaste);
		double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice, within(0.001));

	}


	/*
	 * Test offerType = TwoForAmount
	 * */
	@Test
	void offerTwoForAmount() {

		final double price = 0.69D;
		final int quantity=2;
		final double expectedPrice=0.99D;

		Product cherryTomatoes = new Product("cherryTomatoes", ProductUnit.Each);

		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(cherryTomatoes, price);


		Teller teller= new Teller(catalog);

		ShoppingCart cart = new ShoppingCart();
		//teller.addSpecialOffer(SpecialOfferType.TwoForAmount , cherryTomatoes , price*quantity);
		cart.addItemQuantity(cherryTomatoes  , quantity);


		Discount discountCherryTomatoes = new Discount(cherryTomatoes , "add discount to cherryTomatoes " , 0.39D);


		Receipt receipt=teller.checksOutArticlesFrom(cart);
		receipt.addDiscount(discountCherryTomatoes);

		final double currentPrice = receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice , within(0.001));

	}

	@Test
	void testAddItem(){
		Product productToAddForTest= new Product("banana" +
			"" ,ProductUnit.Kilo);


		ShoppingCart shoppingCart=new ShoppingCart();
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

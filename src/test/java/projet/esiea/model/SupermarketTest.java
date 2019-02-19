package projet.esiea.model;

import org.junit.jupiter.api.Test;
import projet.esiea.ReceiptPrinter;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SupermarketTest {


	public Receipt creationOfOneReceipt() {
		SupermarketCatalog catalog = new FakeCatalog();
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99);
		Product apples = new Product("apples", ProductUnit.Kilo);
		catalog.addProduct(apples, 1.99);

		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(apples, 2.5);
		cart.addItemQuantity(toothbrush, 3);


		Teller teller = new Teller(catalog);
	//	teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
	//	teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0);

		Receipt receipt = teller.checksOutArticlesFrom(cart);

		Discount discountApples = new Discount(apples, "Add discount on product", 0.995);
		Discount discountToothbrush = new Discount(toothbrush, "Add Discount toothbrush", 0.99);
		receipt.addDiscount(discountApples);

		return receipt;
	}

	@Test
	public void testTotalPrice() {

		Receipt receipt = creationOfOneReceipt();
		final double totalPriceTest = 5.96;
		assertThat(receipt.getTotalPrice()).isEqualTo(totalPriceTest);


	}
@Test
	/* Se renseigner aupres du prof pour celui ci */
	public void testProductQuantities() {
		Receipt receipt = creationOfOneReceipt();
		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(new Product("toothbrush", ProductUnit.Each), 3);
		cart.addItemQuantity(new Product("apples", ProductUnit.Kilo), 2.5);

		/*
		Test the total sum of the quantities of articles
		*/
		double sumProductQuantities = 0.0;
		double sumProductQuantitiesTest = 5.5;
		for (Map.Entry<Product, Double> entry : cart.productQuantities.entrySet()) {
			sumProductQuantities += entry.getValue();
		}
		assertThat(sumProductQuantities).isEqualTo(sumProductQuantitiesTest);
	}




}

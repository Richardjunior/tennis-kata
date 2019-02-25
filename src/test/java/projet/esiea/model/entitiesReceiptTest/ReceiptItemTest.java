package projet.esiea.model.entitiesReceiptTest;

import org.junit.jupiter.api.Test;

import projet.esiea.model.FakeCatalog;
import projet.esiea.model.entitiesReceipt.ReceiptPrinter;
import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.*;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReceiptItemTest {


	public Receipt creationOfOneReceipt() {
		SupermarketCatalog catalog = new FakeCatalog();
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99);
		Product apples = new Product("apples", ProductUnit.Kilo);
		catalog.addProduct(apples, 1.99);

		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(apples, 2.5);
		cart.addItemQuantity(toothbrush, 3);
		cart.addItemQuantity(apples, 1);


		Teller teller = new Teller(catalog);


		Receipt receipt = teller.checksOutArticlesFrom(cart);

		Discount discountApples = new Discount(apples, "Add discount on product", 0.995);
		Discount discountToothbrush = new Discount(toothbrush, "Add Discount toothbrush", 0.99);
		receipt.addDiscount(discountApples);

		return receipt;
	}

	@Test
	public void testMethodEqualsOfReceiptItem() {
		Product productToTest1 = new Product("orange", ProductUnit.Kilo);
		Product productToTest2 = new Product("orange", ProductUnit.Kilo);

		ReceiptItem receiptItemForTest1 = new ReceiptItem(productToTest1, 2.3D, 1.99D, 4.577D);
		ReceiptItem receiptItemForTest2 = new ReceiptItem(productToTest2, 2D, 1D, 2D);

		assertThat(productToTest1.equals(receiptItemForTest1)).isNotEqualTo(true);
		assertThat(receiptItemForTest2.equals(receiptItemForTest1)).isNotEqualTo(true);
		assertThat(receiptItemForTest1.equals(receiptItemForTest1)).isEqualTo(true);
		assertThat(receiptItemForTest1).isNotEqualTo(null);
		assertThat(receiptItemForTest2).isNotEqualTo(null);
		assertThat(receiptItemForTest1).isEqualTo(receiptItemForTest1);
		assertEquals(receiptItemForTest1, receiptItemForTest1);
		assertEquals(receiptItemForTest2, receiptItemForTest2);
		assertNotEquals(receiptItemForTest1, new ReceiptItem(new Product("somthing", ProductUnit.Each), 3D, 3D, 9D));

	}


	@Test
	public void testReceiptPrinter() {
		Receipt receipt = creationOfOneReceipt();
		assertThat(receipt.getItems().size()).isEqualTo(3);
		assertThat(new ReceiptPrinter().printReceipt(receipt)).isNotBlank();
	}


}

package projet.esiea.model.entitiesReceiptTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.FiveForAmount;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.TenPercentDiscount;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.ThreeForTwo;
import projet.esiea.model.entitiesMarket.*;
import projet.esiea.model.entitiesReceipt.*;
import projet.esiea.model.entitiesReceipt.Teller;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SupermarketTest {


	public Receipt creationOfOneReceipt() {
		SupermarketCatalog catalog = new FakeCatalog();

		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99);
		Product toothpaste = new Product("toothpaste", ProductUnit.Each);
		catalog.addProduct(toothpaste, 1.79);
		Product bagOfRize = new Product("bagofrize" , ProductUnit.Each);
		catalog.addProduct(bagOfRize , 2.49D);

		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(toothpaste, 2.5);
		cart.addItemQuantity(toothbrush, 3);
		cart.addItemQuantity(bagOfRize, 5);


		Teller teller = new Teller(catalog);
	//	teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
	//	teller.addSpecialOffer(SpecialOfferType.ThreeForTwo, toothbrush, 0);

		teller.addSpecialOffer(new TenPercentDiscount(bagOfRize, 5));
		teller.addSpecialOffer(new ThreeForTwo(toothbrush ,3 ));
		teller.addSpecialOffer(new FiveForAmount(toothpaste, 5));

		Receipt receipt = teller.checksOutArticlesFrom(cart);

		Discount discountToothpaste = new Discount(toothpaste, "Add discount on product", 1.46);
		Discount discountToothbrush = new Discount(toothbrush, "Add Discount toothbrush", 0.99);
		Discount discountbagofrize = new Discount(bagOfRize , "Add discount for bagOfRize " , 1.245);
		receipt.addDiscount(discountToothpaste);
		receipt.addDiscount(discountToothpaste);
		receipt.addDiscount(discountbagofrize);

		return receipt;
	}

	@Test
	public void testTotalPrice() {

		Receipt receipt = creationOfOneReceipt();
		final double totalPriceTest = 14.1175;
		assertThat(receipt.getTotalPrice()).isEqualTo(totalPriceTest, within(0.01));


	}





}

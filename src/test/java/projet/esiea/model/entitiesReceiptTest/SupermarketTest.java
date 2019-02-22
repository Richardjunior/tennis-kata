package projet.esiea.model.entitiesReceiptTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.alldiscounts.FiveForAmount;
import projet.esiea.model.alldiscounts.TenPercentDiscount;
import projet.esiea.model.alldiscounts.ThreeForTwo;
import projet.esiea.model.entitiesMarket.Discount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import java.util.Map;

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
		final double totalPriceTest = 13.495;
		assertThat(receipt.getTotalPrice()).isEqualTo(totalPriceTest, within(0.01));


	}





}

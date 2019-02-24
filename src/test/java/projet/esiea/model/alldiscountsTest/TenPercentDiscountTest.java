package projet.esiea.model.alldiscountsTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.TenPercentDiscount;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class TenPercentDiscountTest {

	/*
	 * Test offeType = tenPercentDiscount
	 * */


	@Test
	void offerTenPercentDiscountTest() {


		final double priceUnit = 2.49D;
		final int quantity = 5;


		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);

		SupermarketCatalog catalog = new FakeCatalog();
		catalog.addProduct(bagofrice, priceUnit);


		Teller teller = new Teller(catalog);
		teller.addSpecialOffer(new TenPercentDiscount(bagofrice, 10 ));

		ShoppingCart cart = new ShoppingCart();
		cart.addItemQuantity(bagofrice , quantity);

		final double expectedPrice = (priceUnit * quantity) - (((priceUnit * quantity) * 0.1) );

		Receipt receipt = teller.checksOutArticlesFrom(cart);
		double currentPrice=receipt.getTotalPrice();

		assertThat(currentPrice).isEqualTo(expectedPrice , within(0.001));




	}
}

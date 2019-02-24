package projet.esiea.model.alldiscountsTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.FakeCatalog;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.ThreeForTwo;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.Receipt;
import projet.esiea.model.entitiesReceipt.ShoppingCart;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import projet.esiea.model.entitiesReceipt.Teller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class ThreeForTwoTest {



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


		Teller teller = new Teller(catalog);
		teller.addSpecialOffer(new ThreeForTwo(toothbrush, 3));
		cart.addItemQuantity(toothbrush, 3);
		Receipt receipt = teller.checksOutArticlesFrom(cart);

		//ici je teste bien que en ajoutant 3 brosses à dents, j'ai bien le prix de 2 qui sont facturés
		assertThat(receipt.getTotalPrice()).isEqualTo(expectedPrice);


	}



}

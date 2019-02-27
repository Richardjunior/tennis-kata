package projet.esiea.model;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import org.springframework.web.servlet.view.RedirectView;

import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;

import projet.esiea.model.entitiesReceipt.SupermarketCatalog;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SpringBootWebApplicationTest {


	private SupermarketCatalog currentCatalog = new FakeCatalog();

	@Test
	void testGetProducts() {

		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);
		SpringBootWebApplication springBootWebApplication = new SpringBootWebApplication();

		currentCatalog.addProduct(bagofrice, 2.2D);
		springBootWebApplication.setCatalog(currentCatalog);
		ObjectNode catalogCard = JsonNodeFactoryTolls.getCatalog(currentCatalog);

		assertThat(springBootWebApplication.getProducts()).isEqualTo(catalogCard.toString());

	}

	@Test
	void testReadProduct() {
		final String expectedProduct = "{\"name\":\"bagofrice\",\"unit\":\"Each\",\"price\":2.2}";
		final String expectedProduct2 = "{\"name\":\"fabofrice\",\"unit\":\"Each\",\"price\":2.2}";
		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);
		SpringBootWebApplication springBootWebApplication = new SpringBootWebApplication();

		currentCatalog.addProduct(bagofrice, 2.2D);
		springBootWebApplication.setCatalog(currentCatalog);


		assertThat(springBootWebApplication.readProduct("bagofrice")).isEqualTo(expectedProduct);
		assertThat(springBootWebApplication.readProduct("bagofrice")).isNotEqualTo(expectedProduct2);

	}

	@Test
	void testAddProduct() {
		SpringBootWebApplication springBootWebApplication = new SpringBootWebApplication();
		assertThat(springBootWebApplication.addProduct("bagofrice", "Each", "2.2D")).isNotEqualTo(new RedirectView("/supermarket/products/{add}"));
		assertThat(springBootWebApplication.addProduct("bagofrice", "Each",
			"2.2D").isRedirectView()).isEqualTo(true);
	}

	@Test
	void testRemoveProduct() {
		SpringBootWebApplication springBootWebApplication = new SpringBootWebApplication();
		final Product bagofrice = new Product("bagofrice", ProductUnit.Each);
		currentCatalog.addProduct(bagofrice, 2.2D);
		springBootWebApplication.setCatalog(currentCatalog);


		assertThat(springBootWebApplication.removeProduct("bagofrice").isRedirectView()).isEqualTo(true);
		assertThat(springBootWebApplication.removeProduct("bagofrice").isRedirectView()).isNotEqualTo(new RedirectView("/supermarket/products/{add}"));
	}
}

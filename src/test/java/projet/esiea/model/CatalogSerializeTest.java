package projet.esiea.model;

import org.junit.jupiter.api.Test;

import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CatalogSerializeTest {
	@Test
	void testCatalogSerialize() {

		Class<SupermarketCatalog> t = null;
		Class<SupermarketCatalog> t2 = null;
		CatalogSerialize serialize = new CatalogSerialize(t);
		assertThat(serialize).isNotEqualTo(new CatalogSerialize(t2));

	}

	@Test
	void testSerialize() {


//TODO TEST


	}
}

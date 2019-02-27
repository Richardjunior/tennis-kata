package projet.esiea.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import static com.sun.tools.doclint.Entity.copy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogSerializeTest {
	@Test
	void testCatalogSerialize() {

		Class<SupermarketCatalog> t = null;
		Class<SupermarketCatalog> t2 = null;
		CatalogSerialize serialize = new CatalogSerialize(t);
		assertThat(serialize).isNotEqualTo(new CatalogSerialize(t2));

	}

	@Test
	void testSerialize() throws IOException {
		SupermarketCatalog catalog = new FakeCatalog();
		Product toothbrush = new Product("toothbrush", ProductUnit.Each);
		catalog.addProduct(toothbrush, 0.99D);
		Product toothpaste = new Product("toothpaste", ProductUnit.Each);
		catalog.addProduct(toothpaste, 1.79D);
		CatalogSerialize serialize=null;

		JsonGenerator jsonGenerator = null;
		SerializerProvider serializer=null;




	}
}

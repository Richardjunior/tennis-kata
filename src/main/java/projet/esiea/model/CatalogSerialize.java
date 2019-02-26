package projet.esiea.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;

import java.io.IOException;
import java.util.Map;

public class CatalogSerialize extends StdSerializer<SupermarketCatalog> {

	public CatalogSerialize(Class<SupermarketCatalog> t) {
		super(t);
	}

	@Override
	public void serialize(
		SupermarketCatalog catalog, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
		Map<String, Product> products = catalog.getProducts();
		Map<String, Double> prices = catalog.getPrices();

		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("Total", Integer.toString(products.size()));
		jsonGenerator.writeObjectFieldStart("Products");


		for (String productName : products.keySet()) {

			jsonGenerator.writeObjectFieldStart(productName);
			jsonGenerator.writeStringField("Unit", products.get(productName).getUnit().toString());
			jsonGenerator.writeStringField("Price", prices.get(productName).toString() + "€");
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndObject();
		jsonGenerator.writeEndObject();
	}
}

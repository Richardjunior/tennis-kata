package projet.esiea.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import projet.esiea.model.entitiesMarket.Product;
import projet.esiea.model.entitiesMarket.ProductUnit;
import projet.esiea.model.entitiesReceipt.CartCatalog;
import projet.esiea.model.entitiesReceipt.SupermarketCatalog;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.application.Application.*;
@SpringBootApplication
@RestController
public class SpringBootWebApplication {

	private static SupermarketCatalog catalog;
	private static ObjectMapper objectMapper;

	public static void main(String[] args) {

		catalog = new CartCatalog();
		objectMapper = new ObjectMapper();
		CatalogSerialize serializer = new CatalogSerialize(SupermarketCatalog.class);
		SimpleModule module = new SimpleModule("Catalog Serializer",new Version(2,9,8,null,null,null));
		module.addSerializer(SupermarketCatalog.class,serializer);
		objectMapper.registerModule(module);

		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	@GetMapping("/market/products")
	public static String getProducts(){
		ObjectNode catalogCard = JsonNodeFactoryTolls.getCatalog(catalog);
		return catalogCard.toString();


	}

	@GetMapping("/market/products/{name}")
	public static String readProduct(@PathVariable String name) {

		Product p = catalog.getProducts().get(name);
		double price = catalog.getPrices().get(name);
		ObjectNode productAndPrice = JsonNodeFactoryTolls.getProduct(p.getName(),p.getUnit(),catalog.getUnitPrice(p));
		return productAndPrice.toString();

	}

	@GetMapping("/market/products/add")
	public static RedirectView addProduct(@RequestParam("name") String name, @RequestParam("unit") String unit, @RequestParam("price") String price) {

		Product p = new Product(name, ProductUnit.valueOf(unit));
		catalog.addProduct(p,Double.valueOf(price));

		ObjectNode productAndPrice = JsonNodeFactoryTolls.getProduct(p.getName(),p.getUnit(),catalog.getUnitPrice(p));
		return new RedirectView("/supermarket/products");
	}

	@RequestMapping("/market/products/remove/{name}")
	public static RedirectView removeProduct(@PathVariable String name) {

		catalog.removeProduct(name);

		return new RedirectView("/supermarket/products");
	}

	public static SupermarketCatalog getCatalog() {
		return catalog;
	}

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static void setCatalog(SupermarketCatalog catalog) {
		SpringBootWebApplication.catalog = catalog;
	}
}

package projet.esiea.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;

public class ProductTest {


	@Test
	public void testMethodEqualsOfProduct() {

		Product productToTest1 = new Product("orange", ProductUnit.Kilo);
		Product productToTest2 = new Product("orange", ProductUnit.Kilo);
		Product differentProductTotest=new Product("chaussure", ProductUnit.Each);
		Discount discountTotest=new Discount(productToTest1 , "Discount for product test", 2.2D);
		assertThat(discountTotest.equals(productToTest1)).isNotEqualTo(true);
		assertThat(discountTotest.equals(differentProductTotest)).isNotEqualTo(true);
		assertThat(productToTest1.equals(productToTest2)).isEqualTo(true);
		assertThat(productToTest1.equals(productToTest1)).isEqualTo(true);
		assertThat(productToTest1.equals(differentProductTotest)).isNotEqualTo(true);
		assertThat(productToTest1.equals(productToTest1)).isNotEqualTo(null);
		assertThat(productToTest2.equals(productToTest2)).isNotEqualTo(null);
		assertThat(differentProductTotest.equals(productToTest1)).isNotEqualTo(null);
		assertThat(differentProductTotest).isNotEqualTo(null);
		assertThat(productToTest1).isNotEqualTo(null);


	}


}

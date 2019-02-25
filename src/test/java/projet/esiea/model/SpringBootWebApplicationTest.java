package projet.esiea.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpringBootWebApplicationTest {
	@Test
	void StringMethodTest() {
		assertThat(SpringBootWebApplication.hello()).isEqualTo("Hello!");
	}



}

package projet.esiea.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReceiptItemTest {
	@Test
	public void testMethodEqualsOfReceiptItem(){
		Product productToTest1 = new Product("orange", ProductUnit.Kilo);
		Product productToTest2 = new Product("orange", ProductUnit.Kilo);

		ReceiptItem receiptItemForTest1=new ReceiptItem(productToTest1 ,2.3D , 1.99D , 4.577D );
		ReceiptItem receiptItemForTest2=new ReceiptItem(productToTest2 ,2D , 1D , 2D );
		assertThat(productToTest1.equals(receiptItemForTest1)).isNotEqualTo(true);
		assertThat(receiptItemForTest2.equals(receiptItemForTest1)).isNotEqualTo(true);
		assertThat(receiptItemForTest1.equals(receiptItemForTest1)).isEqualTo(true);
		assertThat(receiptItemForTest1).isNotEqualTo(null);
		assertThat(receiptItemForTest2).isNotEqualTo(null);

	}
}

package projet.esiea.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
		assertThat(receiptItemForTest1).isEqualTo(receiptItemForTest1);
		assertEquals(receiptItemForTest1, receiptItemForTest1);
		assertEquals(receiptItemForTest2,receiptItemForTest2);
		assertNotEquals(receiptItemForTest1 ,new ReceiptItem(new Product("somthing", ProductUnit.Each) ,3D , 3D , 9D ) );

	}

	@Test
	public void testMethodHashCode(){
		Product productToTest1 = new Product("ananas", ProductUnit.Kilo);
		ReceiptItem receiptItemForTest= new ReceiptItem(productToTest1 , 2D , 2D , 4);

		assertThat(receiptItemForTest.hashCode()).isNotEqualTo(4);
		assertThat(receiptItemForTest.hashCode()).isNotNull();

	}
}

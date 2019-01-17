package projet.esiea.model;

import org.junit.jupiter.api.Test;

import projet.esiea.ReceiptPrinter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SupermarketTest {

    @Test
    public void testSomething() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);
        cart.addItemQuantity(toothbrush, 3);


        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TenPercentDiscount, toothbrush, 10.0);
        teller.addSpecialOffer(SpecialOfferType.ThreeForTwo , toothbrush , 0);


        Receipt receipt = teller.checksOutArticlesFrom(cart);
        
        Discount discountApples=new Discount(apples , "Add discount on product" , 0.995);
        Discount discountToothbrush= new Discount(toothbrush, "Add Discount toothbrush" , 0.99);
        receipt.addDiscount(discountApples);
        assertThat(receipt.getTotalPrice()).isEqualTo(5.96);

        assertThat(new ReceiptPrinter().printReceipt(receipt)).isNotBlank();

        
    }
    
}





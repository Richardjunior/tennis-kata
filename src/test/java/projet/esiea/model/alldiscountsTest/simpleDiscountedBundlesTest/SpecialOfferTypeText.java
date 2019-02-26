package projet.esiea.model.alldiscountsTest.simpleDiscountedBundlesTest;

import org.junit.jupiter.api.Test;
import projet.esiea.model.alldiscounts.simpleDiscountedBundles.SpecialOfferType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static projet.esiea.model.alldiscounts.simpleDiscountedBundles.SpecialOfferType.*;

public class SpecialOfferTypeText {

	@Test
	void testSpecialOfferType(){
		assertThat(SpecialOfferType.valueOf("ThreeForTwo")).isEqualTo(ThreeForTwo);
		assertThat(SpecialOfferType.valueOf("TenPercentDiscount")).isEqualTo(TenPercentDiscount);
		assertThat(SpecialOfferType.valueOf("TwoForAmount")).isEqualTo(TwoForAmount);
		assertThat(SpecialOfferType.valueOf("FiveForAmount")).isEqualTo(FiveForAmount);
	}
}

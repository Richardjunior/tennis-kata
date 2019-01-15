package projet;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MaClasseTest {

    @Test

    void testVerif(){

        assertThat("bonjour").isEqualTo("bonjour");

    }

}

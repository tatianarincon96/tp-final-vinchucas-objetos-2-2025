package especieVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EspecieVinchucaTest {

    @Test
    public void toStringDevuelveNombreLegible() {
        assertEquals("Vinchuca infestans", EspecieVinchuca.VINCHUCA_INFESTANS.toString());
        assertEquals("Vinchuca sordida", EspecieVinchuca.VINCHUCA_SORDIDA.toString());
        assertEquals("Vinchuca guasayana", EspecieVinchuca.VINCHUCA_GUASAYANA.toString());
    }
}

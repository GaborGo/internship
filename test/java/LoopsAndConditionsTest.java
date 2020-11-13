import com.nexttech.internship.lambdasOnStreams.LambdasOnStreams;
import com.nexttech.internship.loopsAndConditions.LoopsAndConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LoopsAndConditionsTest {
@Test
void testGetResultFromPolishCalculator() {
    Assertions.assertEquals(20, LoopsAndConditions.getResultFromPolishCalculator("3 4 * 8 +"));
    Assertions.assertEquals(45.5, LoopsAndConditions.getResultFromPolishCalculator("40 51 + 2 /"));
    Assertions.assertEquals(-1, LoopsAndConditions.getResultFromPolishCalculator("5 5 * 26 -"));
    Assertions.assertEquals(-27,LoopsAndConditions.getResultFromPolishCalculator("3 2 + 8 4 * -"));
    Assertions.assertEquals(60,LoopsAndConditions.getResultFromPolishCalculator("4 5 6 * + 4 7 * 2 - +"));
}
}

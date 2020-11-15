import com.nexttech.internship.loopsAndConditions.LoopsAndConditions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class LoopsAndConditionsTest {

    @Test
    void testGetStringFromComparingMatchingCharacters() {
       Assertions.assertEquals("*****" ,
               LoopsAndConditions.getStringFromComparingMatchingCharacters("12345", "12345"));
        Assertions.assertEquals("*****" ,
                LoopsAndConditions.getStringFromComparingMatchingCharacters("00000", "00000"));
        Assertions.assertEquals("+++++" ,
                LoopsAndConditions.getStringFromComparingMatchingCharacters("12345", "53421"));
        Assertions.assertEquals("*" ,
                LoopsAndConditions.getStringFromComparingMatchingCharacters("12345", "17890"));
        Assertions.assertEquals("**" ,
                LoopsAndConditions.getStringFromComparingMatchingCharacters("12345", "10005"));
    }

    @Test
    void testGetResultFromPolishCalculator() {
        Assertions.assertEquals(20, LoopsAndConditions.getResultFromPolishCalculator("3 4 * 8 +"));
        Assertions.assertEquals(45.5, LoopsAndConditions.getResultFromPolishCalculator("40 51 + 2 /"));
        Assertions.assertEquals(-1, LoopsAndConditions.getResultFromPolishCalculator("5 5 * 26 -"));
        Assertions.assertEquals(-27, LoopsAndConditions.getResultFromPolishCalculator("3 2 + 8 4 * -"));
        Assertions.assertEquals(60, LoopsAndConditions.getResultFromPolishCalculator("4 5 6 * + 4 7 * 2 - +"));
    }

    @Test
    void testGetResultFromSingleCalculation() {
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("6");
        list.add("+");
        String result = LoopsAndConditions.getResultFromSingleCalculation(list);
        Assertions.assertEquals("11.0", result);
    }
}


import com.nexttech.internship.lambdasOnStreams.Author;
import com.nexttech.internship.lambdasOnStreams.LambdasOnStreams;
import com.nexttech.internship.lambdasOnStreams.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class LambdasOnStreamsTest {
    static List<String> listOfStrings = new ArrayList<>();
    static List<Integer> integerList = new ArrayList<>();
    static List<Person> personList = new ArrayList<>();
    static List<Author> authorList = new ArrayList<>();

    @BeforeAll
    public static void populateLists() {
        listOfStrings.add("Japan");
        listOfStrings.add("USA");
        listOfStrings.add("nigeria");
        listOfStrings.add("nfive");
        listOfStrings.add("Nfive");

        integerList.add(1);
        integerList.add(22);
        integerList.add(33);
        integerList.add(100);

        personList.add(new Person("Adam", 18, "Romanian"));
        personList.add(new Person("Ana", 16, "Romanian"));
        personList.add(new Person("Jack", 17, "Romanian"));
        personList.add(new Person("John", 30, "Romanian"));
        personList.add(new Person("Jill", 30, "Russian"));

        authorList.add(new Author(17, "Hemingway", "Ernest", "Male"));
        authorList.add(new Author(24, "Connor", "Sarah", "Female"));
        authorList.add(new Author(80, "Smith", "Nelly", "Female"));
        authorList.add(new Author(66, "Jones", "Nicole", "Female"));
        authorList.add(new Author(78, "Manager", "Karen", "Female"));
        authorList.add(new Author(67, "Lagarde", "Christine", "Female"));
        authorList.add(new Author(22, "Sabo", "Victoria", "Female"));
        authorList.add(new Author(66, "Popa", "Maria", "Female"));
    }

    @Test
    @DisplayName("Test if all strings are UpperCase")
    void testGetAllElementsUpperCase() {
        List<String> result = LambdasOnStreams.getAllElementsUpperCase(listOfStrings);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(listOfStrings.size(), result.size());
        Assertions.assertEquals("JAPAN", result.get(0));
    }

    @Test
    @DisplayName("Test strings have length of 5, and contains 'n'")
    void testGetAllOfLengthFiveContainingCharn() {
        List<String> result = LambdasOnStreams.getAllOfLengthFiveContainingCharn(listOfStrings);
        Assertions.assertAll(
                () -> Assertions.assertTrue(result.get(0).contains("n")),
                () -> Assertions.assertNotNull(result),
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("nfive", result.get(1))
        );
    }

    @Test
    @DisplayName("Test elements that start with 'n', made UpperCase, Sorted")
    void testGetAllStartingWithnUpperCasedSorted() {
        List<String> result = LambdasOnStreams.getAllStartingWithnUpperCasedSorted(listOfStrings);
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals('N', result.get(0).charAt(0)),
                () -> Assertions.assertNotEquals(result.get(0), result.get(1)),
                () -> Assertions.assertEquals("NFIVE", result.get(1))
        );
    }

    @Test
    @DisplayName("Test returned String has numbers preceded by even/odd")
    void testGetOddEvenNumberString() {
        String result = LambdasOnStreams.getOddEvenNumberString(integerList);
        Assertions.assertAll(
                () -> Assertions.assertEquals("o1", result.substring(0, 2)),
                () -> Assertions.assertEquals(result.length(), 15),
                () -> Assertions.assertTrue(result.contains("o33"))
        );
    }

    @Test
    @DisplayName("Get oldest person from list")
    void testGetOldestPersonInList() {
        Person person = LambdasOnStreams.getOldestPersonInList(personList);
        Assertions.assertEquals(30, person.getAge());
    }

    @Test
    void testGetAllUnderAgePersons() {
        List<Person> result = LambdasOnStreams.getAllUnderAgePersons(personList);
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertTrue(result.get(0).getAge() < 18)
        );
    }

    @Test
    void testGetAllRomanianMajorsNameStartingWithA() {
        List<Person> result = LambdasOnStreams.getAllRomanianMajorsNameStartingWithASortedByAge(personList);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, result.size()),
                () -> Assertions.assertEquals("Romanian", result.get(0).getNationality())
        );
    }

    @Test
    void testGetFirstSevenOlderThanSixtyFiveUniqueSurnamesInUpperCase() {
        List<String> result = LambdasOnStreams.getUniqueSurnamesInUpperCaseForOlderThanSixtyFiveLimitSeven(authorList);
        Assertions.assertAll(
                () -> Assertions.assertTrue(result.size() < 8),
                () -> Assertions.assertFalse(result.contains("CONNOR")),
                () -> Assertions.assertTrue(result.contains("LAGARDE"))
        );
    }

    @Test
    void testPrintSumOfAgesOfFemaleAuthorsBelowTwentyFive() {
        int result = LambdasOnStreams.printSumOfAgesOfFemaleAuthorsBelowTwentyFive(authorList);
        Assertions.assertEquals(46, result);
    }

}

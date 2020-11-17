package com.nexttech.internship.SortingMethodsWithLambdasOnStreams;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SortingMethodsWithLambdasOnStreams {

    // Given list of string as input. Write a method that will return the list with all the elements in upper case.
    public static List<String> getAllElementsUpperCase(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    // Given list of string. Write a method that will return a filtered string list
    // (contains letter‘n’ and has length 5)
    public static List<String> getAllOfLengthFiveContainingCharn(List<String> list) {
        return list.stream()
                .filter(n -> n.length() == 5 && n.contains("n"))
                .collect(Collectors.toList());
    }

    //Given words list. Write a method that filter words that starts with letter “n”, transform
   // to upper case and sort them
   public static  List<String> getAllStartingWithnUpperCasedSorted (List<String> list) {
        return list.stream()
                .filter( n -> n.charAt(0) == 'n')
                .map( n -> n = n.toUpperCase())
                .collect(Collectors.toList());
   }

    //Given list of integers {1,62,33}. Write a method that will return a comma separated
    // string based on the input. If the number is even, will be preceded by the letter “e”,
    // otherwise “o” (o1,e62,o33).
    public static String getOddEvenNumberString (List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n % 2 == 0 ? "e" + n : "o" + n)
                .collect(Collectors.joining(","));
    }

    //Given Person objects with name, age, nationality. Write a method that will find the
    //oldest person from a list.
    public static Person getOldestPersonInList(List<Person> persons) {
        return persons.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null);
    }

    //Given Person objects with name, age, nationality. Write a method that will return all
    //persons under 18
    public static List<Person> getAllUnderAgePersons(List<Person> list) {
        return list.stream()
                .filter( n -> n.getAge() < 18)
                .collect(Collectors.toList());
    }

    //Given Person objects with name, age, nationality. Write a method that filter all persons
    //with Romanian nationality, name start with letter “a”, over 18 and sort them after age.
    public static List<Person> getAllRomanianMajorsNameStartingWithASortedByAge(List<Person> list) {
        return list.stream()
                .filter( n -> (n.getAge() > 17 && n.getNationality().equals("Romanian") && n.getName().charAt(0) == 'A'))
                .collect(Collectors.toList());
    }

    // Library Search
    // Get the unique surnames in uppercase of the first 7 book authors that are 66 years old or older.
    public static List<String> getUniqueSurnamesInUpperCaseForOlderThanSixtyFiveLimitFirstSeven(List<Author> authors) {
        Set<Author> result = authors.stream()
                .filter(n -> n.getAge() > 65)
                .limit(7).collect(Collectors.toSet());
        return result.stream()
                .map( n -> n.getFirstName().toUpperCase())
                .collect(Collectors.toList());
    }
    // Print out the sum of ages of all female authors younger than 25
    public static int printSumOfAgesOfFemaleAuthorsBelowTwentyFive(List<Author> authors) {
      int result =  authors.stream()
                .filter( n -> n.getAge() < 25 && n.getGender().equals("Female") )
                .map(Author::getAge)
                .mapToInt(Integer::intValue)
                .sum();
      return result;
    }
}


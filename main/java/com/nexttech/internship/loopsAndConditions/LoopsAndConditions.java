package com.nexttech.internship.loopsAndConditions;

import java.util.*;
import java.util.stream.Collectors;

public class LoopsAndConditions {

//    Write a “Guess the number” game. Program will generate a random number and will ask the user to guess it.
//    If the user guesses the number, the program will stop.
//    If the user does not guess it, the program will display: 'Wrong answer, your number it too high' or 'Wrong answer, your number is too low'.
//    The program will allow the user maximum 3 retries after which it will stop with the message 'You lost'.

    public static void startGuessTheNumber() {
        int numberToFind = (int) (Math.random() * 10);
        Scanner sc = new Scanner(System.in);
        System.out.println("Guess the number: ");
        int userInput = sc.nextInt();

        for (int i = 0; i < 3; i++) {
            if (i > 0)
                userInput = sc.nextInt();
            if (userInput > numberToFind) {
                System.out.println("Wrong answer, your number it too high!");
            } else if (userInput < numberToFind) {
                System.out.println("Wrong answer, your number it too low!");
            } else {
                System.out.println("You won! Number is: " + numberToFind);
                break;
            }
        }
        System.out.println("You lost! Solution was: " + numberToFind);
        sc.close();
    }

    //Improve “Guess the number game”. In this version, will let the user to guess several times (with responses each time) until he finds the number.
    // User can type: o “r” - restart (will generate a new number to guess) o “q” - quit
    // o “d” - debug (toogle:turn on/off - show the number that need to be guessed for every time you are asked to supply the guess)
    // o “m” - move mode (toogle:turn on/off - when is on, after every wrong guess, the number can change by 2 in either direction)

    public static void startGuessTheNumberImproved() {
        boolean isDebugEnabled = false;
        boolean isMoveModeEnabled = false;
        Random random = new Random();  //generate random boolean
        int numberToFind = random.nextInt(1000);
        Scanner scanner = new Scanner(System.in);

        label:
        for (int i = 0; i < 100; i++) {
            System.out.println("Guess the number: ");
            int userInputNumber;
            String userInputString = scanner.next();

            switch (userInputString) {
                case "r":
                    numberToFind = random.nextInt(1000);
                    System.out.println("Generating new number to guess...");
                    isMoveModeEnabled = false;
                    continue;
                case "q":
                    System.out.println("Quitting...");
                    break label;
                case "d":
                    isDebugEnabled = !isDebugEnabled;
                    System.out.println("Debug enabled: " + isDebugEnabled);
                    continue;
                case "m":
                    isMoveModeEnabled = !isMoveModeEnabled;
                    System.out.println("MoveMode enabled: " + isMoveModeEnabled);
                    continue;
                default:
                    if (isInputNumber(userInputString))
                        userInputNumber = Integer.parseInt(userInputString);
                    else
                        continue;
            }
            if (userInputNumber > numberToFind) {
                if (isMoveModeEnabled) {
                    if (random.nextBoolean()) {
                        numberToFind += 2;
                    } else {
                        numberToFind -= 2;
                    }
                }
                if (isDebugEnabled) {
                    System.out.println("Wrong answer, your number it too high! You are searching for: " + numberToFind);
                    continue;
                }
                System.out.println("Wrong answer, your number it too high!");
            } else if (userInputNumber < numberToFind) {
                if (isMoveModeEnabled) {
                    if (random.nextBoolean()) {
                        numberToFind += 2;
                    } else {
                        numberToFind -= 2;
                    }
                }
                if (isDebugEnabled) {
                    System.out.println("Wrong answer, your number it too low! You are searching for: " + numberToFind);
                    continue;
                }
                System.out.println("Wrong answer, your number it too low!");
            } else {
                System.out.println("You won! Number is: " + numberToFind);
                break;
            }
        }
        scanner.close();
    }

    static boolean isInputNumber(String userInputString) {
        try {
            Integer.parseInt(userInputString);
            return true;
        } catch (Exception e) {
            System.out.println("Wrong input!");
            //   e.printStackTrace();
            return false;
        }
    }

    // “The Mentalist” game.
    // Program will generate a string with 5 different digits and after that will ask the user to guess.
    // The return value will be calculated after next algorithm
    // o If a digit matches in the same location, program will return *
    // o If a digit matches but is not in the same location program will return +
    // o If a digit does not match, program will return nothing. o E.g. (random string: 01629 user input: 03785 program output: *
    // o user input 02169 program output: *+++*)
    public static String startMentalistGame() {
        Scanner scanner = new Scanner(System.in);
        String solution;
        System.out.println("Enter 5 number sequence:");
        String userInput = scanner.next();
        String result;
        if (isInputValid(userInput))
            solution = getRandomFiveNumberString();
        else {
            System.out.println("Invalid input. Please enter a five number sequence.");
            return null;
        }
        result = getStringFromComparingMatchingCharacters(userInput, solution);
        scanner.close();
        System.out.println("Mentalist string: " + solution + "\nSolution: " + userInput + "\nResult: " + result);
        return result;
    }

    public static String getRandomFiveNumberString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++)
            stringBuilder.append((int) (Math.random() * 10));
        return stringBuilder.toString();
    }

    public static boolean isInputValid(String input) {
        if (input.length() != 5) {           //check valid length of input
            System.out.println("Input length mismatch");
            return false;
        }
        return true;
    }

    public static String getStringFromComparingMatchingCharacters(String input, String solution) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {         // check for matches
            if (input.charAt(i) == solution.charAt(i)) {
                result.append("*");
            } else if (solution.contains("" + input.charAt(i)))
                result.append("+");
        }
        return result.toString();
    }

    public static double getResultFromPolishCalculator(String equation) {
        String operators = "+-*/";
        //Make ArrayList from input String
        List<String> listOfElements = new ArrayList<>(Arrays.asList(equation.split(" ")));
        //Exit point from recursion
        if (listOfElements.size() < 4) {
            return Double.parseDouble(getResultFromSingleCalculation(listOfElements));
        }
        //Create a copy of listOfElements, which will be used for recursion
        List<String> updatedList = new ArrayList<>(listOfElements);
        System.out.println(listOfElements);
        //Iterate through list
        for (int i = 0; i < listOfElements.size(); i++) {
            List<String> singleOperationElements;
            // check for operand occurrences
            if (operators.contains(listOfElements.get(i))) {
                // extract first single operation elements
                singleOperationElements = listOfElements.subList(i - 2, i + 1);
                //Insert result of first operation at the beginning
                updatedList.add(i + 1, getResultFromSingleCalculation(singleOperationElements));
                //Remove operated elements
                updatedList.remove(i);
                updatedList.remove(i - 1);
                updatedList.remove(i - 2);
                break;
            }
        }
        //Call method recursively
        return getResultFromPolishCalculator(getStringFromList(updatedList));
    }

    //Create String from list
    public static String getStringFromList(List<String> list) {
        return list.stream()
                .map(n -> " " + n)
                .collect(Collectors.joining()).substring(1);
    }
    //Calculate from a list with 2 numbers and an operand
    public static String getResultFromSingleCalculation(List<String> list) {
        double firstNumber = Double.parseDouble(list.get(0));
        double secondNumber = Double.parseDouble(list.get(1));
        String operator = list.get(2);

        switch (operator) {
            case "+":
                return "" + (firstNumber + secondNumber);
            case "-":
                return "" + (firstNumber - secondNumber);
            case "*":
                return "" + (firstNumber * secondNumber);
            case "/":
                return "" + (firstNumber / secondNumber);
        }

        System.out.println("Wrong input. Please enter a valid polish equation String!");
        return "";
    }
}

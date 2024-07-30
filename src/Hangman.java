import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String filePath = "C:\\Users\\Мак\\Desktop\\introduction\\Project de intro progr\\Hangman\\words.txt";

        
        List<String> words = readWordsFromFile(filePath);
        if (words.isEmpty()) {
            System.out.println("La lista de palabras está vacía o no se pudo leer el archivo.");
            return;
        }

        
        String wordToGuess = words.get(random.nextInt(words.size()));

        int attemptsLeft = 5;  
        char[] guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < wordToGuess.length(); i++) {
            guessedWord[i] = '*';  
        }

        while (attemptsLeft > 0 && !isWordGuessed(guessedWord)) {
            System.out.println("Palabra actual: " + new String(guessedWord));
            System.out.println("Intentos restantes: " + attemptsLeft);
            System.out.print("Adivina una letra: ");
            char guessedLetter = scanner.next().charAt(0);

            boolean isLetterInWord = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedLetter) {
                    guessedWord[i] = guessedLetter;
                    isLetterInWord = true;
                }
            }

            if (!isLetterInWord) {
                attemptsLeft--;
            }
        }

        if (isWordGuessed(guessedWord)) {
            System.out.println("¡Felicidades! Adivinaste la palabra: " + wordToGuess);
        } else {
            System.out.println("Te quedaste sin intentos. La palabra era: " + wordToGuess);
        }
    }

    public static List<String> readWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + filePath);
        }
        return words;
    }

    public static boolean isWordGuessed(char[] guessedWord) {
        for (char c : guessedWord) {
            if (c == '*') { 
                return false;
            }
        }
        return true;
    }
}

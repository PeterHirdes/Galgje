import java.util.ArrayList;
//import java.nio.file.Path;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Main {

  public static String WordChoice() {
    ArrayList<String> wordlist = new ArrayList<>();
    String wordchoice = "Geen waarde"; //om door te krijgen of het is gelukt iets in te vullen, zo niet dan blijft dit "Geen waarde"
    //Path path = Path.of("C:\\Users\\Peter\\Documents\\Hacklab\\Java\\Java 2\\Galgje");
    try {
      File myObj = new File("C:\\Users\\Peter\\Documents\\Hacklab\\Java\\Java 2\\Galgje\\test\\woordlijst.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        wordlist.add(data);
      }
      myReader.close();
      Random rand = new Random();
      int upperbound = wordlist.size();
      wordchoice = wordlist.get(rand.nextInt(upperbound));
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return wordchoice;
  }

//  // Method to transform a string into a char array // not used, found a workaround for UpdateImage method
//  public static char[] StringToCharArray(String word){
//    char[] charArray = new char[word.length()];
//    for (int i = 0; i < word.length(); i++){
//      charArray[i] = word.charAt(i);
//    }
//    return charArray;
//  }

  public static String UpdateImage(@NotNull String word, String guessedLetters) {
  String image = "";
    for(int i = 0; i < word.length(); i++) {
        if (guessedLetters.contains(Character.toString(word.charAt(i)))) {
          image += Character.toUpperCase(word.charAt(i)) + " ";
        }
        else {
          image += ". ";
        }
      }
    return image;
  }

  public static int CountCorrectLetters(@NotNull String word, String guessedLetters){
    int count = 0;
    for (int i = 0; i < word.length(); i++) {
      if (guessedLetters.contains(Character.toString(word.charAt(i)))) {
        count += 1;
      }
    }
    return count;
  }

// not working as intended in (String + ..) return
//  public static char @NotNull [] GenerateLetterList(@NotNull String letters) {
//    char[] letterList = new char[letters.length()];
//    for (int i = 0; i < letters.length(); i++) {
//      letterList[i] = Character.toUpperCase(letters.charAt(i));
//    }
//    return letterList;
//  }

  public static String GenerateLetterString(String letters){
    String letterString = "";
    for (int i = 0; i < letters.length(); i++){
      if (i == 0){
        letterString += "[";
      }
      letterString += Character.toUpperCase(letters.charAt(i));
      if (i < letters.length() - 1) {
        letterString += ", ";
      }
      else {
        letterString += "]";
      }
    }
    return letterString;
  }

  public static String GenerateHangman(int numberOfMistakes) {
    String hangman = "";
    if (numberOfMistakes == 0) {
      hangman = ""; }
    else if (numberOfMistakes ==1) {
      hangman="\n\n\n\n _ _"; }
    else if (numberOfMistakes ==2) {
      hangman = "|\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes ==3) {
      hangman = "____\n|\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes ==4) {
      hangman = "____\n|/\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes ==5) {
      hangman = "____\n|/ |\n|\n|\n|\n|_ _"; }
    else if (numberOfMistakes ==6) {
      hangman = "____\n|/ |\n|  O\n|\n|\n|_ _"; }
    else if (numberOfMistakes ==7) {
      hangman = "____\n|/ |\n|  O\n|  |\n|\n|_ _"; }
    else if (numberOfMistakes ==8) {
      hangman = "____\n|/ |\n|  O\n| /|\n|\n|_ _"; }
    else if (numberOfMistakes ==9) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n|\n|_ _"; }
    else if (numberOfMistakes ==10) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n| /\n|_ _"; }
    else if (numberOfMistakes ==11) {
      hangman = "____\n|/ |\n|  O\n| /|\\\n| / \\\n|_ _"; }

    return hangman;
  }

  public static boolean CheckInput(String input){
   if (input.length() == 0){
     System.out.println("Je hebt niets ingetypt!");
     return false;
   }
   else if (input.length() > 1){
     System.out.println("Je hebt meer dan 1 teken ingetypt!");
     return false;
   }
   else if (Character.isAlphabetic(input.charAt(0))){
     System.out.println("Je hebt een '" + Character.toUpperCase(input.charAt(0)) + "' ingevuld.");
     return true;
   }
   else {
     System.out.println("Je hebt geen letter ingetypt");
     return false;
   }
  }

  public static int CheckWord(String word){
    int numberOfDifferentLetters = 0;
    String letterList = "";
    for (int i = 0; i < word.length(); i++){
      if (! letterList.contains(Character.toString(word.charAt(i)))) {
        letterList += Character.toString(word.charAt(i));
        numberOfDifferentLetters += 1;
      }
    }
    return numberOfDifferentLetters;
  }
  public static void Hangman(boolean hardMode){
    String mysteryWord = WordChoice();
    while (CheckWord(mysteryWord) > 16){
      mysteryWord = WordChoice();
    }

    System.out.println("Welkom bij galgje! Je hebt ruimte voor maximaal 10 foute gokken. Succes!");
    String image = UpdateImage(mysteryWord, "");
    System.out.println("Het woord dat je probeert te raden is: " + image);
    int numberOfMistakes = 0;
    String guessedLetters = "";
    int numberOfGuessedLetters = 0;

    if (!hardMode) {
      System.out.println(
          "Je krijgt een overzicht van je gegokte letters na elke gok. Dubbele gokken tellen niet als fout.");
    }
    else {
      System.out.println(
          "Je speelt in hard mode, je krijgt geen overzicht van gegokte letters en dubbele gokken tellen als fout.");
    }
    Scanner myInput = new Scanner(System.in);
    while ((numberOfMistakes < 11) & (numberOfGuessedLetters < mysteryWord.length())) {
      System.out.println("Typ een enkele letter om mee te raden: ");
      String guess = myInput.nextLine();
      while (!CheckInput(guess)) {
        System.out.println(
            "Verkeerde Input[!] - > Probeer nogmaals een enkele letter in te typen: ");
        guess = myInput.nextLine();
      }
      guess = guess.toLowerCase();

      if (mysteryWord.contains(guess)) {
        if (!guessedLetters.contains(guess)) {
          guessedLetters += guess;
          image = UpdateImage(mysteryWord, guessedLetters);
          System.out.println(
              "Goed gegokt. Deze letter zit in het woord. Dit is je tussenstand: " + image);
          System.out.println(GenerateHangman(numberOfMistakes));
          if (!hardMode) {
            System.out.println(
                "Deze letters heb je gegokt: " + GenerateLetterString(guessedLetters));
          }
            numberOfGuessedLetters = CountCorrectLetters(mysteryWord, guessedLetters);
        } else {
          if (!hardMode) {
            System.out.println("Deze letter heb je al gegokt. Probeer een andere letter.");
            System.out.println("Deze letters heb je gegokt: " + GenerateLetterString(guessedLetters));
            System.out.println("Hier is je overzicht: " + image);
          } else {
            System.out.println("Deze letter heb je al gegokt. Maar hij telt wel als fout.");
            System.out.println("Dit is je tussenstand: " + image);
            numberOfMistakes += 1;
            System.out.println(GenerateHangman(numberOfMistakes));
          }
        }
      } else {
        if (!guessedLetters.contains(guess)) {
          System.out.println(
              "Helaas! Deze letter zit niet in het woord. Dit is je tussenstand: " + image);
          numberOfMistakes += 1;
          System.out.println(GenerateHangman(numberOfMistakes));
          guessedLetters += guess;
          if (!hardMode) {
            System.out.println("Deze letters heb je gegokt: " + GenerateLetterString(guessedLetters));
          }
        } else {
          if (!hardMode) {
            System.out.println("Deze letter heb je al gegokt. Probeer een andere letter.");
            System.out.println("Deze letters heb je gegokt: " + GenerateLetterString(guessedLetters));
            System.out.println("Hier is je overzicht: " + image);
          } else {
            System.out.println("Deze letter heb je al gegokt. Maar hij telt wel als fout.");
            System.out.println("Dit is je tussenstand: " + image);
            numberOfMistakes += 1;
            System.out.println(GenerateHangman(numberOfMistakes));
          }
        }
      }
    }

    if (numberOfGuessedLetters == mysteryWord.length()) {
    System.out.println("Gefeliciteerd! Je hebt het woord geraden! Het woord was '" + mysteryWord + "'.");
    }
    else {
        System.out.println("Game Over! Je hebt het woord niet geraden... Het juiste woord was '" + mysteryWord
            + "'. Gelukkig is het maar een spelletje ;)");
    }
  }
  public static void main(String[] args) {
        Hangman(false);
        Hangman(true);
  }
}

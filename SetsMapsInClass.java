import java.util.*;
import java.io.*;

public class SetsMapsInClass {

  public static void main(String[] args) throws FileNotFoundException  {
    PrintStream p = new PrintStream("testing.txt");
    for(int i = 0; i < 50; i++) { p.println("hi"); }
    p.println("bye");

    numPerWord("testing.txt");
  }
  
//works :)
  public static void numPerWord(String pathname) throws FileNotFoundException {
  //Use 2 ArrayLists, 1st would be the words, parallel 2nd would be count per word
  ArrayList<String> wordList = new ArrayList<>();
  ArrayList<Integer> numWords = new ArrayList<>();
  File f = new File(pathname);
  Scanner sc = new Scanner(f);

  while(sc.hasNext()) {
    boolean hasWord = true;
    String nextWord = sc.next();
    nextWord = cleanUp(nextWord);
    int index = 0;
    for(int i = 0; i < wordList.size(); i++) {
      if(wordList.get(i).equals(nextWord)) {
        hasWord = false;
        index = i;
      }
    }
      if(hasWord) {
        wordList.add(nextWord);
        numWords.add(1);
      }
      if(!hasWord) {
        numWords.set(index, numWords.get(index) + 1);
      }
    }
  for(int i = 0; i < wordList.size(); i++) {
    System.out.println(wordList.get(i) + ": " + numWords.get(i) + " occurences");
  }
  }
  public static String cleanUp(String word) {
    word = word.toLowerCase();  //force word to be in lowercase.
    word = word.replaceAll("[^A-Za-z]+", "");
    return word;
  }
}

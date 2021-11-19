package ga.seminar;
//Gradescope: submit your code with only what your function wants you to do, no extra print statements
import java.util.*;
import java.io.*;

public class SetsAndMaps {
  public static void main(String[] args) {
    //testing maxLength()
    Set<String> fruits = new HashSet<>();
    fruits.add("banana");
    fruits.add("durian");
    fruits.add("grape");
    fruits.add("watermelon");
    System.out.println(maxLength(fruits));

    //testing flip()
    Map<Integer, String> siblingAges = new HashMap<>();
    siblingAges.put(20, "Robert");
    siblingAges.put(20, "Jeffrey");
    siblingAges.put(17, "Whitney");
    siblingAges.put(10, "Rusty");
    siblingAges.put(8, "Ruggles");
    Map<String, Integer> sibAgesFlipped = flip(siblingAges);
    System.out.println(sibAgesFlipped);

    //testing mostCommon()
    sibAgesFlipped.put("RobTest", 20);
    sibAgesFlipped.put("WhitTest", 17);
    System.out.println(mostCommon(sibAgesFlipped));
  }

  /*Takes a set of strings as a parameter and returns the length of the longest
  string in the set. If the parameter is an empty set, your method should return 0.*/
  public static int maxLength(Set<String> stringy) {
    Iterator<String> it = stringy.iterator();
    String testing = "";
    int length = 0;
    int max = 0;

    while(it.hasNext()) {
      testing = it.next();
      length = testing.length();
      if(length > max) {
        max = length;
      }
    }

    return length;
  }

  /*Accepts a map from integers to strings as a parameter and returns a new map of strings to
integers that is the ”flipped” version of the original. The flipped map is defined here to be a
new map that uses the values from the original as its keys and the keys from the original as its
values. If original Map has a repeat value, only one is kept as a key in the flipped Map. */
  public static Map<String, Integer> flip(Map<Integer, String> unflipped) {
    Set<Integer> keys = unflipped.keySet();
    Map<String, Integer> flipped = new HashMap<>();

    for(Integer key: keys) {
      flipped.put(unflipped.get(key), key);
    }

    return flipped;
  }
  /*Accepts a map whose keys are strings and whose values are integers as a parameter and returns
the integer value that occurs the most times in the map. If there is a tie, return the smaller
integer value. If the map is empty, throw an exception. (if(condition) {throw new Exception()}
      Exception = general exception, if you want a specific one say throw ExceptionSubclass, like IllegalArgumentException()

Let’s say the map contains mappings from names (strings) to birthday dates (integers). Your
method would return the most frequently occurring birthdate.

e.g., map m contains the following:

{Pam=2, Jim=17, Dwight=6, Karen=6, Michael=6, Angela=6, Kevin=17}

One person was born on the 2nd (Pam), two people were born on the 17th (Kevin and Jim),
and four people were born on the 6th (Dwight, Karen, Michael, and Angela). So calling
mostCommon(m) should return 6 because four people were born on the 6th.

If there is a tie (two or more common dates that occur the same number of times), return the
first date among them. For example, if we added another two pairs of {Kelly=17, Holly=17}
to the map above, there would now be a tie of four people born on the 17th (Jim, Kevin, Kelly,
and Holly) and four people born on the 6th (Dwight, Karen, Michael, and Angela). So the call
mostCommon(m) would now return 6 because 6 is the smaller of the most common dates.*/
  public static int mostCommon(Map<String, Integer> m) {
    //Collection<type> name = m.values(); creates a Collection view of the values in m
      //if a value appears twice, it will show up twice!
    Collection<Integer> mValues = m.values();
    Map<Integer, Integer> numOfEachValue = new HashMap<>();
    Collection<Integer> numOEVValues = numOfEachValue.values();
    ArrayList<Integer> valuesToReturn = new ArrayList<>();
    int max = 0;
    int min = 0;

    //fill numOfEachValue with each value in m, 0 (will eventually be the number of occurences of each value)
    for(Integer value : mValues) {
      //will automatically make it so there are no repeats
      numOfEachValue.put(value, 0);
    }

    //incrementing numOfEachValue by 1 everytime a value shows up in mValues, which is a Collection of all the values including repeats
    for(Integer value: mValues) {
      numOfEachValue.replace(value, numOfEachValue.get(value) + 1);
    }


    //finds the largest value in numOEVValues, aka the largest value of occurences of a number in m
    for(Integer value: numOEVValues) {
      if(value > max) { max = value; }
    }

    //Set with the unique values from Map m/keys from numOfEachValue
    Set<Integer> uniqueMValues = numOfEachValue.keySet();
    /*Goes through this Set and sees if any keys in this set, which are the unique values from Map m,
  have the occurence value of max. If yes, adds the keys from numOfEachValue to valuesToReturn */
    for(Integer mValue_numOfEachValueKey : uniqueMValues) {
      if(numOfEachValue.get(mValue_numOfEachValueKey) == max) {
        valuesToReturn.add(mValue_numOfEachValueKey);
      }
    }

    System.out.println(valuesToReturn);

    //If only one value has max occurences, return it
    if(valuesToReturn.size() == 1) { return valuesToReturn.get(0); }
    //Else, go through and find the smallest value that had max occurences, return it
    else {
      min = valuesToReturn.get(0); //setting min to the first value in valuesToReturn as a comparison point
      for(Integer i : valuesToReturn) {
        if(i < min) { min = i; }
      }
      return min;
    }
 }

}

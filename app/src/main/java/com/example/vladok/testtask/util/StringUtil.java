package com.example.vladok.testtask.util;

/**
 * It provides methods that assist in working with strings.
 */
public class StringUtil {
    /**
     * Cuts input string to fixed count spaces or fixed count chars.
     *
     * @param argString      input string
     * @param argCountSpaces fixed count spaces
     * @param argCountChars  fixed count chars
     * @return the specified substring
     */
    public static String cutStrToCountSpacesOrCountChars(String argString, int argCountSpaces, int argCountChars) {
        int countSpaces = 0;
        int countChar = 0;
        //converts string to char array
        char[] chArray = argString.toCharArray();
        //iterates through all the elements of the char array
        for (int i = 0; i < chArray.length; i++) {
            //if current char of the char array is a space , consequently increments space counts
            if (chArray[i] == ' ')
                countSpaces++;
            //while countSpaces and countChar less than input arguments , saves countChar and goes to next iteration of the cycle
            if (countSpaces < argCountSpaces && i < argCountChars) {
                countChar = i;
                continue;
                //else saves countChar and breaks from cycle;
            } else {
                countChar = i;
                break;
            }
        }
        String outString = argString.substring(0, countChar + 1);
        return outString;
    }
}


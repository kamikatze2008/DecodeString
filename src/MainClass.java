import javafx.util.Pair;

import java.util.Stack;

public class MainClass {

    public static void main(String[] args) {
        String str = "3[a2[c]]";
        System.out.println(decodeString(str));
    }

    public static String decodeString(String s) {
        int strLength = s.length();
        if (strLength <= 1) {
            return s;
        }
        int counter = 1;
        int currentIdx = 0;

        Stack<Pair<StringBuilder, Integer>> subStringStack = new Stack<>();
        StringBuilder subString = new StringBuilder();
        boolean isStillDigit = false;

        char currentChar;
        while (currentIdx < strLength) {
            currentChar = s.charAt(currentIdx++);
            if (Character.isDigit(currentChar)) {
                if (isStillDigit) {
                    counter = counter * 10 + currentChar - '0';
                } else {
                    subStringStack.push(new Pair<>(subString, counter));
                    subString = new StringBuilder();
                    counter = currentChar - '0';
                    isStillDigit = true;
                }
            } else if (Character.isLetter(currentChar)) {
                subString.append(currentChar);
            } else if ('[' == currentChar) {
                isStillDigit = false;
            } else if (']' == currentChar) {
                String tempSubString = subString.toString();
                if (subStringStack.empty()) {
                    for (int i = 0; i < counter - 1; i++) {
                        subString.append(tempSubString);
                    }
                    counter = 1;
                } else {
                    Pair<StringBuilder,Integer> pair = subStringStack.pop();
                    subString = pair.getKey();
                    for (int i = 0; i < counter; i++) {
                        subString.append(tempSubString);
                    }
                    counter = pair.getValue();
                }
            }

        }
        return subString.toString();
    }
}

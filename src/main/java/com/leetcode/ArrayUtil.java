package com.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ArrayUtil {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> valueMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueMap.putIfAbsent(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int leftOver = target - nums[i];
            if (valueMap.containsKey(leftOver) && valueMap.get(leftOver) != i) {
                return new int[]{i, valueMap.get(leftOver)};
            }
        }

        return null;
    }

    public int myAtoi(String s) {

        if (s == null) {
            return 0;
        }

        Deque<Integer> digits = new LinkedList<>();

        String trimmed = s.trim();

        if (trimmed.length() == 0) {
            return 0;
        }

        int startIndex = 0;
        int sign = numberSign(trimmed.charAt(0));

        if (sign != 0) {
            startIndex = 1;
        } else {
            sign = 1;
        }

        for (int i = startIndex; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);
            if (!validDigit(c)) {
                break;
            }
            digits.push((int) c - 48);
        }

        long result = 0;
        int exponent = 0;
        while (digits.size() != 0) {
            result += digits.pop() * Math.pow(10, exponent);
            exponent++;
        }

        result = sign * result;

        if (result > Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            result = Integer.MIN_VALUE;
        }

        return (int) result;
    }

    public int numberSign(char c) {
        if (c == '-') {
            return -1;
        } else if (c == '+') {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean validDigit(char c) {
        int ascii = (int) c;
        if (ascii > 47 && ascii < 58) {
            return true;
        }

        return false;
    }

    public boolean isPalindrome(String s) {
        Deque<Character> characters = new LinkedList<>();

        for (char c : s.toCharArray()) {
            char cLowerCase = Character.toLowerCase(c);
            if ((cLowerCase >= 'a' && cLowerCase <= 'z') || (cLowerCase >= '0' && cLowerCase <= '9')) {
                characters.addLast(cLowerCase);
            }
        }

        while (characters.size() > 1) {
            char first = characters.pollFirst();
            char last = characters.pollLast();

            if (first != last) {
                return false;
            }
        }

        return true;
    }

    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[s.length - i - 1];
            s[s.length - i - 1] = s[i];
            s[i] = temp;
        }
    }

    public String reverseWords(String s) {
        Deque<String> wordQueue = new LinkedList<>();
        StringBuilder word = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (word.length() > 0) {
                    wordQueue.push(word.toString());
                    word = new StringBuilder();
                }
                continue;
            }
            word.append(c);
        }

        if (word.length() > 0) {
            wordQueue.push(word.toString());
        }

        StringBuilder result = new StringBuilder();
        while (wordQueue.size() > 0) {
            result.append(wordQueue.pop());
            result.append(' ');
        }

        return result.toString().trim();

    }

    public static void main(String[] args) {
        ArrayUtil util = new ArrayUtil();
        int[] nums = new int[]{2, 7, 11, 15};
        int[] sum = util.twoSum(nums, 9);

        nums = new int[]{3, 2, 4};
        sum = util.twoSum(nums, 6);

        System.out.printf("Sum: %s\n", sum);

        String s1 = "42";
        System.out.printf("Output: %s\n", util.myAtoi(s1));

        String s2 = "   -42";
        System.out.printf("Output: %s\n", util.myAtoi(s2));

        String s3 = "4193 with words";
        System.out.printf("Output: %s\n", util.myAtoi(s3));

        String s4 = "words and 987";
        System.out.printf("Output: %s\n", util.myAtoi(s4));

        String s5 = "-91283472332";
        System.out.printf("Output: %s\n", util.myAtoi(s5));

        String s6 = " ";
        System.out.printf("Output: %s\n", util.myAtoi(s6));

        String s = "A man, a plan, a canal: Panama";
        System.out.println("Is a valid palindrome: " + util.isPalindrome(s));

        String sInput2 = "0P";
        System.out.println("Is a valid palindrome: " + util.isPalindrome(sInput2));

        char[] sList = new char[]{'h', 'e', 'l', 'l', 'o'};
        util.reverseString(sList);

        String word = "the sky is blue";
        System.out.println("Reserved: " + util.reverseWords(word));

        String word2 = "  hello world  ";
        System.out.println("Reserved: " + util.reverseWords(word2));


    }

}

package medium;

import java.util.HashSet;
import java.util.Set;

public class StringSolution {

    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));
        // System.out.println(solution.longestPalindrome("pwwkew"));
        // System.out.println(solution.longestPalindrome("ccaabb"));

    }

    /**
     * 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = expandMaxLength(s, i, i);
            int even = expandMaxLength(s, i, i + 1);
            int len = Math.max(odd, even);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandMaxLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 无重复字符最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int i = 0, maxLength = 0;
        Set<Character> exists = new HashSet<>();
        exists.add(s.charAt(0));
        for (int j = 1; j < s.length(); j++) {
            if (exists.contains(s.charAt(j))) {
                maxLength = Math.max(maxLength, j - i);
                for (int k = i; k < j; k++) {
                    exists.remove(s.charAt(k));
                    if (s.charAt(k) == s.charAt(j)) {
                        i = k + 1;
                        break;
                    }
                }
            } else {
                maxLength = Math.max(maxLength, j - i + 1);
            }
            exists.add(s.charAt(j));
        }
        return maxLength;
    }
}

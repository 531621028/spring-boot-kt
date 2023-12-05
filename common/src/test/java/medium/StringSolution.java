package medium;

import java.util.HashSet;
import java.util.Set;

public class StringSolution {

    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring("au"));

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

package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringSolution {

    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
        // System.out.println(solution.longestPalindrome("babad"));
        // System.out.println(solution.longestPalindrome("cbbd"));
        // // System.out.println(solution.longestPalindrome("pwwkew"));
        // // System.out.println(solution.longestPalindrome("ccaabb"));
        // System.out.println(solution.longestPalindrome("babad"));
        // System.out.println(Arrays.toString(solution.letterCombinations("234").toArray()));
        System.out.println(Arrays.toString(solution.generateParenthesis(2).toArray()));
    }


    /**
     * 括号生成
     */
    public List<String> generateParenthesis(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }
        List<String> ans = new ArrayList<>();
        backTrack(ans, new StringBuilder(), 0, 0, n);
        return ans;

    }

    public void backTrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            backTrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backTrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 电话号码的组合
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() < 1) {
            return Collections.emptyList();
        }
        Map<String, List<String>> phoneNumber = phoneNumber();
        List<String> result = phoneNumber.get(String.valueOf(digits.charAt(0)));
        for (int i = 1; i < digits.length(); i++) {
            List<String> start = new ArrayList<>(result);
            result = new ArrayList<>();
            String number = String.valueOf(digits.charAt(i));
            List<String> chars = phoneNumber.getOrDefault(number, Collections.emptyList());
            for (String s : start) {
                result.addAll(composeNextChar(chars, s));
            }
        }
        return result;
    }

    private List<String> composeNextChar(List<String> list, String nextChar) {
        if (list.isEmpty()) {
            return Collections.singletonList(nextChar);
        }
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(nextChar + s);
        }
        return result;
    }


    private Map<String, List<String>> phoneNumber() {
        Map<String, List<String>> phoneNumber = new HashMap<>();
        phoneNumber.put("2", Arrays.asList("a", "b", "c"));
        phoneNumber.put("3", Arrays.asList("d", "e", "f"));
        phoneNumber.put("4", Arrays.asList("g", "h", "i"));
        phoneNumber.put("5", Arrays.asList("j", "k", "l"));
        phoneNumber.put("6", Arrays.asList("m", "n", "o"));
        phoneNumber.put("7", Arrays.asList("p", "q", "r", "s"));
        phoneNumber.put("8", Arrays.asList("t", "u", "v"));
        phoneNumber.put("9", Arrays.asList("w", "x", "y", "z"));
        return phoneNumber;
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

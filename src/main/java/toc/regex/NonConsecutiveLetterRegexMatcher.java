package toc.regex;

import java.util.regex.Pattern;

public class NonConsecutiveLetterRegexMatcher extends RegexMatcher {
    private final Pattern nonConsecutiveLetterPattern;
    private final char letter;
    private final int limit;
    private boolean isNonConsecutive;

    public NonConsecutiveLetterRegexMatcher(char letter, int limit) {
        this.letter = letter;
        this.limit = limit;
        String hasNoConsecutiveLetterRegex = "^(?!.*[" + letter + "]{" + limit + "}).*$";
        this.nonConsecutiveLetterPattern = Pattern.compile(hasNoConsecutiveLetterRegex, Pattern.CASE_INSENSITIVE);
    }

    @Override
    protected void doMatch(String str) {
        this.isNonConsecutive = this.nonConsecutiveLetterPattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s\n", this.isNonConsecutive ? "valid string" : ("invalid string, has " + this.limit + " consecutive " + this.letter + "’s"));
    }
}

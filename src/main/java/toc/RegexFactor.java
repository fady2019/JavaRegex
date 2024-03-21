package toc;

import toc.regex.*;

public class RegexFactor {
    private final Integer regexId;

    public RegexFactor(Integer regexId) {
        this.regexId = regexId;
    }

    public RegexMatcher getRegexMatcher() {
        return switch (this.regexId) {
            case 1 -> new EmailRegexMatcher();
            case 2 -> new PhoneNumberRegexMatcher();
            case 3 -> new DateRegexMatcher();
            case 4 -> new IPAddressRegexMatcher();
            case 5 -> new CppVariableMatcher();
            case 6 -> new NonConsecutiveLetterRegexMatcher('b', 3);
            case 7 -> new OddAsAndBsMatcher();
            case 8 -> new WordsWithMultipleOfNumberRegexMatcher(3);
            case 9 -> new URLsRegexMatcher();
            case 10 -> new SimpleMathExprRegexMatcher();
            default -> null;
        };
    }
}

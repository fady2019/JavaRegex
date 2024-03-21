package toc.regex;

import java.util.regex.Pattern;

public class DateRegexMatcher extends RegexMatcher {
    private final String format1 = "^(\\d{4}[-/]\\d{2}[-/]\\d{2})$"; // YYYY/MM/DD | YYYY-MM-DD
    private final String format2 = "^(\\d{1,2}[-/]\\d{2}[-/]\\d{4})$"; // DD/MM/YYYY | D/MM/YYYY | DD-MM-YYYY | D-MM-YYYY
    private final String format3 = "^(\\d{2}[-/]\\d[-/]\\d{4})$"; // DD/M/YYYY | DD-M-YYYY
    private final String dateRegex = format1 + "|" + format2 + "|" + format3;
    private final Pattern datePattern = Pattern.compile(dateRegex);
    private boolean isValidDate;

    @Override
    protected void doMatch(String str) {
        this.isValidDate = this.datePattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s date\n", this.isValidDate ? "valid" : "invalid");
    }
}

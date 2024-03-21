package toc.regex;

import java.util.regex.Pattern;

public class EmailRegexMatcher extends RegexMatcher {
    private final String emailFormat = "[a-zA-Z0-9_]+([.-][a-zA-Z0-9_])*";
    private final String emailDomainFormat = "[a-zA-Z]+(\\.[a-zA-Z]{2,})+";
    private final String emailRegex = "^(" + emailFormat + "@" + emailDomainFormat + ")$";
    private final Pattern emailPattern = Pattern.compile(emailRegex);
    private boolean isValidEmail;

    @Override
    protected void doMatch(String str) {
        this.isValidEmail = this.emailPattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s email\n", this.isValidEmail ? "valid" : "invalid");
    }
}

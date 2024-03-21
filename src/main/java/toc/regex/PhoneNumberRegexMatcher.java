package toc.regex;

import java.util.regex.Pattern;

public class PhoneNumberRegexMatcher extends RegexMatcher {
    private final String format1 = this.getPhoneNumberBasicFormat("");
    private final String format2 = this.getPhoneNumberBasicFormat("\\.");
    private final String format3 = this.getPhoneNumberBasicFormat("-");
    private final String format4 = this.getPhoneNumberBasicFormat(" ");
    private final String format5 = "^(\\(\\d{3}\\)-\\d{3}-\\d{4})$";
    private final String phoneNumberRegex = format1 + "|" + format2 + "|" + format3 + "|" + format4 + "|" + format5;
    private final Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);
    private boolean isValidPhoneNumber;

    private String getPhoneNumberBasicFormat(String sep) {
        return "^(\\d{3}" + sep + "\\d{3}" + sep + "\\d{4})$";
    }

    @Override
    protected void doMatch(String str) {
        this.isValidPhoneNumber = this.phoneNumberPattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s phone number\n", this.isValidPhoneNumber ? "valid" : "invalid");
    }
}

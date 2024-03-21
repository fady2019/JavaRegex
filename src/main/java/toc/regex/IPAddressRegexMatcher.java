package toc.regex;

import java.util.regex.Pattern;

public class IPAddressRegexMatcher extends RegexMatcher {
    private final String oneDigitIPFormat = "([0-9])";
    private final String towDigitsIPFormat = "([1-9][0-9])";
    private final String threeDigitsIPFormat = "(1[0-9][0-9]|2[0-5][0-5])";
    private final String ipAddressFieldFormat = "(" + oneDigitIPFormat + "|" + towDigitsIPFormat + "|" + threeDigitsIPFormat + ")";
    private final String ipAddressRegex = ipAddressFieldFormat + "\\." + ipAddressFieldFormat + "\\." + ipAddressFieldFormat + "\\." + ipAddressFieldFormat;
    private final Pattern ipAddressPattern = Pattern.compile(ipAddressRegex);
    private boolean isValidIPAddress;

    @Override
    protected void doMatch(String str) {
        this.isValidIPAddress = this.ipAddressPattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s IP address\n", this.isValidIPAddress ? "valid" : "invalid");
    }
}
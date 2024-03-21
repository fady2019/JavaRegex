package toc.regex;

import java.util.regex.Pattern;

public class CppVariableMatcher extends RegexMatcher {
    private final String cppVariableRegex = "^([a-zA-Z_][a-zA-Z0-9_]*)$";
    private final Pattern cppVariablePattern = Pattern.compile(cppVariableRegex);
    private boolean isValidCppVariable;

    @Override
    protected void doMatch(String str) {
        this.isValidCppVariable = this.cppVariablePattern.matcher(str).matches();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s C++ variable name\n", this.isValidCppVariable ? "valid" : "invalid");
    }
}

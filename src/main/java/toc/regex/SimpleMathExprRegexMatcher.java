package toc.regex;

import java.util.regex.Pattern;

public class SimpleMathExprRegexMatcher extends RegexMatcher {
    private final String mathExprNumsFormat = "-?[0-9]+(\\.[0-9]+)?";
    private final String mathExprVarFormat = "[a-zA-Z]+[0-9]*";
    private final String mathExprSidePartFormat = "((" + mathExprVarFormat + ")|((" + mathExprNumsFormat + ")(" + mathExprVarFormat + ")?))";
    private final String mathExprSideFormat = mathExprSidePartFormat + "([-+/*]" + mathExprSidePartFormat + ")*";
    private final String mathExprRegex = "^(" + mathExprSideFormat + "=" + mathExprSideFormat + ")$";
    private final Pattern mathExprPattern = Pattern.compile(mathExprRegex);
    private final Pattern mathExprVarPattern = Pattern.compile(mathExprVarFormat);
    private boolean isValidMathExpr;

    @Override
    protected void doMatch(String str) {
        this.isValidMathExpr = this.mathExprPattern.matcher(str).matches() && this.mathExprVarPattern.matcher(str).find();
    }

    @Override
    protected String doGetMatchingResult() {
        return String.format("%s mathematical expression\n", this.isValidMathExpr ? "valid" : "invalid");
    }
}

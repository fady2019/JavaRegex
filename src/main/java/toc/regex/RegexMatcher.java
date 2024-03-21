package toc.regex;

public abstract class RegexMatcher {
    private boolean isMatchCalled;

    public RegexMatcher() {
        this.isMatchCalled = false;
    }

    protected abstract void doMatch(String str);

    protected abstract String doGetMatchingResult();

    public void match(String str) {
        this.doMatch(str);
        this.isMatchCalled = true;
    }

    public String getMatchingResult() {
        if (!this.isMatchCalled) {
            throw new RuntimeException("Make sure to call match first");
        }

        return this.doGetMatchingResult();
    }
}

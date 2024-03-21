package toc.regex;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OddAsAndBsMatcher extends RegexMatcher {
    private final String oddAsAndBsRegex = "((ab|ba){2})*" + "(aa|bb)*" + "(ab|ba)" + "((ab|ba){2})*" + "(aa|bb)*" + "((ab|ba){2})*";
    private final Pattern oddAsAndBsPatterns = Pattern.compile(oddAsAndBsRegex);
    private String lastMatchedString;
    private final List<Triplet<String, Integer, Integer>> matchingResult = new ArrayList<>();

    @Override
    protected void doMatch(String str) {
        Matcher matcher = this.oddAsAndBsPatterns.matcher(str);

        this.matchingResult.clear();

        while (matcher.find()) {
            this.matchingResult.add(Triplet.with(matcher.group(), matcher.start(), matcher.end()));
        }

        lastMatchedString = str;
    }

    @Override
    protected String doGetMatchingResult() {
        StringBuilder matchingResultAsString = new StringBuilder();

        matchingResultAsString.append("*").append(this.lastMatchedString).append("*\n");

        if (this.matchingResult.isEmpty()) {
            matchingResultAsString.append("No substring matches\n");
            return matchingResultAsString.toString();
        }

        matchingResultAsString.append("number of matched substrings: ").append(this.matchingResult.size()).append("\n");

        this.matchingResult.forEach(res -> {
            matchingResultAsString.append("matched substring: ").append(res.getValue0()).append("\n");
            matchingResultAsString.append("start index: ").append(res.getValue1()).append(", end index: ").append(res.getValue2()).append("\n");
        });

        return matchingResultAsString.toString();
    }
}

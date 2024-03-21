package toc.regex;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsWithMultipleOfNumberRegexMatcher extends RegexMatcher {
    private final Pattern wordsWithMultipleOfNumberPattern;
    private String lastMatchedString;
    private final List<Triplet<String, Integer, Integer>> matchingResult = new ArrayList<>();

    public WordsWithMultipleOfNumberRegexMatcher(int multipleOf) {
        String wordsWithMultipleOfNumberRegex = "(\\b[a-zA-Z]{" + multipleOf + "}\\b)+";
        this.wordsWithMultipleOfNumberPattern = Pattern.compile(wordsWithMultipleOfNumberRegex);
    }

    @Override
    protected void doMatch(String str) {
        Matcher matcher = this.wordsWithMultipleOfNumberPattern.matcher(str);

        this.matchingResult.clear();

        while (matcher.find()) {
            this.matchingResult.add(Triplet.with(matcher.group(), matcher.start(), matcher.end()));
        }

        this.lastMatchedString = str;
    }

    @Override
    protected String doGetMatchingResult() {
        StringBuilder matchingResultAsString = new StringBuilder();

        matchingResultAsString.append("*").append(this.lastMatchedString).append("*\n");

        if (this.matchingResult.isEmpty()) {
            matchingResultAsString.append("No word matches\n");
            return matchingResultAsString.toString();
        }

        matchingResultAsString.append("number of matched words: ").append(this.matchingResult.size()).append("\n");

        this.matchingResult.forEach(res -> {
            matchingResultAsString.append("matched word: ").append(res.getValue0()).append("\n");
            matchingResultAsString.append("start index: ").append(res.getValue1()).append(", end index: ").append(res.getValue2()).append("\n");
        });

        return matchingResultAsString.toString();
    }
}

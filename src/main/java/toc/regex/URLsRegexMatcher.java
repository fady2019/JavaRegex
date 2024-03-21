package toc.regex;

import org.javatuples.Quartet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLsRegexMatcher extends RegexMatcher {
    private final String urlDomainFormat = "[a-zA-Z]+(\\.[a-zA-Z]{2,})+";
    private final String urlRegex = "\\b((https?://)" + urlDomainFormat + "(/\\S+)*\\b/?)";
    private final Pattern urlPattern = Pattern.compile(urlRegex);
    private String lastFileName;
    private final List<Quartet<String, Integer, Integer, Integer>> matchingResult = new ArrayList<>();

    @Override
    protected void doMatch(String str) {
        try {
            this.matchingResult.clear();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));

            String line;
            int lineNum = 0;

            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = this.urlPattern.matcher(line);

                ++lineNum;

                while (matcher.find()) {
                    matchingResult.add(Quartet.with(matcher.group(), lineNum, matcher.start(), matcher.end()));
                }
            }

            this.lastFileName = Path.of(str).getFileName().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String doGetMatchingResult() {
        StringBuilder matchingResultAsString = new StringBuilder();

        matchingResultAsString.append("*").append(this.lastFileName).append("*\n");

        if (this.matchingResult.isEmpty()) {
            matchingResultAsString.append("No URLs\n");
            return matchingResultAsString.toString();
        }

        matchingResultAsString.append("Number of URLs: ").append(this.matchingResult.size()).append("\n");

        this.matchingResult.forEach(res -> {
            matchingResultAsString.append("URL: ").append(res.getValue0()).append("\n");
            matchingResultAsString.append("Line: ").append(res.getValue1()).append("\n");
            matchingResultAsString.append("start index: ").append(res.getValue2()).append(", end index: ").append(res.getValue3()).append("\n");
        });

        return matchingResultAsString.toString();
    }
}

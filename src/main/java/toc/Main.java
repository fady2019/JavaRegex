package toc;

import java.io.*;

import toc.io.input.FileInputReader;
import toc.io.input.InputReader;
import toc.io.output.FileOutputWriter;
import toc.io.output.OutputWriter;
import toc.regex.RegexMatcher;

public class Main {
    private static final String inputFilePath = "src/main/resources/input.txt";
    private static final String outputFilePath = "src/main/resources/output.txt";

    public static void main(String[] args) {
        try {
            InputReader inputReader = new FileInputReader(new BufferedReader(new FileReader(inputFilePath)));
            OutputWriter outputWriter = new FileOutputWriter(new BufferedWriter(new FileWriter(outputFilePath)));

            String line;

            while ((line = inputReader.readLine()) != null) {
                Integer regexID = Integer.parseInt(line);

                outputWriter.writeLine(regexID + "\n");

                RegexMatcher regexMatcher = new RegexFactor(regexID).getRegexMatcher();

                if (regexMatcher == null) {
                    break;
                }

                while (!(line = inputReader.readLine()).equalsIgnoreCase("end")) {
                    regexMatcher.match(line);
                    outputWriter.writeLine(regexMatcher.getMatchingResult());
                }

                outputWriter.writeLine("x\n");
            }

            inputReader.cleanup();
            outputWriter.cleanup();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
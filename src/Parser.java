import java.util.Objects;
import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

public class Parser {
    String commandName;
    String[] args;

    public Parser() {
        commandName = "";
        args = new String[10];
    }
    public void parse(String input) {
        String[] words = input.split(" ");
        commandName = words[0];
        if (words.length > 1) {
            args = Arrays.copyOfRange(words, 1, words.length);
        }
    }

    public String getCommandName(){
        return commandName;
    }
    public String[] getArgs(){
        return args;
    }
}
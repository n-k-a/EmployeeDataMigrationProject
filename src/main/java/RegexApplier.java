import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegexApplier {
    private final Pattern initialPattern = Pattern.compile("/^[A-Z]$/");
    private final Pattern prefixPattern = Pattern.compile("^[a-zA-Z]+$");
    private final Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`" +
            "{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0" +
            "e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2" +
            "[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\" +
            "x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    public boolean validateName(String name){
        Matcher matcher = prefixPattern.matcher(name);
        return matcher.find();
    }
    public boolean validateEmail(String email){
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
    public boolean validateMiddleInitial(String middleInitial){
        Matcher matcher = prefixPattern.matcher(middleInitial);
        return matcher.find();
    }
}

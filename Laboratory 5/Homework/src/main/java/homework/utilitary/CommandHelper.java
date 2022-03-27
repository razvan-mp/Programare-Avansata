package homework.utilitary;

public class CommandHelper {
    public static boolean typeIsValid(String type) {
        return type.equals("article") || type.equals("book") || type.equals("other");
    }

    public static boolean answerIsYes(String userAnswer) {
        return userAnswer.equalsIgnoreCase("y") || userAnswer.equalsIgnoreCase("yes");
    }
}

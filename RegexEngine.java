import java.util.Scanner;

public class RegexEngine {

    public static boolean compareChar(char regex, char ch) {
        return regex == ch || regex == '.' || regex == '\0';
    }

    public static boolean compareEqStr(String regex, String str) {
        if (regex.isEmpty() || (regex.equals("$") && str.isEmpty())) {
            return true;
        } else if (regex.length() > 1 && regex.charAt(0) == '\\' && !str.isEmpty()) {
            return compareEqStr(regex.substring(2), str.substring(1));
        } else {
            String regex1 = regex.charAt(0) + regex.substring(2);
            if (regex.length() > 1 && regex.charAt(1) == '?' && !str.isEmpty()) {
                return compareEqStr(regex.substring(2), str) || compareEqStr(regex1, str);
            } else if (regex.length() > 1 && regex.charAt(1) == '*' && !str.isEmpty()) {
                return compareEqStr(regex.substring(2), str) || compareEqStr(regex, str.substring(1));
            } else if (regex.length() > 1 && regex.charAt(1) == '+' && !str.isEmpty()) {
                return compareEqStr(regex1, str) || compareEqStr(regex, str.substring(1));
            } else if (str.isEmpty() || !compareChar(regex.charAt(0), str.charAt(0))) {
                return false;
            } else {
                return compareEqStr(regex.substring(1), str.substring(1));
            }
        }
    }

    public static boolean compareStr(String regex, String str) {
        if (str.isEmpty() && !regex.isEmpty()) {
            return false;
        } else if (compareEqStr(regex, str)) {
            return true;
        } else if (regex.charAt(0) == '^') {
            return compareEqStr(regex.substring(1), str);
        } else {
            return compareStr(regex, str.substring(1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split("\\|");
        String regex = inputs[0];
        String str = inputs[1];
        System.out.println(compareStr(regex, str));
    }
}

public class Solution {
    private static int converted(int base, String value) {
        return Integer.parseInt(value, base);
    }

    public static double findSecret(String filePath) {

    }

    public static void main(String[] args) {
        String filePath = "testcase1.json";
        double secret = findSecret(filePath);
    }

}

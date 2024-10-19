import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Solution {

    public static BigInteger converted(String value, int base) {
        return new BigInteger(value, base);
    }

    public static BigInteger lagrangeInterpolation(List<int[]> points) {
        BigInteger result = BigInteger.ZERO;
        int k = points.size();

        for (int i = 0; i < k; i++) {
            BigInteger xi = BigInteger.valueOf(points.get(i)[0]);
            BigInteger yi = BigInteger.valueOf(points.get(i)[1]);
            BigInteger term = yi;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    BigInteger xj = BigInteger.valueOf(points.get(j)[0]);
                    term = term.multiply(xj.negate()).divide(xi.subtract(xj));
                }
            }
            result = result.add(term);
        }

        return result;
    }

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("input.json"));

        JSONObject keys = (JSONObject) jsonObject.get("keys");
        long n = (long) keys.get("n");
        long k = (long) keys.get("k");

        List<int[]> points = new ArrayList<>();

        for (Object key : jsonObject.keySet()) {
            if (key.equals("keys"))
                continue;

            JSONObject valueObj = (JSONObject) jsonObject.get(key);
            int x = Integer.parseInt((String) key);
            int base = Integer.parseInt((String) valueObj.get("base"));
            String value = (String) valueObj.get("value");

            BigInteger y = converted(value, base);

            points.add(new int[] { x, y.intValue() });
        }

        BigInteger constantTerm = lagrangeInterpolation(points);

        System.out.println(constantTerm);
    }
}
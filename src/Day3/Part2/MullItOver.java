package Day3.Part2;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream("D:\\fakultet\\AdventOfCode2024\\src\\Day3\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        AtomicInteger total = new AtomicInteger();
        AtomicBoolean d = new AtomicBoolean(true);

        br.lines().forEach(l -> {
            Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)");
            Matcher matcher = pattern.matcher(l);

            while (matcher.find()) {
                String match = matcher.group();
                if(match.equals("do()"))
                    d.set(true);
                else if(match.equals("don't()"))
                    d.set(false);
                else if (d.get() && match.startsWith("mul("))
                    total.addAndGet(mul(matcher.group(1), matcher.group(2)));
            }
        });

        System.out.println(total);
    }

    public static int mul(String x, String y) {
        int a = Integer.parseInt(x);
        int b = Integer.parseInt(y);

        return a * b;
    }
}

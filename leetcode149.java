import java.util.Map;

public class leetcode149 {
    public int maxPoints(int[][] points) {
        if (points.length == 1)
            return 1;
        // 计数值
        int maxCounter = 0, tempCounter = 0;
        int size = points.length * (points.length - 1) / 2;
        double[][] parameter = new double[size][2];
        int[] counter = new int[points.length];
        // 求参数
        for (int i = 0; i < points.length; i++) {
            for (int j = i; j < points.length; j++) {
                // 计算斜率和截距
                double k = Double.POSITIVE_INFINITY, b = Double.POSITIVE_INFINITY;
                // 两个点其实是一个点
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    k = Double.POSITIVE_INFINITY;
                    b = Double.POSITIVE_INFINITY;
                    counter[i]++;
                    if (counter[i] > maxCounter)
                        maxCounter = counter[i];
                    continue;
                }
                // 非同一个点 但横坐标相同 直线为 x = x1
                else if (points[i][0] == points[j][0]) {
                    b = Double.POSITIVE_INFINITY;
                    k = points[i][0];
                }
                // 非同一个点 但纵坐标相同 直线为 y = y1
                else if (points[i][1] == points[j][1]) {
                    k = 0;
                    b = points[i][1];
                }
                // 是一条既不垂直于y轴 也不垂直于x轴的直线
                else {
                    k = (double)(points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                    b = points[i][1] - k * points[i][0];
                }
                parameter[tempCounter][0] = k;
                parameter[tempCounter][1] = b;
                tempCounter++;
            }
        }

//        System.out.println(parameter[0][0] + " " + parameter[0][1]);

        // 计数 找存在最多的点直线
        for (int i = 0; i < parameter.length; i++) {
            tempCounter = 0;
            if (parameter[i][0] == Double.POSITIVE_INFINITY && parameter[i][0] == Double.POSITIVE_INFINITY)
                continue;
            else if (parameter[i][0] == Double.POSITIVE_INFINITY) {
                for (int j = 0; j < points.length; j++) {
                    if (points[j][1] == parameter[i][1])
                        tempCounter++;
                }
            }
            else if (parameter[i][1] == Double.POSITIVE_INFINITY) {
                for (int j = 0; j < points.length; j++) {
                    if (points[j][0] == parameter[i][0])
                        tempCounter++;
                }
            }
            else {
                for (int j = 0; j < points.length; j++) {
                    if (Math.abs(parameter[i][0] * points[j][0] + parameter[i][1] - points[j][1]) < 0.000001) {
                        tempCounter++;
                    }
                }
            }
            if (tempCounter > maxCounter) {
                maxCounter = tempCounter;
            }
        }
        return maxCounter;
    }

    private String buildSlope(int p, int q) {
        int gcd = computeGcd(p, q);
        return p / gcd + "_" + q / gcd;
    }

    private int computeGcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return computeGcd(q, r);
    }
}

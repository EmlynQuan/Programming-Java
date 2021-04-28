public class leetcode165 {
    public static  int compareVersion(String version1, String version2) {
        String[] sver1 = version1.split("\\.");
        String[] sver2 = version2.split("\\.");
        int minLength = sver1.length > sver2.length ? sver2.length : sver1.length;

        for (int i = 0; i < minLength; i++) {
            int ver1 = Integer.parseInt(sver1[i]);
            int ver2 = Integer.parseInt(sver2[i]);

            if (ver1 > ver2) {
                return 1;
            }
            else if (ver1 < ver2) {
                return -1;
            }
        }
        if (sver1.length > sver2.length) {
            for (int i = minLength; i < sver1.length; i++) {
                if (Integer.parseInt(sver1[i]) != 0)
                    return 1;
            }

        } else if (sver1.length < sver2.length) {
            for (int i = minLength; i < sver2.length; i++) {
                if (Integer.parseInt(sver2[i]) != 0) {
                    return -1;
                }

            }
        }
        return 0;
    }

    public static void main(String args[]) {
        String s1 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
        String s2 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
        compareVersion(s1, s2);
    }
}

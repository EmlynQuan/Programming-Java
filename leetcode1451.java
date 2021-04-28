public class leetcode1451 {
    public static String arrangeWords(String text) {
        String appendStr = "";
        String[] tempStrList = text.split(" ");
        tempStrList[0] = tempStrList[0].toLowerCase();
        System.out.println("start");
        //按照长度排序 （快速排序）
        //quickSort(tempStrList, 0, tempStrList.length-1);

        //按照长度排序 （稳定的冒泡排序）
        for (int i = 0; i < tempStrList.length; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < tempStrList.length - i - 1; ++j) {
                //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (tempStrList[j].length() > tempStrList[j + 1].length()) {        //即这两个相邻的数是逆序的，交换
                    String stemp = tempStrList[j];
                    tempStrList[j] = tempStrList[j + 1];
                    tempStrList[j + 1] = stemp;
                    flag = true;
                }
            }
            if (!flag) break;
            //没有数据交换，数组已经有序，退出排序
        }

        for (int i = 0; i < tempStrList.length; i++) {
            appendStr += tempStrList[i];
            if (i != tempStrList.length - 1)
                appendStr += " ";
        }
        //首字符大写
        appendStr = appendStr.substring(0,1).toUpperCase() + appendStr.substring(1);
        return appendStr;
    }

    /*
    //快速排序
    public static void quickSort (String[] array, int low, int high) {

        if (low >= high)
            return;
        //基准
        int ancher = array[low].length();
        String temp = array[low];
        int left = low;
        int right = high;

        while (low < high) {
            System.out.println("low" + low + "high" + high);
            while (low < high && array[high].length() >= ancher) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low].length() <= ancher) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        System.out.println("pos" + low + "value" + temp);
        //递归
        quickSort(array, left, low-1);
        quickSort(array, low+1, right);
    }
    public static void main(String args[]) {
        String text = "Keep calm and code on";
        arrangeWords(text);
    }
    */
}

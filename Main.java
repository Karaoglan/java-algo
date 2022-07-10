import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
      
        /*
        int actualCount = countOfSplit("xzxzx");


        int expectedCount = 5;
        if (actualCount != expectedCount) {
            throw new RuntimeException("expected: " + expectedCount + ", but func. returns: " + actualCount);
        }
        System.out.println("****correct: " + actualCount);

         */
        //int[][] arr = {{3, 3, 4, 2}, {4, 4}, {4, 0, 3, 3}, {2, 3}, {3, 3, 3}};
        //int[][] expectedArr = {{0, 4}, {1}, {2, 3}};

        /*int[][] arr = {{-5, 2, 3}, {0, 0}, {0}, {-100, 100}};
        int[][] expectedArr = {{0, 1, 2, 3}};

        int[][] actualArr = meanGroup(arr);

        String expStr = Arrays.deepToString(expectedArr);
        String actStr = Arrays.deepToString(actualArr);
        if (!actStr.equals(expStr)) {
            throw new RuntimeException("expected: " + expStr + ", but func. returns: " + actStr);
        }
        System.out.println("****correct: " + actStr);

        */

        //int[] arr = {1, 2, 1, 2, 3};
        //int[] arr = {0, 0, 0};
        //int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        /*int[] arr = {4257, 1663, 2775, 6742, 1228, 6742, 442, 5364, 5364, 2775, 2775, 2775, 442, 4257, 6664, 6742,
        442, 4257, 5364, 1228, 5364, 2477, 5364, 4257, 4257, 5364, 9387, 9387, 9387, 9387, 1663, 1228, 4257, 2477,
            9387, 5364, 5364, 1228, 5364, 2477, 2477, 1228, 9383, 9387, 1663, 2775, 1228, 6742, 1663, 9387, 6664, 442
            , 1663, 1663, 442, 4257, 1663, 1228, 442, 2775, 5364, 4257, 1228, 2477, 6664, 5364, 442, 1663, 1663, 4257,
            4257,  9387, 2477, 2775, 442, 6742, 6664, 2477, 1663, 4257, 6742, 6664, 2775, 2477, 4257, 1228, 4257,
            4257, 2775, 9387, 2477, 2477, 5364, 5364, 6664, 6664, 2477, 1663};
*/
        int[] arr = {1, 2, 3, 3, 3, 2, 4, 1};

        int expected = 4;

        int actual = numberOfContiguousSubArray(arr);

        if (actual != expected) {
            throw new RuntimeException("expected: " + expected + ", but func. returns: " + actual);
        }
        System.out.println("****correct: " + actual);
    }

    static int countOfSplit(String s) {
        System.out.println("count of split");
        String divider = "-";

        List<String> combinationList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String temp1 = "";
            if (1 < i && i < s.length()) {
                temp1 += s.substring(0, i - 1) + divider;
                for (int j = 0; j < s.length() + 1; j++) {
                    String temp2 = "";
                    if (j > i) {
                        temp2 += s.substring(i - 1, j - 1) + divider + s.substring(j - 1);
                        combinationList.add(temp1 + temp2);
                    }
                }
            }
        }

        int count = 0;
        for (String s1 : combinationList) {
            System.out.println(s1);
            String[] arr = s1.split(divider);
            if (arr.length != 3) {
                throw new IllegalArgumentException("WRONG SPLIT ARRAY LENGTH " + arr.length);
            }

            // ab bc ca
            String ab = arr[0] + arr[1];
            String bc = arr[1] + arr[2];
            String ca = arr[2] + arr[0];
            if (ab.equals(bc) || bc.equals(ca) || ab.equals(ca)) {
                continue;
            }

            count++;
        }

        return count;
    }


    static int[][] meanGroup(int[][] a) {
        System.out.println("mean group");

        Map<Double, List<Integer>> map = new HashMap();

        for (int i = 0; i < a.length; i++) {
            double mean = 0;
            for (int j = 0; j < a[i].length; j++) {
                mean += a[i][j];
            }
            mean /= a[i].length;

            if (map.containsKey(mean)) {
                List<Integer> ints = map.get(mean);
                List<Integer> toAppend = new ArrayList<>(ints);
                toAppend.add(i);
                map.put(mean, toAppend);
                continue;
            }
            map.put(mean, List.of(i));
        }

        System.out.println("means: " + Arrays.toString(map.keySet().toArray()));

        int[][] res = new int[map.size()][];
        for (double mean : map.keySet()) {
            List<Integer> indexes = map.get(mean);

            int[] newArray = new int[indexes.size()];

            int i = 0;
            for (int index : indexes) {
                newArray[i] = index;
                i++;
            }

            res[indexes.get(0)] = newArray;
        }
        return res;
    }

    static int numberOfContiguousSubArray(int[] arr) {
        System.out.println("number of contiguous sub array");
        System.out.println(Arrays.toString(arr));

        int res = 0;

        for (int i = 0; i < arr.length; i++) {
            int counter = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < arr.length; j++) {
                int val = map.containsKey(arr[j]) ? map.get(arr[j]) : 0;
                val++;

                map.put(arr[j], val);
                if (map.get(arr[j]) == 2) {
                    counter++;
                }
                if (counter != 0 && counter == map.size()) {
                    res += 1;
                }
            }
        }

        return res;
    }
}

//parantez altina
//(isbu imza .. tl nin burak karaoglanin .. nolu ibanina gönderilmesine müteakip gecerli olacaktir.)

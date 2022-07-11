import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  private static final String cursor = "|";

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
    //int[] arr = {1, 2, 3, 3, 3, 2, 4, 1};

    /*String expected = "London is the capital of Great Britain!";

    String actual = wordApplication(new String[]{
        "TYPE Great Britain is the capital of London",
        "SELECT 0 12",
        "COPY",
        "SELECT 32 37",
        "COPY",
        "PASTE 2",
        "SELECT 0 12",
        "PASTE",
        "MOVE_CURSOR 32",
        "TYPE !"
    });
    //int actual = numberOfContiguousSubArray(arr);*/

    //String actual = mergeTwoLexi("dce", "cccbd");
    //String expected = "cccbd";

    // if (!actual.equals(expected)) {
    //   throw new RuntimeException("expected: " + expected + ", but func. returns: " + actual);
    //  }
    //System.out.println("****correct: " + actual);

    /*
    figure 1:  +   (0,0)

    figure 2:  +++ (0, 0) (0, 1) (0, 2)

    figure 3:  ++  (0, 0) (0, 1)
               ++  (1, 0) (1, 1)

    figure 4: +    (0, 0)
              ++   (1, 0) (1, 1)
              +    (2, 1)

    figure 5: +          (0,1)
             +++  (1, 0) (1,1) (1, 2)

             no overlap
     */

    int[][] expected = new int[][]{{1, 2, 2, 2}, {1, 1, 3, 0}, {1, 4, 4, 0}, {0, 4, 4, 0}};

    int[][] actual = tetris(4, 4, new int[]{4, 2, 1, 3});

    if (!Arrays.deepEquals(actual, expected)) {
      throw new RuntimeException(
          "expected: " + expected + ", but func. returns: " + Arrays.deepToString(actual)
              .replace("],", "],\n"));
    }
    System.out.println("****correct: \n" + Arrays.deepToString(actual).replace("],", "],\n"));
  }

  static int countOfSplit(String s) {
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

  static int howManyLightsMinRequired(int n, int[][] lights) {
    int[][] range = new int[lights.length][2];
    for (int i = 0; i < lights.length; ++i) {
      int loc = lights[i][0], radius = lights[i][1];
      range[i][0] = loc - radius;
      range[i][1] = loc + radius;
    }

    Arrays.sort(range, (a, b) -> a[0] - b[0]);

    int min = 0, i = 0, start = 0, end = 0;
    while (start < n) {

      while (i < range.length && range[i][0] <= start) {
        end = Math.max(end, range[i][1]);
        i++;
      }

      if (start == end) {
        return -1;
      }

      start = end;
      ++min;
    }

    return min;
  }

  static String mergeTwoLexi(String s1, String s2) {
    System.out.println("mergeTwoLexi \ns1: " + s1 + "\ns2: " + s2);

    String left = s1;
    String right = s2;

    String ans = "";
    int j = 0, k = 0;
    for (int i = 0; i < s1.length() + s2.length(); i++) {
      if (left.isEmpty() || right.isEmpty()) {
        break;
      }

      if (left.compareTo(right) <= 0) {
        ans += left.charAt(0);
        left = left.substring(1);
        ++j;
      } else {
        ans += right.charAt(0);
        right = right.substring(1);
        ++k;
      }
    }

    if (j < k) {
      ans += left;
    } else {
      ans += right;
    }

    return ans;
  }

  static String wordApplication(String[] operations) {
    System.out.println("wordApplication MOVE_COMMANDS with ops: " + Arrays.toString(operations));

    String res = "|";
    String selected = "";

    List<String> clipboard = new ArrayList();

    for (String opWithParam : operations) {
      String[] opWithParamSplit = opWithParam.split(" ");
      OPS op = OPS.valueOf(opWithParamSplit[0]);
      switch (op) {
        case TYPE:
          String appendTo = "";
          for (int i = 1; i < opWithParamSplit.length - 1; i++) {
            appendTo += opWithParamSplit[i] + " ";
          }
          appendTo += opWithParamSplit[opWithParamSplit.length - 1] + cursor;
          System.out.println(appendTo);

          if (!selected.isEmpty()) {
            res = removeCursor(res);
            res = res.substring(res.indexOf(selected)) + res.substring(res.indexOf(selected),
                selected.length()) + res.substring(res.indexOf(selected) + selected.length());
            res = res.substring(0, res.indexOf(selected)) + appendTo + cursor + res.substring(
                res.indexOf(selected) + selected.length());
            selected = "";
          } else {
            res = removeCursor(res);
            res += appendTo;
          }
          System.out.println("res?:" + res);

          System.out.println("\n\n");

          break;
        case SELECT:
          int startIndex = Integer.valueOf(opWithParamSplit[1]);
          int endIndex = Integer.valueOf(opWithParamSplit[2]);
          System.out.println("****resBefore select: ****" + res);

          res = removeCursor(res);
          selected = res.substring(startIndex, endIndex + 1);
          System.out.println("res1: ****" + res);
          res = res.substring(0, endIndex + 1) + cursor + res.substring(endIndex + 1);
          System.out.println("resAfterCursorend-select: ****" + res);
          System.out.println("\n\n");
          break;
        case COPY:
          if (!selected.isEmpty()) {
            clipboard.add(selected);
          }
          System.out.println("\n\n");

          break;
        case PASTE:
          int stepBack = 1;
          if (opWithParamSplit.length > 1) {
            stepBack = Integer.valueOf(opWithParamSplit[1]);
          }

          if (stepBack <= clipboard.size()) {
            String valToPaste = clipboard.get(clipboard.size() - stepBack);
            if (!selected.isEmpty()) {
              res = removeCursor(res);
              res = res.substring(0, res.indexOf(selected)) + valToPaste + cursor + res.substring(
                  res.indexOf(selected) + selected.length());
              selected = "";
            } else {
              res = res.substring(0, res.indexOf(cursor)) + valToPaste + res.substring(
                  res.indexOf(cursor) + 1);
            }
          } else {
            // move to end
            res = removeCursor(res);
            res = res + cursor;
            // TODO check that maybe just for above?
          }
          System.out.println("\n\n");

          break;
        case MOVE_CURSOR:
          int offset = Integer.valueOf(opWithParamSplit[1]);
          if (offset == 0) {
            break;
          }
          if (offset < 0) {
            //left
            int calculateLeftMove = offset + res.indexOf(cursor);
            int indexToPutCursor = calculateLeftMove < 0 ? 0 : calculateLeftMove;
            res = removeCursor(res);
            res = res.substring(0, indexToPutCursor) + cursor + res.substring(indexToPutCursor + 1);
          } else {
            int indexToPutCursor = offset + res.indexOf(cursor) > res.length() ? res.length()
                : offset + res.indexOf(cursor);
            res = removeCursor(res);
            res = res.substring(0, indexToPutCursor) + cursor + res.substring(indexToPutCursor);
          }
                                    /*    move2
                                    123|23
                                    1|2323
                                    offset 2.    -2 + 3
                                    */
          selected = "";
          System.out.println("\n\n");

          break;
      }
    }

    return removeCursor(res);
  }

  static int[] applyMutationToAThreeIndices(int n, int[] a) {
    System.out.println(
        "apply mutations to a three indices: n: " + n + " arr: " + Arrays.toString(a));

    int[] b = new int[n];

    for (int i = 0; i < a.length; i++) {
      int left = i == 0 ? 0 : a[i - 1];
      int right = i == a.length - 1 ? 0 : a[i + 1];
      b[i] = left + a[i] + right;
    }

    return b;
  }

  private static String removeCursor(String res) {
    return res.substring(0, res.indexOf(cursor)) + res.substring(res.indexOf(cursor) + 1);
  }

  static int[][] tetris(int n, int m, int[] figures) {
    System.out.println(
        "tetris app: n: " + n + " m: " + m + " figures: " + Arrays.toString(figures));
    int[][] sol = new int[n][m];
    int figureIndex = 1;
    for (int figure : figures) {
      switch (figure) {
            /*
                        figure 1:  +   (0,0)
*/
        case 1:
          OUTER:
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              if (sol[i][j] == 0) {
                sol[i][j] = figureIndex;
                break OUTER;
              }
            }
          }
          break;
                     /*

                        figure 2:  +++ (0, 0) (0, 1) (0, 2)
*/
        case 2:
          OUTER:
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              if (j + 1 > m || j + 2 > m) {
                continue;
              }
              if (sol[i][j] == 0 && sol[i][j + 1] == 0 && sol[i][j + 2] == 0) {
                sol[i][j] = figureIndex;
                sol[i][j + 1] = figureIndex;
                sol[i][j + 2] = figureIndex;
                break OUTER;
              }
            }
          }
          break;
           /*


                        figure 3:  ++  (0, 0) (0, 1)
                                   ++  (1, 0) (1, 1)

                   */
        case 3:
          OUTER:
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              if (j + 1 >= m || i + 1 >= n) {
                continue;
              }
              if (sol[i][j] == 0 && sol[i][j + 1] == 0 && sol[i + 1][j] == 0
                  && sol[i + 1][j + 1] == 0) {
                sol[i][j] = figureIndex;
                sol[i][j + 1] = figureIndex;
                sol[i + 1][j] = figureIndex;
                sol[i + 1][j + 1] = figureIndex;
                break OUTER;
              }
            }
          }
          break;
           /*
                        figure 4: +    (0, 0)
                                  ++   (1, 0) (1, 1)
                                  +    (2, 1)*/
        case 4:
          OUTER:
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              if (j + 1 >= m || i + 1 >= n || i + 2 >= n) {
                continue;
              }
              if (sol[i][j] == 0 && sol[i + 1][j] == 0 && sol[i + 1][j + 1] == 0
                  && sol[i + 2][j] == 0) {
                sol[i][j] = figureIndex;
                sol[i + 1][j] = figureIndex;
                sol[i + 1][j + 1] = figureIndex;
                sol[i + 2][j] = figureIndex;
                break OUTER;
              }
            }
          }
          break;
           /*

                        figure 5: +          (0,1)
                                 +++  (1, 0) (1,1) (1, 2)*/
        case 5:
          OUTER:
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              if (j + 1 >= m || j + 2 >= m || i + 1 >= n) {
                continue;
              }
              if (sol[i][j + 1] == 0 && sol[i + 1][j] == 0 && sol[i + 1][j + 1] == 0
                  && sol[i + 1][j + 2] == 0) {
                sol[i][j + 1] = figureIndex;
                sol[i + 1][j] = figureIndex;
                sol[i + 1][j + 1] = figureIndex;
                sol[i + 1][j + 2] = figureIndex;
                break OUTER;
              }
            }
          }
          break;
      }
      figureIndex++;
    }
    return sol;
  }

  enum OPS {
    TYPE,
    SELECT,
    COPY,
    PASTE,
    MOVE_CURSOR
  }


}

//parantez altina
//(isbu imza .. tl nin burak karaoglanin .. nolu ibanina gönderilmesine müteakip gecerli olacaktir.)

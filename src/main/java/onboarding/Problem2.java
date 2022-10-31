package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = Decoder.runDecoder(cryptogram);
        return answer;
    }
}


class Decoder {

    static String runDecoder(String str) {
        String n_str = deleteDuplicate(str);
//        System.out.println("_____변경전_____");
//        System.out.println(str);
//        System.out.println("_____변경후_____");
//        System.out.println(n_str);
//        System.out.println();
        if (str.equals(n_str)) return str;
        return runDecoder(n_str);
    }

    private static String deleteDuplicate(String str) {
        String new_str = "";
        if (str.equals("")) return "";

        int l = str.length();
        int i = 0;
        while (i < l-1) {
            if (str.charAt(i) == str.charAt(i+1)) {
                int dup_cnt = 1;
                while (dup_cnt < l - 1 - i) {
                    if (str.charAt(i) == str.charAt(i + 1 + dup_cnt)) {
                        dup_cnt += 1;
                    } else {break;}
                }
                i += dup_cnt + 1;
            } else {
                new_str += str.charAt(i);
                i += 1;
            } if (i == l-1) {
                new_str += str.charAt(i);
            }
        }
        return new_str;
    }
}
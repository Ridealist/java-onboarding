package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem6 {

    private List<Boolean> isdup_list = new ArrayList<>();

    public static List<String> solution(List<List<String>> forms) {
        List<String> answer = List.of("answer");
        return answer;
    }

    static List<List<String>> makeBigramsList(List<List<String>> forms) {
        List<List<String>> bigrams_list = new ArrayList<>();
        for (int i=0; i<forms.size(); i++) {
            String str = forms.get(i).get(1);

            List<String> list = new ArrayList<>();
            int j = 0;
            while (j < str.length() - 1) {
                String sub_str = str.substring(j, j+2);
                list.add(sub_str);
                j += 1;
            }
            bigrams_list.add(list);
        }
        return bigrams_list;
    }

    private void setIsdupList(List<List<String>> forms) {
        for (int i=0; i<forms.size(); i++) {
            isdup_list.add(false);
        }
    }

    void checkDuplicateList(List<List<String>> forms) {
        List<List<String>> bigrams_list = makeBigramsList(forms);
        setIsdupList(forms);
        for (int idx=0; idx<bigrams_list.size(); idx++) {
            if (isdup_list.get(idx)) {
                continue;
            }
            // System.out.println(idx);
            List<String> bigrams = bigrams_list.get(idx);
            for (int inner=0; inner<bigrams.size(); inner++) {
                String bigram = bigrams.get(inner);
                // System.out.println(bigram);
                for (int next=idx+1; next<bigrams_list.size(); next++) {
                    // System.out.println(bigrams_list.get(next));
                    if (bigrams_list.get(next).contains(bigram)) {
                        isdup_list.set(idx, true);
                        isdup_list.set(next, true);
                        // System.out.println(isdup_list);
                    }
                }
            }
        }
    }
}

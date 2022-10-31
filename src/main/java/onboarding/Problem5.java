package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Problem5 {
    public static List<Integer> solution(int money) {
        Withdrawal withdraw = new Withdrawal();
        List<Integer> answer = withdraw.getAnswer(money);
        return answer;
    }
}

class Withdrawal {
    private List<Integer> money_array = new ArrayList<>(9);

    private void setMoneyArray() {
        int m = 50000;
        for (int i=0; i<8; i++) {
            int e = (int) Math.pow(10, i/2);
            if (i % 2 == 0) {
                money_array.add(m/e);
            } else {
                money_array.add(m/(e*5));
            }
        }
        money_array.add(1);
    }

    List<Integer> getAnswer(int money) {
        List<Integer> answer = Arrays.asList(0,0,0,0,0,0,0,0,0);
        setMoneyArray();
        for (int i=0; i<9; i++) {
            int m = money_array.get(i);
            if (money < m) {
                continue;
            }
            int q = money / m;
            answer.set(i, q);
            money = money % m;
        }
        return answer;
    }
}
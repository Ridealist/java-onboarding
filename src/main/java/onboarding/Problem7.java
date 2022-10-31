package onboarding;

import java.util.*;

public class Problem7 {

    Map<String, Integer> user_score = new HashMap<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        // List<String> answer = Collections.emptyList();
        // TODO solution 내에 객체를 생성해야 하는 구조 비효율적. 다른 아키텍처 고민해 refactoring 하기.
        Problem7 problem7 = new Problem7();
        List<String> answer = problem7.returnResult(user, friends, visitors);
        return answer;
    }

    List<String> returnResult(String user, List<List<String>> friends, List<String> visitors) {
        List<String> result = new ArrayList<>();
        setUserScore(user, friends, visitors);
        filterUserScore();
        List<Map.Entry<String, Integer>> entryList = sortUserScore();
        int i = 0;
        while (i < 5 & i < entryList.size()) {
            result.add(entryList.get(i).getKey());
            i += 1;
        }
        return result;
    }

    private List<Map.Entry<String, Integer>> sortUserScore() {
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(user_score.entrySet());
        entryList.sort(Map.Entry.comparingByKey());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entryList;
    }

    private void filterUserScore() {
        for (String key : user_score.keySet()) {
            if (user_score.get(key) == 0) {
                user_score.remove(key);
            }
        }
    }

    private void setUserScore(String user, List<List<String>> friends, List<String> visitors) {
        setVisitorScore(visitors);
        setFriendScore(user, friends);
        Map<String, List<String>> friends_map = makeFriendsMap(friends);
        List<String> user_friends = friends_map.get(user);
        for (String friend : user_friends) {
            user_score.remove(friend);
        }
    }

    private void setVisitorScore(List<String> visitors) {
        for (String visitor : visitors) {
            user_score.put(visitor, user_score.getOrDefault(visitor, 0) + 1);
        }
    }

    private Map<String, List<String>> makeFriendsMap(List<List<String>> friends) {
        Map<String, List<String>> friends_map = new HashMap<>();
        for (List<String> friend_list : friends) {
            for (int i = 0; i <= 1; i++) {
                String friend1 = friend_list.get(i);
                String friend2 = friend_list.get(1 - i);
                List<String> array = new ArrayList<>();
                List<String> friend1_friend_list = friends_map.getOrDefault(friend1, array);
                friend1_friend_list.add(friend2);
                friends_map.put(friend1, friend1_friend_list);
            }
        }
        return friends_map;
    }

    private void setFriendScore(String user, List<List<String>> friends) {
        Map<String, List<String>> friends_map = makeFriendsMap(friends);
        List<String> friend_list = friends_map.get(user);
        for (String friend : friend_list) {
            List<String> cross_friend_list = friends_map.get(friend);
            for (String cross_friend : cross_friend_list) {
                if (cross_friend.equals(user)) {
                    continue;
                }
                user_score.put(cross_friend, user_score.getOrDefault(cross_friend, 0) + 10);
            }
        }
    }
}

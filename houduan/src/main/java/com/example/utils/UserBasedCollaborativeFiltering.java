package com.example.utils;

import java.util.*;

public class UserBasedCollaborativeFiltering {

    // 用户-物品收藏矩阵
    private Map<String, Set<String>> userItemCollections;

    public UserBasedCollaborativeFiltering(Map<String, Set<String>> userItemCollections) {
        this.userItemCollections = userItemCollections;
    }

    // 计算两个用户之间的相似度（Jaccard相似度）
    private double calculateSimilarity(String user1, String user2) {
        Set<String> user1Collections = userItemCollections.get(user1);
        Set<String> user2Collections = userItemCollections.get(user2);

        Set<String> intersection = new HashSet<>(user1Collections);
        intersection.retainAll(user2Collections);

        Set<String> union = new HashSet<>(user1Collections);
        union.addAll(user2Collections);

        double similarity = (double) intersection.size() / union.size();
        return similarity;
    }

    // 基于用户的协同过滤算法，为目标用户推荐物品
    public Map<String, Double> recommendItems(String targetUser, int n) {
        Map<String, Double> recommendations = new HashMap<>();

        // 计算目标用户与其他用户的相似度
        Map<String, Double> userSimilarities = new HashMap<>();
        for (String user : userItemCollections.keySet()) {
            if (!user.equals(targetUser)) {
                double similarity = calculateSimilarity(targetUser, user);
                userSimilarities.put(user, similarity);
            }
        }

        // 选择相似度最高的K个用户，并基于他们的收藏为目标用户推荐物品
        for (String user : userSimilarities.keySet()) {
            double similarity = userSimilarities.get(user);
            Set<String> userCollections = userItemCollections.get(user);
            for (String item : userCollections) {
                if (!userItemCollections.get(targetUser).contains(item)) { // 目标用户尚未收藏过的物品
                    double score = recommendations.getOrDefault(item, 0.0);
                    score += similarity;
                    recommendations.put(item, score);
                }
            }
        }

        // 排序推荐物品，按照分数降序排列
        recommendations = sortByValueDescending(recommendations);

        // 只返回前n个推荐物品
        Map<String, Double> topNRecommendations = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, Double> entry : recommendations.entrySet()) {
            if (count >= n) {
                break;
            }
            topNRecommendations.put(entry.getKey(), entry.getValue());
            count++;
        }

        return topNRecommendations;
    }

    // 将Map按照值降序排列
    private static Map<String, Double> sortByValueDescending(Map<String, Double> map) {
        List<Map.Entry<String, Double>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));

        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }



}

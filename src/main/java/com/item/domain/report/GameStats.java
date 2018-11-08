package com.item.domain.report;

import com.item.domain.SGameRealtime;
import com.item.utils.DateUtils;
import com.item.utils.JsonUtil;

import java.text.DecimalFormat;
import java.util.*;

public class GameStats {
    public String day;
    public long gameId;
    public Map<String, StatData> data;

    public static List<GameStats> gameRealTime2GameStats(List<SGameRealtime> gameRealtimes) {
        if (gameRealtimes == null || gameRealtimes.size() == 0) {
            return null;
        }
        Map<String, GameStats> gameStatsMap = new HashMap<>();
        for (SGameRealtime gr : gameRealtimes) {
            long gameId = gr.getAppId();
            String day = DateUtils.format2yyMMdd(gr.getStatDate());
            GameStats gameStats;
            if (gameStatsMap.containsKey(gameId + day)) {
                gameStats = gameStatsMap.get(gameId + day);
            } else {
                gameStats = new GameStats();
                gameStats.day = day;
                gameStats.gameId = gr.getAppId();
                gameStats.data = new HashMap<>();
            }

            String time = DateUtils.format(gr.getStatDate(), "HH:mm");
            StatData data = new StatData();
            data.activeUsers = gr.getActiveUsers();
            data.onlineUsers = gr.getOnlineUsers();
            data.newDevices = gr.getPayAmount();
            data.newRoles = gr.getRoleUsers();
            data.payAmount = gr.getPayAmount();
            gameStats.data.put(time, data);
            gameStatsMap.put(gameId + day, gameStats);
        }
        System.err.println(gameStatsMap.values());
        return new ArrayList<>(gameStatsMap.values());
    }

    public static List<String> getAllKeys(List<GameStats> gameStats) {
        if (gameStats == null || gameStats.isEmpty()) {
            return null;
        }
        Set<String> keySet = new HashSet();
        for (GameStats stat : gameStats) {
            keySet.addAll(stat.data.keySet());
        }
        List<String> keys = new ArrayList<>(keySet);
        Collections.sort(keys);
        return keys;
    }

    public static Map<String, List<Object>> getData(List<String> keys, List<GameStats> gameStats, String... label) {
        if (label == null || label.length == 0 || keys == null || keys.isEmpty()) {
            return null;
        }
        Map<String, List<Object>> dataMap = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        return obj1.compareTo(obj2);
                    }
                });

        for (GameStats stat : gameStats) {
            List<Object> onlineData = new ArrayList<>();
            List<Object> newDeviceData = new ArrayList<>();
            List<Object> newRoleData = new ArrayList<>();
            List<Object> activeData = new ArrayList<>();
            List<Object> payAmountData = new ArrayList<>();
            for (String time : keys) {
                if (stat.data.get(time) == null) {
                    onlineData.add(0);
                    newDeviceData.add(0);
                    newRoleData.add(0);
                    activeData.add(0);
                    payAmountData.add(new DecimalFormat("0.00").format(0f));
                } else {
                    onlineData.add(stat.data.get(time).onlineUsers);
                    newDeviceData.add(stat.data.get(time).newDevices);
                    newRoleData.add(stat.data.get(time).newRoles);
                    activeData.add(stat.data.get(time).activeUsers);
                    payAmountData.add(new DecimalFormat("0.00").format(stat.data.get(time).payAmount));
                }
            }
            String date = stat.day.substring(stat.day.indexOf("-") + 1);
            dataMap.put(date + label[0], onlineData);
            dataMap.put(date + label[1], newDeviceData);
            dataMap.put(date + label[2], newRoleData);
            dataMap.put(date + label[3], activeData);
            dataMap.put(date + label[4], payAmountData);
        }
        return dataMap;
    }

    @Override
    public boolean equals(Object obj) {
        GameStats gameStats = (GameStats) obj;
        if (gameStats.day.equals(this.data) && gameStats.gameId == this.gameId) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }

    public static StatData getEmptyData() {
        StatData data = new StatData();
        data.activeUsers = 0;
        data.onlineUsers = 0;
        data.newDevices = 0;
        data.newRoles = 0;
        data.payAmount = 0;
        return getEmptyData();
    }

    public static class StatData {
        public int onlineUsers;
        public int newDevices;
        public int newRoles;
        public int activeUsers;
        public long payAmount;
    }
}

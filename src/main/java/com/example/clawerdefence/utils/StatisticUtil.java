package com.example.clawerdefence.utils;

import com.example.clawerdefence.pojo.Pair;
import com.example.clawerdefence.pojo.VisitData;

import java.util.*;

//访客数据统计
public class StatisticUtil {
    static Map<String, ArrayList<Long>> visitMap = new HashMap<>();
    static Set<String> visitSet = new HashSet<>();
//    增加一次访问
    public static void addVisit(String ip,Long t){
        Long timestamp = System.currentTimeMillis();
        timestamp/=1000;

        timestamp -= t;

        if(visitMap.containsKey(ip)){
            ArrayList<Long> arrayList = visitMap.get(ip);
            arrayList.add(timestamp);

            int j = arrayList.size()-1;
            while(j>0 && timestamp-arrayList.get(j)<60){
                j--;
            }
            int times = arrayList.size()-j;
            if (times > 20){
                visitSet.add(ip);
            }
        }else {
            ArrayList<Long> arr = new ArrayList<>();
            arr.add(timestamp);
            visitMap.put(ip,arr);
        }
    }

    public static boolean cancelBlack(String ip){
        if(visitSet.contains(ip)){
            visitSet.remove(ip);
            return true;
        }
        return false;
    }

    public static List<Integer> getHoursVisit(){
        int[] arr = new int[24];

        Date date =new Date();
        for(Map.Entry<String,ArrayList<Long>> entry:visitMap.entrySet()){
            for(Long x:entry.getValue()){
                date.setTime(x*1000);
                Integer h = date.getHours();
                arr[h] += 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <24; i++) {
            ans.add(new Integer(arr[i]));
        }
        return ans;
    }

    public static boolean isBlack(String ip){
        if(visitSet.contains(ip)){
            return true;
        }
        return false;
    }


    public static List<VisitData> getVisitData(){
        Long timeNow = System.currentTimeMillis();
        timeNow/=1000;
        List<VisitData> ans = new ArrayList<>();
        for(Map.Entry<String,ArrayList<Long>> entry:visitMap.entrySet()){
//            ip
            String ip = entry.getKey();
            ArrayList<Long> arrayList = entry.getValue();
//            lastDate
            Date lastDate = new Date();
            String lastVisitTime = new Date(arrayList.get(arrayList.size()-1)*1000).toLocaleString();
//            vistTimesIn24
            String vistTimesIn24 = new Integer(arrayList.size()).toString();
            for(int i = arrayList.size()-1;i>=0;i--){
                if(timeNow-arrayList.get(i)>86400){
                    vistTimesIn24 = new Integer(arrayList.size()-i).toString();
                    break;
                }
            }
//            highestRate
            int j = 0;
            int max_rate = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                while (j<arrayList.size() && arrayList.get(j)-arrayList.get(i) < 60){
                    j++;
                }
                max_rate = Math.max(max_rate,j-i);
            }
            String highestRate = new Integer(max_rate).toString();
//          isBlack
            String isBlack = "正常";
            if(visitSet.contains(ip)){
                isBlack = "已封禁";
            }
            ans.add(new VisitData(ip,lastVisitTime,vistTimesIn24,highestRate,isBlack));
        }
        return ans;
    }

}

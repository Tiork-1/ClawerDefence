package com.example.clawerdefence.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitData {
    private String ip;
    private String lastVisitTime;
    private String visitTimesIn24;
    private String highestRate;
    private String isBlack;
}

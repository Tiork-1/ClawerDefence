package com.example.clawerdefence.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("passage")
public class Passage {
    private String id;
    private String title;
    private String context;
}

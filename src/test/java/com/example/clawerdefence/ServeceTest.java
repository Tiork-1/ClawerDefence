package com.example.clawerdefence;


import com.example.clawerdefence.pojo.Passage;
import com.example.clawerdefence.service.PassageServece;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServeceTest {
    @Autowired
    PassageServece passageServece;

    //增加文章
    @Test
    public void test01(){
        Passage passage = new Passage("123456","阳光养猪场","dfjhdjdshfjksdfhk");
        passageServece.addPassage(passage);
    }
}

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
        Integer id = 123787328;
        for (int i = 0; i < 50; i++) {
            id += 1;
            Passage passage = new Passage(id.toString(),"阳光养猪场","dfjhdjdshfjksdfhk");
            passageServece.addPassage(passage);
        }
    }

    @Test
    public void test02(){
        System.out.println(passageServece.getAllPassages());
    }

    @Test
    public void test03(){

    }
}

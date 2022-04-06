package com.example.clawerdefence.service;

import com.example.clawerdefence.mapper.PassageMapper;
import com.example.clawerdefence.pojo.Passage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageServece {
    @Autowired
    PassageMapper passageMapper;

    public boolean addPassage(Passage passage){
        if(passageMapper.insert(passage) > 0){
            return true;
        }
        return false;
    }

    public List<Passage> getAllPassages(){
        List<Passage> passages = passageMapper.selectList(null);
        return passages;
    }

    public Passage getPassageById(String id){
        return passageMapper.selectById(id);
    }
}

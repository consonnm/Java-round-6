package com.example.hotSpot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hotSpot.dao.IConversationDao;
import com.example.hotSpot.entity.Conversation;
import com.example.hotSpot.service.IConversationService;

import org.springframework.stereotype.Service;

@Service
public class ConversationService extends ServiceImpl<IConversationDao, Conversation> implements IConversationService {
    @Override
    public Conversation queryById(int user1Id,int user2Id) {
        return getOne(new LambdaQueryWrapper<Conversation>()
                .eq(Conversation::getUser1Id, user1Id).eq(Conversation::getUser2Id,user2Id));
    }

    @Override
    public boolean insert(int user1Id,int user2Id) {
       Conversation conversation=new Conversation(user1Id,user2Id,null);

        return save(conversation);
    }

}
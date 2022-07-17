package com.example.hotSpot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hotSpot.entity.Conversation;


public interface IConversationService extends IService<Conversation> {
    Conversation queryById(int user1Id,int user2Id);
    boolean insert(int user1Id,int user2Id);
}

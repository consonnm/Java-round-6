package com.example.hotSpot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hotSpot.entity.Conversation;
import org.springframework.stereotype.Repository;

@Repository
public interface IConversationDao extends BaseMapper<Conversation> {
}

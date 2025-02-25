package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapepr;

    @Override
    public int add(Pay pay) {
        return payMapepr.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapepr.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapepr.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapepr.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapepr.selectAll();
    }
}

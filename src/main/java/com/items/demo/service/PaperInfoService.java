package com.items.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.entity.PaperInfo;
import com.items.demo.mapper.PaperInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class PaperInfoService extends ServiceImpl<PaperInfoMapper,PaperInfo> {
}

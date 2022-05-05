package com.items.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.entity.PaperContent;
import com.items.demo.mapper.PaperContentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaperContentService extends ServiceImpl<PaperContentMapper, PaperContent> {
}

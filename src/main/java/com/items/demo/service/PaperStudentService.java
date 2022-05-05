package com.items.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.demo.entity.PaperStudent;
import com.items.demo.mapper.PaperStudentMapper;
import org.springframework.stereotype.Service;

@Service
public class PaperStudentService extends ServiceImpl<PaperStudentMapper, PaperStudent> {
}

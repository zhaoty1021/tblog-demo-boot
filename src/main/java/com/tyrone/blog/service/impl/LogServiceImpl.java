package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.domain.Log;
import com.tyrone.blog.service.LogService;
import com.tyrone.blog.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author zhaot
* @description 针对表【t_log】的数据库操作Service实现
* @createDate 2024-07-29 22:22:13
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}





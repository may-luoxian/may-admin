package com.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Home;
import com.myblog.service.HomeService;
import com.myblog.mapper.HomeMapper;
import org.springframework.stereotype.Service;

/**
* @author lx_syk
* @description 针对表【t_home】的数据库操作Service实现
* @createDate 2024-01-30 15:52:53
*/
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home>
    implements HomeService{

}





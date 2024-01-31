package com.myblog.service;

import com.myblog.entity.Home;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myblog.model.dto.HomeEnableAndNotEnableDTO;
import com.myblog.model.vo.HomeVO;

/**
* @author lx_syk
* @description 针对表【t_home】的数据库操作Service
* @createDate 2024-01-30 15:52:53
*/
public interface HomeService extends IService<Home> {
    void createModel(HomeVO homeVO);

    void editModel(HomeVO homeVO);

    HomeEnableAndNotEnableDTO getEnableNotEnableList();
}

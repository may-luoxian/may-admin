package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Home;
import com.myblog.enums.StatusCodeEnum;
import com.myblog.exception.BizException;
import com.myblog.model.dto.HomeDTO;
import com.myblog.model.dto.HomeEnableAndNotEnableDTO;
import com.myblog.model.vo.HomeVO;
import com.myblog.service.HomeService;
import com.myblog.mapper.HomeMapper;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lx_syk
 * @description 针对表【t_home】的数据库操作Service实现
 * @createDate 2024-01-30 15:52:53
 */
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public void createModel(HomeVO homeVO) {
        if (ObjectUtils.isEmpty(homeVO)) {
            throw new BizException(StatusCodeEnum.VALID_ERROR.getDesc());
        }
        Home home = BeanCopyUtil.copyObject(homeVO, Home.class);
        home.setIsEnable(0);
        this.save(home);
    }

    @Override
    public void editModel(HomeVO homeVO) {
        if (ObjectUtils.isEmpty(homeVO)) {
            throw new BizException(StatusCodeEnum.VALID_ERROR.getDesc());
        }
        Home home = BeanCopyUtil.copyObject(homeVO, Home.class);
        this.updateById(home);
    }

    @Override
    public HomeEnableAndNotEnableDTO getEnableNotEnableList() {
        Integer id = UserUtil.getUserDetailsDTO().getUserInfoId();
        List<Home> enableList = homeMapper.listEnableHome(id);
        List<HomeDTO> enableDTO = BeanCopyUtil.copyList(enableList, HomeDTO.class);
        List<Home> notEnableList = homeMapper.listNotEnableHome(id);
        List<HomeDTO> notEnableDTO = BeanCopyUtil.copyList(notEnableList, HomeDTO.class);
        return HomeEnableAndNotEnableDTO.builder()
                .enableList(enableDTO)
                .notEnableList(notEnableDTO)
                .build();
    }
}





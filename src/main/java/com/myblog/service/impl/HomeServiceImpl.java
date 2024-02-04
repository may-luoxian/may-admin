package com.myblog.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Home;
import com.myblog.entity.RoleHome;
import com.myblog.entity.UserHome;
import com.myblog.enums.StatusCodeEnum;
import com.myblog.exception.BizException;
import com.myblog.model.dto.HomeDTO;
import com.myblog.model.dto.HomeEnableAndNotEnableDTO;
import com.myblog.model.vo.HomeOrderVO;
import com.myblog.model.vo.HomeVO;
import com.myblog.service.HomeService;
import com.myblog.mapper.HomeMapper;
import com.myblog.service.RoleHomeService;
import com.myblog.service.UserHomeService;
import com.myblog.util.BeanCopyUtil;
import com.myblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lx_syk
 * @description 针对表【t_home】的数据库操作Service实现
 * @createDate 2024-01-30 15:52:53
 */
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements HomeService {

    @Autowired
    private HomeMapper homeMapper;

    @Autowired
    private UserHomeService userHomeService;

    @Autowired
    private RoleHomeService roleHomeService;

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
    public HomeEnableAndNotEnableDTO getEnableNotEnableListByUser(Integer userId) {
        List<Home> notEnableList = homeMapper.listNotEnableHomeByUser(userId);
        List<HomeDTO> notEnableDTO = BeanCopyUtil.copyList(notEnableList, HomeDTO.class);
        List<HomeDTO> enableDTO = getOrderHomeList(userId);
        return HomeEnableAndNotEnableDTO.builder()
                .enableList(enableDTO)
                .notEnableList(notEnableDTO)
                .build();
    }

    @Override
    public HomeEnableAndNotEnableDTO getEnableNotEnableListByRole(Integer roleId) {
        List<Home> enableList = homeMapper.listEnalbeHomeByRole(roleId);
        List<HomeDTO> enableDTO = BeanCopyUtil.copyList(enableList, HomeDTO.class);
        List<Home> notEnableList = homeMapper.listNotEnableHomeByRole(roleId);
        List<HomeDTO> notEnableDTO = BeanCopyUtil.copyList(notEnableList, HomeDTO.class);
        return HomeEnableAndNotEnableDTO.builder()
                .enableList(enableDTO)
                .notEnableList(notEnableDTO)
                .build();
    }

    @Override
    public void enableUserHome(Integer userId, List<HomeOrderVO> homeOrderList) {
        UserHome userHome = UserHome.builder()
                .userInfoId(userId)
                .homeOrder(JSONUtil.toJsonStr(homeOrderList))
                .build();
        LambdaUpdateWrapper<UserHome> updateWrapper = new LambdaUpdateWrapper<UserHome>().eq(UserHome::getUserInfoId, userId);
        userHomeService.saveOrUpdate(userHome, updateWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableRoleHome(Integer roleId, List<HomeOrderVO> homeOrderList) {
        if (roleId == null) {
            throw new BizException("未选择角色");
        }
        LambdaQueryWrapper<RoleHome> removeLambda = new LambdaQueryWrapper<RoleHome>().eq(RoleHome::getRoleId, roleId);
        roleHomeService.remove(removeLambda);
        if (homeOrderList != null && homeOrderList.size() != 0) {
            List<RoleHome> collect = homeOrderList.stream().map(item -> RoleHome.builder()
                            .roleId(roleId)
                            .homeId(item.getHomeId())
                            .orderNum(item.getOrderNum())
                            .build())
                    .collect(Collectors.toList());
            roleHomeService.saveBatch(collect);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteHomeById(Integer id) {
        if (id == null) {
            throw new BizException("id不能为空");
        }
        this.removeById(id);
        roleHomeService.remove(new LambdaQueryWrapper<RoleHome>()
                .eq(RoleHome::getHomeId, id));
    }

    @Override
    public List<HomeDTO> getHomeList() {
        Integer userInfoId = UserUtil.getUserDetailsDTO().getUserInfoId();
        return getOrderHomeList(userInfoId);
    }

    private List<HomeDTO> getOrderHomeList(Integer userId) {
        List<Home> enableList = homeMapper.listEnableHomeByUser(userId);
        List<HomeDTO> enableDTO = BeanCopyUtil.copyList(enableList, HomeDTO.class);
        // 启用模块需要根据userhome表排序
        UserHome userHome = userHomeService.selectByUserId(userId);
        String homeOrder = userHome.getHomeOrder();
        List<HomeOrderVO> homeOrderVOList = JSONUtil.toList(homeOrder, HomeOrderVO.class);
        homeOrderVOList.stream().sorted(Comparator.comparing(HomeOrderVO::getOrderNum));
        List<HomeDTO> orderList = new ArrayList<>();
        homeOrderVOList.stream().forEach((item) -> {
            enableDTO.stream().forEach(enableHome -> {
                if (ObjectUtils.isNotEmpty(enableHome) && item.getHomeId().equals(enableHome.getId())) {
                    orderList.add(enableHome);
                }
            });
        });
        return Stream.concat(orderList.stream(), enableDTO.stream())
                .distinct()
                .collect(Collectors.toList());

    }
}





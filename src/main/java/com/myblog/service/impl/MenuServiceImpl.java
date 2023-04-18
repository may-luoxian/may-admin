package com.myblog.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myblog.entity.Menu;
import com.myblog.model.dto.UserMenuDTO;
import com.myblog.mapper.MenuMapper;
import com.myblog.service.MenuService;
import com.myblog.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.myblog.constant.CommonConstant.TRUE;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    //获取用户菜单
    @Override
    public List<UserMenuDTO> listUserMenus() {
        List<Menu> menus = menuMapper.listUserMenu();
        //所有菜单
        List<UserMenuDTO> userMenuDTOList = new ArrayList<>();
        for (Menu menu : menus) {
            UserMenuDTO userMenuDTO = BeanCopyUtil.copyObject(menu, UserMenuDTO.class);
            userMenuDTOList.add(userMenuDTO);
        }
        //拼装结果
        List<UserMenuDTO> MenuTree = new ArrayList<>();
        //存储结点子元素map
        Map<Integer, UserMenuDTO> childMap = new LinkedHashMap<>();
        for (UserMenuDTO userMenuDTO : userMenuDTOList) {
            childMap.put(userMenuDTO.getId(), userMenuDTO);
        }
        for (Integer menuId : childMap.keySet()) {
            UserMenuDTO userMenuDTO = childMap.get(menuId);
            Integer parentId = userMenuDTO.getParentId();
            if (parentId == null) {
                MenuTree.add(userMenuDTO);
            } else {
                UserMenuDTO parentMenu = childMap.get(parentId);
                if (parentMenu.getChildren() == null) {
                    parentMenu.setChildren(new ArrayList<>());
                }
                parentMenu.getChildren().add(userMenuDTO);
            }
        }
        return MenuTree;
//      List<Menu> rootNode = listRootNode(menus);
//      Map<Integer, List<Menu>> childMap = mapSubNode(menus);
//      return convertUserMenuList(rootNode, childMap);
    }
    //获取菜单根节点
//    private List<Menu> listRootNode(List<Menu> menus) {
//        return menus.stream()
//                .filter(item -> Objects.isNull(item.getParentId()))
//                .sorted(Comparator.comparing(Menu::getOrderNum))
//                .collect(Collectors.toList());
//    }
//
//    //对子结点分组
//    private Map<Integer, List<Menu>> mapSubNode(List<Menu> menus) {
//        return menus.stream().filter(item -> Objects.nonNull(item.getParentId()))
//                .collect(Collectors.groupingBy(Menu::getParentId));
//    }
//
//    //组合根、子结点，构建菜单
//    private List<UserMenuDTO> convertUserMenuList(List<Menu> rootNode, Map<Integer, List<Menu>> childMap) {
//        return rootNode.stream().map(item -> {
//            UserMenuDTO userMenuDTO;
//            List<UserMenuDTO> list;
//            List<Menu> children = childMap.get(item.getId());
//            //将根节点Entity转换为DTO对象
//            userMenuDTO = BeanCopyUtil.copyObject(item, UserMenuDTO.class);
//            //如果该父结点存在子结点，那么构建子节点DTO对象
//            if (CollectionUtils.isNotEmpty(children)) {
//                //遍历子结点，排序并转换为DTO对象
//                list = children.stream()
//                        .sorted(Comparator.comparing(Menu::getOrderNum))
//                        .map(menu -> {
//                            UserMenuDTO dto = BeanCopyUtil.copyObject(menu, UserMenuDTO.class);
//                            dto.setHidden(menu.getIsHidden().equals(TRUE));
//                            return dto;
//                        })
//                        .collect(Collectors.toList());
//                userMenuDTO.setChildren(list);
//            }
//            userMenuDTO.setHidden(item.getIsHidden().equals(TRUE));
//            return userMenuDTO;
//        }).collect(Collectors.toList());
//    }
}

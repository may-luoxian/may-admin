package com.myblog.util;

import com.myblog.model.dto.MenuDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    /**
     * @param roots         根节点
     * @param childMap      孩子节点
     * @param t             实体实例对象
     * @param propertyName  子节点属性名
     * @param isDeleteChild 对于有父节点的，是否删除子节点
     * @param <T>
     */
    public static <T> void buildTree(List<T> roots, Map<Integer, List<T>> childMap, T t, String propertyName, boolean isDeleteChild) {
        for (T root : roots) {
            Integer id = null;
            try {
                id = (Integer) t.getClass().getMethod("getId").invoke(root);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            List<T> childList = childMap.get(id);
            Field f = null;
            try {
                f = root.getClass().getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            f.setAccessible(true);
            try {
                f.set(root, childList);
                if (isDeleteChild == true) {
                    childMap.remove(id);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (childList != null && childList.size() > 0) {
                buildTree(childList, childMap, t, propertyName, isDeleteChild);
            }
        }
    }
}

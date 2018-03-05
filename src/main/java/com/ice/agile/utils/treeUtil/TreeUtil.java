package com.ice.agile.utils.treeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:解析树形数据工具类
 * Cteated by wangpeng
 * 2018/3/5 9:24
 */
public class TreeUtil {

    /**
     * 解析树形数据
     * @param topId 顶层id
     * @param entityList 需要解析的元素集合
     * @param <E>   泛型
     * @return  树形结构数据集合
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(Integer topId, List<E> entityList) {
        List<E> resultList = new ArrayList<>();
        Integer pid;

        //获取顶层元素
        for (E entity : entityList) {
            pid = entity.getPid();
            if (topId.equals(pid)) {
                resultList.add(entity);
            }
        }

        //获取顶层元素的子集
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        return resultList;
    }

    /**
     * 获取顶层元素的子集
     * @param id 顶层元素的id
     * @param entityList 所有元素的集合
     * @param <E>   泛型
     * @return  顶层元素的子集
     */
    private static <E extends TreeEntity<E>> List<E> getSubList(Integer id, List<E> entityList) {
        List<E> childList = new ArrayList<>();
        Integer pid;

        for (E entity : entityList) {
            pid = entity.getPid();
            if (id.equals(pid)) {
                childList.add(entity);
            }
        }

        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getId(), entityList));
        }

        if (childList.size() == 0) {
            return null;
        }

        return childList;

    }
}

package com.ice.agile.utils.treeUtil;

import java.util.List;

/**
 * Cteated by wangpeng
 * 2018/3/5 9:20
 */
public interface TreeEntity<E> {

    Integer getId();

    Integer getPid();

    void setChildList(List<E> childList);
}

package com.yanjunwang.multiple_node_tree.domain;

import com.yanjunwang.multiple_node_tree.tree.domain.BaseTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: wangyj
 * @date: 2020-09-22 14:24
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Organization implements BaseTreeNode {

    private Long id;
    private String name;
    private Long parent;

    public Organization(Long id, Long parent, String name) {
        this.id = id;
        this.parent = parent;
        this.name = name;
    }

    @Override
    public Long getNodeId() {
        return this.id;
    }

    @Override
    public Long getNodeParentId() {
        return this.parent;
    }
}

















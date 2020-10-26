package com.yanjunwang.multiple_node_tree.tree.service;


import com.yanjunwang.multiple_node_tree.tree.domain.TreeNode;

/**
 * @description:
 * @author: wangyj
 * @date: 2020-10-21 15:58
 **/
public interface ITree {

    TreeNode getRoot();

    TreeNode getTreeNode(Long nodeId);

    void move(Long treeNodeId, Long targetNodeId);

}

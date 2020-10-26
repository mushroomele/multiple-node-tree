package com.yanjunwang.multiple_node_tree.tree.service.impl;


import com.yanjunwang.multiple_node_tree.tree.domain.BaseTreeNode;
import com.yanjunwang.multiple_node_tree.tree.domain.TreeNode;
import com.yanjunwang.multiple_node_tree.tree.service.ITree;
import org.springframework.util.Assert;

import java.util.*;

import static com.yanjunwang.multiple_node_tree.tree.common.TreeConstant.TREE_NODES_MAP;


/**
 * @description:
 * @author: wangyj
 * @date: 2020-10-21 16:00
 **/
public class MyTree implements ITree {

    public MyTree(List<BaseTreeNode> list) {
        initTreeNodeMap(list);
    }

    private synchronized void initTreeNodeMap(List<BaseTreeNode> list) {
        for (BaseTreeNode node : list) {
            TREE_NODES_MAP.put(node.getNodeId(), new TreeNode(node));
        }

        TREE_NODES_MAP.values().stream()
                .filter(node -> null != node.getParentNodeId())
                .forEach(treeNode -> {
                    TreeNode parentTreeNode = TREE_NODES_MAP.get(treeNode.getParentNodeId());
                    if (parentTreeNode != null) {
                        treeNode.setParent(parentTreeNode);
                        parentTreeNode.addChild(treeNode);
                    }
                });
    }

    @Override
    public synchronized TreeNode getRoot() {
        for (Map.Entry<Long, TreeNode> entry : TREE_NODES_MAP.entrySet()) {
            TreeNode treeNode = entry.getValue();
            if (null == treeNode.getParent()) {
                return treeNode;
            }
        }
        return null;
    }

    @Override
    public synchronized TreeNode getTreeNode(Long nodeId) {
        return TREE_NODES_MAP.get(nodeId);
    }

    @Override
    public synchronized void move(Long treeNodeId, Long targetNodeId) {
        TreeNode treeNode = this.getTreeNode(treeNodeId);
        Assert.notNull(treeNode, "要移动的节点不存在");
        TreeNode parentNode = this.getTreeNode(treeNode.getParentNodeId());
        parentNode.removeChild(treeNode);
        TreeNode targetNode = this.getTreeNode(targetNodeId);
        Assert.notNull(targetNode, "目标节点不存在");
        targetNode.addChild(treeNode);

    }
}

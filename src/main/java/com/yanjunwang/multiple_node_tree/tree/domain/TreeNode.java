package com.yanjunwang.multiple_node_tree.tree.domain;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.yanjunwang.multiple_node_tree.tree.common.TreeConstant.TREE_NODES_MAP;


/**
 * @description:
 * @author: wangyj
 * @date: 2020-10-21 15:11
 **/
public class TreeNode<T extends BaseTreeNode> {

    private Long nodeId;

    private Long parentNodeId;

    private TreeNode parent;

    private T treeNode;

    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {

    }

    public TreeNode(T obj) {
        this.nodeId = obj.getNodeId();
        this.parentNodeId = obj.getNodeParentId();
        this.treeNode = obj;
    }

    public synchronized void addChild(TreeNode treeNode) {
        this.children.add(treeNode);

        TREE_NODES_MAP.put(treeNode.getNodeId(), treeNode);
        List<TreeNode> allChildren = treeNode.getAllChildren();
        if (!CollectionUtils.isEmpty(allChildren)) {
            allChildren.forEach(c -> TREE_NODES_MAP.put(c.getNodeId(), c));
        }

    }

    public synchronized void removeChild(TreeNode treeNode) {
        this.children.remove(treeNode);
        this.removeThisNodeAndLeaf(treeNode);
    }

    public synchronized Long getNodeId() {
        return nodeId;
    }

    public synchronized void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public synchronized Long getParentNodeId() {
        return parentNodeId;
    }

    public synchronized void setParentNodeId(Long parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public synchronized TreeNode getParent() {
        return parent;
    }

    public synchronized void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public synchronized List<TreeNode> getChildren() {
        if (children == null || children.isEmpty()) {
            return null;
        }
        return children;
    }

    public synchronized void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public synchronized T getTreeObject() {
        return treeNode;
    }

    public synchronized void setTreeObject(T treeNode) {
        this.treeNode = treeNode;
    }

    public synchronized List<TreeNode> getAllChildren() {
        List<TreeNode> allChildren = new ArrayList<>();
        for (TreeNode treeNode : this.children) {
            allChildren.add(treeNode);
            allChildren.addAll(treeNode.getAllChildren());
        }
        return allChildren;
    }

    private synchronized void removeThisNodeAndLeaf(TreeNode treeNode) {
        TREE_NODES_MAP.remove(treeNode.getNodeId());
        if (CollectionUtils.isEmpty(treeNode.getChildren())) {
            return;
        }
        for (Object node : treeNode.getChildren()) {
            TreeNode removeNode = (TreeNode) node;
            this.removeThisNodeAndLeaf(removeNode);
        }
    }
}

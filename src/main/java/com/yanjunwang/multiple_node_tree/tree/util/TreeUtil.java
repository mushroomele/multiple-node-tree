package com.yanjunwang.multiple_node_tree.tree.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.yanjunwang.multiple_node_tree.domain.Organization;
import com.yanjunwang.multiple_node_tree.tree.domain.BaseTreeNode;
import com.yanjunwang.multiple_node_tree.tree.domain.TreeNode;
import com.yanjunwang.multiple_node_tree.tree.service.impl.MyTree;

import java.util.ArrayList;
import java.util.List;

import static com.yanjunwang.multiple_node_tree.tree.common.TreeConstant.TREE_NODES_MAP;


/**
 * @author wangyanjun
 */
public class TreeUtil {

    public static Object getTreeInJsonObject(List<? extends BaseTreeNode> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        MyTree tree = new MyTree(new ArrayList<>(list));
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("nodeId");
        TreeNode treeNode1 = tree.getTreeNode(2L);
        List allChildren = tree.getTreeNode(3L).getAllChildren();
        TREE_NODES_MAP.get(4L);
        // *********************move test*************************
        tree.move(4L, 2L);
        // *********************move test*************************

        TreeNode treeNode6 = new TreeNode(new Organization(6L, 2L, "6"));
        TreeNode treeNode7 = new TreeNode(new Organization(7L, 6L, "7"));
        treeNode6.addChild(treeNode7);
        tree.getTreeNode(treeNode6.getParentNodeId()).addChild(treeNode6);

        TreeNode treeNode2 = tree.getTreeNode(2L);
        List allChildren1 = tree.getTreeNode(2L).getAllChildren();
        TREE_NODES_MAP.get(4L);
        String treeJsonString = JSONObject.toJSONString(treeNode2, filter);
        Object data = JSONObject.parse(treeJsonString);
        return data;
    }
}

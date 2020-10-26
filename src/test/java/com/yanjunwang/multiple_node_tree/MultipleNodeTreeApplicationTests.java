package com.yanjunwang.multiple_node_tree;

import com.alibaba.fastjson.JSONObject;
import com.yanjunwang.multiple_node_tree.domain.Organization;
import com.yanjunwang.multiple_node_tree.tree.domain.BaseTreeNode;
import com.yanjunwang.multiple_node_tree.tree.util.TreeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MultipleNodeTreeApplicationTests {

	@Test
	void orgTest() {
		List<BaseTreeNode> organizations = new ArrayList<>();
		organizations.add(new Organization(0L, -1L, "0"));
		organizations.add(new Organization(1L, 0L, "1"));
		organizations.add(new Organization(2L, 1L, "2"));
		organizations.add(new Organization(5L, 4L, "5"));
		organizations.add(new Organization(3L, 2L, "3"));
		organizations.add(new Organization(4L, 3L, "4"));
		Object data = TreeUtil.getTreeInJsonObject(organizations);
		System.out.println(JSONObject.toJSONString(data));


	}

}

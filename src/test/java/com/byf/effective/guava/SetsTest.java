package com.byf.effective.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Lists Sets使用
 */
public class SetsTest {
    /**
     * Sets 工具类的常见用法
     * 并集 / 交集 / 差集 / 分解集合中的所有子集  / 求两个集合的笛卡尔积
     *
     * Lists 工具类的常见用法
     * 反转 / 拆分
     */
    private static final Set set1 = Sets.newHashSet(1,2);

    private static final Set set2 = Sets.newHashSet(4);

    // 并集
    @Test
    public void union(){
        Set<Integer> set = Sets.union(set1,set2);
        System.out.println(set);
    }

    // 交集
    @Test
    public void intersection(){
        Set<Integer> set = Sets.intersection(set1,set2);
        System.out.println(set);
    }

    // 差集：如果元素属于A而且不属于B
    @Test
    public void difference(){
        Set<Integer> set = Sets.difference(set1,set2);
        System.out.println(set);

        // 相对差集：属于A且不属于B，或者属于B且不属于A
        // 除了在两个集合都存在的元素
        set = Sets.symmetricDifference(set1,set2);
        System.out.println(set);
    }

    // 分解所有子集
    @Test
    public void powerSet(){
        Set<Set<Integer>> set = Sets.powerSet(set1);
        System.out.println(JSON.toJSONString(set,true));
    }

    // 笛卡尔积
    @Test
    public void cartesianProduct(){
        Set<List<Integer>> product = Sets.cartesianProduct(set1,set2);
        System.out.println(JSON.toJSONString(product,true));
    }

    // 分区
    @Test
    public void partition(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7);
        List<List<Integer>> partition = Lists.partition(list, 3);
        System.out.println(JSON.toJSONString(partition,false));
    }

    // 反转
    @Test
    public void reverse(){
        List<Integer> list = Lists.newLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> newList = Lists.reverse(list);
        System.out.println(newList);

    }
}

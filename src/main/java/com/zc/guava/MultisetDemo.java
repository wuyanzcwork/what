package com.zc.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;

import java.util.List;

/**
 * Created by zhangchao on 2017/7/1.
 */
public class MultisetDemo {

    public static void main(String[] args) {
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");
        System.out.println(multiset.count("a"));
        ArrayListMultimap<String, String> mutimap = ArrayListMultimap.create();
        List<String> valueList = mutimap.get("");
    }
}

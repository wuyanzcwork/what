package com.zc.java8;

import com.google.common.collect.Lists;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhangchao on 2017/8/20.
 */
public class Lambda {

    public static void main(String[] args) {

        System.out.println(LocalDate.now().withDayOfMonth(32));
        System.out.println(LocalDate.now());
    }
}

package com.hkk.spring.kt.repository.demo;

import com.google.common.collect.Lists;
import java.util.List;

public class S extends P {

    @Override
    @SuppressWarnings(value = "unchecked")
    protected List<Object> parseExcel() {
        return null;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    protected ValidateResult<String, Integer> checkAndRenderItem() {
        hello(parseExcel());
        return null;
    }

    public static void main(String[] args) {
        List<Object> list = Lists.newArrayList("11");
        list.add("11");
    }

    @Override
    protected void hello(List<Object> list) {

    }
}

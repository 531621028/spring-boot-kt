package com.hkk.spring.kt.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static void main(String[] args) {
        BigDecimal value = new BigDecimal("0.00");

        // equals会首先比较精度，精度不同直接返回false
        if (value.equals(BigDecimal.ZERO)) {
            System.out.println("BigDecimal is equal to ZERO.");
        } else {
            System.out.println("BigDecimal is not equal to ZERO.");
        }
    }
}


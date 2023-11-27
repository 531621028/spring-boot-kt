package com.hkk.spring.kt.repository.demo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

public abstract class P {

    protected abstract void hello(List<Object> list);

    protected void doImport() {
        List<?> items = parseExcel();
        final ValidateResult<?, ?> validateResult = checkAndRenderItem();

    }

    protected abstract <T> List<T> parseExcel();

    /**
     * 检查导入数据，并返回没有错误的数据
     */
    protected abstract <I, E> ValidateResult<I, E> checkAndRenderItem();


    @Data
    public static class ValidateResult<I, E> {

        private List<I> items = new ArrayList<>();
        private List<E> errorItems = new ArrayList<>();
    }

}

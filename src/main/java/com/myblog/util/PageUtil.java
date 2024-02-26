package com.myblog.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

public class PageUtil {
    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    public static Page<?> getPage() {
        Page<?> page = PAGE_HOLDER.get();
        if (Objects.isNull(page)) {
            setCurrentPage(new Page<>());
        }
        return PAGE_HOLDER.get();
    }

    public static Long getCurrent() {
        Long current = getPage().getCurrent();
        return current;
    }

    public static Long getSize() {
        return getPage().getSize();
    }

    public static Long getLimitCurrent() {
        Long startRow = (getCurrent() - 1) * getSize();
        return startRow;
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }
}

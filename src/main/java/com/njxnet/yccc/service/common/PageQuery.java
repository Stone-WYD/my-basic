package com.njxnet.yccc.service.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@FunctionalInterface
public interface PageQuery<T> {
        Page<T> query();
}

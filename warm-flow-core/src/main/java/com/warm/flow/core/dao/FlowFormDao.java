package com.warm.flow.core.dao;

import com.warm.flow.core.entity.Form;

/**
 * 流程表单Dao接口，不同的orm扩展包实现它
 *
 * @author vanlin
 * @className FlowFormDao
 * @description
 * @since 2024/8/19 10:24
 */
public interface FlowFormDao<T extends Form> extends WarmDao<T> {
}

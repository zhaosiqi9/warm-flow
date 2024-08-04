/*
 *    Copyright 2024-2025, Warm-Flow (290631660@qq.com).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.warm.flow.orm.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.warm.flow.core.dao.FlowUserDao;
import com.warm.flow.core.invoker.FrameInvoker;
import com.warm.flow.core.utils.ArrayUtil;
import com.warm.flow.core.utils.CollUtil;
import com.warm.flow.core.utils.ObjectUtil;
import com.warm.flow.orm.entity.FlowUser;
import com.warm.flow.orm.mapper.FlowUserMapper;

import java.util.Arrays;
import java.util.List;

/**
 * 流程用户Mapper接口
 *
 * @author warm
 * @date 2023-03-29
 */
public class FlowUserDaoImpl extends WarmDaoImpl<FlowUser> implements FlowUserDao<FlowUser> {

    @Override
    public FlowUserMapper getMapper() {
        return FrameInvoker.getBean(FlowUserMapper.class);
    }

    @Override
    public FlowUser newEntity() {
        return new FlowUser();
    }

    @Override
    public int deleteByTaskIds(List<Long> taskIdList) {
        return getMapper().deleteBatchIds(taskIdList);
    }

    @Override
    public List<FlowUser> listByAssociatedAndTypes(List<Long> associateds, String[] types) {
        LambdaQueryWrapper<FlowUser> queryWrapper = new LambdaQueryWrapper<>();
        if (CollUtil.isNotEmpty(associateds)) {
            if (associateds.size() == 1) {
                queryWrapper.eq(FlowUser::getAssociated, associateds.get(0));
            } else {
                queryWrapper.in(FlowUser::getAssociated, associateds);
            }
        }
        queryWrapper.in(ArrayUtil.isNotEmpty(types), FlowUser::getType, Arrays.asList(types));
        return getMapper().selectList(queryWrapper);
    }

    @Override
    public List<FlowUser> listByProcessedBys(Long associated, List<String> processedBys, String[] types) {
        LambdaQueryWrapper<FlowUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(associated), FlowUser::getAssociated, associated);
        if (CollUtil.isNotEmpty(processedBys)) {
            if (processedBys.size() == 1) {
                queryWrapper.eq(FlowUser::getProcessedBy, processedBys.get(0));
            } else {
                queryWrapper.in(FlowUser::getProcessedBy, processedBys);
            }
        }
        queryWrapper.in(ArrayUtil.isNotEmpty(types), FlowUser::getType, types);
        return getMapper().selectList(queryWrapper);
    }
}

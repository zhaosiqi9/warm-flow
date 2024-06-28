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
import com.warm.flow.core.dao.FlowNodeDao;
import com.warm.flow.core.invoker.FrameInvoker;
import com.warm.flow.core.utils.CollUtil;
import com.warm.flow.orm.entity.FlowNode;
import com.warm.flow.orm.mapper.FlowNodeMapper;
import com.warm.flow.orm.utils.TenantDeleteUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
 * 流程节点Mapper接口
 *
 * @author warm
 * @date 2023-03-29
 */
public class FlowNodeDaoImpl extends WarmDaoImpl<FlowNode> implements FlowNodeDao<FlowNode> {

    @Override
    public FlowNodeMapper getMapper() {
        return FrameInvoker.getBean(FlowNodeMapper.class);
    }

    @Override
    public FlowNode newEntity() {
        return new FlowNode();
    }

    @Override
    public List<FlowNode> getByNodeCodes(List<String> nodeCodes, Long definitionId) {
        LambdaQueryWrapper<FlowNode> queryWrapper = TenantDeleteUtil.getLambdaWrapperDefault(newEntity());
        queryWrapper.in(CollUtil.isNotEmpty(nodeCodes), FlowNode::getNodeCode, nodeCodes)
                .eq(FlowNode::getDefinitionId, definitionId);
        return getMapper().selectList(queryWrapper);
    }

    /**
     * 批量删除流程节点
     *
     * @param defIds 需要删除的数据主键集合
     * @return 结果
     */
    @Override
    public int deleteNodeByDefIds(Collection<? extends Serializable> defIds) {
        return delete(newEntity(), (luw) -> luw.in(FlowNode::getDefinitionId, defIds)
                , (lqw) -> lqw.in(FlowNode::getDefinitionId, defIds));
    }

}

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
package com.warm.flow.orm.entity;

import com.easy.query.core.annotation.*;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.warm.flow.core.entity.Definition;
import com.warm.flow.core.entity.Node;
import com.warm.flow.core.entity.User;
import com.warm.flow.orm.entity.proxy.FlowDefinitionProxy;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 流程定义对象 flow_definition
 *
 * @author warm
 * @date 2023-03-29
 */
@Getter
@Setter
@Accessors(chain = true)
@EntityProxy
@EasyAlias("flowDefinition")
@Table("flow_definition")
public class FlowDefinition implements Definition,ProxyEntityAvailable<FlowDefinition, FlowDefinitionProxy> {

    /** 主键 */
    @Column(value = "id", primaryKey = true)
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 租户ID */
    private String tenantId;

    /** 删除标记 */
    private String delFlag;

    /** 流程编码 */
    private String flowCode;

    /** 流程名称 */
    private String flowName;

    /** 流程版本 */
    private String version;

    /** 是否发布（0未发布 1已发布 9失效） */
    private Integer isPublish;

    /** 审批表单是否自定义（Y是 2否） */
    private String fromCustom;

    /** 审批表单是否自定义（Y是 2否） */
    private String fromPath;

    /** 审批表单是否自定义（Y是 2否） */
    @ColumnIgnore
    private String xmlString;

    @ColumnIgnore
    private List<Node> nodeList = new ArrayList<>();

    @ColumnIgnore
    private List<User> userList = new ArrayList<>();


    @Override
    public String toString() {
        return "FlowDefinition{" +
            "id=" + id +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", flowCode='" + flowCode + '\'' +
            ", flowName='" + flowName + '\'' +
            ", version='" + version + '\'' +
            ", isPublish=" + isPublish +
            ", fromCustom='" + fromCustom + '\'' +
            ", fromPath='" + fromPath + '\'' +
            ", xmlString='" + xmlString + '\'' +
            ", nodeList=" + nodeList +
            '}';
    }
}

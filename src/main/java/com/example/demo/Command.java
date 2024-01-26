package com.example.demo;

import lombok.Data;

@Data
public class Command {
    private String id;

    /**
     * 版本
     */
    private Integer rev;

    /**
     * 业务状态
     */
    private String status;

    /**
     * 流程状态
     */
    private String wfStatus;

    /**
     * 工作流任务ID;
     */
    private String wfTaskId;
}

package com.hxb.smartspace.entity;

/**
 * 文件状态
 *
 * @author Zhao Jinyan
 * @date 2019/2/11 21:25
 */
public enum State {

    /**
     * 临时文件
     */
    TEMP("临时文件"),

    /**
     * 已保存文件
     */
    SAVED("已保存文件"),

    /**
     * 已删除文件
     */
    DELETED("已删除文件");

    private String description;

    /**
     * 构造行数
     * @param description 文件描述
     */
    State(String description) {
        this.description = description;
    }

    /**
     * 获取描述
     * @return 描述
     */
    public String getDescription() {
        return description;
    }

}

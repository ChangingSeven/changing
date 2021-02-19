package com.changing.bg.model.entity;

import lombok.Setter;
import lombok.ToString;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:04
 */
@Setter
@ToString
public class BaseDO {

    /**
     * 页码
     */
    private Integer pageIndex;

    /**
     * 单页记录数
     */

    private Integer pageSize;
    /**
     * 起始索引
     */
    private Integer startIndex;

    public Integer getPageIndex() {
        return this.pageIndex == null ? 1 : this.pageIndex;
    }

    public Integer getPageSize() {
        return this.pageSize == null ? 10 : this.pageSize;
    }

    public Integer getStartIndex() {
        int pageIndex = this.pageIndex == null ? 1 : this.pageIndex;
        int pageSize = this.pageSize == null ? 10 : this.pageSize;

        return (pageIndex - 1) + pageSize;
    }

}
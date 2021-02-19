package com.changing.bg.model.vo.cookbook;

import lombok.Data;

/**
 * @author chenjun
 * @version V1.0
 * @since 2020-08-15 14:30
 */
@Data
public class CookBookRandomColVO {

    /**
     * 午/晚餐
     */
    private String timeType;
    /**
     * 星期一
     */
    private String monday;
    /**
     * 星期二
     */
    private String tuesday;
    /**
     * 星期三
     */
    private String wednesday;
    /**
     * 星期四
     */
    private String thursday;
    /**
     * 星期五
     */
    private String friday;
    /**
     * 星期六
     */
    private String saturday;
    /**
     * 星期天
     */
    private String sunday;

}
package com.jlxu.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 全国各省市区城市编码
 * </p>
 *
 * @author jlxu
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TRegion {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer isEdit;
    private Integer isDel;
    private Integer type;
    private Integer status;
    private Integer sendType;
}

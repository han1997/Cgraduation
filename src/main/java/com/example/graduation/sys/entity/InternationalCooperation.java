package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 国际合作
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InternationalCooperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ic_id", type = IdType.AUTO)
    private Integer icId;

    /**
     * 大学/企业名称
     */
    private String icUniversity;

    /**
     * 所属国家
     */
    private String icCountry;

    /**
     * 合作项目id
     */
    private String icProject;


}

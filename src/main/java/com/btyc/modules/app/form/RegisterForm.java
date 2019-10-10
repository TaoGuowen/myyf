package com.btyc.modules.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 注册表单
 *
 * @author ams
 */
@Data
@ApiModel(value = "注册表单")
public class RegisterForm {
    @ApiModelProperty(value = "code")
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotBlank(message="姓名不能为空")
    private String name;
    /**
     * 出生年月
     */
    @ApiModelProperty(value = "出生年月")
    @NotBlank(message="出生年月不能为空")
    private String birthDay;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @NotBlank(message="手机不能为空")
    private String mobile;
    /**
     * 体重
     */
    private Integer weight;
    /**
     * 身高
     */
    @ApiModelProperty(value = "身高")
    @NotBlank(message="身高不能为空")
    private Integer height;
    /**
     * 关系
     */
    private String relation;

}

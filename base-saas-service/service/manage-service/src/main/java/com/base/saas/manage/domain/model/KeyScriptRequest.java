package com.base.saas.manage.domain.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangYunFei
 * @create 2021/3/2
 * @since 1.0.0
 */
@Data
public class KeyScriptRequest {

    @NotNull(message = "关键字为空，请检查是否遭到篡改！")
    private String keyScript;
}

package com.itxwl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/6/15 11:24
 * @Description 描述信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TotpVerificationDto implements Serializable {
    @NotNull
    private String mfaId;
    @NotNull
    private String code;
}

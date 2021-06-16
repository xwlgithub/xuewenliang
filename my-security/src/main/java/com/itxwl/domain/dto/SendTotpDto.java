package com.itxwl.domain.dto;

import com.itxwl.domain.MfaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author xueWenLiang
 * @date 2021/6/15 10:54
 * @Description 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendTotpDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private MfaType mfaType = MfaType.SMS;

    @NotNull
    private String mfaId;
}

package com.itxwl.exception;

import com.itxwl.util.Constants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

/**
 * @author xueWenLiang
 * @date 2021/6/15 13:56
 * @Description 描述信息
 */
public class InvalidTotpProblem extends AbstractThrowableProblem {
    private static final URI TYPE = URI.create(Constants.PROBLEM_BASE_URI + "/invalid-token");

    public InvalidTotpProblem() {
        super(
                TYPE,
                "验证码错误",
                Status.UNAUTHORIZED,
                "验证码不正确或已过期，请重新输入");
    }
}

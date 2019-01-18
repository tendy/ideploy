package io.ideploy.common.cmd;

import lombok.Data;

/**
 * @author code4china
 * @description
 * @date Created in 11:53 2019/1/10
 */
@Data
public class CommandResult {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 成功日志
     */
    private String message;

    private int exitValue;

    private String host;

    /**
     * 错误日志
     */
    private String errorMessage;

}

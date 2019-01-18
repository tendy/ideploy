package io.ideploy.common.cmd;

/**
 * 功能：执行命令接口
 * 详细：
 */
public interface Command {

    CommandResult exec(String[] cmdArray);
}

package io.ideploy.common.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * @author code4china
 * @description
 * @date Created in 11:59 2019/1/10
 */
@Slf4j
public class LocalCommand implements Command{

    public static final int SUCCESS_CODE = 0;

    /**
     * 执行命令的最大timeout时间，秒
     */
    public static final int MAX_TIMEOUT = 500;

    private static final String CHARSET = "UTF-8";

    protected int timeout = MAX_TIMEOUT;

    private Process process;


    @Override
    public CommandResult exec(String[] cmdArray) {
        CommandResult result = new CommandResult();
        try {
            log.debug("LocalCommand.exec 命令：" + StringUtils.join(cmdArray, " "));

            process = Runtime.getRuntime().exec(cmdArray);

            readSuccessMessage(result);
            readErrorMessage(result);

            process.waitFor(timeout, TimeUnit.SECONDS);

            if (process.isAlive()) {
                process.destroy();
            }
            result.setSuccess(getProcessExitCode() == SUCCESS_CODE);

            log.info("执行local脚本结果, success: {}, message: {}", result.isSuccess(), result.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("执行local脚本失败，执行命令：{}", StringUtils.join(cmdArray, " "),e);
        }
        return result;
    }

    private int getProcessExitCode() throws InterruptedException {
        return process.exitValue();
    }

    protected void readSuccessMessage(CommandResult result) throws Exception {
        result.setMessage(readMessage(process.getInputStream()));
    }

    protected void readErrorMessage(CommandResult result) throws Exception {
        result.setErrorMessage(readMessage(process.getErrorStream()));
    }

    protected String readMessage(InputStream inputStream) throws IOException, InterruptedException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CHARSET));
            StringBuilder builder = new StringBuilder();
            String s1;
            while ((s1 = reader.readLine()) != null) {
                builder.append(s1);
            }
            return builder.toString();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

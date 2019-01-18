package io.ideploy.common.cmd;

import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/**
 * @author code4china
 * @description
 * @date Created in 12:07 2019/1/10
 */
@Slf4j
public class AnsibleArgs {

    private static final String SUCCESS_FLAG = "SUCCESS";

    public AnsibleArgs(String shell, String...hosts){
        this.shell = shell;
        this.localHosts = new HashSet<>();
        this.remoteHosts = new HashSet<>();
        if(hosts.length > 0){

        }
        for(String host: hosts){
            this.localHosts.add(host);
        }
    }

    private String user;

    private int fork = 5;

    private Set<String> localHosts;

    private Set<String> remoteHosts;

    private String shell;

    public String[] buildRemoteCmdArgs(){
        return null;
    }

    public String[] buildLocalCmdArgs(){
        return null;
    }
}

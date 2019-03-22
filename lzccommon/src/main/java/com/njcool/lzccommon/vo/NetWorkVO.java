package com.njcool.lzccommon.vo;

/**
 * Created by chuan on 2017/7/20.
 */

public class NetWorkVO {
    private boolean connected;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public NetWorkVO(boolean connected) {
        this.connected = connected;
    }
}

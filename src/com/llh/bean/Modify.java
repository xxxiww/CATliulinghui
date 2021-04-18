package com.llh.bean;

public class Modify {
    private String modifyName;
    private String modifyHead;
    private String modifyPassword;

    public Modify(String modifyName, String modifyHead, String modifyPassword) {
        this.modifyName = modifyName;
        this.modifyHead = modifyHead;
        this.modifyPassword = modifyPassword;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getModifyHead() {
        return modifyHead;
    }

    public void setModifyHead(String modifyHead) {
        this.modifyHead = modifyHead;
    }

    public String getModifyPassword() {
        return modifyPassword;
    }

    public void setModifyPassword(String modifyPassword) {
        this.modifyPassword = modifyPassword;
    }
}

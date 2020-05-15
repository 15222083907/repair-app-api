package com.toec.market.repair.beans;

import com.toec.market.repair.pojo.Passward;

public class PasswardInfoBean {
    private Passward passward;
    private String oldPassward;

    public Passward getPassward() {
        return passward;
    }

    public void setPassward(Passward passward) {
        this.passward = passward;
    }

    public String getOldPassward() {
        return oldPassward;
    }

    public void setOldPassward(String oldPassward) {
        this.oldPassward = oldPassward;
    }
}

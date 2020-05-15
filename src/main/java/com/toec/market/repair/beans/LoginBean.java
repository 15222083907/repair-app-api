package com.toec.market.repair.beans;

import com.toec.market.repair.pojo.Passward;
import com.toec.market.repair.pojo.Role;
import com.toec.market.repair.pojo.User;

public class LoginBean {
    private User user;
    private Passward passward;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Passward getPassward() {
        return passward;
    }

    public void setPassward(Passward passward) {
        this.passward = passward;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

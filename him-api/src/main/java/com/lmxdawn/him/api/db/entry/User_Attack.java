package com.lmxdawn.him.api.db.entry;
//生成一个用户时创建一张表，用户ID和用户攻击值
public class User_Attack{
    private String userId;
    private String attack;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public User_Attack() {
    }
}

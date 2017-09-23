package com.leagueofdecay.model;

/**
 * Created by jasie on 14.09.2017.
 */

public class Player {
    private long id;
    private long accountId;
    private String name;
    private int profileIconId;
    private int revicisonDate;
    private int summonerLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public int getRevicisonDate() {
        return revicisonDate;
    }

    public void setRevicisonDate(int revicisonDate) {
        this.revicisonDate = revicisonDate;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}

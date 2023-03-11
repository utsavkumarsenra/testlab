package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;

import java.util.List;

public class UserResponse {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("total_pages")
    @Expose
    private String totalPages;

    @SerializedName("data")
    @Expose
    private List<User> users = null;
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

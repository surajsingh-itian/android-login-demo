package com.phyder.myhelper.net;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 24/03/17.
 *
 * @author Suraj Singh
 *         Phyder Solutions Private Limited, a division of, Cyber Manager Software Services Private Limited
 */

public class LoginResponse {

    private Boolean status;

    @SerializedName("display_name")
    private String displayName;

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}

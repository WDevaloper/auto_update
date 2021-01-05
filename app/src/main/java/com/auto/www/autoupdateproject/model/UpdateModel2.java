package com.auto.www.autoupdateproject.model;

import com.auto.update.model.LibraryUpdateEntity;

public class UpdateModel2 implements LibraryUpdateEntity {
    private int code;
    private String message;
    private Data data;

    @Override
    public int getAppVersionCode() {
        if (data.getIsUpdate() == 1) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public int forceAppUpdateFlag() {
        if (data.getIsForce() == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public String getAppVersionName() {
        return data.getVersion();
    }

    @Override
    public String getAppApkUrls() {
        return data.getAddr();
    }

    @Override
    public String getAppUpdateLog() {
        return data.getNotes();
    }

    @Override
    public String getAppApkSize() {
        return data.getFileSize();
    }

    @Override
    public String getAppHasAffectCodes() {
        return null;
    }

    @Override
    public String getFileMd5Check() {
        return null;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

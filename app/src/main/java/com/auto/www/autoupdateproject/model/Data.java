package com.auto.www.autoupdateproject.model;


//"isUpdate": 1,
//
//                "name": null,
//
//                "version": "1.1.3.3",
//
//                "notes": "ceshi",
//
//                "isForce": 0,
//
//                "addr": "http://img-test.youbesun.com/4,6abd49cd2f",
//
//                "fileSize": "19633"
public class Data {
    private int isUpdate;
    private String name;
    private String version;
    private String notes;
    private int isForce;
    private String addr;
    private String fileSize;

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}

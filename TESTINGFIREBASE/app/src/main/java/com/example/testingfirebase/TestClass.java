package com.example.testingfirebase;

public class TestClass {

    String tId;
    String tType;
    String tData;

    public TestClass() {
    }

    public TestClass(String tId, String tType, String tData) {
        this.tId = tId;
        this.tType = tType;
        this.tData = tData;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public String gettData() {
        return tData;
    }

    public void settData(String tData) {
        this.tData = tData;
    }
}

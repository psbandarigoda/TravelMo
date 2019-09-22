package com.example.travelapp;

public class GuideModel {
    private String txtName;
    private String txtAge;
    private String txtEmail;
    private int txtCon;
    private String txtDes;
    private String txtNic;


    public GuideModel() {
    }

    public GuideModel(String txtName, String txtAge, String txtEmail, int txtCon, String txtDes, String txtNic) {

        this.txtName = txtName;
        this.txtAge = txtAge;
        this.txtEmail = txtEmail;
        this.txtCon = txtCon;
        this.txtDes = txtDes;
        this.txtNic = txtNic;

    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtAge() {
        return txtAge;
    }

    public void setTxtAge(String txtAge) {
        this.txtAge = txtAge;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public int getTxtCon() {
        return txtCon;
    }

    public void setTxtCon(int txtCon) {
        this.txtCon = txtCon;
    }

    public String getTxtDes() {
        return txtDes;
    }

    public void setTxtDes(String txtDes) {
        this.txtDes = txtDes;
    }

    public String getTxtNic() {
        return txtNic;
    }

    public void setTxtNic(String txtNic) {
        this.txtNic = txtNic;
    }

}
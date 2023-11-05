package model;

import java.io.Serializable;

public class User implements Serializable {
    private String taikhoan;
    private String matkhau;
    private String diachi;
    private String sdt;
    private String gmail;
    private boolean gioitinh;
    private int tuoi;
 private String trangthai="dangxuat";

    public User() {
    }

    public User(String taikhoan, String matkhau, String diachi, String sdt, String gmail, boolean gioitinh, int tuoi, String trangthai) {
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.diachi = diachi;
        this.sdt = sdt;
        this.gmail = gmail;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
        this.trangthai = trangthai;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "User{" +
                "taikhoan='" + taikhoan + '\'' +
                ", matkhau='" + matkhau + '\'' +
                ", diachi='" + diachi + '\'' +
                ", sdt='" + sdt + '\'' +
                ", gmail='" + gmail + '\'' +
                ", gioitinh=" + gioitinh +
                ", tuoi=" + tuoi +
                ", trangthai='" + trangthai + '\'' +
                '}';
    }
}

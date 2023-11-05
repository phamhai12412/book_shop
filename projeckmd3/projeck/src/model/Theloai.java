package model;

import java.io.Serializable;

public class Theloai implements Serializable,Cloneable {
    private int idtheloai;
    private String matheloai;
    private String tentheloai;
    private int khuyenmaitheloai=0;

    public Theloai() {
    }

    public Theloai(int idtheloai, String matheloai, String tentheloai, int khuyenmaitheloai) {
        this.idtheloai = idtheloai;
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.khuyenmaitheloai = khuyenmaitheloai;
    }

    public int getIdtheloai() {
        return idtheloai;
    }

    public void setIdtheloai(int idtheloai) {
        this.idtheloai = idtheloai;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public int getKhuyenmaitheloai() {
        return khuyenmaitheloai;
    }

    public void setKhuyenmaitheloai(int khuyenmaitheloai) {
        this.khuyenmaitheloai = khuyenmaitheloai;
    }
    @Override
    public Theloai clone() {
        try {
            return (Theloai) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "Theloai{" +
                "matheloai='" + matheloai + '\'' +
                ", tentheloai='" + tentheloai + '\'' +
                ", khuyenmaitheloai=" + khuyenmaitheloai +
                '}';
    }
}

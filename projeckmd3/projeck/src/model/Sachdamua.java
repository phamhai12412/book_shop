package model;

import java.io.Serializable;

public class Sachdamua implements Serializable {
    private int idsachdamua;
    private String masach;
    private int soluong;
    private double giamua;
    private String tensach;
    private String matheloai;
    private int khuyenmaitheosach;
    private int khuyenmaitheotheloai;

    public Sachdamua() {
    }

    public Sachdamua(int idsachdamua, String masach, int soluong, double giamua, String tensach, String matheloai, int khuyenmaitheosach, int khuyenmaitheotheloai) {
        this.idsachdamua = idsachdamua;
        this.masach = masach;
        this.soluong = soluong;
        this.giamua = giamua;
        this.tensach = tensach;
        this.matheloai = matheloai;
        this.khuyenmaitheosach = khuyenmaitheosach;
        this.khuyenmaitheotheloai = khuyenmaitheotheloai;
    }

    public int getIdsachdamua() {
        return idsachdamua;
    }

    public void setIdsachdamua(int idsachdamua) {
        this.idsachdamua = idsachdamua;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiamua() {
        return giamua;
    }

    public void setGiamua(double giamua) {
        this.giamua = giamua;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public int getKhuyenmaitheosach() {
        return khuyenmaitheosach;
    }

    public void setKhuyenmaitheosach(int khuyenmaitheosach) {
        this.khuyenmaitheosach = khuyenmaitheosach;
    }

    public int getKhuyenmaitheotheloai() {
        return khuyenmaitheotheloai;
    }

    public void setKhuyenmaitheotheloai(int khuyenmaitheotheloai) {
        this.khuyenmaitheotheloai = khuyenmaitheotheloai;
    }

    @Override
    public String toString() {
        return "Sachdamua{" +
                "idsachdamua=" + idsachdamua +
                ", masach='" + masach + '\'' +
                ", soluong=" + soluong +
                ", giamua=" + giamua +
                ", tensach='" + tensach + '\'' +
                ", matheloai='" + matheloai + '\'' +
                ", khuyenmaitheosach=" + khuyenmaitheosach +
                ", khuyenmaitheotheloai=" + khuyenmaitheotheloai +
                '}';
    }
}

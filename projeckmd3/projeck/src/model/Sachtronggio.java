package model;

import java.io.Serializable;

public class Sachtronggio implements Serializable {
    private int idgiohang;
    private String masach;
    private int soluong;


    public Sachtronggio() {
    }

    public Sachtronggio(int idgiohang, String masach, int soluong) {
        this.idgiohang = idgiohang;
        this.masach = masach;
        this.soluong = soluong;
    }

    public int getIdgiohang() {
        return idgiohang;
    }

    public void setIdgiohang(int idgiohang) {
        this.idgiohang = idgiohang;
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

    @Override
    public String toString() {
        return "Giohang{" +
                "idgiohang=" + idgiohang +
                ", masach='" + masach + '\'' +
                ", soluong=" + soluong +

                '}';
    }
}

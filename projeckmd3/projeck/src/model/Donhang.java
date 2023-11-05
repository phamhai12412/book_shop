package model;

import config.Hangso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Donhang implements Serializable,Cloneable {
    private int iddonhang;
    private String magiaodich;
private String makhachhang;
private String nguoinhan;
    private Date ngaygiomua=new Date();
    private String trangthai= Hangso.choxacnhan;
    private String phuongthucthanhtoan=Hangso.thanhtoankhinhan;
    private String ghichu;
    private List<Sachdamua> sachdamua = new ArrayList<>();
private String diachi;
private String sdt;

    public Donhang() {
    }

    public Donhang(int iddonhang, String magiaodich, String makhachhang, String nguoinhan, Date ngaygiomua, String trangthai, String phuongthucthanhtoan, String ghichu, List<Sachdamua> sachdamua, String diachi, String sdt) {
        this.iddonhang = iddonhang;
        this.magiaodich = magiaodich;
        this.makhachhang = makhachhang;
        this.nguoinhan = nguoinhan;
        this.ngaygiomua = ngaygiomua;
        this.trangthai = trangthai;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        this.ghichu = ghichu;
        this.sachdamua = sachdamua;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public int getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(int iddonhang) {
        this.iddonhang = iddonhang;
    }

    public String getMagiaodich() {
        return magiaodich;
    }

    public void setMagiaodich(String magiaodich) {
        this.magiaodich = magiaodich;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getNguoinhan() {
        return nguoinhan;
    }

    public void setNguoinhan(String nguoinhan) {
        this.nguoinhan = nguoinhan;
    }

    public Date getNgaygiomua() {
        return ngaygiomua;
    }

    public void setNgaygiomua(Date ngaygiomua) {
        this.ngaygiomua = ngaygiomua;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public List<Sachdamua> getSachdamua() {
        return sachdamua;
    }

    public void setSachdamua(List<Sachdamua> sachdamua) {
        this.sachdamua = sachdamua;
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

    @Override
    public Donhang clone() {
        try {
            return (Donhang) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "**Lich su mua hàng: " +

                "** magiaodich:" + magiaodich + '\'' +
                "** ngaygiomua:" + ngaygiomua +
                "** phuongthucthanhtoan='" + phuongthucthanhtoan + '\'' +
                "** ghichu='" + ghichu + '\'';
    }
}

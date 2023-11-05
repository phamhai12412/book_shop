package model;

import config.Hangso;
import config.InputMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Khachhang extends User implements Serializable, Cloneable {
    private String xetkhoa = Hangso.trangthaikhongkhoa;
    private int idkhachhang;
    private String makhachhang;
    private List<Sachtronggio> giohang = new ArrayList<>();

    private List<String> listdonhang = new ArrayList<>();
private List<Sach> sachyeuthich=new ArrayList<>();
private double vidientu=0;
private int trangthainaptienvaovi=0;
private int maxacthucnapvi;
private double sotiennapvidangchoxacnhan;

    public Khachhang() {
    }

    public Khachhang(String xetkhoa, int idkhachhang, String makhachhang, List<Sachtronggio> giohang, List<String> listdonhang, List<Sach> sachyeuthich, double vidientu, int trangthainaptienvaovi, int maxacthucnapvi, double sotiennapvidangchoxacnhan) {
        this.xetkhoa = xetkhoa;
        this.idkhachhang = idkhachhang;
        this.makhachhang = makhachhang;
        this.giohang = giohang;
        this.listdonhang = listdonhang;
        this.sachyeuthich = sachyeuthich;
        this.vidientu = vidientu;
        this.trangthainaptienvaovi = trangthainaptienvaovi;
        this.maxacthucnapvi = maxacthucnapvi;
        this.sotiennapvidangchoxacnhan = sotiennapvidangchoxacnhan;
    }

    public Khachhang(String taikhoan, String matkhau, String diachi, String sdt, String gmail, boolean gioitinh, int tuoi, String trangthai, String xetkhoa, int idkhachhang, String makhachhang, List<Sachtronggio> giohang, List<String> listdonhang, List<Sach> sachyeuthich, double vidientu, int trangthainaptienvaovi, int maxacthucnapvi, double sotiennapvidangchoxacnhan) {
        super(taikhoan, matkhau, diachi, sdt, gmail, gioitinh, tuoi, trangthai);
        this.xetkhoa = xetkhoa;
        this.idkhachhang = idkhachhang;
        this.makhachhang = makhachhang;
        this.giohang = giohang;
        this.listdonhang = listdonhang;
        this.sachyeuthich = sachyeuthich;
        this.vidientu = vidientu;
        this.trangthainaptienvaovi = trangthainaptienvaovi;
        this.maxacthucnapvi = maxacthucnapvi;
        this.sotiennapvidangchoxacnhan = sotiennapvidangchoxacnhan;
    }

    public String getXetkhoa() {
        return xetkhoa;
    }

    public void setXetkhoa(String xetkhoa) {
        this.xetkhoa = xetkhoa;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public List<Sachtronggio> getGiohang() {
        return giohang;
    }

    public void setGiohang(List<Sachtronggio> giohang) {
        this.giohang = giohang;
    }

    public List<String> getListdonhang() {
        return listdonhang;
    }

    public void setListdonhang(List<String> listdonhang) {
        this.listdonhang = listdonhang;
    }

    public List<Sach> getSachyeuthich() {
        return sachyeuthich;
    }

    public void setSachyeuthich(List<Sach> sachyeuthich) {
        this.sachyeuthich = sachyeuthich;
    }

    public double getVidientu() {
        return vidientu;
    }

    public void setVidientu(double vidientu) {
        this.vidientu = vidientu;
    }

    public int getTrangthainaptienvaovi() {
        return trangthainaptienvaovi;
    }

    public void setTrangthainaptienvaovi(int trangthainaptienvaovi) {
        this.trangthainaptienvaovi = trangthainaptienvaovi;
    }

    public int getMaxacthucnapvi() {
        return maxacthucnapvi;
    }

    public void setMaxacthucnapvi(int maxacthucnapvi) {
        this.maxacthucnapvi = maxacthucnapvi;
    }

    public double getSotiennapvidangchoxacnhan() {
        return sotiennapvidangchoxacnhan;
    }

    public void setSotiennapvidangchoxacnhan(double sotiennapvidangchoxacnhan) {
        this.sotiennapvidangchoxacnhan = sotiennapvidangchoxacnhan;
    }

    public void inputkhachhang() {

        while (true) {
            System.out.println("Mời bạn nhập vào password");
            String mk = InputMethods.getpassword();
            System.out.println("Xác nhận lại password");
            String mk1 = InputMethods.getpassword();
            if (mk.equals(mk1)) {
                this.setMatkhau(mk);
                System.out.println("Mời bạn nhập vào email");
                this.setGmail(InputMethods.getEmailAddress());
                break;
            }else {
                System.err.println("Mật khẩu xác nhận không trùng mời bạn tạo lại mật khẩu");
            }

        }



    }

    @Override
    public Khachhang clone() {
        try {
            return (Khachhang) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Khachhang{" +
                "makhachhang='" + makhachhang + '\'' +
                ", giohang=" + giohang +
                ", listdonhang=" + listdonhang +

                '}';
    }
}

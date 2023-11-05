package model;

import config.Hangso;
import config.InputMethods;
import service.Servicetheloai;

import java.io.Serializable;
import java.util.List;

public class Sach implements Serializable, Cloneable {
    private int idsach;
    private String masach;
    private String tensach;
    private int soluong;
    private int khuyenmai = 0;
    private String matheloai;
    private double giaxuat;

    private String mota;
    private String tacgia;
    private String trangthai = Hangso.trangthaidangban;

    public Sach() {
    }

    public Sach(int idsach, String masach, String tensach, int soluong, int khuyenmai, String matheloai, double giaxuat, String mota, String tacgia, String trangthai) {
        this.idsach = idsach;
        this.masach = masach;
        this.tensach = tensach;
        this.soluong = soluong;
        this.khuyenmai = khuyenmai;
        this.matheloai = matheloai;
        this.giaxuat = giaxuat;
        this.mota = mota;
        this.tacgia = tacgia;
        this.trangthai = trangthai;
    }

    public int getIdsach() {
        return idsach;
    }

    public void setIdsach(int idsach) {
        this.idsach = idsach;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(int khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public double getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(double giaxuat) {
        this.giaxuat = giaxuat;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public void inputSach() {
        Servicetheloai servicetheloai = new Servicetheloai();
        List<Theloai> listtheloai = servicetheloai.getAll();


        System.out.print("Nhập số lượng: ");
        this.setSoluong(InputMethods.getPositiveInteger());


        for (Theloai c : listtheloai
        ) {
            System.out.println("Mã thể loại: " + c.getMatheloai() + " Tên thể loại: " + c.getTentheloai());
        }
        while (true) {
            System.out.print("Nhập mã thể loại bạn muốn chon: ");
            String matheloai = InputMethods.gettheloai();
            Theloai theloai = servicetheloai.findById(matheloai);
            if (theloai != null) {
                this.setMatheloai(theloai.getMatheloai());
                break;
            } else {
                System.err.println("Mã thể loại không đúng mời bạn nhập lại");
            }
        }


        System.out.print("Nhập giá xuất: ");
        this.setGiaxuat(InputMethods.getgiasach());


        System.out.print("Nhập mô tả: ");
        this.setMota(InputMethods.gettentheloaivatensach());


        System.out.print("Nhập tên tác giả: ");
        this.setTacgia(InputMethods.gettentheloaivatensach());

    }

    @Override
    public Sach clone() {
        try {
            return (Sach) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return " **Thông tin Sach:" +
                ", masach='" + masach + '\'' +
                ", tensach='" + tensach + '\'' +
                ", soluong=" + soluong +
                ", khuyenmai=" + khuyenmai +
                ", matheloai='" + matheloai + '\'' +
                ", giaxuat=" + giaxuat +

                ", mota='" + mota + '\'' +

                ", tacgia=" + tacgia +
                ", trangthai='" + trangthai + '\'' +
                '}';
    }
}

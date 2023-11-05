package giaodienAdmin;

import config.Hangso;
import config.InputMethods;
import model.Donhang;
import model.Sachdamua;
import run.Chucnangsachbanchaytrongthang;
import run.Giaodiennguoidung;
import service.Servicedonhang;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Giaodienadmin {
    public static void AdminMenu() {
        int choice;

        do {
            System.out.println("================MENU_ADMIN==============");
            System.out.println("1. Quản lý người dùng");
            System.out.println("2. Quản lý đơn hàng");
            System.out.println("3. Quản lý Sách");
            System.out.println("4. Quản lý Danh mục");
            System.out.println("5. Tổng doanh thu trong tháng này của bạn");
            System.out.println("6. Tổng doanh thu của bạn");
            System.out.println("7. Sách bán chạy trong tháng");
            System.out.println("0. Đăng xuất");
            System.out.println("========================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:

        GiaodienAdminquanlynguoidung.Quanlynguoidung();
                    break;
                case 2:

          GiaodienAdminquanlydonhang.quanlydonhang( );
                    break;
                case 3:

                   GiaodienAdmiquanlysach.Quanlysach();
                    break;
                case 4:

                   GiaodienAdminquanlytheloai.Quanlytheloai();
                    break;
                case 5:

                   doanhthuthangnay();
                    break;
                case 6:
tongdoanhthu();
                    break;
                case 7:
       Chucnangsachbanchaytrongthang.sachbanchaytrongthang();
                    break;
                case 0:
                    System.out.println("Bạn đã chọn Đăng xuất");
                    Giaodiennguoidung.Chuadangnhap();
                    choice = 0;

                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);

    }



    public static void doanhthuthangnay() {
        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        double tongdoanhthu = 0;
        if (donhangList.isEmpty()) {
            System.out.println("Tổng doanh thu của tháng này: "+dinhDangSo.format(tongdoanhthu)+Hangso.vnd );
            return;
        }
        // Lấy thời gian hiện tại của máy
        Calendar ngaygiohientai = Calendar.getInstance();
        int thangHienTai = ngaygiohientai.get(Calendar.MONTH)+1;
        int namhientai= ngaygiohientai.get(Calendar.YEAR);
        //lấy đc tháng hiện tại
        //==========
        //lấy toàn bộ đơn hàng đã được giao thành công đổi thời gian mua qua tháng
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check = -1;
        for (Donhang donhang : donhangList) {
            if (donhang.getTrangthai().equals(Hangso.giaothanhcong)) {
                //lấy ngày giờ hiện tại đơn hàng
                Calendar ngaygiodonhang = Calendar.getInstance();
                ngaygiodonhang.setTime(donhang.getNgaygiomua());
                int thangDonHang = ngaygiodonhang.get(Calendar.MONTH)+1;
                int namdonhang=ngaygiodonhang.get(Calendar.YEAR);
                //kết thúc lấy ra ngày giờ hiện tại đơn hàng
                if (thangDonHang == thangHienTai&&namdonhang==namhientai) {

                    System.out.println("\u001B[35m"+"**Mã giao dich: " + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng: " + donhang.getMakhachhang()));
                    System.out.println("**Thời gian đặt hàng: "+dinhdangngay.format(donhang.getNgaygiomua()) );
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
                    // Đoạn này có thể tận dụng để hiển thị lịch sử mua hàng
                    for (Sachdamua sachdamua : sachdamuaList) {
                        System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai() )+"%"+ " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua() ) +Hangso.vnd+ " **Số lượng: " + sachdamua.getSoluong());
                        tong += sachdamua.getGiamua() * sachdamua.getSoluong();

                        tongdoanhthu += sachdamua.getGiamua() * sachdamua.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println("**Phương thức thanh toán: "+donhang.getPhuongthucthanhtoan());
                    System.out.println("\u001B[34m"+"==================================================================================");
                    check = 1;
                }


            }
        }
        if (check == -1) {
            System.err.println("Chưa có đơn hàng nào được bán thành công trong tháng này.");
        }

        // Print the total revenue
        System.out.println("Tổng doanh thu: " +dinhDangSo.format(tongdoanhthu)+Hangso.vnd );
    }

public static void tongdoanhthu(){
    Servicedonhang servicedonhang = new Servicedonhang();
    List<Donhang> donhangList = servicedonhang.getAll();
    DecimalFormat dinhDangSo = new DecimalFormat("#,###");

    double tongdoanhthu = 0;
    if (donhangList.isEmpty()) {
        System.out.println("Tổng doanh thu của tháng này: "+dinhDangSo.format(tongdoanhthu)+Hangso.vnd );
        return;
    }
    int check = -1;
    SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    for (Donhang donhang : donhangList) {
        if (donhang.getTrangthai().equals(Hangso.giaothanhcong)) {

                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
            System.out.println("**Thời gian đặt hàng: "+dinhdangngay.format(donhang.getNgaygiomua()) );
                double tong = 0;
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
                // Đoạn này có thể tận dụng để hiển thị lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList) {
                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " + (int)Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sachdamua.getGiamua())+Hangso.vnd  + " **Số lượng: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();

                    tongdoanhthu += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
            System.out.println("**Phương thức thanh toán: "+donhang.getPhuongthucthanhtoan());
                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }


        }

    if (check == -1) {
        System.err.println("Chưa có đơn hàng nào được bán thành công");
    }


    System.out.println("Tổng doanh thu: " +dinhDangSo.format(tongdoanhthu) +Hangso.vnd);
}


}

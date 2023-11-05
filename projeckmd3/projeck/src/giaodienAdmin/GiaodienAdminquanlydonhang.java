package giaodienAdmin;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Donhang;
import model.Khachhang;
import model.Sach;
import model.Sachdamua;
import service.Servicedonhang;
import service.Servicekhachhang;
import service.Servicesach;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class GiaodienAdminquanlydonhang {
    public static void quanlydonhang() {
        int choice;

        do {
            System.out.println("=============Quản lý đơn hàng============");
            System.out.println("1. Hiển thị đơn hàng chờ xác nhận");
            System.out.println("2. Hiển thị đơn hàng đang giao");
            System.out.println("3. Hiển thị đơn hàng đã hoàn thành");
            System.out.println("4. Hiển thị đơn hàng đã hủy");
            System.out.println("5. Hiện thị đơn hàng giao không thành công");
            System.out.println("6. Thay đổi trạng thái đơn hàng");
            System.out.println("7. Tra cứu đơn hàng theo mã giao dịch");
            System.out.println("8. Trở về");
            System.out.println("========================================");
            System.out.println("Vui lòng chọn: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:
                    donhangchoxacnhan();
                    break;
                case 2:
                    hienthidonhangdanggiao();
                    break;
                case 3:
                    giaothanhcong();
                    break;
                case 4:
                    hienthidonhangchodahuy();
                    break;
                case 5:
                    hienthidonkhongthanhcong();
                    break;
                case 6:
                    thaydoitrangthai();
                    break;
                case 7:
                    tracuudonhangtheoma();
                    break;
                case 8:
                    System.out.println("Bạn đã chọn trở về.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }


        } while (choice != 8);


    }

    public static void donhangchoxacnhan() {


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        if (donhangList.isEmpty()) {
            System.err.println("Danh sách đơn hàng chờ xác nhận trống");
            return;
        }
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getTrangthai().equals(Hangso.choxacnhan)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

                System.out.println(("**Thời gian đặt hàng:" + dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai() +"%"+ "||**Tổng khuyến mãi: " + (int) Math.round(sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai()) +"%" + " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());
                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }
        }
        if (check == -1) {
            System.err.println("Danh sách đơn hàng chờ xác nhận trống");
        }
    }

    public static void hienthidonhangchodahuy() {


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        if (donhangList.isEmpty()) {
            System.err.println("Danh sách đơn hàng đã hủy trống");
            return;
        }
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getTrangthai().equals(Hangso.dahuydon)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

                System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai() )+"%"+ " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sachdamua.getGiamua())+Hangso.vnd  + " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " + dinhDangSo.format(tong)+Hangso.vnd );
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());

                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }
        }
        if (check == -1) {
            System.err.println("Danh sách đơn hàng đã hủy trống");
        }
    }

    public static void giaothanhcong() {


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();

        if (donhangList.isEmpty()) {
            System.err.println("Danh sách đơn hàng giao thành công trống");
            return;
        }
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getTrangthai().equals(Hangso.giaothanhcong)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

                System.out.println(("**Thời gian đặt hàng:" + dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach() +"%"+ "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " + (int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " + dinhDangSo.format(tong) );
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());

                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }
        }
        if (check == -1) {
            System.err.println("Danh sách đơn hàng giao thành công trống");
        }
    }

    public static void hienthidonhangdanggiao() {


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        if (donhangList.isEmpty()) {
            System.err.println("Danh sách đơn hàng đang giao trống");
            return;
        }
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getTrangthai().equals(Hangso.danggiao)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

                System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int)Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " +dinhDangSo.format( tong)+Hangso.vnd);
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());

                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }
        }
        if (check == -1) {
            System.err.println("Danh sách đơn hàng đang giao trống");
        }
    }

    public static void thaydoitrangthai() {


        IOFile<Donhang> ioFilelichsuma = new IOFile<>();


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();

        System.out.println("Nhập vào mã giao dịch:");
        String mgd = InputMethods.getString();
        Donhang donhang = servicedonhang.findById(mgd);
        if (donhang == null) {
            System.err.println("ko tồn tại giao dịch này");
            return;
        }
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        if (donhang.getTrangthai().equals(Hangso.choxacnhan)) {
            System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
            System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

            System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
            System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
            System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
            double tong = 0;
            DecimalFormat dinhDangSo = new DecimalFormat("#,###");
            List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
            for (Sachdamua sachdamua : sachdamuaList
            ) {

                System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                tong += sachdamua.getGiamua() * sachdamua.getSoluong();
            }
            System.out.println("**Tổng tiền: " +dinhDangSo.format( tong)+Hangso.vnd);
            System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
            System.out.println("\u001B[34m"+"1. Chuyển sang trạng thái đang giao");
            System.out.println("2. Chuyển sang trạnh thái hoàn thành");
            System.out.println("3. Chuyển sang trạng thái hủy đơn hàng");
            System.out.println("4. Hủy bỏ chuyển trạng thái đơn hàng");

            System.out.println(("Mời bạn nhập vào lựa chọn"));
            int c = InputMethods.getchonxacnhan();
            switch (c) {
                case 1:
                    donhang.setTrangthai(Hangso.danggiao);
                    ioFilelichsuma.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);
                    System.out.println("cập nhật thành công");
                    break;
                case 2:
                    donhang.setTrangthai(Hangso.giaothanhcong);
                    ioFilelichsuma.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);
                    System.out.println("cập nhật thành công");

                    break;
                case 3:
                    Servicekhachhang servicekhachhang=new Servicekhachhang();
                    List<Khachhang> khachhangList=servicekhachhang.getAll();
                    Khachhang usedathang =servicekhachhang.findById(donhang.getMakhachhang());

                    donhang.setTrangthai(Hangso.dahuydon);

                    // Cập nhật số lượng sách trong kho
                    List<Sachdamua> sachdamuaList1 = donhang.getSachdamua();
                    Servicesach servicesach = new Servicesach();
                    List<Sach> sachList = servicesach.getAll();

                    for (Sachdamua sachdamua : sachdamuaList1) {
                        for (Sach sach : sachList) {
                            if (sach.getMasach().equals(sachdamua.getMasach())) {
                                sach.setSoluong(sach.getSoluong() + sachdamua.getSoluong());
                                if(donhang.getPhuongthucthanhtoan().equals(Hangso.thanhtoanbangvi)){
                                    usedathang.setVidientu(usedathang.getVidientu()+sachdamua.getGiamua()*sachdamua.getSoluong());

                                    break;
                            }
                        }
                        }
                    }
                        IOFile<Khachhang> khachhangIOFile=new IOFile<>();

                    IOFile<Sach> ioFilesach = new IOFile<>();
                    ioFilesach.writeToFile(sachList, IOFile.LISTSACH_FILE);
                        khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);

                    ioFilelichsuma.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);

                    System.out.println("Đã hủy đơn hàng có mã giao dịch " + mgd);


                    break;
                case 4:
                    break;
            }

        } else if (donhang.getTrangthai().equals(Hangso.danggiao)) {
            System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
            System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
            System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
            System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
            System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
            double tong = 0;
            DecimalFormat dinhDangSo = new DecimalFormat("#,###");
            List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
            for (Sachdamua sachdamua : sachdamuaList) {
                System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai() )+"%"+ " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sachdamua.getGiamua())+Hangso.vnd  + " **Số lương: " + sachdamua.getSoluong());
                tong += sachdamua.getGiamua() * sachdamua.getSoluong();
            }
            System.out.println("**Tổng tiền: " +dinhDangSo.format( tong)+Hangso.vnd);
            System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
            System.out.println("\u001B[34m"+"1. Chuyển sang trạnh thái hoàn thành");
            System.out.println("2. Chuyển sang trạng thái giao hàng thất bại");
            System.out.println("3. Hủy bỏ chuyển trạng thái đơn hàng");
            System.out.println((" Nhập vào lựa chọn của bạn"));
            int c = InputMethods.getdanggiao();
            switch (c) {
                case 1:
                    donhang.setTrangthai(Hangso.giaothanhcong);
                    ioFilelichsuma.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);

                    break;

                case 2:
                    Servicekhachhang servicekhachhang=new Servicekhachhang();
                    List<Khachhang> khachhangList=servicekhachhang.getAll();
                    Khachhang usedathang =servicekhachhang.findById(donhang.getMakhachhang());

                    donhang.setTrangthai(Hangso.giaohangthatbai);

                    // Cập nhật số lượng sách trong kho
                    List<Sachdamua> sachdamuaList1 = donhang.getSachdamua();
                    Servicesach servicesach = new Servicesach();
                    List<Sach> sachList = servicesach.getAll();

                    for (Sachdamua sachdamua : sachdamuaList1) {
                        for (Sach sach : sachList) {
                            if (sach.getMasach().equals(sachdamua.getMasach())) {
                                sach.setSoluong(sach.getSoluong() + sachdamua.getSoluong());
                                if(donhang.getPhuongthucthanhtoan().equals(Hangso.thanhtoanbangvi)){
                                    usedathang.setVidientu(usedathang.getVidientu()+sachdamua.getGiamua()*sachdamua.getSoluong());

                                    break;
                                }
                            }
                        }
                    }
                    IOFile<Khachhang> khachhangIOFile=new IOFile<>();

                    IOFile<Sach> ioFilesach = new IOFile<>();
                    ioFilesach.writeToFile(sachList, IOFile.LISTSACH_FILE);
                    khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);

                    ioFilelichsuma.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);

                    System.out.println("Đơn hàng có mã giao dịch: "+mgd +" giao không thành công" );
                    break;
                case 3:
                    break;
            }
        } else System.err.println("Đơn hàng không thể đổi trạng thái");
    }

    public static void tracuudonhangtheoma() {
        System.out.println("Mời bạn nhập vào mã giao dịch:");
        String magiaodich = InputMethods.getString();
        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        if (donhangList.isEmpty()) {
            System.err.println("Không tìm thấy đơn hàng");
            return;
        }
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getMagiaodich().equals(magiaodich)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
                System.out.println("**Người nhận: " + donhang.getNguoinhan());

                System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%"  + "||**Tổng khuyến mãi: " +(int)Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%"  + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format( sachdamua.getGiamua()) +Hangso.vnd+ " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " + dinhDangSo.format( tong)+Hangso.vnd);
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());

                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }

        }
        if (check == -1) {
            System.err.println("Không tìm thấy đơn hàng");
        }
    }
    public static void hienthidonkhongthanhcong() {


        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        if (donhangList.isEmpty()) {
            System.err.println("Danh sách đơn hàng chờ xác nhận trống");
            return;
        }
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        int check = -1;
        for (Donhang donhang : donhangList
        ) {

            if (donhang.getTrangthai().equals(Hangso.giaohangthatbai)) {
                System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));

                System.out.println(("**Thời gian đặt hàng:" + dinhdangngay.format(donhang.getNgaygiomua()) ));
                System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                double tong = 0;
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                for (Sachdamua sachdamua : sachdamuaList
                ) {

                    System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai() +"%"+ "||**Tổng khuyến mãi: " + (int) Math.round(sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai()) +"%" + " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                    tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                }
                System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                System.out.println("**Phương thức thanh toán: " +donhang.getPhuongthucthanhtoan());
                System.out.println("\u001B[34m"+"==================================================================================");
                check = 1;
            }
        }
        if (check == -1) {
            System.err.println("Danh sách đơn hàng giao thất bại trống");
        }
    }

}



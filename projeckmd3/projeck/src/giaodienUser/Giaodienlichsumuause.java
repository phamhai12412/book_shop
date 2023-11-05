package giaodienUser;

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

public class Giaodienlichsumuause {
    public static void Giaodienlichsumuahangcuause(String mataikhoan){
        int choice;

        do {
            System.out.println("===============Lịch sử mua hàng=================");
            System.out.println("1. Hiện thị đơn hàng đang giao");
            System.out.println("2. Hiện thị đơn hàng chờ xác nhận");
            System.out.println("3. Hiện thị đơn hàng đã hủy");
            System.out.println("4. Hiện thị đơn hàng đã giao thành công");
            System.out.println("5. Hiện thị đơn hàng giao không thành công");
            System.out.println("6. Hủy đơn hàng chờ xác nhận");
            System.out.println("0. Trở về");
            System.out.println("================================================");
            System.out.println("Vui lòng chọn một số từ menu: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:

                    hienthidonhangdanggiao(mataikhoan);
                    break;
                case 2:

                    hienthidonhangchoxacnhan(mataikhoan);
                    break;
                case 3:

                    hienthidonhangchodahuy(mataikhoan);
                    break;
                case 4:

                    hienthidonhanggiaothanhcong(mataikhoan);
                    break;
                case 5:

                    donhanggiaokhongthanhcong(mataikhoan);
                    break;
                case 6:

                    huydonhangchoxacnhan(mataikhoan);
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }

        } while (choice != 0);


    }
    public static void hienthidonhangdanggiao(String mataikhoan){
        Servicedonhang servicedonhang =new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        List<String> lichsumuause=servicekhachhang.findById(mataikhoan).getListdonhang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
     int check=-1;
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Donhang donhang : donhangList
        ) {
            for (String s:lichsumuause
            ) {
                if(s.equals(donhang.getMagiaodich())&& donhang.getTrangthai().equals(Hangso.danggiao)){
                    System.out.println("\u001B[35m"+"**Mã giao dich: " + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng: " + donhang.getMakhachhang()));
                    System.out.println("**Người nhận: "+donhang.getNguoinhan());
                    System.out.println(("**Thời gian đặt hàng: " +dinhdangngay.format(donhang.getNgaygiomua()) ));
                    System.out.println(("**Địa chỉ nhận hàng: " + donhang.getDiachi()));
                    System.out.println("**Số điện thoại nhận hàng: " + donhang.getSdt());
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                    for (Sachdamua sachdamua : sachdamuaList
                    ) {

                        System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai() )+"%"+ " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                        tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println("**Phương thức thanh toán: "+donhang.getPhuongthucthanhtoan());
                    System.out.println("\u001B[34m"+"==================================================================================");
check=1;
                }
            }
        }
        if (check==-1){
            System.err.println("Danh sách đơn hàng đang giao trống");
        }
    }
    public static void hienthidonhangchoxacnhan(String mataikhoan){
        Servicedonhang servicedonhang =new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        Servicekhachhang servicekhachhang=new Servicekhachhang();

        List<String> lichsumuause=servicekhachhang.findById(mataikhoan).getListdonhang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
int check=-1;
        for (String s:lichsumuause
        ) {
            for (Donhang donhang : donhangList
            ) {
                if(s.equals(donhang.getMagiaodich())&& donhang.getTrangthai().equals(Hangso.choxacnhan)){
                    System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
                    System.out.println("**Người nhận: "+donhang.getNguoinhan());

                    System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                    System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                    System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                    for (Sachdamua sachdamua : sachdamuaList
                    ) {

                        System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai() +"%"+ "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuyến mãi: " +dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                        tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println(("Phương thức thanh toán:"+donhang.getPhuongthucthanhtoan()));
                    System.out.println("\u001B[34m"+"==================================================================================");
check=1;
                }
            }
        }
        if (check==-1){
            System.err.println("Danh sách đơn hàng chờ xác nhận trống");
        }
    }
    public static void hienthidonhangchodahuy(String mataikhoan){
        Servicedonhang servicedonhang =new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        Servicekhachhang servicekhachhang=new Servicekhachhang();

        List<String> listdonhangdamuause=servicekhachhang.findById(mataikhoan).getListdonhang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check=-1;
        for (Donhang donhang : donhangList
        ) {
            for (String s:listdonhangdamuause
            ) {
                if(s.equals(donhang.getMagiaodich())&& donhang.getTrangthai().equals(Hangso.dahuydon)){
                    System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng: "+donhang.getMakhachhang()));
                    System.out.println("**Người nhận: "+donhang.getNguoinhan());

                    System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format( donhang.getNgaygiomua())));
                    System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                    System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                    for (Sachdamua sachdamua : sachdamuaList
                    ) {

                        System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuyến mãi: " + dinhDangSo.format(sachdamua.getGiamua()) +Hangso.vnd + " **Số lương: " + sachdamua.getSoluong());
                        tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong)+Hangso.vnd );
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println("**Phương thức thanh toán: "+ donhang.getPhuongthucthanhtoan());
                    System.out.println("\u001B[34m"+"==================================================================================");
check=1;
                }
            }
        }
        if (check==-1){
            System.err.println("Danh sách đơn hàng đã hủy trống");
        }
    }
    public static void hienthidonhanggiaothanhcong(String mataikhoan){
        Servicedonhang servicedonhang =new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        Servicekhachhang servicekhachhang=new Servicekhachhang();

        List<String> donhangmuacuause =servicekhachhang.findById(mataikhoan).getListdonhang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check=-1;
        for (Donhang donhang : donhangList
        ) {
            for (String s: donhangmuacuause
            ) {
                if(s.equals(donhang.getMagiaodich())&& donhang.getTrangthai().equals(Hangso.giaothanhcong)){
                    System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
                    System.out.println("**Người nhận: "+donhang.getNguoinhan());

                    System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                    System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                    System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                    for (Sachdamua sach : sachdamuaList
                    ) {

                        System.out.println("||**Tên sách: " + sach.getTensach() + "||**khuyên mãi theo sách:" + sach.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sach.getKhuyenmaitheotheloai()+"%"  + "||**Tổng khuyến mãi: " +(int) Math.round (sach.getKhuyenmaitheosach() + sach.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sach.getGiamua()) +Hangso.vnd + " **Số lương: " + sach.getSoluong());
                        tong += sach.getGiamua() * sach.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong) +Hangso.vnd);
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println("**Phương thức thanh toán: "+donhang.getPhuongthucthanhtoan());
                    System.out.println("\u001B[34m"+"==================================================================================");
check=1;
                }
            }
        }
        if (check==-1){
            System.err.println("Danh sách đơn hàng giao thành công trống");
        }
    }
    public static void huydonhangchoxacnhan(String mataikhoan){
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        List<Khachhang> khachhangList=servicekhachhang.getAll();
        Khachhang usedangonl=servicekhachhang.findById(mataikhoan);
        System.out.println("Danh sách đơn hàng chờ xác nhận");
        hienthidonhangchoxacnhan(mataikhoan);
        System.out.println("========================================================");
        System.out.println("nhập vào mã giao dịch cần hủy:");
        String maGiaoDich=InputMethods.getString();
        IOFile<Donhang> ioFilelichsuma = new IOFile<>();
        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> listdonhangdamua = servicedonhang.getAll();

        for (Donhang donhang : listdonhangdamua) {
            if (donhang.getMagiaodich().equals(maGiaoDich) && donhang.getTrangthai().equals(Hangso.choxacnhan)) {
                donhang.setTrangthai(Hangso.dahuydon);

                // Cập nhật số lượng sách trong kho
                List<Sachdamua> sachdamuaList = donhang.getSachdamua();
                Servicesach servicesach = new Servicesach();
                List<Sach> sachList = servicesach.getAll();

                for (Sachdamua sachdamua : sachdamuaList) {
                    for (Sach sach : sachList) {
                        if (sach.getMasach().equals(sachdamua.getMasach())) {
                            sach.setSoluong(sach.getSoluong() + sachdamua.getSoluong());
                            if(donhang.getPhuongthucthanhtoan().equals(Hangso.thanhtoanbangvi)){
                            usedangonl.setVidientu(usedangonl.getVidientu()+sachdamua.getGiamua()*sachdamua.getSoluong());

                        }
                            break;}
                    }
                }

                IOFile<Sach> ioFilesach = new IOFile<>();
                IOFile<Khachhang> khachhangIOFile=new IOFile<>();
                ioFilesach.writeToFile(sachList, IOFile.LISTSACH_FILE);
khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);
                ioFilelichsuma.writeToFile(listdonhangdamua, IOFile.LISTLICHSUMUA_FILE);

                System.out.println("Đã hủy đơn hàng có mã giao dịch " + maGiaoDich);
                return;
            }
        }

        System.err.println("Không tìm thấy đơn hàng có mã giao dịch " + maGiaoDich + " hoặc đơn hàng không thể hủy.");


    }
    public static void donhanggiaokhongthanhcong(String mataikhoan){
        Servicedonhang servicedonhang =new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
        Servicekhachhang servicekhachhang=new Servicekhachhang();

        List<String> donhangmuacuause =servicekhachhang.findById(mataikhoan).getListdonhang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        int check=-1;
        for (Donhang donhang : donhangList
        ) {
            for (String s: donhangmuacuause
            ) {
                if(s.equals(donhang.getMagiaodich())&& donhang.getTrangthai().equals(Hangso.giaohangthatbai)){
                    System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
                    System.out.println(("**Mã khách hàng:" + donhang.getMakhachhang()));
                    System.out.println("**Người nhận: "+donhang.getNguoinhan());

                    System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
                    System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
                    System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());
                    double tong = 0;
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
                    for (Sachdamua sach : sachdamuaList
                    ) {

                        System.out.println("||**Tên sách: " + sach.getTensach() + "||**khuyên mãi theo sách:" + sach.getKhuyenmaitheosach()+"%" + "|| **Khuyến mãi theo thể loại: " + sach.getKhuyenmaitheotheloai()+"%"  + "||**Tổng khuyến mãi: " +(int) Math.round (sach.getKhuyenmaitheosach() + sach.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(sach.getGiamua()) +Hangso.vnd + " **Số lương: " + sach.getSoluong());
                        tong += sach.getGiamua() * sach.getSoluong();
                    }
                    System.out.println("**Tổng tiền: " +dinhDangSo.format(tong) +Hangso.vnd);
                    System.out.println(("**Trạng thái:" + donhang.getTrangthai()));
                    System.out.println("**Phương thức thanh toán: "+donhang.getPhuongthucthanhtoan());
                    System.out.println("\u001B[34m"+"==================================================================================");
                    check=1;
                }
            }
        }
        if (check==-1){
            System.err.println("Danh sách đơn hàng giao không thành công trống");
        }
    }

}

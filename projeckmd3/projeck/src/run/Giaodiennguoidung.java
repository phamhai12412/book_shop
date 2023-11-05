package run;

import config.Hangso;
import config.InputMethods;
import giaodienUser.*;
import model.Donhang;
import model.Khachhang;
import model.Sachdamua;
import service.Servicedonhang;
import service.Servicekhachhang;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

public class Giaodiennguoidung {

    static String taikhoanol = "";
    static String mataikhoan = "";
    static boolean isLogged = false;

    public static void main(String[] args) {


        if (isLogged) {
            Dadangnhap();
        } else {
            Chuadangnhap();
        }

    }


    public static void Chuadangnhap() {
        Servicekhachhang servicekhachhang = new Servicekhachhang();
        int choice;

        do {
            System.out.println("\u001B[34m"+"==================Menu=================");
            System.out.println("1. Đăng kí, Đăng nhập");
            System.out.println("2. Tìm kiếm sách");



            System.out.println("3. Thoát");
            System.out.println("=======================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:
                    // Gọi phương thức đăng kí hoặc đăng nhập ở đây

                    Dangkidangnhap.Dangki_Dangnhap(servicekhachhang);
                    choice = 3;
                    break;
                case 2:
                    // Gọi phương thức tìm kiếm sách ở đây

                    Giaodientimkiem.Menutimkiem();
                    break;

                case 3:

                    System.out.println("Bạn đã chọn thoát khỏi chương trình");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 3);


    }

    public static void Dadangnhap() {
        Servicekhachhang servicekhachhang = new Servicekhachhang();

        List<Khachhang> khachhangList = servicekhachhang.getAll();

        for (Khachhang k : khachhangList
        ) {
            if (k.getTrangthai().equals("dangnhap")) {
                taikhoanol = k.getTaikhoan();
                mataikhoan = k.getMakhachhang();
                break;
            }
        }
        int choice;

        do {
            System.out.println("==================Menu=================");
            System.out.println("Xin chào: " + taikhoanol);
            System.out.println("1. Tìm kiếm sách");
            System.out.println("2. Đặt hàng");
            System.out.println("3. Giỏ hàng");
            System.out.println("4. Chỉnh sửa thông tin cá nhân");
            System.out.println("5. Lịch sử mua hàng");
            System.out.println("6. Thay đổi mật khẩu");
            System.out.println("7. Danh sách yêu thích");
            System.out.println("8. Tổng Chi tiêu trong tháng này của bạn");
            System.out.println("9. Ví điện tử");
            System.out.println("0. Đăng xuất");
            System.out.println("=======================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:
                    // Gọi phương thức tìm kiếm sách ở đây

                    Giaodientimkiem.Menutimkiem();
                    break;
                case 2:
                    // Gọi phương thức đặt hàng ở đây

                    Chucnangdathang.dathang(mataikhoan);
                    break;
                case 3:
                    // Gọi phương thức giỏ hàng ở đây

                    Giaodiengiohanguse.giohanguse(mataikhoan);
                    break;
                case 4:
                    // Gọi phương thức chỉnh sửa thông tin cá nhân ở đây
chinhsuathongtincanhan();
                    break;
                case 5:
                    // Gọi phương thức lịch sử mua hàng ở đây

                    Giaodienlichsumuause.Giaodienlichsumuahangcuause(mataikhoan);
                    break;
                case 6:


               thaydoimatkhau();
                    break;
                case 7:
Giaodiendanhsachyeuthich.Danhsachyeuthich(mataikhoan);


                    break;
                case 8:
                    chitieutrongthang(mataikhoan);


                    break;
                case 9:


                    Giaodienvi.vidientu(mataikhoan);
                    break;
                case 0:

                    System.out.println("Bạn đã đăng xuất");


                    Chuadangnhap();
                    choice = 0;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);


    }



public static void chinhsuathongtincanhan(){
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        List<Khachhang> khachhangList=servicekhachhang.getAll();
        Khachhang usedangonl=servicekhachhang.findById(mataikhoan);
    System.out.println("**Thông tin tài khoản của bạn:");
    System.out.println("**Mã khách hàng: "+usedangonl.getMakhachhang());
    System.out.println("**Usename: "+usedangonl.getTaikhoan());
    System.out.println(("**Email: "+usedangonl.getGmail()));
    System.out.println(("**Giới tính: "+(usedangonl.isGioitinh()?"nam":"nữ")));
    System.out.println("**Tuổi: "+usedangonl.getTuoi());
 Khachhang   edituse=servicekhachhang.findById(mataikhoan).clone();
    System.out.println("Bạn có muốn chỉnh sửa thông tin cá nhân");
    System.out.println("nhập 'ok' để chỉnh sửa, kí tự khác để thoát");
    String check=InputMethods.getString();
    if(!check.trim().equalsIgnoreCase("ok")){
   return;
    }
    System.out.println("Mời bạn nhập các thông tin để chỉnh sửa");
    System.out.println("Mời bạn nhập vào Email:");
    edituse.setGmail(InputMethods.getEmailAddress());
    System.out.println("Mời bạn nhập vào giới tính: (nam:true, nữ: false)");
    edituse.setGioitinh(InputMethods.getBoolean());
    System.out.println("Mời bạn nhập vào tuổi: ");
    edituse.setTuoi(InputMethods.getPositiveInteger());
    System.out.println("thông tin sau khi chỉnh sửa của bạn sẽ là:");
    System.out.println("**Thông tin tài khoản của bạn:");
    System.out.println("**Mã khách hàng: "+edituse.getMakhachhang());
    System.out.println("**Usename: "+edituse.getTaikhoan());
    System.out.println(("**Email: "+edituse.getGmail()));
    System.out.println(("**Giới tính: "+(edituse.isGioitinh()?"nam":"nữ")));
    System.out.println("**Tuổi: "+edituse.getTuoi());
    System.out.println("Mời bạn nhập ok để xác nhận, kí tự khác để hủy");
    String kt=InputMethods.getString().trim().toLowerCase();
    if(kt.equals("ok")){
        servicekhachhang.save(edituse);
        System.out.println("cập nhật thành công");
    }else {
        System.out.println("bạn đã hủy thành công");
    }
}
public static void thaydoimatkhau(){
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        Khachhang usedangonl=servicekhachhang.findById(mataikhoan);
    System.out.println("Mời bạn nhập vào mật khẩu đang sử dụng");
    String mk=InputMethods.getpassword();
    if(mk.equals(usedangonl.getMatkhau())){
        System.out.println("mời bạn nhập vào mật khẩu mới");
        String mkm=InputMethods.getpassword();
        System.out.println("Mời bạn xác nhận lại mật khẩu mới");
        String mkmkt=InputMethods.getpassword();
        if(mkm.equals(mkmkt)){
            usedangonl.setMatkhau(mkmkt);
            servicekhachhang.save(usedangonl);
            System.out.println("cập nhật mật khẩu mới thành công");
        }else {
            System.err.println("mật khẩu xác nhận không khớp");
        }
    }else {
        System.err.println("mật khẩu cũ ko đúng");
    }

}
public static void chitieutrongthang(String mataikhoan){
Servicedonhang servicedonhang=new Servicedonhang();
List<Donhang> donhangList=servicedonhang.getAll();
Servicekhachhang servicekhachhang=new Servicekhachhang();
Khachhang usedangonl=servicekhachhang.findById(mataikhoan);
List<String> listdonmuahanguse=usedangonl.getListdonhang();
if(listdonmuahanguse.isEmpty()){
    System.out.println("Tổng chi tiêu tháng này: "+0);
    return;
}
    // Lấy thời gian hiện tại của máy
    Calendar ngaygiohientai = Calendar.getInstance();
    int thangHienTai = ngaygiohientai.get(Calendar.MONTH)+1;
    int namhientai= ngaygiohientai.get(Calendar.YEAR);
    //lấy đc tháng hiện tại
    //==========
    //lấy toàn bộ đơn hàng đã được giao thành công đổi thời gian mua qua tháng
    double tongChiTieu = 0;
    DecimalFormat dinhDangSo = new DecimalFormat("#,###");
   for (String magiaodich: listdonmuahanguse)
    for (Donhang donhang: donhangList
         ) {
        if(magiaodich.equals(donhang.getMagiaodich())&&donhang.getTrangthai().equals(Hangso.giaothanhcong)){
            //lấy ngày giờ hiện tại đơn hàng
            Calendar ngaygiodonhang = Calendar.getInstance();
            ngaygiodonhang.setTime(donhang.getNgaygiomua());
            int thangDonHang = ngaygiodonhang.get(Calendar.MONTH)+1;
            int namdonhang=ngaygiodonhang.get(Calendar.YEAR);
            //kết thúc lấy ra ngày giờ hiện tại đơn hàng
            if (thangDonHang == thangHienTai&&namdonhang==namhientai) {

                // Lặp qua từng đơn hàng trong tháng và tính tổng chi tiêu
                System.out.println("\u001B[36m "+"============================================");
                System.out.println("**Mã giao dịch: "+donhang.getMagiaodich());
                System.out.println("**Mã Khách hàng: "+donhang.getMakhachhang());
                System.out.println("**Ngày giờ mua: "+donhang.getNgaygiomua());
                System.out.println("Sách đã mua:");
double tonghoadon=0;
                for (Sachdamua sachdamua : donhang.getSachdamua()) {
                 double   chitieusach=sachdamua.getGiamua()*sachdamua.getSoluong();
                    tongChiTieu += chitieusach;
                    System.out.println("--------------------------------------------");
                    System.out.println("**Tên sách: "+sachdamua.getTensach()+"||**số lương: "+sachdamua.getSoluong()+" ||**Giá sách: "+dinhDangSo.format( sachdamua.getGiamua()) +Hangso.vnd+" ||**tổng tiền:"+dinhDangSo.format(chitieusach) +Hangso.vnd);
tonghoadon+=chitieusach;
                }
                System.out.println("**Tổng tiền hóa đơn: "+dinhDangSo.format(tonghoadon)+Hangso.vnd );
                System.out.println("\u001B[0m"+"============================================");
            }

        }
    }
    System.out.println("***************************************");

    System.out.println("\u001B[34m"+"Tổng chi tiêu trong tháng này của bạn:"+ dinhDangSo.format(tongChiTieu) +Hangso.vnd);


}

}

















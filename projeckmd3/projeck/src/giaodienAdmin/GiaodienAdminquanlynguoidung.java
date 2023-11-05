package giaodienAdmin;

import config.InputMethods;
import model.Khachhang;
import service.Servicekhachhang;

import java.util.List;

public class GiaodienAdminquanlynguoidung {
    public static void Quanlynguoidung(){

        int choice;

        do {
            System.out.println("============Quản Lý Người Dùng==========");
            System.out.println("1. Hiển thị toàn bộ tài khoản");
            System.out.println("2. Khóa tài khoản");
            System.out.println("3. Mở khóa tài khoản");

            System.out.println("4. Trở về");
            System.out.println("========================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();


            switch (choice) {
                case 1:

                    hienthitoanbouse();
                    break;

                case 2:

                    khoataikhoan();
                    break;
                case 3:

                    mokhoause();
                    break;
                case 4:
                    System.out.println("Trở về");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    break;
            }


        } while (choice != 4);

    }
    public static void hienthitoanbouse(){
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        List<Khachhang> khachhangList=servicekhachhang.getAll();
        if (khachhangList.isEmpty()) {

            System.out.println("Danh mục trống");
            return;
        }
        for (Khachhang k:khachhangList
        ) {
            System.out.println("**Mã khách hang: "+k.getMakhachhang()+" **Uesname: "+k.getTaikhoan()+"**Trạng thái: "+k.getXetkhoa());
        }
    }
    public static void khoataikhoan() {
        Servicekhachhang servicekhachhang=new Servicekhachhang();


        System.out.println("nhập vào mã khách hàng bạn muốn khóa");
        String makhach = InputMethods.getString();
        servicekhachhang.Khoause(makhach);
    }
    public static void mokhoause() {
        Servicekhachhang servicekhachhang=new Servicekhachhang();


        System.out.println("nhập vào mã Khách hàng bạn muốn mở");
        String makhachhang = InputMethods.getString();
        servicekhachhang.Mokhoause(makhachhang);
    }
}

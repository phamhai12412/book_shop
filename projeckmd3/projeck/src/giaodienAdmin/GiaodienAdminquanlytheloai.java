package giaodienAdmin;

import config.InputMethods;
import model.Theloai;
import service.Servicetheloai;

import java.util.List;

public class GiaodienAdminquanlytheloai {
    public static void Quanlytheloai() {

        int choice;

        do {
            System.out.println("==============Quản Lý Thể Loại Sách==============");
            System.out.println("1. Thêm thể loại");
            System.out.println("2. Cập nhật thông tin thể loại");
            System.out.println("3. Xóa thể loại");
            System.out.println("4. Hiển thị toàn bộ thể loại");
            System.out.println("5. Event khuyến mãi cho thể loại:");
            System.out.println("6. Trở về");
            System.out.println("=================================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();


            switch (choice) {
                case 1:

                    themtheloai();
                    break;

                case 2:

                    capnhattheloai();
                    break;

                case 3:

                    xoatheloai();
                    break;

                case 4:

                    hienthitheloai();
                    break;

                case 5:

                    khuyenmaitheloai();
                    break;

                case 6:
                    System.out.println("==============Trở về==============");

                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    break;
            }


        } while (choice != 6);

    }


    public static void themtheloai() {
        Servicetheloai servicetheloai = new Servicetheloai();
        List<Theloai> listtheloai = servicetheloai.getAll();


        // Sử dụng vòng lặp vô hạn để nhập thể loại
        while (true) {
            System.out.println("Nhập thông tin thể loại");
            Theloai theloai = new Theloai();
            int check = 1;

            while (check == 1) {
                System.out.println("Nhập vào tên thể loại");
                String tentl = InputMethods.gettentheloaivatensach();

                // Kiểm tra xem tên thể loại đã tồn tại trong danh sách hay chưa
                boolean tontai = false;
                for (Theloai tl : listtheloai) {
                    if (tl.getTentheloai().equalsIgnoreCase(tentl)) {
                        tontai = true;
                        break;
                    }
                }

                if (tontai) {
                    System.err.println("Tên thể loại đã tồn tại. Vui lòng nhập lại.");
                } else {
                    int id = servicetheloai.getNewId();
                    theloai.setIdtheloai(id);
                    theloai.setMatheloai("MTL" + (100 + id));
                    theloai.setTentheloai(tentl);
                    servicetheloai.save(theloai);
                    check = 0; // Thoát khỏi vòng lặp while
                }
            }

            System.out.println("Nhập 'ok' để tiếp tục, hoặc một kí tự khác để dừng");
            String choice = InputMethods.getString();

            if (!choice.equalsIgnoreCase("ok")) {
                break; // Thoát khỏi vòng lặp while (true)
            }
        }
    }





    public static void capnhattheloai() {


        Servicetheloai servicetheloai = new Servicetheloai();
        List<Theloai> theloaiList = servicetheloai.getAll();
        if (theloaiList.isEmpty()) {

            System.err.println("Danh mục trống");
            return;
        }
        hienthitheloai();

        System.out.println("Nhập vào mã thể loại bạn muốn cập nhật: ");
        String ms = InputMethods.getString();
        Theloai theloai = servicetheloai.findById(ms);
        if (theloai == null) {
            System.err.println("Không tồn tại thể loại này");
            return;
        }
        Theloai theloaiedit = servicetheloai.findById(ms).clone();
        int check = 1;

        while (check == 1) {
            System.out.println("Nhập vào tên thể loại");
            String tentl = InputMethods.getString();

            // Kiểm tra xem tên thể loại đã tồn tại trong danh sách hay chưa
            boolean tontai = false;
            for (Theloai tl : theloaiList) {
                if (tl.getTentheloai().equalsIgnoreCase(tentl)) {
                    tontai = true;
                    break;
                }
            }

            if (tontai) {
                System.err.println("Tên thể loại đã tồn tại. Vui lòng nhập lại.");
            } else {

                theloaiedit.setTentheloai(tentl);

                check = 0; // Thoát khỏi vòng lặp while
            }
        }
        System.out.println("==============Xác nhận cập nhật==============");
        System.out.println("Nhập ok để xác nhận, nhập kí tự bất kì để hủy bỏ");
        String kt = InputMethods.getString().trim().toLowerCase();
        if (kt.equals("ok")) {
            servicetheloai.save(theloaiedit);


        } else {
            System.out.println("==============Đã hủy bỏ==============");
        }

    }

    public static void hienthitheloai() {
        Servicetheloai servicetheloai = new Servicetheloai();
        List<Theloai> theloaiList = servicetheloai.getAll();
        if (theloaiList.isEmpty()) {

            System.err.println("Danh mục trống");
            return;
        }
        for (Theloai s : theloaiList
        ) {

            System.out.println(" ||**mã thể loại: " + s.getMatheloai() + " ||**Tên thể loại: " + s.getTentheloai()+ "||**Khuyến mãi theo thể loại: "+s.getKhuyenmaitheloai()+"%");
        }
    }

    public static void xoatheloai() {

        Servicetheloai servicetheloai = new Servicetheloai();
        List<Theloai> theloaiList = servicetheloai.getAll();
        if (theloaiList.isEmpty()) {

            System.err.println("Danh mục trống");
            return;
        }

        System.out.println("nhập vào mã thể loại bạn muốn xóa");
        String matl = InputMethods.getString();
        Theloai theloai = servicetheloai.findById(matl);

        if (theloai == null) {
            System.err.println("Không tồn tại thể loại này");
            return;
        }
        servicetheloai.delete(matl);

    }
    public static void khuyenmaitheloai(){
        Servicetheloai servicetheloai = new Servicetheloai();

        System.out.println("nhập vào mã thê loại:");
        String ms=InputMethods.getString();
        Theloai theloai = servicetheloai.findById(ms);

        if (theloai == null) {
            System.err.println("Không tồn tại thể loại này");
            return;
        }
        System.out.println("Nhập vào % bạn muốn giảm, lưu ý tổng % khuyến mãi sẽ bằng % khuyến mại của sách và % khuyến mãi của thể loại: ");

        int giam=InputMethods.getEvensach();
        servicetheloai.Eventtheloai(ms,giam);
    }
}

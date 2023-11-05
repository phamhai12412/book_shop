package giaodienAdmin;

import config.Hangso;
import config.InputMethods;
import model.Sach;
import model.Theloai;
import service.Servicesach;
import service.Servicetheloai;

import java.text.DecimalFormat;
import java.util.List;

public class GiaodienAdmiquanlysach {
    public static void Quanlysach() {

        int choice;
        do {
            System.out.println("========Quản Lý Thể Loại Sách===========");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Thiết lập trạng thái ngừng bán");
            System.out.println("4. Hiện thị toàn bộ sách");

            System.out.println("5. Khôi phục trạng thái bán sách ");
            System.out.println("6. Event khuyến mãi theo sách");
            System.out.println("7. Trở về");

            System.out.println("========================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getByte();


            switch (choice) {
                case 1:
                    // Gọi phương thức thêm sách ở đây

                    themsach();
                    break;
                case 2:
                    // Gọi phương thức cập nhật thông tin sách ở đây

                    capnhatsach();
                    break;
                case 3:
                    // Gọi phương thức khóa sách ở đây

                    khoasach();
                    break;
                case 4:

                    hienthisach();
                    break;
                case 5:

                    mosach();
                    break;
                case 6:

                    khuyenmaisach();
                    break;
                case 7:

                    break;
                default:
                    System.out.println("==============Lựa chọn không hợp lệ. Vui lòng chọn lại.==============");
            }
        } while (choice != 7);

    }

    public static void themsach() {
        Servicetheloai servicetheloai = new Servicetheloai();
        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        List<Theloai> listtheloai = servicetheloai.getAll();
        if (listtheloai.isEmpty()) {
            System.err.println("Bạn chưa tạo danh mục sách, hãy thêm danh mục sách trước: ");
            return;
        }
        System.out.println();


        while (true) {
            System.out.println("Nhập thông tin sách");
            Sach book = new Sach();

            int check = 1;

            while (check == 1) {
                System.out.println("Nhập tên sách: ");
                String tensach = InputMethods.gettentheloaivatensach();
                boolean tontai = false;
                for (Sach sach : sachList) {
                    if (sach.getTensach().equalsIgnoreCase(tensach)) {
                        tontai = true;
                        break;
                    }
                }

                if (tontai) {
                    System.err.println("Tên sách đã tồn tại. Vui lòng kiểm tra lại");
                } else {
                    book.setTensach(tensach);
                    book.inputSach();
                    int id = servicesach.getNewId();
                    book.setIdsach(id);
                    book.setMasach("MSB" + (100 + id));
                    servicesach.save(book);
                    check = 0;
                }
            }

            System.out.println("Nhập 'ok' để tiếp tục, hoặc một kí tự khác để dừng");
            String choice = InputMethods.getString();

            if (!choice.equalsIgnoreCase("ok")) {
                break;
            }
        }
    }


    public static void capnhatsach() {
        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();


        System.out.println();
        System.out.print("Nhập vào mã sách bạn muốn cập nhật: ");
        String ms = InputMethods.getString();
        Sach sach = servicesach.findById(ms);
        if (sach == null) {
            System.err.println("Không tồn tại sách này");
            return;
        }
        Sach sachedit = servicesach.findById(ms).clone();
        int check=1;
        while (check == 1) {
            System.out.println("Nhập tên sách: ");
            String tensach = InputMethods.gettentheloaivatensach();
            boolean tontai = false;
            for (Sach s : sachList) {
                if (s.getTensach().equalsIgnoreCase(tensach)&&!tensach.equals(sach.getTensach())) {
                    tontai = true;
                    break;
                }
            }

            if (tontai) {
                System.err.println("Tên sách đã tồn tại. Vui lòng kiểm tra lại");
            } else {
                sachedit.setTensach(tensach);
                sachedit.inputSach();

                check=0;
            }


        }

        System.out.println("==============Xác nhận cập nhật==============");
        System.out.println("Nhập ok để xác nhận, nhập kí tự bất kì để hủy bỏ");
        String kt = InputMethods.getString().trim().toLowerCase();
        if (kt.trim().equalsIgnoreCase("ok")) {
            servicesach.save(sachedit);

        } else {
            System.out.println("==============Đã hủy bỏ=================");
        }

    }

    public static void hienthisach() {
        Servicesach servicesach = new Servicesach();
        List<Sach> listsach = servicesach.getAll();
        Servicetheloai servicetheloai = new Servicetheloai();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        if (listsach.isEmpty()) {
            System.out.println("Không tìm thấy sách");
        } else {
            for (Sach s : listsach) {
                double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                System.out.println(" ||**mã sách: " + s.getMasach() + " ||**Tên sách: " + s.getTensach() + " ||**Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + " ||**Tác giả: " + s.getTacgia() + " ||Số lượng: " + s.getSoluong() + " ||**Trạng thái:  " + s.getTrangthai());
                System.out.println(" ||**Giá bán:" + dinhDangSo.format(s.getGiaxuat())+ Hangso.vnd + " ||**Khuyến mãi theo sách: " + s.getKhuyenmai() + "%" + " ||**Khuyến mãi theo thể loại:" + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai() + "%" + " ||**Tổng khuyến mãi: " +(int) Math.round(tongkhuyenmai)  + "%" + " ||**Giá sau khuyến mãi: " + dinhDangSo.format(giaban) +Hangso.vnd);
                System.out.println("------------------------------------------------------------------------------");
            }
        }
    }

    public static void khoasach() {
        Servicesach servicesach = new Servicesach();

        System.out.println("nhập vào mã sách bạn muốn ngừng bán");
        String masach = InputMethods.getString();
        servicesach.Khoasach(masach);
    }

    public static void mosach() {
        Servicesach servicesach = new Servicesach();

        System.out.println("nhập vào mã sách bạn muốn mở bán");
        String masach = InputMethods.getString();
        servicesach.Mosach(masach);
    }

    public static void khuyenmaisach() {
        Servicesach servicesach = new Servicesach();

        System.out.println("nhập vào mã sách:");
        String ms = InputMethods.getString();
        if(servicesach.findById(ms)==null){
            System.err.println("Mã sách không tồn tại.");
            return;
        }
        System.out.println("nhập vào % bạn muốn giảm:");
        int giam = InputMethods.getEvensach();
        servicesach.Eventsach(ms, giam);
    }
}

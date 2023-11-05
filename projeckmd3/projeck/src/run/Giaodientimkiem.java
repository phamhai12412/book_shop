package run;

import config.Hangso;
import config.InputMethods;
import model.Sach;
import service.Servicesach;
import service.Servicetheloai;

import java.text.DecimalFormat;
import java.util.List;

public class Giaodientimkiem {
    public static void Menutimkiem() {
        int choice;


        System.out.println("============Tìm kiếm sách===============");
        System.out.println("1. Tìm kiếm sách theo tên tác giả");
        System.out.println("2. Tìm kiếm sách theo danh mục");
        System.out.println("3. Tìm kiếm sách theo tên");
        System.out.println("4. Hiện thị toàn bộ sách");

        System.out.println("========================================");
        System.out.print("Nhập lựa chọn của bạn: ");
        choice = InputMethods.getByte();

        switch (choice) {
            case 1:
                System.out.println("Bạn đã chọn Tìm kiếm sách theo tên tác giả");
                // Gọi phương thức tìm kiếm sách theo tên tác giả ở đây
                timkiemtheotacgia();
                break;
            case 2:
                System.out.println("Bạn đã chọn Tìm kiếm sách theo danh mục");
                timkiemtheodanhmuc();
                break;
            case 3:
                System.out.println("Bạn đã chọn Tìm kiếm sách theo tên");
                hienthitoanbo();
                break;
            case 4:
                System.out.println("Bạn đã chọn hiện thị toàn bộ sách theo tên");
                timkiemtonghop();
                break;
            case 5:
                System.out.println("Sách bán chạy trong tháng");
            Chucnangsachbanchaytrongthang.sachbanchaytrongthang();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
        }

    }

    public static void timkiemtheotacgia() {
        Servicetheloai servicetheloai=new Servicetheloai();
        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        System.out.println("Mời bạn nhập vào tên tác giả:");
        String tk = InputMethods.getString();
        int check=-1;
        if (sachList.isEmpty()) {
            System.out.println("Không tìm thấy sách");
            return;
        } else {
            for (Sach s : sachList
            ) {
                if(s.getTrangthai().equals(Hangso.trangthaingungban)){
                    continue;
                }
                if (s.getTacgia().trim().toLowerCase().contains(tk.trim().toLowerCase())) {
                    double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                    double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                    giaban=Math.round(giaban);


                    DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                    System.out.println("|| **mã sách: " + s.getMasach() + "|| **Tên sách: " + s.getTensach()+(s.getSoluong()!=0?" ||**Số lương:"+s.getSoluong():" ||**Trạng thái: Hết hàng") + "|| **Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + "|| **Tác giả: " + s.getTacgia() +
                            "\n" +"|| **Giá bán:" + dinhDangSo.format(s.getGiaxuat()) +Hangso.vnd + "|| **Khuyến mại: " + (int) tongkhuyenmai + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(Math.round(giaban))+Hangso.vnd);
                    System.out.println("\u001B[35m"+"--------------------------------------------------------------------------------------------------------"+"\u001B[34m");
check=1;
                }
            }
        }
        if(check==-1){
            System.err.println("Không tìm thấy sách");
        }
    }

    public static void timkiemtheodanhmuc() {
        Servicetheloai servicetheloai = new Servicetheloai();

        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        System.out.println("Mời bạn nhập vào tên danh muc:");
        String tk = InputMethods.getString();
        int check=-1;
        if (sachList.isEmpty()) {
            System.out.println("Không tìm thấy sách");
            return;
        } else {
            for (Sach s : sachList
            ) {
                if(s.getTrangthai().equals(Hangso.trangthaingungban)){
                    continue;
                }
                if (servicetheloai.findById(s.getMatheloai()).getTentheloai().trim().toLowerCase().contains(tk.trim().toLowerCase())) {
                    double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                    double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                    giaban=Math.round(giaban);

                    DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                    System.out.println("|| **mã sách: " + s.getMasach() + "|| **Tên sách: " + s.getTensach()+(s.getSoluong()!=0?" ||**Số lương:"+s.getSoluong():" ||**Trạng thái: Hết hàng")+ "|| **Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + "|| **Tác giả: " + s.getTacgia() +
                            "\n" +"|| **Giá bán:" + dinhDangSo.format(s.getGiaxuat()) +Hangso.vnd + "|| **Khuyến mại: " + (int) tongkhuyenmai + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(Math.round(giaban))+Hangso.vnd);
                    System.out.println("\u001B[35m"+"--------------------------------------------------------------------------------------------------------"+"\u001B[34m");

                    check=1;
                }
            }
        }
        if(check==-1){
            System.err.println("Không tìm thấy sách");
        }
    }

    public static void timkiemtheoten() {
        Servicetheloai servicetheloai = new Servicetheloai();

        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        System.out.println("Mời bạn nhập vào tên sách:");
        String tk = InputMethods.getString();
        int check=-1;
        if (sachList.isEmpty()) {
            System.out.println("Không tìm thấy sách");
        } else {
            for (Sach s : sachList
            ) {
                if(s.getTrangthai().equals(Hangso.trangthaingungban)){
                    continue;
                }
                if (s.getTensach().trim().toLowerCase().contains(tk.trim().toLowerCase())) {
                    double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                    double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                    giaban=Math.round(giaban);

                    DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                    System.out.println("|| **mã sách: " + s.getMasach() + "|| **Tên sách: " + s.getTensach()+(s.getSoluong()!=0?" ||**Số lương:"+s.getSoluong():" ||**Trạng thái: Hết hàng") + "|| **Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + "|| **Tác giả: " + s.getTacgia() +
                            "\n" +"|| **Giá bán:" + dinhDangSo.format(s.getGiaxuat()) +Hangso.vnd + "|| **Khuyến mại: " + (int) tongkhuyenmai + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(Math.round(giaban))+Hangso.vnd);
                    System.out.println("\u001B[35m"+"--------------------------------------------------------------------------------------------------------"+"\u001B[34m");
                    check=1;
                }
            }
        }
        if(check==-1){
            System.err.println("Không tìm thấy sách");
        }
    }

    public static void hienthitoanbo() {
        Servicetheloai servicetheloai = new Servicetheloai();

        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();

        if (sachList.isEmpty()) {
            System.out.println("Không tìm thấy sách");
        } else {
            for (Sach s : sachList
            ) {
if(s.getTrangthai().equals(Hangso.trangthaingungban)){
    continue;
}
                double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                giaban=Math.round(giaban);

                DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                System.out.println("|| **mã sách: " + s.getMasach() + "|| **Tên sách: " + s.getTensach()+(s.getSoluong()!=0?" ||**Số lương:"+s.getSoluong():" ||**Trạng thái: Hết hàng") + "|| **Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + "|| **Tác giả: " + s.getTacgia() +
                        "\n" +"|| **Giá bán:" + dinhDangSo.format(s.getGiaxuat()) +Hangso.vnd + "|| **Khuyến mại: " + (int) tongkhuyenmai + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(Math.round(giaban))+Hangso.vnd);
                System.out.println("\u001B[35m"+"--------------------------------------------------------------------------------------------------------"+"\u001B[34m");

            }
        }
    }
    public static void timkiemtonghop() {
        Servicetheloai servicetheloai = new Servicetheloai();

        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        System.out.println("Mời bạn nhập vào từ khóa:");
        String tk = InputMethods.getString();
        int check=-1;
        if (sachList.isEmpty()) {
            System.out.println("Không tìm thấy sách");
        } else {
            for (Sach s : sachList
            ) {
                if(s.getTrangthai().equals(Hangso.trangthaingungban)){
                    continue;
                }
                if (s.getTensach().trim().toLowerCase().contains(tk.trim().toLowerCase())||servicetheloai.findById(s.getMatheloai()).getTentheloai().toLowerCase().contains(tk.toLowerCase())) {
                    double tongkhuyenmai = s.getKhuyenmai() + servicetheloai.findById(s.getMatheloai()).getKhuyenmaitheloai();
                    double giaban = s.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                    giaban=Math.round(giaban);

                    DecimalFormat dinhDangSo = new DecimalFormat("#,###");
                    System.out.println("|| **mã sách: " + s.getMasach() + "|| **Tên sách: " + s.getTensach()+(s.getSoluong()!=0?" ||**Số lương:"+s.getSoluong():" ||**Trạng thái: Hết hàng") + "|| **Thể loại: " + servicetheloai.findById(s.getMatheloai()).getTentheloai() + "|| **Tác giả: " + s.getTacgia() +
                            "\n" +"|| **Giá bán:" + dinhDangSo.format(s.getGiaxuat()) +Hangso.vnd + "|| **Khuyến mại: " + (int) tongkhuyenmai + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(Math.round(giaban))+Hangso.vnd);
                    System.out.println("\u001B[35m"+"--------------------------------------------------------------------------------------------------------"+"\u001B[34m");
                    check=1;
                }
            }
        }
        if(check==-1){
            System.err.println("Không tìm thấy sách");
        }
    }
}

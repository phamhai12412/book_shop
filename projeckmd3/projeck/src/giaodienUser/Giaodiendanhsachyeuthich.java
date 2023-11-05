package giaodienUser;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Khachhang;
import model.Sach;
import run.Giaodientimkiem;
import service.Servicekhachhang;
import service.Servicesach;
import service.Servicetheloai;

import java.text.DecimalFormat;
import java.util.List;

public class Giaodiendanhsachyeuthich {


public static void Danhsachyeuthich(String mauseonl){



        int choice;
        do {
            System.out.println("============Danh sách yêu thích===========");
            System.out.println("1. Hiển thị danh sách yêu thích");
            System.out.println("2. Thêm vào danh sách yêu thích");

            System.out.println("3. Xóa yêu thích");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("0. Trở về");
            System.out.println("==========================================");
            System.out.println("Vui lòng chọn: ");
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:
                    hienthisachyeuthich(mauseonl);
                    break;
                case 2:
                    themyeuthich(mauseonl);
                    break;
                case 3:
                    xoayeuthich(mauseonl);
                    break;
                case 4:
                    Giaodientimkiem.Menutimkiem();

                    break;
                case 5:
                    break;
                case 0:

                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);


    }

    private static void hienthisachyeuthich(String mauseonl) {
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        Khachhang usedangonl=servicekhachhang.findById(mauseonl);
       List<Sach> sachListyeuthich =usedangonl.getSachyeuthich();
        Servicetheloai servicetheloai=new Servicetheloai();
        if (sachListyeuthich.isEmpty()) {
            System.err.println("Danh sách yêu thích trống.");
        } else {
            DecimalFormat dinhDangSo = new DecimalFormat("#,###");
            System.out.println("Danh sách yêu thích:");
            for (Sach sach:sachListyeuthich) {
                double tongkhuyenmai = sach.getKhuyenmai() + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai();
                double giaban = sach.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                System.out.println("|| **mã sách: " + sach.getMasach() + "|| **Tên sách: " + sach.getTensach()+"|| Số lương:"+sach.getSoluong() + "|| **Thể loại: " + servicetheloai.findById(sach.getMatheloai()).getTentheloai() + "|| **Tác giả: " + sach.getTacgia() +
                        "\n" +"|| **Giá bán:" + dinhDangSo.format(sach.getGiaxuat()) + Hangso.vnd + "|| **Khuyến mại: " + (int) Math.round(tongkhuyenmai ) + "%" + "|| **Giá sau khuyến mãi: " + dinhDangSo.format(giaban )+Hangso.vnd );
                System.out.println("--------------------------------------------------------------------------------------");


            }
        }
    }

    private static void themyeuthich(String mauseonl) {
        IOFile<Khachhang> khachhangIOFile=new IOFile<>();
    Servicekhachhang servicekhachhang=new Servicekhachhang();
    List<Khachhang> khachhangList=servicekhachhang.getAll();
        Servicesach servicesach=new Servicesach();
    Khachhang usedangonl=servicekhachhang.findById(mauseonl);
    List<Sach> listsachyeuthich=usedangonl.getSachyeuthich();
        System.out.println("Mời bạn nhập vào mã sách để thêm vào yêu thích");
        String sachyeuthich = InputMethods.getString();
        Sach sach=servicesach.findById(sachyeuthich);
     if(sach==null){
         System.err.println("Không tìm thấy sách");
     }else {
         for (Sach s :listsachyeuthich
                 ) {
             if(s.getMasach().equals(sach.getMasach()))
             {
                 System.err.println("Bạn đã thêm sách vào yêu thích rồi");
                 return;
             }
         }

         listsachyeuthich.add(servicesach.findById(sachyeuthich));
         System.out.println("Đã thêm vào danh sách yêu thích.");
         khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);
     }

    }



    private static void xoayeuthich(String mauseonl) {
        IOFile<Khachhang> khachhangIOFile = new IOFile<>();
        Servicekhachhang servicekhachhang = new Servicekhachhang();
        List<Khachhang> khachhangList = servicekhachhang.getAll();
        Khachhang usedangonl = servicekhachhang.findById(mauseonl);
        Servicesach servicesach = new Servicesach();
        List<Sach> sachListyeuthich = usedangonl.getSachyeuthich();

        if (sachListyeuthich.isEmpty()) {
            System.err.println("Danh sách yêu thích trống.");
        } else {
            hienthisachyeuthich(mauseonl);
            System.out.println("Nhập mã sách bạn muốn hủy yêu thích: ");
            String masach = InputMethods.getString();

            Sach sach = servicesach.findById(masach);
            if (sach == null) {
                System.err.println("Không tìm thấy sách.");
            } else {
                for (int i = 0; i < sachListyeuthich.size(); i++) {
                    if(sachListyeuthich.get(i).getMasach().equals(sach.getMasach())){
                        sachListyeuthich.remove(i);
                        usedangonl.setSachyeuthich(sachListyeuthich);
                    khachhangIOFile.writeToFile(khachhangList, IOFile.LISTUSE_FILE);
                    System.out.println("Đã xóa sách khỏi danh mục yêu thích thành công.");
                    }

                }
            }
        }
    }

}

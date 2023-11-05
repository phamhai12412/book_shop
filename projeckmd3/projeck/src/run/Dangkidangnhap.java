package run;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import giaodienAdmin.Giaodienadmin;
import model.Admin;
import model.Khachhang;
import service.Serviceadmin;
import service.Servicekhachhang;

import java.util.List;

public class Dangkidangnhap {

    public static void Dangki_Dangnhap(Servicekhachhang servicekhachhang) {

        int choice;
        do {
            System.out.println("==========Đăng kí_Đăng nhập============");
            System.out.println("1. Đăng kí");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");
            System.out.println("=======================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    // Gọi phương thức đăng kí ở đây
                    System.out.println("================Đăng kí=================");
                    if (Dangki(servicekhachhang)) {
                        if (Dangnhap(servicekhachhang)) {
                            System.out.println("nếu lỗi logic đăng kí đăng nhập sẽ xuất hiện dòng này");
                        } else {
                            choice = 100;
                        }
                    } else {
                        choice = 100;
                    }

                    break;
                case 2:
                    // Gọi phương thức đăng nhập ở đây
                    System.out.println("============Đăng nhập==================");

                    if (Dangnhap(servicekhachhang)) {
                        choice=3;
                    } else {
                        choice = 100;
                    }
                    break;
                case 3:
                    Giaodiennguoidung.Chuadangnhap();
                    choice = 3;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 3);


    }

    public static boolean Dangki(Servicekhachhang servicekhachhang) {
        Serviceadmin serviceadmin = new Serviceadmin();
        Admin admin=serviceadmin.getadmin();

        List<Khachhang> listkhachhang = servicekhachhang.getAll();
        Khachhang newkhachhang = new Khachhang();
        System.out.println("Mời bạn nhập vào usename");
        String usename = InputMethods.getusename();
        if(admin.getTaikhoan().equals(usename)){
            System.err.println("==============Tài khoản đã tồn tại==============");
            return false;
        }
        for (int i = 0; i < listkhachhang.size(); i++) {
            if (listkhachhang.get(i)!=null&& listkhachhang.get(i).getTaikhoan().equals(usename)) {
                System.err.println("==============Tài khoản đã tồn tại==============");
                return false;
            }
        }
        newkhachhang.setTaikhoan(usename);
        newkhachhang.inputkhachhang();
        int id = (servicekhachhang.getNewId());
        newkhachhang.setIdkhachhang(id);
        newkhachhang.setMakhachhang("MKH" + (100 + id));
        servicekhachhang.save(newkhachhang);
        System.out.println("==============Đã đăng ký thành công==========");
        System.out.println("==============Mời bạn đăng nhập==============");
        return true;
    }

    public static boolean Dangnhap(Servicekhachhang servicekhachhang) {
        Serviceadmin serviceadmin = new Serviceadmin();
        IOFile<Khachhang> ioFile = new IOFile<>();
        System.out.println("Mời bạn nhập vào usename:");
        String usename = InputMethods.getusename();
        System.out.println("Mời bạn nhập vào password:");
        String password = InputMethods.getpassword();
        List<Khachhang> listkhachhang = servicekhachhang.getAll();
     Admin admin = serviceadmin.getadmin();

                if (admin.getTaikhoan().equals(usename) && admin.getMatkhau().equals(password)) {
                    System.out.println("\u001B[35m"+"==============Xin chào ADMIN============"+"\u001B[34m");
                    Giaodienadmin.AdminMenu();
                    return true;
                }


        int check = -1;
        for (int i = 0; i < listkhachhang.size(); i++) {
            if (listkhachhang.get(i) == null) {
                continue;
            }
            if (listkhachhang.get(i).getTaikhoan().equals(usename) && listkhachhang.get(i).getMatkhau().equals(password)) {
                if(listkhachhang.get(i).getXetkhoa().equals(Hangso.trangthaikhoa)){
                    System.err.println("Tài khoản bạn đã bị khóa.");
                    return false;
                }
                check = 1;
                listkhachhang.get(i).setTrangthai("dangnhap");


            } else {
                listkhachhang.get(i).setTrangthai("dangxuat");
            }
        }

        ioFile.writeToFile(listkhachhang, IOFile.LISTUSE_FILE);

        if (check == 1) {
            System.out.println("==========Đăng nhập thành công=========");
            Giaodiennguoidung.Dadangnhap();
            return true;
        }
        System.err.println("===========Đăng nhập thất bại==========");
        return false;
    }
}

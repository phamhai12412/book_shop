package service;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Khachhang;


import java.util.List;

public class Servicekhachhang {
    private IOFile<Khachhang> ioFile;
    private List<Khachhang> listkhachhang ;

    public Servicekhachhang() {
        ioFile = new IOFile<>();
        this.listkhachhang = ioFile.readFromFile(IOFile.LISTUSE_FILE);
    }


    public List<Khachhang> getAll() {
        return listkhachhang;
    }


    public void save(Khachhang khachhang) {
        if (findById(khachhang.getMakhachhang()) == null) {
            listkhachhang.add(khachhang);
        } else {
            listkhachhang.set(listkhachhang.indexOf(findById(khachhang.getMakhachhang())), khachhang);
        }
        ioFile.writeToFile(listkhachhang,IOFile.LISTUSE_FILE);
    }


    public Khachhang findById(String makhachhang) {
        for (Khachhang c : listkhachhang) {
            if (c.getMakhachhang() .equals(makhachhang) ) {
                return c;
            }
        }
        return null;
    }


    public void Khoause(String makhachhang) {
        if (findById(makhachhang) != null) {
            if(findById(makhachhang).getXetkhoa().equals(Hangso.trangthaikhoa)){
                System.err.println("Tài khoản này đã bị bạn khóa trước đó. Vui lòng kiểm tra lại");
                return;
            }
            Khachhang khachhang=findById(makhachhang).clone();
            khachhang.setXetkhoa(Hangso.trangthaikhoa);
            System.out.println("==============Xác nhận khóa==============");
            System.out.println("Nhập ok để Xác nhận, nhập kí tự bất kì để hủy bỏ");
            String kt= InputMethods.getString().trim().toUpperCase();
            if(kt.equals("OK")){
                listkhachhang.set(listkhachhang.indexOf(findById(khachhang.getMakhachhang())), khachhang);
                ioFile.writeToFile(listkhachhang,IOFile.LISTUSE_FILE);
                System.out.println("==============Khóa use thành công==============");

            }else {
                System.out.println("==============Đã hủy====================");
            }

        } else {
            System.err.println("========Không tìm thấy use này==========");
        }
    }

    public int getNewId() {
        int idMax = 0;
        for (Khachhang c : listkhachhang) {
            if (c.getIdkhachhang() > idMax) {
                idMax = c.getIdkhachhang();
            }
        }
        return idMax + 1;
    }

    public void Mokhoause(String use) {
        if (findById(use) != null) {
            if(findById(use).getXetkhoa().equals(Hangso.trangthaikhongkhoa)){
                System.err.println("Tài khoản này không bị khóa vui lòng kiểm tra lại.");
                return;
            }
            Khachhang khachhang=findById(use).clone();
            khachhang.setXetkhoa(Hangso.trangthaikhongkhoa);
            System.out.println("==============Xác nhận mở==============");
            System.out.println("Nhập ok để Xác nhận, nhập kí tự bất kì để hủy bỏ");
            String kt= InputMethods.getString();
            if(kt.equals("ok")){
                listkhachhang.set(listkhachhang.indexOf(findById(khachhang.getMakhachhang())), khachhang);
                ioFile.writeToFile(listkhachhang,IOFile.LISTUSE_FILE);
                System.out.println("==============Mở Khóa use thành công==============");
            }else {
                System.out.println("==============Đã hủy====================");
            }

        } else {
            System.err.println("========Không tìm thấy use này==========");
        }
    }
}
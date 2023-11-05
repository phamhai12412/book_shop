package service;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Sach;

import java.util.List;

public class Servicesach {
    private IOFile<Sach> ioFile;
    private List<Sach> listsach ;

    public Servicesach() {
        ioFile = new IOFile<>();
        this.listsach = ioFile.readFromFile(IOFile.LISTSACH_FILE);
    }


    public List<Sach> getAll() {
        return listsach;
    }


    public void save(Sach sach) {
        if (findById(sach.getMasach()) == null) {
            listsach.add(sach);
            System.out.println("==============Thêm mới thành công==============");
        } else {
            listsach.set(listsach.indexOf(findById(sach.getMasach())), sach);
            System.out.println("==============Cập nhật thành công==============");

        }
        ioFile.writeToFile(listsach,IOFile.LISTSACH_FILE);
    }


    public Sach findById(String s) {
        for (Sach p : listsach) {
            if (p.getMasach().equals(s)) {
                return p;
            }
        }
        return null;
    }


    public void Khoasach(String masach) {

        if (findById(masach) != null) {
            if (findById(masach).getTrangthai().equals(Hangso.trangthaingungban)){
                System.err.println("Sách đang ở trạng thái ngừng bán xin kiểm tra lại:");
                return;
            }
Sach sach=findById(masach).clone();
sach.setTrangthai(Hangso.trangthaingungban);
            System.out.println("==============Xác nhận khóa==============");
            System.out.println("Nhập ok để Xác nhận, nhập kí tự bất kì để hủy bỏ");
            String kt= InputMethods.getString();
           if(kt.equals("ok")){
               listsach.set(listsach.indexOf(findById(sach.getMasach())), sach);
               ioFile.writeToFile(listsach,IOFile.LISTSACH_FILE);
               System.out.println("=========Khóa sách thành công===========");
           }else {
               System.out.println("==============Đã hủy====================");
           }

        } else {
            System.err.println("Không tìm thấy sách");
        }
    }
    public void Mosach(String masach) {
        if (findById(masach) != null) {
            if (findById(masach).getTrangthai().equals(Hangso.trangthaidangban)){
                System.err.println("Sách đang ở trạng thái Đang bán xin kiểm tra lại:");
                return;
            }
            Sach sach=findById(masach).clone();
            sach.setTrangthai(Hangso.trangthaidangban);
            System.out.println("==============Xác nhận mở==============");
            System.out.println("Nhập ok để Xác nhận, nhập kí tự bất kì để hủy bỏ");
            String kt= InputMethods.getString();
            if(kt.equals("ok")){
                listsach.set(listsach.indexOf(findById(sach.getMasach())), sach);
                ioFile.writeToFile(listsach,IOFile.LISTSACH_FILE);
                System.out.println("==============Mở sách thành công==============");
            }else {
                System.out.println("==============Đã hủy====================");
            }

        } else {
            System.err.println("Không tìm thấy sách này");
        }
    }
    public int getNewId() {
        int idMax = 0;
        for (Sach c : listsach) {
            if (c.getIdsach() > idMax) {
                idMax = c.getIdsach();
            }
        }
        return idMax + 1;
    }
    public  void Eventsach(String ms, int s){
        if(findById(ms)==null){
            System.out.println("không tìm thấy sách");
        }
        else {
            Sach sach=findById(ms);
            sach.setKhuyenmai(s);
            ioFile.writeToFile(listsach,IOFile.LISTSACH_FILE);
            System.out.println("Cập nhật thành công");
        }
    }
}
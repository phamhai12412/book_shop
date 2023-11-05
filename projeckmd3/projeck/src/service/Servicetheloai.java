package service;

import data.IOFile;
import model.Sach;
import model.Theloai;

import java.util.List;

public class Servicetheloai {

        private IOFile<Theloai> ioFile;
        private List<Theloai> listtheloai ;

        public Servicetheloai() {
            ioFile = new IOFile<>();
            this.listtheloai = ioFile.readFromFile(IOFile.LISTDANHMUC_FILE);
        }


        public List<Theloai> getAll() {
            return listtheloai;
        }


        public void save(Theloai theloai) {
            if (findById(theloai.getMatheloai()) == null) {
                listtheloai.add(theloai);
                System.out.println("==============Thêm mới thành công==============");
            } else {
                listtheloai.set(listtheloai.indexOf(findById(theloai.getMatheloai())), theloai);
                System.out.println("==============Cập nhật thành công==============");

            }
            ioFile.writeToFile(listtheloai,IOFile.LISTDANHMUC_FILE);
        }


        public Theloai findById(String s) {
            for (Theloai p : listtheloai) {
                if (p.getMatheloai().equals(s)) {
                    return p;
                }
            }
            return null;
        }


    public void delete(String matheloai) {
            Servicesach servicesach=new Servicesach();
            List<Sach> sachList=servicesach.getAll();
            if(!sachList.isEmpty()) {
                for (Sach s : sachList
                ) {
                    if (s.getMatheloai().equals(matheloai)) {
                        System.err.println("không thể xóa vì vẫn còn sách trong danh mục");
                        return;
                    }
                }
            }
        if (findById(matheloai) != null) {
            listtheloai.remove(findById(matheloai));
            ioFile.writeToFile(listtheloai,IOFile.LISTDANHMUC_FILE);
            System.out.println("==============Xóa thành công==============");
        } else {
            System.err.println("==============Không có danh mục này==============");
        }
    }

    public int getNewId() {
            int idMax = 0;
            for (Theloai c : listtheloai) {
                if (c.getIdtheloai() > idMax) {
                    idMax = c.getIdtheloai();
                }
            }
            return idMax + 1;
        }
    public  void Eventtheloai(String ms, int s){
        if(findById(ms)==null){
            System.err.println("không tìm thấy the loai ");
        }
        else {
            Theloai theloai=findById(ms);
            theloai.setKhuyenmaitheloai(s);
            ioFile.writeToFile(listtheloai,IOFile.LISTDANHMUC_FILE);
            System.out.println("Cập nhật thành công");
        }
    }
    }


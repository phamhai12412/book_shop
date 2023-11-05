package giaodienUser;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Donhang;
import model.Khachhang;
import model.Sach;
import model.Sachdamua;
import run.Giaodientimkiem;
import service.Servicedonhang;
import service.Servicekhachhang;
import service.Servicesach;
import service.Servicetheloai;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Chucnangdathang {
    public static void dathang(String mataikhoan) {


        IOFile<Sach> ioFilesach = new IOFile<>();
        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();
        Servicetheloai servicetheloai = new Servicetheloai();
        Servicedonhang servicedonhang = new Servicedonhang();

        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        Servicekhachhang servicekhachhang = new Servicekhachhang();
        List<Khachhang> khachhangList = servicekhachhang.getAll();
        Khachhang useonl = servicekhachhang.findById(mataikhoan);
        if (sachList.isEmpty()) {
            System.err.println("Xin lỗi bạn toàn bộ sách trong kho đã hết:");
            return;
        }
        System.out.println("Đặt hàng cần dùng mã sách nên bạn bỏ chút thời gian lấy mã sách nhé:");

        Giaodientimkiem.Menutimkiem();
        Donhang donhang = new Donhang();
        List<Sachdamua> sachdamuaList = new ArrayList<>();
        int check = -1;

        boolean tiepTucMuaHang = true;
        while (tiepTucMuaHang) {

            System.out.println("Mời bạn nhập vào mã sách muốn mua:");
            String masach = InputMethods.getString();
            Sach sachmuonmua = servicesach.findById(masach);
            if (sachmuonmua == null || sachmuonmua.getTrangthai().equals(Hangso.trangthaingungban)) {
                System.err.println("Không tìm thấy sách.");
            }else if( sachmuonmua.getSoluong() <= 0){
                System.err.println("Sách này trong kho hiện đang hết hàng. Mời bạn quay lại sau.");
            }
            else {
                System.out.println("nhập vào số lượng bạn muôn mua");

                int soLuongMua = InputMethods.getPositiveInteger();

                if (sachmuonmua.getSoluong() < soLuongMua) {
                    do {
                        System.err.println("Số lượng sách này trong kho không đủ số lượng bán bạn vừa nhập:");
                        System.out.println("số sách này trong kho còn: " + sachmuonmua.getSoluong());
                        System.out.println("Mời bạn chọn lại số lượng muốn mua");
                        soLuongMua = InputMethods.getPositiveInteger();
                    } while (sachmuonmua.getSoluong() < soLuongMua);
                }
                boolean daTonTai = false;

                for (Sachdamua sachmua : sachdamuaList) {
                    if (sachmua.getMasach().equals(masach)) {
                        sachmua.setSoluong(sachmua.getSoluong() + soLuongMua);
                        sachmuonmua.setSoluong(sachmuonmua.getSoluong()-soLuongMua);
                        daTonTai = true;
                        break;
                    }
                }
                if (!daTonTai) {
                    Sachdamua sachmua = new Sachdamua();
                    double tongkhuyenmai = sachmuonmua.getKhuyenmai() + servicetheloai.findById(sachmuonmua.getMatheloai()).getKhuyenmaitheloai();
                    double giaban = sachmuonmua.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
                    sachmua.setMasach(masach);
                    sachmua.setSoluong(soLuongMua);
                    sachmua.setTensach(sachmuonmua.getTensach());
                    sachmua.setGiamua(giaban);
                    sachmua.setMatheloai(sachmuonmua.getMatheloai());
                    sachmua.setKhuyenmaitheosach(sachmuonmua.getKhuyenmai());
                    sachmua.setKhuyenmaitheotheloai(servicetheloai.findById(sachmuonmua.getMatheloai()).getKhuyenmaitheloai());
                    sachdamuaList.add(sachmua);
                    donhang.setSachdamua(sachdamuaList);
                    //cập nhật số lượng trong kho lại cho sách
                    sachmuonmua.setSoluong(sachmuonmua.getSoluong() - soLuongMua);

                    check = check + 1;
                }
            }


            System.out.println("Bạn có muốn tiếp tục thêm sản phẩm:");
            System.out.println("nhập ok để tiếp tục thêm sản phẩm, nhập bất kì kí tự khác để hoàn thành đặt hàng");
            String luaChon = InputMethods.getString();

            if (!luaChon.equalsIgnoreCase("ok")) {
                tiepTucMuaHang = false;
            }
        }
        if (check != -1) {
            int id = servicedonhang.getNewId();
            donhang.setIddonhang(id);
            donhang.setMagiaodich("MGD" + (100 + id + 1));
            donhang.setMakhachhang(useonl.getMakhachhang());
            System.out.println("Mời bạn nhập vào tên người nhận hàng:");
            donhang.setNguoinhan(InputMethods.gettentheloaivatensach());
            System.out.println("Nhập vào ghi chú:");
            donhang.setGhichu(InputMethods.getString());
            System.out.println("Mời bạn nhập vào địa chỉ nhận hàng:");
            donhang.setDiachi(InputMethods.getdiachi());
            System.out.println("Mời bạn nhập vào số điện thoại:");
            donhang.setSdt(InputMethods.getPhoneNumber());


            System.out.println("Thông tin mua hàng:");

//cập nhật lại danh sách lịch sử mua hàng cho use
            List<String> newlichsuamuahang = useonl.getListdonhang();
            newlichsuamuahang.add("MGD" + (100 + id + 1));
            useonl.setListdonhang(newlichsuamuahang);

            SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            System.out.println("\u001B[35m"+"Đơn hàng bạn vừa đặt:");
            System.out.println("**Người nhận:"+donhang.getNguoinhan());
            System.out.println("**Mã giao dich:" + donhang.getMagiaodich());
            System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
            System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
            System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());

            List<Sachdamua> donhangvuamua = donhang.getSachdamua();
            DecimalFormat dinhDangSo = new DecimalFormat("#,###");
            double tong = 0;
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
            for (Sachdamua sachdamua : donhangvuamua
            ) {
Sach sach=servicesach.findById(sachdamua.getMasach());
                System.out.println("||**Tên sách: " + sachdamua.getTensach()  + " **Số lương: " + sachdamua.getSoluong() +"||**Giá bán: "+dinhDangSo.format(sach.getGiaxuat())+Hangso.vnd +"\n"+ "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach() +"%" + "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai() +"%" + "||**Tổng khuyến mãi: " +(int)Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai()) +"%" + " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua())+Hangso.vnd );
                tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                System.out.println("\u001B[34m"+"----------------------------------------------------------------------------"+"\u001B[35m");
            }
            tong=Math.round(tong);


            System.out.println("**Tổng tiền: " + dinhDangSo.format(tong) +Hangso.vnd);
            System.out.println("bạn có muốn thanh toán bằng ví không");
            System.out.println(("nhập 'ok' để thực hiện thanh toán bằng ví, kí tự khác để thanh toán mặc định khi nhận hàng"));
            String chon=InputMethods.getString();
            if(chon.trim().equalsIgnoreCase("OK")){
                if(useonl.getVidientu()<tong){
                    System.err.println("ví của bạn không đủ để thanh toán, chuyển qua hình thức thanh toán mặc định");
                }else {
                    useonl.setVidientu(useonl.getVidientu()-tong);
                    donhang.setPhuongthucthanhtoan(Hangso.thanhtoanbangvi);
                    System.out.println("Đã thanh toán bằng ví");
                }
            }else {
                System.out.println("Thanh toán khi nhận hàng");
            }
            System.out.println("đặt hàng thành công");
            System.out.println("phương thức thanh toán:"+donhang.getPhuongthucthanhtoan()+"\u001B[34m");
            servicedonhang.themmoi(donhang);
            ioFilekhachhang.writeToFile(khachhangList, IOFile.LISTUSE_FILE);
            //cập nhật lại số sách trong kho
            ioFilesach.writeToFile(sachList, IOFile.LISTSACH_FILE);
        } else {
            System.err.println("Đặt hàng thất bại"+"\u001B[34m");
        }
    }

}

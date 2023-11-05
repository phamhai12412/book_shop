package giaodienUser;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.*;
import run.Giaodientimkiem;
import service.Servicedonhang;
import service.Servicekhachhang;
import service.Servicesach;
import service.Servicetheloai;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Giaodiengiohanguse {
    public static void giohanguse(String mataikhoan) {


        int choice;

        do {
            displayMenu();
            choice = InputMethods.getByte();

            switch (choice) {
                case 1:


                    hienthigiohang(mataikhoan);

                    break;

                case 2:



                    themoigiohang(mataikhoan);
                    break;
                case 3:

                    thaydoisoluong(mataikhoan);


                    break;
                case 4:

                    xoatoanbogiohang(mataikhoan);
                    break;

                case 5:
                    muatoanbogiohang(mataikhoan);
                    break;
                case 6:
                    xoatungsanphamtheomma(mataikhoan);


                    break;
                case 0:
                    System.out.println("Trở về.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);
    }

    public static void displayMenu() {
        System.out.println("==============Menu Giỏ Hàng============");
        System.out.println("1. Hiện thị giỏ hàng");

        System.out.println("2. Thêm sản phẩm vào giỏ hàng");
        System.out.println("3. Thay đổi số lượng sản phẩm trong giỏ hàng");
        System.out.println("4. Xóa toàn bộ sản phẩm trong giỏ hàng");

        System.out.println("5. Mua toàn bộ sản phẩm trong giỏ hàng");
        System.out.println("6. Xóa sản phẩm trong giỏ hàng theo mã sản phẩm");

        System.out.println("0. Trở về");
        System.out.println("=======================================");
        System.out.println("Chọn lựa chọn của bạn: ");
    }

    public static void themoigiohang(String mataikhoan) {
        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();

        Servicekhachhang servicekhachhang = new Servicekhachhang();
        List<Khachhang> khachhangList = servicekhachhang.getAll();
        Servicetheloai servicetheloai = new Servicetheloai();
        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        Khachhang usedangonl = servicekhachhang.findById(mataikhoan);
        if (sachList.isEmpty()) {
            System.err.println("Xin lỗi bạn toàn bộ sách trong kho đã hết:");
            return;
        }
        System.out.println("Thêm sách vào giỏ hàng cần dùng mã sách nên bạn bỏ chút thời gian lấy mã sách nhé:");

        Giaodientimkiem.Menutimkiem();
        List<Sachtronggio> giohang = usedangonl.getGiohang();

        boolean tiepTucMuaHang = true;
        while (tiepTucMuaHang) {

            System.out.println("Mời bạn nhập vào mã sách muốn thêm:");
            String masachmuonmua = InputMethods.getString();
            Sach sachmuonmua = servicesach.findById(masachmuonmua);
            if (sachmuonmua == null || sachmuonmua.getTrangthai().equals(Hangso.trangthaingungban)) {
                System.out.println("Không tìm thấy sách.");
            }else if(sachmuonmua.getSoluong() <= 0){
                System.err.println("Sách này trong kho hiện đang hết hàng. Mời bạn quay lại sau.");
            }
            else {
                System.out.println("nhập vào số lượng bạn muôn thêm");

                int soLuong = InputMethods.getPositiveInteger();
                if (sachmuonmua.getSoluong() < soLuong) {
                    do {
                        System.out.println("Số lượng sách này trong kho không đủ số lượng bán bạn vừa nhập:");
                        System.out.println("số sách này trong kho còn: " + sachmuonmua.getSoluong());
                        System.out.println("Mời bạn chọn lại số lượng muốn thêm");
                        soLuong = InputMethods.getPositiveInteger();
                    } while (sachmuonmua.getSoluong() < soLuong);
                }
                boolean daTonTai = false;

                for (Sachtronggio sachtronggio : giohang) {
                    if (sachtronggio.getMasach().equals(masachmuonmua)) {
                        sachtronggio.setSoluong(sachtronggio.getSoluong() + soLuong);
                        daTonTai = true;
                        break;
                    }
                }
                if (!daTonTai) {
                    Sachtronggio sachtronggio = new Sachtronggio();

                    sachtronggio.setMasach(masachmuonmua);
                    sachtronggio.setSoluong(soLuong);


                    giohang.add(sachtronggio);

                    //cập nhật số lượng trong kho lại cho sách


                }
            }


            System.out.println("Bạn có muốn tiếp tục thêm sản phẩm:");
            System.out.println("nhập ok để tiếp tục thêm sản phẩm, nhập bất kì kí tự khác để hoàn thành thêm hàng vào giỏ");
            String luaChon = InputMethods.getString();

            if (!luaChon.equalsIgnoreCase("ok")) {
                tiepTucMuaHang = false;
            }

        }
        usedangonl.setGiohang(giohang);
        ioFilekhachhang.writeToFile(khachhangList, IOFile.LISTUSE_FILE);
        if (giohang.isEmpty()) {
            return;
        }
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        double tong = 0;
        for (Sachtronggio g : giohang
        ) {
            Sach sach = servicesach.findById(g.getMasach());
            double tongkhuyenmai = sach.getKhuyenmai() + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai();
            double giaban = sach.getGiaxuat() * (1 -  (tongkhuyenmai) / 100);
            System.out.println("Thông tin sách:");
            System.out.println("||**Mã sách: " +g.getMasach()+" ||**Tên sách:" + sach.getTensach() + "||**Thể loại: " + servicetheloai.findById(sach.getMatheloai()).getTentheloai() + " ||**Tác giả: " + sach.getTacgia() + " ||**Số lương: " + g.getSoluong()+" ||**Giá: "+dinhDangSo.format(sach.getGiaxuat())+Hangso.vnd );
            System.out.println("||**khuyên mãi theo sách:" + sach.getKhuyenmai()+"%" + "|| **Khuyến mãi theo thể loại: " + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai()+"%" + "||**Tổng khuyến mãi: " +(int) Math.round(tongkhuyenmai) +"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format( giaban)+Hangso.vnd);
            tong += giaban * g.getSoluong();

        }
        System.out.println("**Tổng tiền: " + dinhDangSo.format(tong)+Hangso.vnd );
    }

    //đoạn này có thể tận dụng để hiện thị giỏ hàng
    public static void thaydoisoluong(String mataikhoan) {

        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();


        Servicesach servicesach = new Servicesach();

        Servicekhachhang servicekhachhang = new Servicekhachhang();
        List<Khachhang> khachhangList = servicekhachhang.getAll();
        Khachhang usedangonl = servicekhachhang.findById(mataikhoan);
        List<Sachtronggio> giohang = usedangonl.getGiohang();
        if (giohang.isEmpty()) {
            System.err.println("giỏ hàng đang trống");
            return;
        }
        boolean tiepTucThayDoi = true;

        do {
            System.out.println("Nhập mã sách cần thay đổi số lượng:");
            String ms = InputMethods.getString();

            boolean sachDaTimThay = false;

            for (Sachtronggio sachtronggio : giohang) {
                if (sachtronggio.getMasach().equals(ms)) {
                    sachDaTimThay = true;
                    int soluongsach = servicesach.findById(sachtronggio.getMasach()).getSoluong();

                    System.out.println("Số lượng hiện tại của sách là: " + sachtronggio.getSoluong());
                    System.out.println("số lượng sách này còn trong kho là:" + soluongsach);



                    System.out.println("Nhập số lượng mới nhỏ hơn hoặc bằng:"+soluongsach);
                    int soLuongMoi = InputMethods.getthaydoisoluongsach();

                    if (soLuongMoi > 0 && soLuongMoi <= soluongsach) {
                        sachtronggio.setSoluong(soLuongMoi);
                        System.out.println("Đã thay đổi số lượng sách thành công.");
                    }else if(soLuongMoi <= 0){
giohang.remove(sachtronggio);
                        System.out.println("bạn đã nhập số lượng bằng 0 nên sách đã bị xóa khỏi giỏ hàng.");
                    }
                    else {
                        System.err.println("Thay đổi số lượng không thành công, số lượng sách bạn muốn cập nhật lớn hơn số lượng sách còn lại trong kho");
                    }
                    ioFilekhachhang.writeToFile(khachhangList, IOFile.LISTUSE_FILE);
                    break;
                }
            }

            if (!sachDaTimThay) {
                System.err.println("Không tìm thấy sách trong giỏ hàng.");
            }

            System.out.println("Bạn có muốn tiếp tục thay đổi số lượng sách trong giỏ hàng không");
            System.out.println("Nhập 'ok' để tiếp tục, hoặc nhập bất kỳ kí tự khác để dừng.");
            String luaChon = InputMethods.getString();

            if (!luaChon.equalsIgnoreCase("ok")) {
                tiepTucThayDoi = false;
            }
        } while (tiepTucThayDoi);
    }
    public static void hienthigiohang(String mataikhoan){
        Servicekhachhang servicekhachhang = new Servicekhachhang();

        Servicetheloai servicetheloai = new Servicetheloai();


        Servicesach servicesach = new Servicesach();

        Khachhang usedangonl = servicekhachhang.findById(mataikhoan);

        List<Sachtronggio> giohang = usedangonl.getGiohang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        if (giohang.isEmpty()) {
            System.err.println("giỏ hàng đang trống");
            return;
        }
        double tong = 0;
        for (Sachtronggio g : giohang
        ) {
            Sach sach = servicesach.findById(g.getMasach());
            double tongkhuyenmai = sach.getKhuyenmai() + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai();
            double giaban = (sach.getGiaxuat() * (1 - (tongkhuyenmai) / 100));
            System.out.println("Thông tin sách:");
            System.out.println("||**Mã sách: " +g.getMasach()+"||**Tên sách: " + sach.getTensach() + "||**Thể loại: " + servicetheloai.findById(sach.getMatheloai()).getTentheloai() + " ||**Tác giả: " + sach.getTacgia() + " ||**Số lương: " + g.getSoluong()+" ||**Giá bán: "+ dinhDangSo.format(sach.getGiaxuat())+Hangso.vnd );
            System.out.println("||**khuyên mãi theo sách:" + sach.getKhuyenmai()+"%" + "|| **Khuyến mãi theo thể loại: " + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai()+"%" + "||**Tổng khuyến mãi: " + (int) Math.round(tongkhuyenmai) +"%" + " ||**Giá sách sau khuến mãi: " +dinhDangSo.format(giaban) +Hangso.vnd);
            tong += giaban * g.getSoluong();
        }
        System.out.println("**Tổng tiền: " + dinhDangSo.format(tong) +Hangso.vnd);
    }
    public static void xoatoanbogiohang(String mataikhoan){
        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();
        Servicekhachhang servicekhachhang = new Servicekhachhang();

        List<Khachhang> khachhangList = servicekhachhang.getAll();

        servicekhachhang.findById(mataikhoan).getGiohang().clear();
        System.out.println("Xóa toàn bộ sản phẩm trong giỏ hàng thành công:");
        ioFilekhachhang.writeToFile(khachhangList, IOFile.LISTUSE_FILE);



    }
    public static void muatoanbogiohang(String mataikhoan){
        IOFile<Sach> ioFilesach = new IOFile<>();
        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();
        Servicetheloai servicetheloai = new Servicetheloai();
        Servicedonhang servicedonhang = new Servicedonhang();
        Servicesach servicesach = new Servicesach();
        List<Sach> sachList = servicesach.getAll();
        Servicekhachhang servicekhachhang = new Servicekhachhang();
        List<Khachhang> khachhangList = servicekhachhang.getAll();
        Khachhang usedangonl = servicekhachhang.findById(mataikhoan);
        List<Sachtronggio> listgiohang= usedangonl.getGiohang();
        List<Sachdamua> sachdamuaList= new ArrayList<>();
        if (listgiohang.isEmpty()) {
            System.err.println("giỏ hàng đang trống");
            return;
        }
        if (sachList.isEmpty()) {
            System.out.println("Xin lỗi bạn toàn bộ sách trong kho đã hết:");
            return;
        }
        System.out.println("giỏ hàng của bạn hiện tại có các sản phẩm sau:"+"\u001B[35m");
        hienthigiohang(mataikhoan);

        Donhang donhang = new Donhang();
        List<Sachtronggio> giohang = usedangonl.getGiohang();
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        int check=1;
        for (Sachtronggio g: giohang
        ) {
         Sach sach =  servicesach.findById(g.getMasach());
            double tongkhuyenmai = sach.getKhuyenmai() + servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai();
            double giaban = sach.getGiaxuat() * (1 - (tongkhuyenmai) / 100);
            if(g.getSoluong()> sach.getSoluong()){
                System.out.println("\u001B[31m"+"----------------------------------------------------------------------");
                System.out.println("||**Mã sách: "+g.getMasach()+"||**Tên sách: " + sach.getTensach()+ " **Số lương: " + g.getSoluong() +"\n"+ "||**khuyên mãi theo sách:" + sach.getKhuyenmai()+"%" + "|| **Khuyến mãi theo thể loại: " +servicetheloai.findById( sach.getMatheloai()).getKhuyenmaitheloai()+"%"  + "||**Tổng khuyến mãi: " +(int)Math.round(tongkhuyenmai)  +"%" + " ||**Giá sách sau khuyến mãi: " + dinhDangSo.format(giaban) +Hangso.vnd );
                System.out.println("có số lượng lớn hơn số lượng sách trong kho bạn hãy điều chỉnh lại số lượng nhỏ hơn hoặc bằng số lượng sách còn trong kho"+"\u001B[34m");
                check=-1;
            }else {

                Sachdamua sachdinhmua=new Sachdamua();
sachdinhmua.setIdsachdamua(sach.getIdsach());
sachdinhmua.setMasach(sach.getMasach());
                sachdinhmua.setTensach(sach.getTensach());
                sachdinhmua.setGiamua(giaban);
                sachdinhmua.setMatheloai(sach.getMatheloai());
                sachdinhmua.setKhuyenmaitheosach(sach.getKhuyenmai());
                sachdinhmua.setKhuyenmaitheotheloai(servicetheloai.findById(sach.getMatheloai()).getKhuyenmaitheloai());
sachdinhmua.setSoluong(g.getSoluong());
sachdamuaList.add(sachdinhmua);
                //cập nhật số lượng trong kho lại cho sách
                sach.setSoluong(sach.getSoluong() - g.getSoluong());

            }
        }
        if (check != -1) {
            int id = servicedonhang.getNewId();
            donhang.setSachdamua(sachdamuaList);
            donhang.setIddonhang(id);
            donhang.setMagiaodich("MGD" + (100 + id + 1));
            donhang.setMakhachhang(usedangonl.getMakhachhang());
            System.out.println("\u001B[34m"+"Nhập vào tên người nhận");
            donhang.setNguoinhan(InputMethods.getString());
            System.out.println("Nhập vào ghi chú:");
            donhang.setGhichu(InputMethods.getString());
            System.out.println("Mời bạn nhập vào địa chỉ nhận hàng:");
            donhang.setDiachi(InputMethods.getString());
            System.out.println("Mời bạn nhập vào số điện thoại:");
            donhang.setSdt(InputMethods.getPhoneNumber());
            System.out.println("Đặt hàng thành công");
            System.out.println("Thông tin mua hàng:");

//cập nhật lại danh sách lịch sử mua hàng cho use
            List<String> newlichsuamuahang = usedangonl.getListdonhang();
            newlichsuamuahang.add("MGD" + (100 + id + 1));
            usedangonl.setListdonhang(newlichsuamuahang);

            //cập nhật lại số sách trong kho

            System.out.println("Đơn hàng bạn vừa đặt:");
            SimpleDateFormat dinhdangngay = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            System.out.println("\u001B[35m"+"**Mã giao dich:" + donhang.getMagiaodich());
            System.out.println(("**Thời gian đặt hàng:" +dinhdangngay.format(donhang.getNgaygiomua()) ));
            System.out.println(("Người nhận:"+donhang.getNguoinhan()));
            System.out.println(("**Địa chỉ nhận hàng:" + donhang.getDiachi()));
            System.out.println("**Số điện thoại nhận hàng:" + donhang.getSdt());



            double tong = 0;
//đoạn này có thể tận dụng để hiện thị  lịch sử mua hàng
            for (Sachdamua sachdamua : sachdamuaList
            ) {

                System.out.println("||**Tên sách: " + sachdamua.getTensach() + "||**khuyên mãi theo sách:" + sachdamua.getKhuyenmaitheosach() +"%"+ "|| **Khuyến mãi theo thể loại: " + sachdamua.getKhuyenmaitheotheloai()+"%" + "||**Tổng khuyến mãi: " +(int)Math.round (sachdamua.getKhuyenmaitheosach() + sachdamua.getKhuyenmaitheotheloai())+"%" + " ||**Giá sách sau khuến mãi: " + dinhDangSo.format(sachdamua.getGiamua())+Hangso.vnd  + " **Số lương: " + sachdamua.getSoluong());
                tong += sachdamua.getGiamua() * sachdamua.getSoluong();
                System.out.println("\u001B"+"-------------------------------------------------------------------"+"\u001B[35m");
            }
            tong=Math.round(tong);


            System.out.println("**Tổng tiền: " + dinhDangSo.format(tong) +Hangso.vnd);
            System.out.println("\u001B[34m"+"bạn có muốn thanh toán bằng ví không");
            System.out.println(("nhập vào 'ok' để thực hiện thanh toán bằng ví, kí tự khác để thanh toán mặc định khi nhận hàng"));
            String chon=InputMethods.getString();
            if(chon.trim().equalsIgnoreCase("ok")){
                if(usedangonl.getVidientu()<tong){
                    System.err.println("ví của bạn không đủ để thanh toán, chuyển qua hình thức thanh toán mặc định");
                }else {
                    usedangonl.setVidientu(usedangonl.getVidientu()-tong);
                    donhang.setPhuongthucthanhtoan(Hangso.thanhtoanbangvi);
                    System.out.println("Đã thanh toán bằng ví");
                }
            }
            System.out.println("đặt hàng thành công");
            System.out.println("phương thức thanh toán:"+donhang.getPhuongthucthanhtoan());
            servicedonhang.themmoi(donhang);
            giohang.clear();
            ioFilekhachhang.writeToFile(khachhangList, IOFile.LISTUSE_FILE);
            //cập nhật lại số sách trong kho
            ioFilesach.writeToFile(sachList, IOFile.LISTSACH_FILE);
        } else {
            System.out.println("==============Đặt hàng thất bại==============");

        }
    }
    public static void xoatungsanphamtheomma(String mataikhoan){
        IOFile<Khachhang> ioFilekhachhang = new IOFile<>();
        Servicekhachhang servicekhachhang = new Servicekhachhang();

        List<Khachhang> khachhangList = servicekhachhang.getAll();
        System.out.println("Giỏ hàng của bạn hiện tại có các sản phẩm sau");
        hienthigiohang(mataikhoan);
        List<Sachtronggio> sachtronggioList= servicekhachhang.findById(mataikhoan).getGiohang();
        System.out.println("Mời bạn nhập vào mã sách muốn xóa");
        String masach=InputMethods.getString();
        for (int i = 0; i <sachtronggioList.size() ; i++) {
            if(sachtronggioList.get(i).getMasach().equals(masach)){
                sachtronggioList.remove(i);
                System.out.println("Đã xóa sản phẩm ra khỏi giỏ hàng");
                ioFilekhachhang.writeToFile(khachhangList,IOFile.LISTUSE_FILE);
                return;
            }
        }

    }
}

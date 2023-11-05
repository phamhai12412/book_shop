package giaodienUser;

import config.Hangso;
import config.InputMethods;
import data.IOFile;
import model.Khachhang;
import service.Servicekhachhang;

import java.text.DecimalFormat;
import java.util.List;

public class Giaodienvi {
    public static void vidientu (String mauseonl){
        int luaChon ;
        do {
            hienThiMenu();

luaChon=InputMethods.getByte();

            switch (luaChon) {
                case 1:
                    napTienVaoVi(mauseonl);
                    break;
                case 2:
                    kiemTraSoDuVi(mauseonl);
                    break;
                case 3:
                    laymaxacthuc(mauseonl);
                    break;
                case 0:
               break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while (luaChon!=0);

    }

    private static void hienThiMenu() {
        System.out.println("=================Ví thanh toán================");
        System.out.println("1. Nạp tiền vào ví");
        System.out.println("2. Kiểm tra số dư ví");
        System.out.println("3. Nhận mã xác thực nạp ví");
        System.out.println("0. Trở về");
        System.out.println("==============================================");
        System.out.print("Vui lòng chọn một lựa chọn: ");
    }

    private static void napTienVaoVi(String mauseonl) {
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        Khachhang usedangonl=servicekhachhang.findById(mauseonl);
        List<Khachhang> khachhangList=servicekhachhang.getAll();
        IOFile<Khachhang> khachhangIOFile=new IOFile<>();
        if(usedangonl.getTrangthainaptienvaovi()!=0){

            System.out.println("bạn hãy nhập vào mã xác thực để hoàn tất nạp tiền");
            int manhap=InputMethods.getInteger();
            if(manhap== usedangonl.getMaxacthucnapvi()){
                usedangonl.setVidientu(usedangonl.getSotiennapvidangchoxacnhan()+ usedangonl.getVidientu());
                usedangonl.setTrangthainaptienvaovi(0);
                System.out.println("nạp tiền thành công");
                khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);
                System.out.println("nhập ok để tiếp tục nạp ví, kí tự khác để dừng");
                String chon=InputMethods.getString();
                if(!chon.trim().toLowerCase().equals("ok")){
                    return;
                }
            }else {
                System.err.println("Mã xác nhận không hợp lệ hãy thử lại");
                return;
            }
        }
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        System.out.println("Số tiền trong ví hiện tại của bạn là:"+dinhDangSo.format(usedangonl.getVidientu())+ Hangso.vnd);
        System.out.print("Nhập số tiền bạn muốn nạp vào ví: ");
        double soTienNap = InputMethods.gettientrongvi();
        System.out.println("Bạn sẽ nạp: "+dinhDangSo.format(soTienNap)+Hangso.vnd +" vào ví");
        System.out.println("Mời bạn nhập ok để xác nhận nạp, kí tự khác để hủy bỏ");
        String chon=InputMethods.getString();
        if(chon.trim().toLowerCase().equals("ok")){
usedangonl.setSotiennapvidangchoxacnhan(soTienNap);
int maxacthuc= (int) Math.floor(Math.random()*10000+1000);
usedangonl.setMaxacthucnapvi(maxacthuc);
usedangonl.setTrangthainaptienvaovi(1);
            khachhangIOFile.writeToFile(khachhangList,IOFile.LISTUSE_FILE);

            System.out.println("bạn hãy lấy mã xác thực và quay lại để hoàn thành nạp ví");
        }else {
            System.out.println("Đã hủy bỏ");
        }


    }

    private static void kiemTraSoDuVi(String mauseonl) {
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        Khachhang usedangonl=servicekhachhang.findById(mauseonl);
        DecimalFormat dinhDangSo = new DecimalFormat("#,###");
        System.out.println("Số dư ví hiện tại: " +dinhDangSo.format(usedangonl.getVidientu())+Hangso.vnd );
    }
    private static void laymaxacthuc(String mauseonl){
        Servicekhachhang servicekhachhang=new Servicekhachhang();
        Khachhang usedangonl=servicekhachhang.findById(mauseonl);
        if(usedangonl.getTrangthainaptienvaovi()!=0){
            System.out.println("Mã xác thực giao dịch nạp ví của bạn là:"+usedangonl.getMaxacthucnapvi());
        }else {
            System.out.println("Bạn chưa có giao dịch nào");
        }
    }
}

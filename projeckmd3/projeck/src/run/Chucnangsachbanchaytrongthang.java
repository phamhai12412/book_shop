package run;

import config.Hangso;
import model.Donhang;
import model.Sachdamua;
import service.Servicedonhang;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chucnangsachbanchaytrongthang {
    public static void sachbanchaytrongthang() {
        Servicedonhang servicedonhang = new Servicedonhang();
        List<Donhang> donhangList = servicedonhang.getAll();
if(donhangList.isEmpty()){
    System.err.println("Danh sách trống.");
    return;
}
        // Khởi tạo HashMap để lưu trữ thông tin sách bán chạy
        HashMap<String, Integer> sachBanChay = new HashMap<>();

        for (Donhang donhang : donhangList) {
            if (donhang.getTrangthai().equals(Hangso.giaothanhcong)) {
                //lấy tháng nam  của đơn hàng
                Calendar ngaygiodonhang = Calendar.getInstance();
                ngaygiodonhang.setTime(donhang.getNgaygiomua());
                int thangDonHang = ngaygiodonhang.get(Calendar.MONTH) + 1;
                int namdonhang = ngaygiodonhang.get(Calendar.YEAR);
                //lấy tháng năm hiện tại của máy
                Calendar ngaygiohientai = Calendar.getInstance();
                int thangHienTai = ngaygiohientai.get(Calendar.MONTH) + 1;
                int namhientai = ngaygiohientai.get(Calendar.YEAR);

                if (thangDonHang == thangHienTai && namdonhang == namhientai) {
                    List<Sachdamua> sachdamuaList = donhang.getSachdamua();

                    // Hiển thị thông tin sách và tính toán số lượng sách bán chạy
                    for (Sachdamua sachdamua : sachdamuaList) {
                        String tenSach = sachdamua.getTensach();
                        int soLuong = sachdamua.getSoluong();
                        if (sachBanChay.containsKey(tenSach)) {
                            soLuong += sachBanChay.get(tenSach);
                        }
                        sachBanChay.put(tenSach, soLuong);
                    }
                }
            }
        }
if(sachBanChay.isEmpty()){
    System.err.println("Danh sách trống.");
    return;
}
        // In ra danh sách sách bán chạy kèm số lượng trong tháng
        System.out.println("Danh sách sách bán chạy kèm số lượng trong tháng:");
        for (Map.Entry<String, Integer> entry : sachBanChay.entrySet()) {
            String tenSach = entry.getKey();
            int soLuong = entry.getValue();
            System.out.println("Sách: " + tenSach + ", Số lượng đã bán: " + soLuong);
        }
    }

}

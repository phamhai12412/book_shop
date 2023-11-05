package model;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private final boolean ADMIN=true;

    public Admin() {
    }

    public Admin(String taikhoan, String matkhau, String diachi, String sdt, String gmail, boolean gioitinh, int tuoi, String trangthai) {
        super(taikhoan, matkhau, diachi, sdt, gmail, gioitinh, tuoi, trangthai);
    }

    public boolean isADMIN() {
        return ADMIN;
    }

    @Override
    public String toString() {
        return "Admin{" +

                "ADMIN=" + ADMIN +
                '}';
    }
}

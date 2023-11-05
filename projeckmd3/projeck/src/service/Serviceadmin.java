package service;

import data.IOFileT;
import model.Admin;

public class Serviceadmin {
    private IOFileT<Admin> ioFileT;
    private Admin admin;

    public Serviceadmin() {
        ioFileT = new IOFileT<>();

        this.admin = ioFileT.readFromFile(IOFileT.ADMIN_FILE);
    }

    public Admin getadmin() {
        return admin;
    }
}

package service;

import data.IOFile;
import model.Donhang;

import java.util.List;

public class Servicedonhang {
    private List<Donhang> donhangList;

        private IOFile<Donhang> ioFile;


        public Servicedonhang() {
            ioFile = new IOFile<>();
            this.donhangList = ioFile.readFromFile(IOFile.LISTLICHSUMUA_FILE);
        }


        public List<Donhang> getAll() {
            return donhangList;
        }


        public void themmoi(Donhang donhang) {
            if (findById(donhang.getMagiaodich()) == null) {

                donhangList.add(donhang);

                ioFile.writeToFile(donhangList, IOFile.LISTLICHSUMUA_FILE);
            } else {
                System.out.println("check lại logic");
            }
        }

        public Donhang findById(String s) {
            for (Donhang p : donhangList) {
                if (p.getMagiaodich().equals(s)) {
                    return p;
                }
            }
            return null;
        }




        public int getNewId() {
            int idMax = 0;
            for (Donhang c : donhangList) {
                if (c.getIddonhang() > idMax) {
                    idMax = c.getIddonhang();
                }
            }
            return idMax + 1;
        }




}

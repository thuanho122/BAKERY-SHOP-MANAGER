package vn.ht.bakery.shop.services;

import vn.ht.bakery.shop.model.Bakery;
import vn.ht.bakery.shop.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class BakeryService implements IBakeryService {
    List<Bakery> bakerys = new ArrayList<>();
    public static String path = "data/bakerys.csv";

    public BakeryService(){
        getBakerys();
    }

    @Override
    public List<Bakery> getBakerys() {
        List<Bakery> newBakerys = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newBakerys.add(new Bakery(record));
        }
        return bakerys = newBakerys;
    }

    @Override
    public void add(Bakery newBakery) {
        bakerys.add(newBakery);
        CSVUtils.write(path, bakerys);
    }

    @Override
    public void update() {
        CSVUtils.write(path, bakerys);
    }



    @Override
    public Bakery getBakeryById(int id) {
        for (Bakery bakery : bakerys) {
            if (bakery.getId() == id)
                return bakery;
        }
        return null;
    }
    @Override
    public boolean exist(int id) {
        return getBakeryById(id) != null;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Bakery bakery : bakerys) {
            if (bakery.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateId(int id) {
        for (Bakery bakery : bakerys) {
            if (bakery.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public void remove(Bakery bakery) {
        bakerys.remove(bakery);
        update();
    }


//    public static void writeFile(String path, List<Bakery> products) {
//        try {
//            FileWriter fw = new FileWriter(path);
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (Product o : products) {
//                bw.write(o.toString());
//            }
//            bw.close();
//            fw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Object readFile(String path) {
//        Object products = new ArrayList<>();
//        try {
//            FileReader fr = new FileReader(path);
//            BufferedReader br = new BufferedReader(fr);
//            products = br.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return products;
//    }

}




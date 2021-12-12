package vn.ht.bakery.shop.services;

import vn.ht.bakery.shop.model.Bakery;

import java.util.List;

public interface IBakeryService {

    List<Bakery> getBakerys();

    void add(Bakery newBakery);

    void update();

    Bakery getBakeryById(int id);

    boolean exist(int id);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void remove(Bakery bakery);
}

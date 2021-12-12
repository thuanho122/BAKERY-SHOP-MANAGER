package vn.ht.bakery.shop.views;

import vn.ht.bakery.shop.model.Bakery;
import vn.ht.bakery.shop.services.BakeryService;

import java.util.List;
import java.util.Scanner;

public class BakeryView {
    private final BakeryService bakeryService;
    private final Scanner scanner;

    public BakeryView() {
        bakeryService = new BakeryService();
        scanner = new Scanner(System.in);
    }

    public void add() {
        bakeryService.getBakerys();
        System.out.println("Nhập id sản phẩm: ");
        System.out.print("⭆ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (bakeryService.checkDuplicateId(id)) {
            System.out.println("Id bánh đã tồn tại! Vui lồng nhập lại");
            add();
        } else {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print("⭆ ");
            String name = scanner.nextLine();
            if (bakeryService.checkDuplicateName(name)) {
                System.out.println("Tên bánh đã tồn tại! Vui lòng nhập lại: ");
                add();
            } else {
                System.out.println("Nhập giá sản phẩm: ");
                System.out.print("⭆ ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Nhập số lượng: ");
                System.out.print("⭆ ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Mô tả sản phẩm: ");
                System.out.print("⭆ ");
                String description = scanner.nextLine();
                Bakery bakery = new Bakery(id, name, price, quantity, description);
                bakeryService.add(bakery);
                System.out.println("Bạn đã thêm bánh thành công\n");
            }
        }
        boolean is = true;
        do {
            System.out.println("Nhấn 'y' để thêm bánh mới \t|\t 'q' để quay lại \t|\t 't' để thoát");
            System.out.print(" ⭆ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    add();
                    break;
                case "q":
                   ManagerBakeryView.run();
                    break;
                case "t":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhấn không đúng! vui lòng chọn lại");
                    is = false;
            }
        } while (!is);
    }

    public void update() {
        show();
        bakeryService.getBakerys();
        System.out.println("Nhập Id sản phẩm cần sửa: ");
        System.out.print("⭆ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Bakery bakery = bakeryService.getBakeryById(id);
        if (bakeryService.checkDuplicateId(id)) {
            System.out.println("-----------------");
            System.out.println("| 1.Sửa số lượng |");
            System.out.println("| 2.Sửa giá bánh |");
            System.out.println("| 3.Quay lại MENU|");
            System.out.println("------------------");
            System.out.println("Chọn chức năng: ");
            System.out.print("⭆ ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Nhập số lượng bạn muốn sửa: ");
                    System.out.print("⭆ ");
                    int quantity = scanner.nextInt();
                    bakery.setQuantity(quantity);
                    bakeryService.update();
                    System.out.println("Số lượng bánh đã cập nhật thành công");
                    break;
                case 2:
                    System.out.println("Nhập giá bạn muốn sửa: ");
                    System.out.print("⭆ ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    bakery.setPrice(price);
                    bakeryService.update();
                    System.out.println("Bạn đã sửa giá bánh thành công");
                    break;
                case 3:
                    ManagerBakeryView.run();
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    update();
            }

            boolean is = true;
            do {
                System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choi = scanner.nextLine();
                switch (choi) {
                    case "y":
                        update();
                        break;
                    case "q":
                        ManagerBakeryView.run();
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhấn không đúng! vui lòng chọn lại");
                        is = false;
                }
            } while (!is);
        } else {
            System.out.println("Không tìm thấy id! Vui lòng nhập lại");
            update();
        }
    }

    public void show(){
        List<Bakery> bakerys = bakeryService.getBakerys();
        System.out.println("-----------------------------------------DANH SÁCH BÁNH-------------------------------------------");
        System.out.printf("%-10s %-30s %-18s %-10s %-10s", "Id", "Tên bánh", "Giá bánh", "Số lượng", "Mô tả");
        System.out.println(" ");
        for (Bakery bakery : bakerys) {
            System.out.printf("%-10d %-30s %-18f %-10d %-10s\n", bakery.getId(), bakery.getName(), bakery.getPrice(),
                    bakery.getQuantity(), bakery.getDescription());
        }
        System.out.println("--------------------------------------------------------------------------------------------------\n");
    }
    public void showBakery() {
        show();
        boolean is = true;
        do {
            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
            System.out.println("|  Nhấn 'y' để trở lại \t|\t 'n' để thoát chương trình |");
            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
            System.out.print(" ⭆ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    ManagerBakeryView.run();
                    break;
                case "n":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhấn không đúng! vui lòng chọn lại");
                    is = false;
            }
        } while (!is);
    }

    public void remove() {
        show();
        bakeryService.getBakerys();
        System.out.println("Nhập id bạn cần xoá: ");
        System.out.print(" ⭆ ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Bakery bakery = bakeryService.getBakeryById(id);
        if (bakery == null) {
            System.out.println("Không tìm thấy id để xoá!");
        } else {
            boolean check = true;
                System.out.println("❄ ❄ ❄ ❄ REMOVE COMFIRM ❄ ❄ ❄");
                System.out.println("❄  1. Nhấn 1 để xoá        ❄");
                System.out.println("❄  2. Nhấn 2 để quay lại   ❄");
                System.out.println("❄ ❄ ❄ ❄ ❄ ❄ ❄  ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                System.out.print(" ⭆ ");
                int choi = scanner.nextInt();
                scanner.nextLine();
                switch (choi) {
                    case 1:
                        bakeryService.remove(bakery);
                        System.out.println("Đã xoá thành công! \uD83C\uDF8A");
                        do {
                            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
                            System.out.println("|  Nhấn 'y' để trở lại \t|\t 'n' để thoát chương trình |");
                            System.out.println("── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ── ──");
                            System.out.print(" ⭆ ");
                            String choice = scanner.nextLine();
                            switch (choice) {
                                case "y":
                                    ManagerBakeryView.run();
                                    break;
                                case "n":
                                    Menu.exit();
                                    System.exit(0);
                                    break;
                                default:
                                    System.out.println("Nhấn không đúng! vui lòng chọn lại");
                                    check = false;
                            }
                        } while (!check);
                        break;
                    case 2:
                        ManagerBakeryView.run();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                }
        }
    }
}

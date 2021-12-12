package vn.ht.bakery.shop.views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerBakeryView {
    public static void run(){
        int number;
        do {
            Scanner scanner = new Scanner(System.in);
            BakeryView bakeryView = new BakeryView();
            Menu.menuBakery();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.print("⭆ ");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        bakeryView.add();
                        break;
                    case 2:
                        bakeryView.update();
                        break;
                    case 3:
                        bakeryView.remove();
                        break;
                    case 4:
                        bakeryView.showBakery();
                        break;
                    case 5:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        run();
                }
            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}

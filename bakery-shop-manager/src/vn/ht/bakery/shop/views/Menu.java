package vn.ht.bakery.shop.views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menuBakery() {
        System.out.println();
        System.out.println("* * * * --MENU BAKERY-- * * * * *");
        System.out.println("*                               *");
        System.out.println("*    1. Thêm bánh               *");
        System.out.println("*    2. Sửa thông tin bánh      *");
        System.out.println("*    3. Xoá bánh                *");
        System.out.println("*    4. Hiển thị tất cả bánh    *");
        System.out.println("*    5. Thoát                   *");
        System.out.println("*                               *");
        System.out.println("* * * * * * * * * * * * * * * * *");
    }

    public static void exit() {
        System.out.println("\tTạm biệt. Hẹn gặp lại!");
        System.exit(5);
    }

    public static void user() {
        UserView userView = new UserView();
        userView.loginAdmin();
        chon();
    }

    public static void chon() {
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nChọn chức năng ");
                System.out.print("⭆ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        ManagerUserView.run();
                        break;
                    case 2:
                        ManagerBakeryView.run();
                        break;
                    case 3:
                        ManagerOrderView.run();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        chon();
                }

            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void menuUser() {
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪  USERS MANAGER  ⚪ ⚪ ⚪ ⚪ ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪     1. Thêm người dùng               ⚪");
        System.out.println("⚪     2. Sửa thông tin người dùng      ⚪");
        System.out.println("⚪     3. Hiện danh sách người dùng     ⚪");
        System.out.println("⚪                                      ⚪");
        System.out.println("⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪ ⚪");
    }

    public static void mainMenu() {
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬MAIN MENU✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.println("\t ✬                                 ✬");
        System.out.println("\t ✬     1. Quản lí người dùng       ✬");
        System.out.println("\t ✬     2. Quản lí bánh             ✬");
        System.out.println("\t ✬     3. Quản lí đơn đặt hàng     ✬");
        System.out.println("\t ✬                                 ✬");
        System.out.println("\t ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
    }

    public static void orderMenu() {
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬ORDER MENU✬ ✬ ✬ ✬ ✬ ✬ ✬");
        System.out.println("✬                                  ✬");
        System.out.println("✬     1. Tạo order                 ✬");
        System.out.println("✬     2. xem danh sách order       ✬");
        System.out.println("✬                                  ✬");
        System.out.println("✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬ ✬");
    }
}

package vn.ht.bakery.shop.views;

import vn.ht.bakery.shop.model.Bakery;
import vn.ht.bakery.shop.model.Order;
import vn.ht.bakery.shop.model.OrderItem;
import vn.ht.bakery.shop.services.*;
import vn.ht.bakery.shop.utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class OrderView {
    private IBakeryService bakeryService;
    private IOrderService orderService;
    private IOderItemService oderItemService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        bakeryService = new BakeryService();
        orderService = new OrderService();
        oderItemService = new OrderItemService();
    }

    public OrderItem addOrderItems(long orderId) {
        oderItemService.getOrderItems();
        BakeryView bakeryView = new BakeryView();
        bakeryView.show();
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập id bánh: ");
        System.out.print(" ⭆ ");
        int bakeryId = scanner.nextInt();
        scanner.nextLine();
        while (!bakeryService.checkDuplicateId(bakeryId)) {
            System.out.println("Id bánh không tồn tại");
            System.out.println("Nhập id bánh: ");
            System.out.print(" ⭆ ");
            bakeryId = scanner.nextInt();
        }
        Bakery bakery = bakeryService.getBakeryById(bakeryId);
        double price = bakery.getPrice();
        int oldQuantity = bakery.getQuantity();
        System.out.println("Nhập số lượng");
        System.out.print(" ⭆ ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        while (!checkQualityBakery(bakery, quantity)) {
            System.out.println("Vượt quá số lượng! Vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print(" ⭆ ");
            quantity = scanner.nextInt();
        }
        String bakeryName = bakery.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        bakery.setQuantity(currentQuantity);
        bakeryService.update();
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, bakeryId, bakeryName, total);
        return orderItem;
    }

    public boolean checkQualityBakery(Bakery bakery, int quantity) {
        if (quantity <= bakery.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên (vd: Ho Thi Thuan) ");
            System.out.print(" ⭆ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên " + name + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu và không dấu)");
                System.out.println("Nhập tên (vd: Ho Thuan) ");
                System.out.print(" ⭆ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điên thoại");
            System.out.print(" ⭆ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
                System.out.println("Nhập số điện thoại (vd: 0345129876)");
                System.out.print(" ⭆ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ");
            System.out.print(" ⭆ ");
            String address = scanner.nextLine();
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            oderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng thành công");
            do {
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡     1.Nhấn 'y' để tạo tiếp đơn hàng     ㋡");
                System.out.println("㋡     2. Nhấn 'q' để trở lại              ㋡");
                System.out.println("㋡     3. nhấp 'p' để in hoá đơn           ㋡");
                System.out.println("㋡     4. Nhấn 't' để thoát                ㋡");
                System.out.println("㋡                                         ㋡");
                System.out.println("㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡ ㋡");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        ManagerOrderView.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Nhập không hợp lệ! Vui lòng nhập lại");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Nhập sai. vui lòng nhập lại!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------------------------------HOÁ ĐƠN----------------------------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", "   Id", "Tên khách hàng", "   SĐT", "Địa chỉ", "Tên bánh", "Số lượng", "Giá");
            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getName(), order.getPhone(),
                    order.getAddress(), orderItem.getBakeryName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println(" ------------------------------------------------------------------------------------------------- Tổng tiền:" + orderItem.getTotal());
            System.out.println("---------------------------------------------THUAN HO BAKERY-----------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.println("Nhấn ");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOrderView.run();
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
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = oderItemService.getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("----------------------------------------------------------LIST ORDER--------------------------------------------------------------------");
            System.out.println("|                                                                                                                                      |");
            System.out.printf("|%-15s %-20s %-12s %-23s %-10s %-10s %-15s %-21s\n|", "   Id", "Tên khách hàng", "  SĐT", "Địa chỉ", "Tên bánh", "Số lượng", "   Giá", "   Tổng" + "               |");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-12s %-23s %-10s %-10d %-15f %-21f %-7s\n|", order.getId(), order.getName(), order.getPhone(), order.getAddress(), newOrderItem.getBakeryName(), newOrderItem.getQuantity(), newOrderItem.getPrice(), newOrderItem.getTotal(), "|");
            }
            System.out.println("                                                                                                                                      |");
            System.out.println("---------------------------------------------THUAN HO BAKERY----------------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát chương trình");
                System.out.print(" ⭆ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOrderView.run();
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
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

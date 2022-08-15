package view;

import controller.ComputerController;
import controller.RevenueController;
import controller.ServiceController;
import model.Computer;
import model.Revenue;
import model.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserView {
    Scanner sc = new Scanner(System.in);
    ComputerController computerController = new ComputerController();
    ServiceController serviceController = new ServiceController();
    RevenueController revenueController = new RevenueController();
    List<Computer> computerList = computerController.getComputerList();
    List<Service> serviceList = serviceController.getServiceList();

    List<Revenue> revenueList = revenueController.getRevenueList();

    public void menu() {
        System.out.println("1. Computer List");
        System.out.println("2. Create new Computer");
        System.out.println("3. Edit computer");
        System.out.println("4. Delete Computer");
        System.out.println("5. Service List");
        System.out.println("6. Create new Service");
        System.out.println("7. Edit play-time price");
        System.out.println("8. Payment");
        System.out.println("9. Account manage");
        System.out.println("10. Revenue");
        System.out.println("11. Exit");
        System.out.println("12. Show list Revenue");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                this.showComputerList();
                break;
            case 2:
                this.formCreateComputer();
                break;
            case 3:
                this.formEditComputer();
                break;
            case 4:
                this.formDeleteComputer();
                break;
            case 5:
                this.showServiceList();
                break;
            case 6:
                this.formCreateService();
                break;
            case 7:
                this.formEditPlayTimePrice();
                break;
            case 8:
                this.formPayment();
                break;
            case 9:
                new AccountManageView().menu();
                break;
            case 10:
                this.formShowRevenue();
                break;
            case 11:
                System.exit(0);
            case 12:
                System.out.println(revenueList);
                break;
            default:
                System.out.println("Invalid choice");
        }
        this.menu();
    }

    private void formShowRevenue() {
        System.out.println("1. Total revenue");
        System.out.println("2. Find revenue by time");
        int choose = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 1:
                System.out.println(revenueController.getTotalRevenue());
                break;
            case 2:
                System.out.println("Find from:");
                String dateFromInString = sc.nextLine();
                int[] dateFromInArray = Arrays.stream(dateFromInString.split("[/-]")).mapToInt(Integer::parseInt).toArray();
                LocalDate dateFrom = LocalDate.of(dateFromInArray[2], dateFromInArray[1], dateFromInArray[0]);

                System.out.println("To:");
                String dateToInString = sc.nextLine();
                int[] dateToInArray = Arrays.stream(dateToInString.split("[/-]")).mapToInt(Integer::parseInt).toArray();
                LocalDate dateTo = LocalDate.of(dateToInArray[2], dateToInArray[1], dateToInArray[0]);

                LocalDate current = dateFrom;
                while (current.compareTo(dateTo) <= 0) {
                    Revenue revenue = revenueController.findByLocalDate(current);
                    if (revenue == null) {
                        System.out.println(current + ": 0");
                    } else {
                        System.out.println(current + ": " + revenue.getRevenueOfDay());
                    }
                    current = current.plusDays(1);
                }

                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void formEditPlayTimePrice() {
        System.out.println("Enter new price:");
        int newPrice = Integer.parseInt(sc.nextLine());
        computerController.editPlayTimePrice(newPrice);
    }

    private void formPayment() {
        System.out.println("1. Get bill");
        System.out.println("2. Call a service");
        int choice = Integer.parseInt(sc.nextLine());
        int id;
        Computer computer;
        switch (choice) {
            case 1:
                for (Computer c : computerList) {
                    if (c.isStatus()) {
                        System.out.println("ID   Name         ");
                        System.out.println(c.getId() + ". " + c.getName());
                    }
                }
                System.out.println("Enter computer id");
                id = Integer.parseInt(sc.nextLine());
                computer = computerController.findComputerByID(id);
                System.out.println("Total money have to pay:");
                int mustPay = computerController.countMoney(computer);
                System.out.println(mustPay);
                int idRevenue = revenueList.size() == 0 ? 1 : revenueList.get(revenueList.size() - 1).getId() + 1;
                revenueController.saveRevenue(new Revenue(idRevenue, mustPay, LocalDate.now()));
                computer.turnOff();
                break;
            case 2:
                for (Computer c : computerList) {
                    if (c.isStatus()) {
                        System.out.println("ID   Name         ");
                        System.out.println(c.getId() + ". " + c.getName());
                    }
                }
                System.out.println("Enter computer id");
                id = Integer.parseInt(sc.nextLine());
                computer = computerController.findComputerByID(id);
                showServiceList();
                System.out.println("Enter service id");
                int idService = Integer.parseInt(sc.nextLine());
                computer.setServiceMoney(computer.getServiceMoney() + serviceList.get(idService - 1).getPrice());
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void formEditComputer() {
        System.out.println("Enter id of computers to swap");
        System.out.println("Enter id 1");
        int id1 = Integer.parseInt(sc.nextLine());
        System.out.println("Enter id 2");
        int id2 = Integer.parseInt(sc.nextLine());

        Computer c1 = computerController.findComputerByID(id1);
        Computer c2 = computerController.findComputerByID(id2);

        if (c1 == null || c2 == null) {
            System.out.println("Computer not found");
            return;
        }
        Collections.swap(computerList, computerList.indexOf(c1), computerList.indexOf(c2));
        c1.setId(id2);
        c2.setId(id1);
    }

    private void showComputerList() {
        System.out.println("1. All Computers");
        System.out.println("2. Online");
        System.out.println("3. Offline");
        int choose = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 1:
                System.out.println("Computer List:");
                System.out.println("ID   Name         Status");
                for (Computer c : computerList) {
                    System.out.printf("%2d   %-10s %8s\n", c.getId(), c.getName(), c.isStatus() ? "Online" : "Offline");
                }
                break;
            case 2:
                System.out.println("Computer List:");
                System.out.println("ID   Name         Status");
                for (Computer c : computerList) {
                    if (c.isStatus())
                        System.out.printf("%2d   %-10s %8s\n", c.getId(), c.getName(), "Online");
                }
                break;
            case 3:
                System.out.println("Computer List:");
                System.out.println("ID   Name         Status");
                for (Computer c : computerList) {
                    if (!c.isStatus())
                        System.out.printf("%2d   %-10s %8s\n", c.getId(), c.getName(), "Offline");
                }
                System.out.println("Enter computer id to active");
                int id = Integer.parseInt(sc.nextLine());
                Computer c1 = computerController.findComputerByID(id);
                if (c1 == null || c1.isStatus()) {
                    System.out.println("ID not found");
                    return;
                }
                c1.turnOn();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }

    private void formCreateComputer() {
        System.out.println("Enter Computer name:");
        String name = sc.nextLine();
        int id = computerList.size() == 0 ? 1 : computerList.get(computerList.size() - 1).getId() + 1;
        computerController.saveComputer(new Computer(id, name));
    }

    private void formDeleteComputer() {
        System.out.println("Enter Computer id:");
        int id = Integer.parseInt(sc.nextLine());
        if (computerController.findComputerByID(id) == null) {
            System.out.println("ID does not exist");
        } else {
            computerController.deleteComputer(id);
        }
    }

    private void showServiceList() {
        System.out.println("Service List:");
        System.out.println("ID   Name     Price");
        for (Service s : serviceList) {
            System.out.printf("%2d   %-8s %5d\n", s.getId(), s.getName(), s.getPrice());
        }
    }

    private void formCreateService() {
        System.out.println("Enter Service name:");
        String name = sc.nextLine();
        System.out.println("Enter Service price:");
        int price = Integer.parseInt(sc.nextLine());
        int id = serviceList.size() == 0 ? 1 : serviceList.get(serviceList.size() - 1).getId() + 1;
        serviceController.createService(new Service(id, name, price));
    }

}

package view;


import config.Config;

import static data.Path.*;

import controller.AccountController;
import model.Account;

import java.util.List;
import java.util.Scanner;

public class LoginView {
    AccountController accountController = new AccountController();
    List<Account> accountList = accountController.getAccountList();
    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("1. Login");
//        System.out.println("2. Change Password");
        System.out.println("2. Forgot Password");
        System.out.println("3. Exit");
        try {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    this.login();
                    break;
//                case 2:
//                    this.changePassword();
//                    break;
                case 2:
                    this.forgotPassword();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
        menu();
    }

    private void login() {
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        if (checkAccountAvailable(username, password)) {
            new UserView().menu();
        } else {
            System.out.println("Wrong username or password");
        }
    }

    private boolean checkAccountAvailable(String username, String password) {
        return accountList
                .stream()
                .filter(account -> account.getUsername().equals(username) && account.getPassword().equals(password))
                .findAny()
                .orElse(null)
                != null;
    }

//    private void changePassword() {
//        System.out.println("Change password");
////        System.out.println("Enter old password");
////        String oldPassword = sc.nextLine();
////        if (oldPassword.equals(adminAccount.getPassword())) {
////            System.out.println("Enter new password");
////            String newPassword = sc.nextLine();
////            System.out.println("Enter new password again");
////            String newPasswordRepeat = sc.nextLine();
////            if (newPassword.isEmpty()) {
////                System.out.println("New password can not be empty");
////            } else if (newPasswordRepeat.equals(newPassword)) {
////                adminAccount.setPassword(newPassword);
////                config.write(adminAccount, PATH_ACCOUNT);
////                System.out.println("Changed password successfully");
////            } else {
////                System.out.println("Password does not match");
////            }
////        } else {
////            System.out.println("Wrong password");
////        }
//    }

    private void forgotPassword() {
        System.out.println("Enter username");
        String username = sc.nextLine();
        Account find = accountList.stream().filter(account -> account.getUsername().equals(username)).findAny().orElse(null);
        if (find == null) {
            System.out.println("No account found with username " + username);
            return;
        }
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        accountController.editAccount(new Account(find.getId(), username, newPassword));

        System.out.println("Completed");
    }
}

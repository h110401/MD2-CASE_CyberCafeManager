package view;

import controller.AccountController;
import model.Account;
import model.Role;

import java.util.List;
import java.util.Scanner;

public class AccountManageView {

    Scanner sc = new Scanner(System.in);
    AccountController accountController = new AccountController();
    List<Account> accountList = accountController.getAccountList();

    public void menu() {
        System.out.println("1. Create a new account");
        System.out.println("2. Edit account");
        System.out.println("3. Delete account");
        System.out.println("4. Show account list");
        System.out.println("5. Exit");
        try {
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    this.formCreateAccount();
                    break;
                case 2:
                    this.formEditAccount();
                    break;
                case 3:
                    this.formDeleteAccount();
                    break;
                case 4:
                    this.formShowAccountList();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choose");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
        menu();
    }

    private void formEditAccount() {
        formShowAccountList();
        System.out.println("Enter account id to edit");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter new username");
        String newUsername = sc.nextLine();
        System.out.println("Enter new password");
        String newPassword = sc.nextLine();
        accountController.editAccount(new Account(id, newUsername, newPassword, Role.ADMIN));
    }

    private void formDeleteAccount() {
        System.out.println("Enter account id to delete");
        int id = Integer.parseInt(sc.nextLine());
        if (accountController.findById(id) == null) {
            System.out.println("Account not found");
        } else {
            System.out.println("Deleted account " + id);
            accountController.deleteAccount(id);
        }
    }

    private void formCreateAccount() {
        System.out.println("Enter account username:");
        String username = sc.nextLine();
        System.out.println("Enter account password:");
        String password = sc.nextLine();
        int id = accountList.size() == 0 ? 1 : accountList.get(accountList.size() - 1).getId() + 1;
        accountController.saveAccount(new Account(id, username, password, Role.ADMIN));
    }

    private void formShowAccountList() {
        for (Account account : accountList) {
            System.out.println(account.getId() + ": " + account.getUsername() + " - " + account.getPassword());
        }
    }

}

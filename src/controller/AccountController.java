package controller;

import model.Account;
import service.account.AccountServiceIMPL;
import service.account.IAccountService;

import java.util.List;

public class AccountController {

    IAccountService accountService = new AccountServiceIMPL();

    public List<Account> getAccountList() {
        return accountService.findAll();
    }

    public void saveAccount(Account account) {
        accountService.save(account);
    }

    public void deleteAccount(int id) {
        accountService.remove(id);
    }

    public Account findById(int id) {
        return accountService.findById(id);
    }

    public void editAccount(Account account) {
        accountService.edit(account);
    }

}

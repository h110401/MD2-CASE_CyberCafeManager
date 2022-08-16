package service.account;

import config.Config;

import static data.Path.*;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceIMPL implements IAccountService {

    static Config<List<Account>> config = new Config<>();
    static List<Account> accountList;

    static {
//        accountList.add(new Account(1, "a", "a"));
//        accountList.add(new Account(2, "b", "b"));
//        accountList.add(new Account(3, "c", "c"));
        accountList = config.read(PATH_ACCOUNT_LIST);
    }

    @Override
    public List<Account> findAll() {
        return accountList;
    }

    @Override
    public void save(Account e) {
        accountList.add(e);
        saveData();
    }

    @Override
    public void remove(int id) {
        accountList.remove(findById(id));
        saveData();
    }

    @Override
    public Account findById(int id) {
        return accountList.stream().filter(account -> account.getId() == id).findAny().orElse(null);
    }

    @Override
    public void edit(Account account) {
        Account editAccount = findById(account.getId());
        editAccount.setUsername(account.getUsername());
        editAccount.setPassword(account.getPassword());
        saveData();
    }

    @Override
    public void saveData() {
        config.write(accountList, PATH_ACCOUNT_LIST);
    }
}

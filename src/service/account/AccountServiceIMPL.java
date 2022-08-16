package service.account;

import config.Config;

import static data.Path.*;

import model.Account;
import model.Role;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceIMPL implements IAccountService {

    //    static List<Account> accountList = new ArrayList<>();
    static Config<List<Account>> config = new Config<>();
    static List<Account> accountList;

    static {
//        accountList.add(new Account(1, "a", "a", Role.ADMIN));
//        accountList.add(new Account(2, "b", "b", Role.ADMIN));
//        accountList.add(new Account(3, "c", "c", Role.ADMIN));
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

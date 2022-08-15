package service.account;

import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceIMPL implements IAccountService {

    static List<Account> accountList = new ArrayList<>();

    static {
        accountList.add(new Account(1, "a", "a"));
        accountList.add(new Account(2, "b", "b"));
        accountList.add(new Account(3, "c", "c"));
    }

    @Override
    public List<Account> findAll() {
        return accountList;
    }

    @Override
    public void save(Account e) {
        accountList.add(e);
    }

    @Override
    public void remove(int id) {
        accountList.remove(findById(id));
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
    }
}

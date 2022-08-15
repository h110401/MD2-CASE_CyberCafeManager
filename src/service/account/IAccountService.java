package service.account;

import model.Account;
import service.IGeneric;

public interface IAccountService extends IGeneric<Account> {
    void edit(Account account);
}

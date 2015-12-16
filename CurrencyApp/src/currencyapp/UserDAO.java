package currencyapp;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
   public List<User> getAllUsers();
   public User getUser(String username);
   public void addUser(User user);
  // public User getUser();
   public void deleteUser(String username);
   public void addAccount(String username, String acc_currency);
   public void createAllAccounts();
   public Exchange_account getAccount(String username, String currencysrc);
   public List<Exchange_account> getAllExchangeAccounts();
   public void deleteAccount(String username, String acc_currency);
   public void deleteAllAccounts();
   public String showUser(String username);


}

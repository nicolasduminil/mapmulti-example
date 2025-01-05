package fr.simplex_software.mapmulti;

import org.apache.commons.lang3.builder.*;

import java.math.*;
import java.util.*;
import java.util.function.*;

public class AccountOwner
{
  private String firstName;
  private String lastName;
  private List<BankAccount> bankAccountList;

  public AccountOwner(String firstName, String lastName, List<BankAccount> bankAccountList)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.bankAccountList = bankAccountList;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public List<BankAccount> getBankAccountList()
  {
    return bankAccountList;
  }

  public String toString()
  {
    return new ReflectionToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).toString();
  }

  public void hfpifi(Consumer<HfPifi> consumer)
  {
    bankAccountList.forEach(account -> {
      if (account.getHfpifi().compareTo(new BigDecimal("0.6")) > 0)
        consumer.accept(new HfPifi(this, account));
    });
  }
}

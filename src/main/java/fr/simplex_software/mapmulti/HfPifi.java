package fr.simplex_software.mapmulti;

import org.apache.commons.lang3.builder.*;

public class HfPifi
{
  private AccountOwner accountOwner;
  private BankAccount bankAccount;

  public HfPifi(AccountOwner accountOwner, BankAccount bankAccount)
  {
    this.accountOwner = accountOwner;
    this.bankAccount = bankAccount;
  }

  public AccountOwner getAccountOwner()
  {
    return accountOwner;
  }

  public BankAccount getBankAccount()
  {
    return bankAccount;
  }

  public String toString()
  {
    return ReflectionToStringBuilder.toString(this);
  }
}

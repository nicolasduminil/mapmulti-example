package fr.simplex_software.mapmulti;

import org.apache.commons.lang3.builder.*;

import java.math.*;
import java.time.*;

public class BankAccount
{
  private String sortCode;
  private String accountNumber;
  private String bankName;
  private BigDecimal hfpifi;

  public BankAccount(String sortCode, String accountNumber, String bankName, BigDecimal hfpifi)
  {
    this.sortCode = sortCode;
    this.accountNumber = accountNumber;
    this.bankName = bankName;
    this.hfpifi = hfpifi;
  }

  public String getSortCode()
  {
    return sortCode;
  }

  public String getAccountNumber()
  {
    return accountNumber;
  }

  public String getBankName()
  {
    return bankName;
  }

  public BigDecimal getHfpifi()
  {
    return hfpifi;
  }

  public String toString()
  {
    return new ReflectionToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).toString();
  }
}

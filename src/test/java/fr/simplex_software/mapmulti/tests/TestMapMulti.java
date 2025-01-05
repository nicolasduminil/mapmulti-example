package fr.simplex_software.mapmulti.tests;

import fr.simplex_software.mapmulti.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.util.*;
import java.util.stream.*;

public class TestMapMulti
{
  @Test
  public void testMapMulti()
  {
    List<AccountOwner> accountOwners = getAccountOwners();
    List<HfPifi> hfPifis = accountOwners.stream()
      .<HfPifi>mapMulti((accountOwner, consumer) ->
        accountOwner.getBankAccountList()
          .forEach(bankAccount -> consumer.accept(new HfPifi(accountOwner, bankAccount))))
      .collect(Collectors.toList());
    Assertions.assertEquals(9, hfPifis.size());
    Assertions.assertTrue(hfPifis.toString().contains("accountNumber=accountNumber1"));
  }

  @Test
  public void testFlatMap()
    throws Exception
  {
    List<AccountOwner> accountOwners = getAccountOwners();
    List<HfPifi> hfPifis = accountOwners.stream()
      .flatMap(accountOwner -> accountOwner.getBankAccountList().stream()
        .map(bankAccount -> new HfPifi(accountOwner, bankAccount)))
      .collect(Collectors.toList());
    Assertions.assertEquals(9, hfPifis.size());
    Assertions.assertTrue(hfPifis.toString().contains("bankName=Bank2"));
  }

  @Test
  public void testMapMultiCase1()
  {
    List<AccountOwner> accountOwners = getAccountOwners();
    List<HfPifi> hfPifis = accountOwners.stream()
      .<HfPifi>mapMulti((accountOwner, consumer) ->
        accountOwner.getBankAccountList()
          .forEach(bankAccount ->  {
            if (bankAccount.getHfpifi().compareTo(new BigDecimal("0.6")) > 0)
              consumer.accept(new HfPifi(accountOwner, bankAccount));
          }))
      .collect(Collectors.toList());
    Assertions.assertEquals(5, hfPifis.size());
    String print = hfPifis.toString();
    Assertions.assertTrue(hfPifis.toString().contains("hfpifi=0.78"));
  }

  @Test
  public void testFlatMapCase1()
  {
    List<AccountOwner> accountOwners = getAccountOwners();
    List<HfPifi> hfPifis = accountOwners.stream()
      .flatMap(accountOwner -> accountOwner.getBankAccountList().stream()
        .filter(bankAccount -> bankAccount.getHfpifi().compareTo(new BigDecimal("0.6")) > 0)
        .map(bankAccount -> new HfPifi(accountOwner, bankAccount)))
      .collect(Collectors.toList());
    Assertions.assertEquals(5, hfPifis.size());
    Assertions.assertTrue(hfPifis.toString().contains("sortCode=sortCode1"));
  }

  @Test
  public void testMapMultiCas2()
  {
    List<HfPifi> hfPifis = getAccountOwners().stream()
      .<HfPifi>mapMulti(AccountOwner::hfpifi)
      .collect(Collectors.toList());
    Assertions.assertEquals(5, hfPifis.size());
    Assertions.assertTrue(hfPifis.toString().contains("sortCode=sortCode1"));
  }

  private List<AccountOwner> getAccountOwners()
  {
    return List.of(
      new AccountOwner("John", "Doe", List.of(
        new BankAccount("sortCode1", "accountNumber1", "Bank1", new BigDecimal("0.78")),
        new BankAccount("sortCode2", "accountNumber2", "Bank2", new BigDecimal("0.56")),
        new BankAccount("sortCode3", "accountNumber3", "Bank3", new BigDecimal("0.61"))
      )),
      new AccountOwner("Jane", "Doe", List.of(
        new BankAccount("sortCode4", "accountNumber4", "Bank4", new BigDecimal("0.43")),
        new BankAccount("sortCode5", "accountNumber5", "Bank5", new BigDecimal("0.24")),
        new BankAccount("sortCode6", "accountNumber6", "Bank6", new BigDecimal("0.69"))
      )),
      new AccountOwner("Mike", "Doe", List.of(
        new BankAccount("sortCode7", "accountNumber7", "Bank7", new BigDecimal("0.71")),
        new BankAccount("sortCode8", "accountNumber8", "Bank8", new BigDecimal("0.84")),
        new BankAccount("sortCode9", "accountNumber9", "Bank9", new BigDecimal("0.32"))
      ))
    );
  }
}

package org.lotusbank.framework.repository;

import org.lotusbank.banking.domain.BankAccount;
import org.lotusbank.banking.domain.CompanyAccount;
import org.lotusbank.banking.domain.PersonalAccount;
import org.lotusbank.common.Address;
import org.lotusbank.common.Customer;
import org.lotusbank.creditcard.domain.CreditCardAccount;
import org.lotusbank.framework.dataaccess.entities.AccountEntity;
import org.lotusbank.framework.dataaccess.entities.AccountEntryEntity;
import org.lotusbank.framework.dataaccess.entities.AddressEntity;
import org.lotusbank.framework.dataaccess.entities.CustomerEntity;
import org.lotusbank.framework.domain.Account;
import org.lotusbank.framework.domain.AccountEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LotusMapper {

    LotusMapper INSTANCE = Mappers.getMapper(LotusMapper.class);

    AccountEntity getAccountEnity(Account account);
    PersonalAccount getPersonalAccount(AccountEntity account);
    CompanyAccount getCompanyAccount(AccountEntity account);
    CreditCardAccount getCreditCardAccount(AccountEntity account);

    AddressEntity getAddressEnity(Address account);
    Address getAddress(AddressEntity account);

    CustomerEntity getCustomerEntity(Customer account);
    Customer getCustomer(CustomerEntity account);

    AccountEntryEntity getAccountEntryEntity(AccountEntry entry);
    AccountEntry getAccountEntry(AccountEntryEntity entry);
}

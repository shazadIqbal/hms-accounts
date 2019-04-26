package com.hs.accounts.Services;

import com.hs.accounts.Commons.AccountRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.AccountsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService {

    @Autowired
    AccountsRepository accountsRepository;



    public RestTemplateResponseDTO postAccount(AccountRestDTO accountRestDTO){
        //Accounts DTO Object


        //Accounts Object
       Accounts accounts = new Accounts();
       accounts.setUserId(accountRestDTO.getId());
       accounts.setAccountType(accountRestDTO.getAccountType());
       accounts.setCreatedDate(new Date());
       accounts.setGender(accountRestDTO.getGender());
       accounts.setStatus("Active");
       accounts.setUserName(accountRestDTO.getName());
       //accounts.setTransactions(accountRestDTO.getTransactions());
        accountsRepository.save(accounts);

        return new RestTemplateResponseDTO("200","successfull");
    }
    public List<Accounts> getAccounts(){
        List<Accounts> accounts = accountsRepository.findAll();
        List<Accounts> activeAccounts = new ArrayList<>();
        accounts.forEach(accountsActive ->{
            if(accountsActive.getStatus().equalsIgnoreCase("Active")){
                activeAccounts.add(accountsActive);
            }
        });
        return activeAccounts;
    }

    public String updateAccountsByID(AccountsDTO accountsDTO, Long id){
        Optional<Accounts> accounts = accountsRepository.findById(id);
        if(accounts.isPresent()){
            Accounts _accounts = accounts.get();
            _accounts.setUserId(accountsDTO.getUserId());
            _accounts.setUserName(accountsDTO.getUserName());
            _accounts.setStatus(accountsDTO.getStatus());
            _accounts.setGender(accountsDTO.getGender());
            _accounts.setAccountType(accountsDTO.getAccountType());
            _accounts.setCreatedDate(accountsDTO.getCreatedDate());
            _accounts.setEndDate(accountsDTO.getEndDate());

            accountsRepository.save(_accounts);

        }
        return "{\"UPDATED SUCCESFULLY\":1}";
    }
    public String deleteAccountById(Long id){
        Optional<Accounts> accounts = accountsRepository.findById(id);
        if(accounts.isPresent()){
            Accounts deleteAccount = accounts.get();
            deleteAccount.setStatus("Inactive");
            accountsRepository.save(deleteAccount);
        }
        return "{\"DELETED SUCCESFULLY\":1}";
    }

    public Optional<Accounts> getAccountsById(Long id){
        Optional<Accounts> accounts = accountsRepository.findById(id);

        return accounts;
    }
    public Accounts getAccountsByUserId(String userId){
    Accounts accounts = accountsRepository.findByUserId(userId);
    return accounts;
    }
}

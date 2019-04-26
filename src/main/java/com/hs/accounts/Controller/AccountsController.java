package com.hs.accounts.Controller;

import com.hs.accounts.Commons.AccountRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.AccountsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {


    @Autowired
    AccountsService accountsService;

//    @RequestMapping(value = "/" ,method = RequestMethod.POST)
//    public String postAccount(@RequestBody AccountsDTO accountsDTO){
//
//
//        return accountsService.postAccount(accountsDTO);
//
//    }
@RequestMapping(value = "/" ,method = RequestMethod.POST)
public RestTemplateResponseDTO postAccount(@RequestBody AccountRestDTO accountRestDTO){


    return accountsService.postAccount(accountRestDTO);

}

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public List<Accounts> getAccounts(){
        return accountsService.getAccounts();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public Optional<Accounts> getErByID(@PathVariable("id") Long id){
        return accountsService.getAccountsById(id);
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,consumes = "application/json")
    public String updateAccounts(@RequestBody AccountsDTO accountsDTO, @PathVariable("id") Long id){
        return accountsService.updateAccountsByID(accountsDTO, id);
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    public String deleteAccountsByID(@PathVariable("id") Long id){
        return accountsService.deleteAccountById(id);
    }


}

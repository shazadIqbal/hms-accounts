package com.hs.accounts.Controller;

import com.hs.accounts.Commons.DashboardRestDTO;
import com.hs.accounts.Commons.TransactionRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.TransactionsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Transactions;
import com.hs.accounts.Model.Vendor;
import com.hs.accounts.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    //Post Transactions
//    @RequestMapping(value = "/" ,method = RequestMethod.POST)
//    public String postTransaction(@RequestBody TransactionsDTO transactionsDTO){
//        return transactionService.postTransaction(transactionsDTO);
//    }

    //Rest Call
    @RequestMapping(value = "/" ,method = RequestMethod.POST)
    public RestTemplateResponseDTO postTransaction(@RequestBody TransactionRestDTO transactionRestDTO){
        return transactionService.postTransaction(transactionRestDTO);
    }

//    @RequestMapping(value = "/" ,method = RequestMethod.GET)
//    public List<Transactions> getTransaction(){
//        return transactionService.getTransactions();
//    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public RestTemplateResponseDTO getTransactionsByID(@PathVariable("id") String id){
        return transactionService.getTransactionsById(id);
    }

//    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
//    public String deleteTransactionsByID(@PathVariable("id") Long id){
//        return transactionService.deleteTransactionsById(id);
//    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public String updateTransactionsByID(@RequestBody TransactionsDTO transactionsDTO, @PathVariable("id") Long id){
        return transactionService.updateTransactionsById(transactionsDTO,id);
    }


    @GetMapping("/delete/{refId}")
    public RestTemplateResponseDTO deleteTransactionsByID(@PathVariable("refId") String refId)

    {
        return transactionService.deleteTransactionsByRefId(refId);

    }


    @PostMapping("/update/{id}")
    public RestTemplateResponseDTO updateTransactionDues(@PathVariable("id") Long id,@RequestBody TransactionsDTO transactionsDTO)
    {
        return transactionService.updateTransactionDues(id,transactionsDTO);
    }


    @PostMapping("/dashboard/account")
    public RestTemplateResponseDTO getDashboardReportsByDateNaccount(@RequestBody DashboardRestDTO dashboardRestDTO)
    {
        return transactionService.getDashboardByDateNaccount(dashboardRestDTO);
    }


    @PostMapping("/dashboard/all")
    public RestTemplateResponseDTO getDashboardByDate(@RequestBody DashboardRestDTO dashboardRestDTO)
    {
        return transactionService.getDashboardByDate(dashboardRestDTO);
    }

    @PostMapping("/dashboard/employeereports")
    public RestTemplateResponseDTO getAllTransactions(@RequestBody DashboardRestDTO dashboardRestDTO){
        return transactionService.gatAllTransactions(dashboardRestDTO);
    }

}



package com.hs.accounts.Services;

import com.hs.accounts.Commons.TransactionRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.TransactionsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Transactions;
import com.hs.accounts.Repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountsService accountsService;

    public RestTemplateResponseDTO postTransaction(TransactionRestDTO transactionRestDTO){

        //Account Rest DTO Object
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        Accounts accounts = accountsService.getAccountsByUserId(transactionRestDTO.getAccountNoUUID());
        RestTemplateResponseDTO restTemplateResponseDTO = new RestTemplateResponseDTO();
//        Accounts account = new Accounts();
//        account.setId(1L);
//        accounts.add(account);
        if(accounts != null) {
//            transactionsDTO.setCurrency("PKR");
//            transactionsDTO.setDescription(transactionRestDTO.getDescription());
//            transactionsDTO.setReceivedAmount(transactionRestDTO.getReceivedAmount());
//            transactionsDTO.setTotalAmount(transactionRestDTO.getTotalAmount());
//            transactionsDTO.setTransactionType(transactionRestDTO.getTransactionType());
//            transactionsDTO.setOperationType(transactionRestDTO.getOperationType());
//            transactionsDTO.setAccounts(accounts);

            //Transaction Object
            Transactions transactions = new Transactions();
            transactions.setCurrency("PKR");
            transactions.setAccounts(accounts);
            transactions.setDescription(transactionRestDTO.getDescription());

            if(transactionRestDTO.getTotalAmount() > transactionRestDTO.getReceivedAmount())
            {
            transactions.setDues(transactionRestDTO.getTotalAmount() - transactionRestDTO.getReceivedAmount());
            }
            else
                {
                transactions.setDues(0.0);
            }
            transactions.setReceivedAmount(transactionRestDTO.getReceivedAmount());
            transactions.setTotalAmount(transactionRestDTO.getTotalAmount());
            transactions.setTransactionDate(new Date());
            transactions.setTransactionType(transactionRestDTO.getTransactionType());
            transactions.setOperationType(transactionRestDTO.getOperationType());
            transactionsRepository.save(transactions);

            return new RestTemplateResponseDTO("200","Suceessfully Added");
        }
        return new RestTemplateResponseDTO("404","Account Not Found");
    }


//   // Insert Transactions in Database
//    public String postTransaction(TransactionsDTO transactionsDTO){
//
//        Transactions transactions = new Transactions();
//        transactions.setAccounts(transactionsDTO.getAccounts());
//        transactions.setCurrency(transactionsDTO.getCurrency());
//        transactions.setDescription(transactionsDTO.getDescription());
//        transactions.setDues(transactionsDTO.getTotalAmount() - transactionsDTO.getReceivedAmount());
//        transactions.setReceivedAmount(transactionsDTO.getReceivedAmount());
//        transactions.setTotalAmount(transactionsDTO.getTotalAmount());
//        transactions.setTransactionDate(new Date());
//        transactions.setTransactionType(transactionsDTO.getTransactionType());
//        transactions.setOperationType(transactionsDTO.getOperationType());
//        transactionsRepository.save(transactions);
//        return "{\"ADDED SUCCESFULLY\":1}";
//    }

//    public List<Transactions> getTransaction(){
//        List<Transactions> transactions = transactionsRepository.findAll();
//        List<Transactions> activeTransactions = new ArrayList<>();
//        transactions.forEach(transaction -> {
//            if(transaction.getStatus().equalsIgnoreCase("Active")){
//                activeTransactions.add(transaction);
//            }
//        });
//        return activeTransactions;
//    }
//    public String deleteTransactionsById(Long id){
//        Optional<Transactions> transactions = transactionsRepository.findById(id);
//        if(transactions.isPresent()){
//            Transactions deleteTransaction = transactions.get();
//            deleteTransaction.setStatus("Inactive");
//            transactionsRepository.save(deleteTransaction);
//        }
//        return "{\"DELETED SUCCESFULLY\":1}";
//    }
    public String updateTransactionsById(TransactionsDTO transactionsDTO, Long id){
        Optional<Transactions> transactions = transactionsRepository.findById(id);
        if(transactions.isPresent()){
            Transactions _transaction = transactions.get();
            _transaction.setAccounts(transactionsDTO.getAccounts());
            _transaction.setCurrency(transactionsDTO.getCurrency());
            _transaction.setDescription(transactionsDTO.getDescription());
            _transaction.setDues(transactionsDTO.getDues());
            _transaction.setReceivedAmount(transactionsDTO.getReceivedAmount());
            _transaction.setTotalAmount(transactionsDTO.getTotalAmount());
            _transaction.setTransactionDate(transactionsDTO.getTransactionDate());
            _transaction.setTransactionType(transactionsDTO.getTransactionType());

            transactionsRepository.save(_transaction);
        }

        return "{\"UPDATED SUCCESFULLY\":1}";
    }
    public RestTemplateResponseDTO getTransactionsById(String id){
       Accounts accounts = accountsService.getAccountsByUserId(id);
       List<Transactions> transactions =  transactionsRepository.findByAccountsId(accounts.getId());
       RestTemplateResponseDTO response = new RestTemplateResponseDTO("200","Get Successfully",transactions);
        return response;
    }

}

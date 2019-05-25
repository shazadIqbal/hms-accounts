package com.hs.accounts.Services;

import com.hs.accounts.Commons.TransactionRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.TransactionsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Transactions;
import com.hs.accounts.Model.Vendor;
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

    @Autowired
    VendorService vendorService;

    public RestTemplateResponseDTO postTransaction(TransactionRestDTO transactionRestDTO){

        //Account Rest DTO Object
      //  TransactionsDTO transactionsDTO = new TransactionsDTO();
        Accounts customerAccount = accountsService.getAccountsByUserId(transactionRestDTO.getAccountNoUUID());
        Accounts shareAccount = accountsService.getAccountsByUserId(transactionRestDTO.getShareAccountNo());
        Accounts vendorAccount = accountsService.getAccountsByUserName("Barkhia Hospital");
    //    RestTemplateResponseDTO restTemplateResponseDTO = new RestTemplateResponseDTO();
//        Accounts account = new Accounts();
//        account.setId(1L);
//        accounts.add(account);

        //Customer Account
        if(customerAccount != null) {
//            transactionsDTO.setCurrency("PKR");
//            transactionsDTO.setDescription(transactionRestDTO.getDescription());
//            transactionsDTO.setReceivedAmount(transactionRestDTO.getReceivedAmount());
//            transactionsDTO.setTotalAmount(transactionRestDTO.getTotalAmount());
//            transactionsDTO.setTransactionType(transactionRestDTO.getTransactionType());
//            transactionsDTO.setOperationType(transactionRestDTO.getOperationType());
//            transactionsDTO.setAccounts(accounts);

            //Transaction Object
            Transactions customerTransactions = new Transactions();
            customerTransactions.setCurrency("PKR");
            customerTransactions.setAccounts(customerAccount);
            customerTransactions.setTransactionRefId(transactionRestDTO.getTransactionRefId());
            // set transaction ref id
            customerTransactions.setDescription(transactionRestDTO.getDescription());

            if(transactionRestDTO.getTotalAmount() > transactionRestDTO.getReceivedAmount())
            {
                customerTransactions.setDues(transactionRestDTO.getTotalAmount() - transactionRestDTO.getReceivedAmount());
            }
            else
                {
                    customerTransactions.setDues(0.0);
            }
            customerTransactions.setReceivedAmount(transactionRestDTO.getReceivedAmount());
            customerTransactions.setTotalAmount(transactionRestDTO.getTotalAmount());
            customerTransactions.setTransactionDate(new Date());
            customerTransactions.setTransactionType(transactionRestDTO.getTransactionType());
            customerTransactions.setOperationType(transactionRestDTO.getOperationType());
            transactionsRepository.save(customerTransactions);

            //Share Holder Account

    if(shareAccount != null) {

        //Transaction Object
        Transactions shareTransactions = new Transactions();
        shareTransactions.setCurrency("PKR");
        shareTransactions.setAccounts(shareAccount);
        shareTransactions.setDescription(transactionRestDTO.getShareDescription());
        shareTransactions.setDues(0.0);
        shareTransactions.setTransactionRefId(transactionRestDTO.getTransactionRefId());
        //set transactin ref Id
        shareTransactions.setReceivedAmount(0.0);
        shareTransactions.setTotalAmount(transactionRestDTO.getTotalAmount() * (transactionRestDTO.getSharePercent().doubleValue() / 100));
        shareTransactions.setTransactionDate(new Date());
        shareTransactions.setTransactionType("CREDIT");
        shareTransactions.setOperationType(transactionRestDTO.getOperationType());
        transactionsRepository.save(shareTransactions);

    }
    else{
        transactionRestDTO.setSharePercent(0);
    }


            //Vendor Account



                //Transaction Object
                Transactions vendorTransactions = new Transactions();
                vendorTransactions.setCurrency("PKR");
                vendorTransactions.setAccounts(vendorAccount);
                vendorTransactions.setDescription(vendorDescription(customerAccount, vendorAccount));
                vendorTransactions.setDues(0.0);
                vendorTransactions.setReceivedAmount(0.0);
                vendorTransactions.setTotalAmount(transactionRestDTO.getTotalAmount() - (transactionRestDTO.getTotalAmount() * (transactionRestDTO.getSharePercent().doubleValue() / 100)));
                vendorTransactions.setTransactionDate(new Date());
                vendorTransactions.setTransactionType("CREDIT");
                vendorTransactions.setTransactionRefId(transactionRestDTO.getTransactionRefId());
                vendorTransactions.setOperationType(transactionRestDTO.getOperationType());
                transactionsRepository.save(vendorTransactions);



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

    public String vendorDescription(Accounts customerAccount, Accounts vendorAccount){
        String des = vendorAccount.getUserName() + " share via " + customerAccount.getUserName();
        return des;
    }


    public RestTemplateResponseDTO deleteTransactionsByRefId(String refId)
    {
        try {
            transactionsRepository.deleteByTransactionRefId(refId);


            return new RestTemplateResponseDTO("200", "Transaction Successfully deleted");
        }
        catch (Exception ex)
        {
            return  new RestTemplateResponseDTO();
        }

    }


    public RestTemplateResponseDTO updateTransactionDues(Long id,TransactionsDTO transactionsDTO)
    {
        Optional<Transactions> obj=transactionsRepository.findById(id);

        if(obj.isPresent())
        {
            Transactions trans=obj.get();

            trans.setDues(transactionsDTO.getDues());
            trans.setReceivedAmount(transactionsDTO.getReceivedAmount());

            transactionsRepository.save(trans);
            return new RestTemplateResponseDTO("200","Successully updated!");
        }

        return new RestTemplateResponseDTO("000","Error Updating!");


    }


}



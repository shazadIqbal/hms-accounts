package com.hs.accounts.Services;

import com.hs.accounts.Commons.DashboardRestDTO;
import com.hs.accounts.Commons.TransactionRestDTO;
import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.TransactionsDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Transactions;
import com.hs.accounts.Model.Vendor;
import com.hs.accounts.Repository.AccountsRepository;
import com.hs.accounts.Repository.TransactionsRepository;
import com.hs.accounts.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepository transactionsRepository;
    @Autowired
    AccountsService accountsService;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    VendorService vendorService;

    public RestTemplateResponseDTO postTransaction(TransactionRestDTO transactionRestDTO){

        //Account Rest DTO Object
      //  TransactionsDTO transactionsDTO = new TransactionsDTO();
        Accounts customerAccount = accountsService.getAccountsByUserId(transactionRestDTO.getAccountNoUUID());
        Accounts shareAccount = accountsService.getAccountsByUserId(transactionRestDTO.getShareAccountNo());
        Accounts vendorAccount = accountsService.getAccountsByUserName("Barkhia Hospital");

        if(customerAccount != null) {

            //Transaction Object
            Transactions customerTransactions = new Transactions();
            customerTransactions.setCurrency("PKR");
            customerTransactions.setAccounts(customerAccount);
            customerTransactions.setCreatedAt(transactionRestDTO.getCreatedAt());
            customerTransactions.setCreatedBy(transactionRestDTO.getCreatedBy());
            customerTransactions.setFlag(transactionRestDTO.isFlag());

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
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
            customerTransactions.setTransactionDate(new Date());
            customerTransactions.setTransactionType(transactionRestDTO.getTransactionType());
            customerTransactions.setOperationType(transactionRestDTO.getOperationType());
            transactionsRepository.save(customerTransactions);

            //Share Holder Account

    if(shareAccount != null) {

        //Transaction Object
        Transactions shareTransactions = new Transactions();
        shareTransactions.setCurrency("PKR");
        shareTransactions.setCreatedAt(transactionRestDTO.getCreatedAt());
        shareTransactions.setCreatedBy(transactionRestDTO.getCreatedBy());
        shareTransactions.setFlag(transactionRestDTO.isFlag());
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
            vendorTransactions.setCreatedAt(transactionRestDTO.getCreatedAt());
            vendorTransactions.setCreatedBy(transactionRestDTO.getCreatedBy());
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
            _transaction.setUpdateAt(transactionsDTO.getUpdateAt());
            _transaction.setUpdatedBy(transactionsDTO.getUpdatedBy());

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
            trans.setUpdatedBy(transactionsDTO.getUpdatedBy());
            trans.setUpdateAt(transactionsDTO.getUpdateAt());
            trans.setReceivedAmount(transactionsDTO.getReceivedAmount());

            transactionsRepository.save(trans);
            return new RestTemplateResponseDTO("200","Successully updated!");
        }

        return new RestTemplateResponseDTO("000","Error Updating!");


    }


  public RestTemplateResponseDTO getDashboardByDateNaccount(DashboardRestDTO dashboardRestDTO)
  {
      if(dashboardRestDTO.getRole().equalsIgnoreCase("Hospital"))
      {
          Vendor obj=vendorRepository.findByName("Barkhia Hospital");

          String account_no=obj.getAccountNo();

          Accounts accounts=accountsRepository.findByUserId(account_no);

//          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
          List<Transactions> transactions=transactionsRepository.getByDateDurationNaccount(accounts.getId(),dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());//findByTransactionDateBetween(dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());//findByEffctDateAfterAndExpDateBefore(date, date); //getByDateDuration(dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());
          return new RestTemplateResponseDTO("200","Get successfully",transactions);

      }
      return new RestTemplateResponseDTO("000","Transactions fetching failed!");
  }

    public RestTemplateResponseDTO getDashboardByDate(DashboardRestDTO dashboardRestDTO)
    {
        if(dashboardRestDTO.getRole().equalsIgnoreCase("Hospital"))
        {
//          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            List<Transactions> transactions=transactionsRepository.getByDateDuration(dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());//findByTransactionDateBetween(dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());//findByEffctDateAfterAndExpDateBefore(date, date); //getByDateDuration(dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());
            return new RestTemplateResponseDTO("200","Get successfully",transactions);

        }
        return new RestTemplateResponseDTO("000","Transactions fetching failed!");
    }

    public RestTemplateResponseDTO gatAllTransactions(DashboardRestDTO dashboardRestDTO) {
        if(dashboardRestDTO.getRole() == null){
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            List<Transactions> transactions = transactionsRepository.getByDateDuration(dashboardRestDTO.getFrom(), dashboardRestDTO.getTill());
            return new RestTemplateResponseDTO("200","Get successfully", transactions);
        }
        else if(dashboardRestDTO.getRole()  != null) {
            List<Transactions> transactions = transactionsRepository.getByUserName(dashboardRestDTO.getRole(), dashboardRestDTO.getFrom(), dashboardRestDTO.getTill());
            return new RestTemplateResponseDTO("200", "Get Successfully", transactions);
        }

        return new RestTemplateResponseDTO("000","Transactions fetching failed!!");
    }


    public RestTemplateResponseDTO getAllFaultyTransactions(DashboardRestDTO dashboardRestDTO) {
        if (dashboardRestDTO.getRole() == null) {
            List<Transactions> transactions = transactionsRepository.getFaultyReportsByDateDuration(dashboardRestDTO.getFrom(), dashboardRestDTO.getTill());
            return new RestTemplateResponseDTO("200","Get successfully", transactions);
        }
        else if(dashboardRestDTO.getRole()  != null) {
            List<Transactions> transactions = transactionsRepository.getFaultyReportsByUserName(dashboardRestDTO.getRole(), dashboardRestDTO.getFrom(), dashboardRestDTO.getTill());
            return new RestTemplateResponseDTO("200", "Get Successfully", transactions);
        }

        return new RestTemplateResponseDTO("000","Transactions fetching failed!!");
    }


    public RestTemplateResponseDTO getAllHospitalTransactions(DashboardRestDTO dashboardRestDTO){
        Vendor obj=vendorRepository.findByName("Barkhia Hospital");
        if(dashboardRestDTO.getRole() == null){
            String account_no=obj.getAccountNo();

            Accounts accounts = accountsRepository.findByUserId(account_no);
            List<Transactions> transactions = transactionsRepository.getByDateDurationNaccount(accounts.getId(),dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());

            return new RestTemplateResponseDTO("200","Get successfully",transactions);

        }
        else if(dashboardRestDTO.getRole()!= null){

            String account_no = obj.getAccountNo();
            Accounts accounts = accountsRepository.findByUserId(account_no);
            List<Transactions> transactions = transactionsRepository.getByOperationTypeAndAccounts(accounts.getId(),dashboardRestDTO.getRole(),dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());

            return new RestTemplateResponseDTO("200","Get successfully",transactions);
        }
        else {
            return new RestTemplateResponseDTO("000", "Transactions fetching failed!");
        }
    }


    public RestTemplateResponseDTO getAllDoctorTransactions(DashboardRestDTO dashboardRestDTO) throws Exception {
        if(dashboardRestDTO.getRole() == null) {
            Date  dateFrom = dateformatter(dashboardRestDTO.getFrom());
            Date  dateTill = dateformatter(dashboardRestDTO.getTill());
            List<Accounts> accounts = accountsRepository.findByAccountType("Doctor Account");
            List<Transactions> doctorTransaction = new ArrayList<>();
            for (Accounts account : accounts) {
                List<Transactions> datedTransactions = account.getTransactions().stream().filter(t-> {
                    boolean flag = (t.getTransactionDate().equals(dateFrom) && t.getTransactionDate().equals(dateTill)) || (t.getTransactionDate().after(dateFrom) && t.getTransactionDate().before(dateTill));
                    return flag;
                }).collect(Collectors.toList());//(t.getTransactionDate().getTime() >= dateFrom.getTime() && t.getTransactionDate().getTime()<= dateTill.getTime())).collect(Collectors.toList());
                if(datedTransactions != null){
                    doctorTransaction.addAll(datedTransactions);
                }

            }

            return new RestTemplateResponseDTO("200","Get successfully",doctorTransaction);
        }
        else if(dashboardRestDTO.getRole() != null){
            Accounts obj = accountsRepository.findByUserId(dashboardRestDTO.getRole());

            if(obj == null){
                return new RestTemplateResponseDTO("000", "Transactions fetching failed!");
            }
            else{
                Long account_id = obj.getId();
                List<Transactions> doctorTransactions = transactionsRepository.getByDateDurationNaccount(account_id,dashboardRestDTO.getFrom(),dashboardRestDTO.getTill());
                return new RestTemplateResponseDTO("200","Get successfully",doctorTransactions);
            }
        }
        else{
            return new RestTemplateResponseDTO("000", "Transactions fetching failed!");
        }
    }

    public Date dateformatter(String strDate) throws Exception {
        Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(strDate);
        return date1;
    }


}



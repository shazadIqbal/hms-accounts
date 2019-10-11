package com.hs.accounts.Services;

import com.hs.accounts.Commons.RestTemplateResponseDTO;
import com.hs.accounts.DTO.VendorDTO;
import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Vendor;
import com.hs.accounts.Repository.AccountsRepository;
import com.hs.accounts.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

@Service
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Transient
    private UUID corrId;

    public RestTemplateResponseDTO postVendor(VendorDTO vendorDTO){
        Vendor isexist = vendorRepository.findByName(vendorDTO.getName());
        if(isexist == null) {
            //Create Vendor
            corrId = UUID.randomUUID();
            Vendor vendor = new Vendor();
            vendor.setName(vendorDTO.getName());
            vendor.setStartDate(new Date());
            vendor.setDescription(vendorDTO.getDescription());
            vendor.setAccountNo(corrId.toString());
            vendor.setSharePercent(vendorDTO.getSharePercent());
            vendor.setCreatedAt(new Date());
            vendorRepository.save(vendor);

            //Create Vendor Account
            Accounts accounts = new Accounts();
            accounts.setUserId(vendor.getAccountNo());
            accounts.setAccountType("Vendor Account");
            accounts.setCreatedDate(new Date());
            accounts.setGender("");
            accounts.setStatus("Active");
            accounts.setUserName(vendor.getName());
            //accounts.setTransactions(accountRestDTO.getTransactions());
            accountsRepository.save(accounts);
            return new RestTemplateResponseDTO("200", "successfull");
        }
        else {
            return new RestTemplateResponseDTO("200", "Vendor Already exists");
        }

    }
    public Vendor getVendorByAccountNo(String accountNo){
        Vendor vendor = vendorRepository.findByAccountNo(accountNo);
        return vendor;
    }

}

package com.hs.accounts.Repository;

import com.hs.accounts.Model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    Vendor findByAccountNo(String accountNo);
}

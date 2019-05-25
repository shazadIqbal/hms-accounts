package com.hs.accounts.Repository;

import com.hs.accounts.Model.Accounts;
import com.hs.accounts.Model.Transactions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
    List<Transactions> findByAccountsId(Long accountId);


//    @Modifying
//    @Query("delete from Transactions t where t.accountId = :accountId")
//    void deleteByAccountsId(@Param("accountId") Long accountId);

    @Transactional
    @Modifying
    void deleteByAccountsId(Long accountId);


    @Transactional
    @Modifying
     void deleteByTransactionRefId(String refId);


}

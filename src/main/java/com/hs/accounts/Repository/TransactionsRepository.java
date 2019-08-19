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
import java.util.Date;
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

    @Query(value = "select * from transactions where account_id=:id AND transaction_date between :from AND :till order by transaction_date",nativeQuery = true)
   public List<Transactions> getByDateDurationNaccount(@Param("id") Long id, @Param("from") String  from,@Param("till") String till);


    @Query(value = "select * from transactions where  transaction_date between :from AND :till order by transaction_date",nativeQuery = true)
    public List<Transactions> getByDateDuration( @Param("from") String  from,@Param("till") String till);
  //  List<Transactions> findByStartDateBetween(Date from, Date till);

   // List<Transactions> findByTransactionDateBetween(Date from, Date till);
    @Query(value = "select * from transactions where created_by=:role AND transaction_date between :from AND :till order by transaction_date",nativeQuery = true)
    public List<Transactions> getByUserName(@Param("role") String role, @Param("from") String  from,@Param("till") String till);
    //List<Transactions> findByAccountIdANDTransactionDateBetween(int i, Date from, Date till);

    //List<Transactions> findByAccount_IdANDTransactionDateBetween(int i, Date from, Date till);
}

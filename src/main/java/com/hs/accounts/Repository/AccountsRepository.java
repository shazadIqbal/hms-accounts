package com.hs.accounts.Repository;

import com.hs.accounts.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Accounts findByUserId(String userId);
    Accounts findByUserName(String userName);

    List<Accounts> findByAccountType(String userName);

}

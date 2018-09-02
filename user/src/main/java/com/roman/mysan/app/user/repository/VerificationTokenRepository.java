package com.roman.mysan.app.user.repository;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
    VerificationToken findByUser(UserAccount user);
}

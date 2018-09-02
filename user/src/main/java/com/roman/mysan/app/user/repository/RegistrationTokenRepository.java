package com.roman.mysan.app.user.repository;

import com.roman.mysan.app.user.domain.UserAccount;
import com.roman.mysan.app.user.domain.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {

    RegistrationToken findByToken(String token);
    RegistrationToken findByUser(UserAccount user);
}

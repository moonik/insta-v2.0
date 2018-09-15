package com.roman.mysan.app.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.roman.mysan.app.user.domain.Authority;
import com.roman.mysan.app.user.domain.AuthorityName;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>{

    Authority findByName(AuthorityName authorityName);
}

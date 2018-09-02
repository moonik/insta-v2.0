package com.roman.mysan.app.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
public class RegistrationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    private UserAccount user;

    private Date expiryDate;

    public void setExpDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, 1440); //24 hours
        this.expiryDate = new Date(cal.getTime().getTime());
    }

    public RegistrationToken(String token, UserAccount user) {
        this.token = token;
        this.user = user;
    }
}

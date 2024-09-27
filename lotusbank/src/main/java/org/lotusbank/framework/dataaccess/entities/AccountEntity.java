package org.lotusbank.framework.dataaccess.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class AccountEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Getter
    @Setter
    @Column(name = "customer_id")
    private int customer_id;

    @Getter
    @Setter
    @Column(name = "account_type")
    private String type;

    @Getter
    @Setter
    @Column(name = "accountopeningdate")
    private String accountopeningdate;

    @Getter
    @Setter
    @Column(name = "sub_type")
    private String subType;

    @Getter
    @Setter
    @Column(name = "balance")
    private String balance;

}

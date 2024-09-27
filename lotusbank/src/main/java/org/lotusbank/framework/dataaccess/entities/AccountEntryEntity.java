package org.lotusbank.framework.dataaccess.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accountentry")
public class AccountEntryEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "entry_date", nullable = false)
    private String entry_date;

    @Getter
    @Setter
    @Column(name = "account_id")
    private int account_id;

    @Setter
    @Getter
    @Column(name = "description")
    private String description;

    @Setter
    @Getter
    @Column(name = "amount")
    private double amount;
}

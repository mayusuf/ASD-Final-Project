package org.lotusbank.framework.dataaccess.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name = "street", nullable = false)
    private String street;

    @Getter
    @Setter
    @Column(name = "state")
    private String state;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Setter
    @Getter
    @Column(name = "zip_code")
    private String zipCode;
}

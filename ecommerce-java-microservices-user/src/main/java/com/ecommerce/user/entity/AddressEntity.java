package com.ecommerce.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "address")
public class AddressEntity {

    public AddressEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private String doorNo;

    private String street;

    private String building;

    private String city;

    private Integer pinCode;

    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetailsEntity userDetails;
}

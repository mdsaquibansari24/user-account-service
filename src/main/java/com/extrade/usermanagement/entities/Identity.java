package com.extrade.usermanagement.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="identity")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_identity_id")
    private int userIdentityId;

    @Column(name="identity_number")
    private String identityNo;
    @Column(name="issued_authority")
    private String issuedAuthority;
    @Column(name="expiry_year")
    private int expiryYear;
    @Column(name="expiry_month")
    private int expiryMonth;
    @Column(name="last_modified_dt")
    private LocalDateTime lastModifiedDate;
    @Column(name="last_modified_by")
    private String lastModifiedBy;

    //say you have a Product entity and a Category entity.
    // Each product belongs to a category, but many products
    // can belong to the same category. In the Product class,
    // you would use @ManyToOne to indicate that the product
    // belongs to a category.
    //many identity belongs to identity_type
    @ManyToOne
    @JoinColumn(name = "identity_type_id")
    private IdentityType identityType;

}

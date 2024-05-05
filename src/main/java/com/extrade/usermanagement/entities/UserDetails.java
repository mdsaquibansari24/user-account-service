package com.extrade.usermanagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="user_details")
@GenericGenerator(name = "user_account_foreign_gen", strategy = "foreign", //Now, when it comes to primary key generation, the "foreign" strategy means that instead of generating a new primary key value for a particular entity independently, it will be derived from a related entity's primary key.
        parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "userAccount")})   //This parameter likely tells the generator strategy (in this case, the "foreign" strategy) which property or field in the UserDetails entity to use when generating primary key values.
public class UserDetails {
    @Id
    @GeneratedValue(generator="user_account_foreign_gen")
    @Column(name="user_account_id")
    private int userAccountId;

    private int experience;
    @Column(name="highest_degree")
    private String highestDegree;
    @Column(name="position_type")
    private String positionType;
    @Column(name="hired_dt")
    private LocalDate hiredDate;
    @Column(name="terminated_dt")
    private String terminatedDate;
    @Column(name="reason_for_termination")
    private String reasonForTermination;

    @OneToOne  // one user one detail
    @PrimaryKeyJoinColumn //Specifies that the primary key of the UserDetails entity is also a foreign key to the UserAccount entity.
    private UserAccount userAccount;

    @Column(name="last_modified_dt")
    private LocalDateTime lastModifiedDate;
    @Column(name="last_modified_by")
    private String lastModifiedBy;
}

//In many databases, primary keys are generated automatically by the database itself. However, in some cases, you might
// want more control over how primary keys are generated, especially when dealing with relationships between entities.
//
//Imagine you have two tables: UserDetails and UserAccount. UserDetails holds details about users, and each user has an
// associated UserAccount.
//
//        Now, let's say you want the primary key of UserDetails to be derived from the primary key of UserAccount.
//        This means that each UserDetails entry will have a primary key that is the same as the primary key of the
//        corresponding UserAccount.
//
//To achieve this, you need a custom strategy for generating primary keys. This is where @GenericGenerator comes in.
// You can define a custom strategy that tells Hibernate to use the primary key of UserAccount as the primary key for
// UserDetails.
//
//        Here's a simple breakdown of the custom strategy:
//
//Name: You give a name to your custom strategy, like "user_account_foreign_gen", so Hibernate can recognize it.
//        Strategy: You specify the strategy as "foreign", indicating that you want to base the primary key of UserDetails on a foreign key relationship with UserAccount.
//         Parameters: You provide additional parameters to the strategy. In this case, you specify that the property to be used for generating the primary key is "userAccount", meaning that UserDetails will use the primary key of the associated UserAccount for its own primary key.
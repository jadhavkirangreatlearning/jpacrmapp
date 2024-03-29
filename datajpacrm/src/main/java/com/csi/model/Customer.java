package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;

    @Size(min = 2, message = "Customer Name should be atleast 2 character")
    private String custName;

    private String custAddress;

    private double custAccountBalance;

    @Column(unique = true)
    @Range(min = 1000000000, max = 9999999999L, message = "Customer Contact Number must be 10 digit")
    private long custContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date custDOB;

    @Column(unique = true)
    @Email(message = "Email ID must be valid")
    private String custEmailId;

    @Size(min = 4, message = "Password should be atleast 4 character")
    private String custPassword;
}

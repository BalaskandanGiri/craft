package com.intuit.craft.data;

import com.intuit.craft.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessProfileData {

    @NotBlank(message = "Company name is mandatory")
    @Size(max = 255, message = "Company name must be between {min} and {max} characters long")
    private String companyName;

    @NotBlank(message = "Legal name is mandatory")
    @Size(max = 255, message = "Legal name must be between {min} and {max} characters long")
    private String LegalName;

    private Address businessAddress;

    private Address legalAddress;

    @NotBlank(message = "Tax identifier name is mandatory")
    private String taxIdentifier;

    @NotBlank(message = "Email is mandatory")
    @Size(max = 255, message = "Email must be between {min} and {max} characters long")
    private String email;

    private String website;

    private List<String> products;
}

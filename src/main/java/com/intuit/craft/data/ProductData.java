package com.intuit.craft.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductData {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "Name must be between {min} and {max} characters long")
    String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1000, message = "Description must be between {min} and {max} characters long")
    String description;
}

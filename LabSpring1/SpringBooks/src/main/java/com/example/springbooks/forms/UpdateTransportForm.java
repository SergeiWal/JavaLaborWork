package com.example.springbooks.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransportForm {
    private String type;
    private String number;
    private int newCost;
}

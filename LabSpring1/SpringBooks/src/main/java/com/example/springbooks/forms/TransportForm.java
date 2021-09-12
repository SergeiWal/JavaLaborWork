package com.example.springbooks.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportForm {
    private String type;
    private String number;
    private int cost;
}

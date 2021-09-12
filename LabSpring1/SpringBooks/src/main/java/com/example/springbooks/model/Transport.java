package com.example.springbooks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    private String type;
    private String number;
    private int cost;
}

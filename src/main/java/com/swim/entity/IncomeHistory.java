package com.swim.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class IncomeHistory {
    private Integer incomeId;
    private Integer id;
    private String source;
    private Float incomeMoney;
    private Timestamp time;
}

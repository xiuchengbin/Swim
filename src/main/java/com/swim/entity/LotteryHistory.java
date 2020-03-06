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
public class LotteryHistory {
    private Integer lotteryId;
    private Integer id;
    private String prize;
    private Timestamp lotteryTime;
}

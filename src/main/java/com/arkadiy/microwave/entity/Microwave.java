package com.arkadiy.microwave.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Scope("singleton")
@Component("microwave")
public class Microwave {
    private int timeUsage = 0;
    private int countUsage = 0;
    private boolean needMaintenance = false;
}

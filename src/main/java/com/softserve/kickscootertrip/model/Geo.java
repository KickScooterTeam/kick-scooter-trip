package com.softserve.kickscootertrip.model;


import com.softserve.kickscootertrip.dto.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@RedisHash("Geo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Geo implements Serializable {
    @Id
    private String id;
    @Indexed
    private UUID scooterId;
    private Point point;


}

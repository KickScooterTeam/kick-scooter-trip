package com.softserve.kickscootertrip.model;

import com.softserve.kickscootertrip.dto.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "geolocation")
public class Geo{
        @Id
        @GeneratedValue(generator = "UUID")
        private UUID id;

//        @ElementCollection(fetch = FetchType.EAGER)
//        @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
        private UUID scooterId;
        private Point point;
}

package org.example.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AirportDTO {
    private Long id;
    private String code;
    private String name;
    private String city;
    private String country;
    private String timeZone;

    @Data
    @Builder

    public static class CreateAirport {
        @NotBlank(message = "Airport code is required")
        @Size(min = 3, max = 3)
        private String code;

        @NotBlank(message = "Airport name is required")
        private String name;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "Country is required")
        private String country;

        private String timeZone;
        private Double latitude;
        private Double longitude;
    }
}

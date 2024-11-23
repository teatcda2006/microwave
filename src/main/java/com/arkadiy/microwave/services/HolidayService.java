package com.arkadiy.microwave.services;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class HolidayService {
    private static final String HOLIDAYS_API_URL = "https://date.nager.at/Api/v3/PublicHolidays/{year}/{country}";

    public boolean isHoliday() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            int year = LocalDate.now().getYear();
            String url = HOLIDAYS_API_URL.replace("{year}", String.valueOf(year))
                    .replace("{country}", "KZ");
            ResponseEntity<PublicHoliday[]> response = restTemplate.getForEntity(url, PublicHoliday[].class);

            if (response.getBody() != null) {
                List<LocalDate> holidays = Arrays.stream(response.getBody())
                        .map(PublicHoliday::getDate)
                        .toList();
                return holidays.contains(LocalDate.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Data
    static class PublicHoliday {
        private LocalDate date;
    }
}

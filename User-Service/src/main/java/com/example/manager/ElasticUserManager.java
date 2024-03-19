package com.example.manager;


import com.example.dto.request.UserRequestDto;
import com.example.dto.request.UserUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.constants.RestApiUrls.ADD;

@FeignClient(name = "elastic-user-service", url = "http://localhost:9091/dev/v1/elastic/user")
public interface ElasticUserManager {
    @PostMapping(ADD)
    public ResponseEntity<Void> save(@RequestBody UserRequestDto dto);

}

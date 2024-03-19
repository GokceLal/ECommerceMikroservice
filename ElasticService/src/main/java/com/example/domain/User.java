package com.example.domain;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "user-index")
public class User implements Serializable {
    @Id
    String id;
    Long authId;
    String email;
    String photo;
    String userName;
    String phone;
    String about;
    String name;
}

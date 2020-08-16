package com.bigbird.vuebackstage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class User {
    private String id;
    private String name;
    private int age;
    private String email;
    private String address;
    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date birthday;
}

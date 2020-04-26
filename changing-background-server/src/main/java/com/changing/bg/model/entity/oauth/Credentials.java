package com.changing.bg.model.entity.oauth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Credentials {

    private Long id;

    private Integer version;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    private List<Authority> authorities;

    private boolean enabled;
}

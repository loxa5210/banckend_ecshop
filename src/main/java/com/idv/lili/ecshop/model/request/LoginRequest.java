package com.idv.lili.ecshop.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginRequest {

	private String email;

	private String password;
}

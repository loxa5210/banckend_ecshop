package com.idv.lili.ecshop.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UsersResponse {

	private String email;

	private String token;

}

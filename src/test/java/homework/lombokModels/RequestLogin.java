package homework.lombokModels;

import lombok.Data;

@Data
public class RequestLogin {
	private String password;
	private String email;
}
package com.PuckYeah.responseModel;

import java.util.List;

public class Tokens extends BaseResponse {
	
	private List<Token> tokens;

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	public class Token {
		
		private String token;
		
		private int rights;

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public int getRights() {
			return rights;
		}

		public void setRights(int rights) {
			this.rights = rights;
		}
	}

}

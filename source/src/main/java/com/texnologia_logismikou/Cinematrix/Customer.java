package com.texnologia_logismikou.Cinematrix;

	public class Customer {
		private String full_name;
		private String email;
		private String phone_number;
		private boolean admin;
		private String password;
		
		public Customer() {
			
		}
		
		public Customer(boolean admin, String fullName, String email, String phoneNumber, String password) {
			
			this.admin = admin;
			this.full_name = fullName;
			this.email = email;
			this.phone_number = phoneNumber;
			this.password = password;
		}
		
		//Getters and Setters
		public String getFull_name() {
			return full_name;
		}
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setFull_name(String full_name) {
			this.full_name = full_name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone_number() {
			return phone_number;
		}
		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}

		public boolean getAdmin() {
			
			return admin;
		}
		
		public void setAdmin(boolean newAdmin) {
			
			admin = newAdmin;
		}
}

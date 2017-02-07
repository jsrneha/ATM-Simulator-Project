public class Admin {

		private String adminid ;  
		private String name ;
		private String address ; 
		private String phone ; 
		private String password ;
		
		public String getAdminid() {
			return adminid;
		}
		public void setAdminid(String adminid) {
			this.adminid = adminid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String toString()
		{
			return (this.adminid + this.name + this.address + this.phone);
		}
		public int hashCode()
		{
			return Integer.parseInt(adminid); 
		}		
}		

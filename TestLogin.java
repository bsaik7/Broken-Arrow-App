package com.brokenarrow.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class TestLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login1 l=new Login1();
		System.out.println(l.doLogin("battula.krishna@subex.com", "battula.krishna@subex.com"));
	}
}
class Login1 
	{
	   	    public String doLogin(@QueryParam("username") String uname, @QueryParam("password") String pwd){
	        String response = "";
	        if(checkCredentials(uname, pwd)){
	            response = Utility.constructJSON("login",true);
	        }else{
	            response = Utility.constructJSON("login", false, "Incorrect Email or Password");
	        }
	    return response;        
	    }
	 
	    //Method to check whether the entered credential is valid

	    private boolean checkCredentials(String uname, String pwd){
	        //System.out.println("Inside checkCredentials");
	        boolean result = false;
	        if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
	            try {
	                result = DBConnection.checkLogin(uname, pwd);
	                //System.out.println("Inside checkCredentials try "+result);
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                //System.out.println("Inside checkCredentials catch");
	                result = false;
	            }
	        }else{
	            //System.out.println("Inside checkCredentials else");
	            result = false;
	        }
	 
	        return result;
	    }
	}

package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import java.lang.Exception;
import java.lang.RuntimeException;



@Controller
public class LoginController {

	@GetMapping("/Login")
	

    public String login(@RequestParam(name="fname", required=false, defaultValue="World") String name, Model model) {
		
		try{
			System.out.println(name);
			if(!name.equals("")){
				System.out.println("No name");

			}
			else {
				model.addAttribute("name", name);
				User u = new User();
				u.setUserName(name);
				u.printUserName(u);
			}

			

		}
		catch (RuntimeException e){
			
			System.out.println(e);
			

		}
		finally{
			System.out.println("Do something");

		}
		

	
		return "hello";
	}

}

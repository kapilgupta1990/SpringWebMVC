package com.cdk.gist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cdk.gist.model.Employee;

@Controller
public class EmployeeController {

	public EmployeeController() {
		System.out.println("=============Inside EmployeeController==========");
	}
	@RequestMapping(method=RequestMethod.GET,value="/empform")
	public ModelAndView getEmployee(){
		return new ModelAndView("empform","command",new Employee());  
	}
	@RequestMapping(value="/save",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("employee") Employee employee){  
        //write code to save emp object  
        //here, we are displaying emp object to prove emp has data  
        System.out.println(employee.getName()+" "+employee.getSalary()+" "+employee.getDesignation());  
          
       // return new ModelAndView("empform","command",employee);//will display object data  
        return new ModelAndView("redirect:/viewemp");//will redirect to viewemp request mapping  
    }  
	@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        return "Hello world";
    }
	@RequestMapping("/viewemp")  
    public ModelAndView viewemp(){  
        //write the code to get all employees from DAO  
        //here, we are writing manual code of list for easy understanding  
        List<Employee> list=new ArrayList<Employee>();  
        list.add(new Employee(1,"rahul",35000f,"S.Engineer"));  
        list.add(new Employee(2,"aditya",25000f,"IT Manager"));  
        list.add(new Employee(3,"sachin",55000f,"Care Taker"));  
          
        return new ModelAndView("viewemp","list",list);  
    }  
}

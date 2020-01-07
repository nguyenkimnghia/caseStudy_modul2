package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerFrom;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@PropertySource("classpath:globle_config_app.properties")
public class CustomerController {

    @Autowired
    Environment env;

    @Autowired
    CustomerService customerService;

    @RequestMapping("/home")
    public ModelAndView viewsHome() {
        ModelAndView modelAndView = new ModelAndView("/trang chu");
        return modelAndView;
    }

    @RequestMapping("/registration")
    public ModelAndView addCustomer() {
        //add Product to database
        ModelAndView modelAndView = new ModelAndView("/registration");
        modelAndView.addObject("customerFrom", new CustomerFrom());
        return modelAndView;
    }

    @RequestMapping("save-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customerFrom") CustomerFrom customerFrom, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        // lay ten file
        MultipartFile multipartFile = customerFrom.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload");

        // luu file len server
        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(customerFrom.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // tao doi tuong de luu vao db
        Customer customer = new Customer(customerFrom.getName(), customerFrom.getAddress(), customerFrom.getEmail(), customerFrom.getPhone(), fileName);

        //add to database
        customerService.AddCustomer(customer);
        ModelAndView modelAndView = new ModelAndView("/registration");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @RequestMapping("/customer")

    public ModelAndView getAllProduct() {

        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("viewCustomer");
        modelAndView.addObject("customers", customers);

        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        List<Customer> customerList = customerService.findAll();
        boolean check = false;
        for (int i = 0; i <customerList.size() ; i++) {
            if(customerList.get(i).getName().equals(name) && customerList.get(i).getPhone().equals(phone)){
                check = true;
            }
        }
        if(check){
            Customer customer = customerService.login(name,phone);
            ModelAndView modelAndView = new ModelAndView("/viewCustomer");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/trang chu");
            return modelAndView;
        }
    }
}

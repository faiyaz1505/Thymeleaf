package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {
	
	@Autowired
    private EmployeeService service;

    @GetMapping({"/",""})
    public String viewHomePage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("employees", service.listAll(PageRequest.of(page, 5)));
        model.addAttribute("currentPage", page);
        return "employee-list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee emp,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "employee-form";
        }
        service.save(emp);  // JPA will update if ID exists, insert if null
        return "redirect:/";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.get(id));
        return "employee-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

}

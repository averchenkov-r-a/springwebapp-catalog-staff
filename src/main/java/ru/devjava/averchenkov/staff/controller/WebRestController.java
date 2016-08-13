package ru.devjava.averchenkov.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.service.structure.IStaffService;

/**
 * Rest контроллер.
 *
 * @author Averchenkov R.A.
 */
@RestController
@RequestMapping("/api")
public class WebRestController {

    private IStaffService staffService;
    @Autowired
    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("/home")
    public String home(){
        return "{\"home\": \"available\"}";
    }

    @RequestMapping(value = "/staff", method = { RequestMethod.GET, RequestMethod.POST})
    public Staff staffById(@RequestParam(value = "id", required = false) String stfId){
        return staffService.getStaffById(stfId);
    }
}

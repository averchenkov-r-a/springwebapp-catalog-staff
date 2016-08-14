package ru.devjava.averchenkov.staff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.service.structure.IStaffService;

import java.util.List;

/**
 * Rest контроллер.
 *
 * @author Averchenkov R.A.
 */
@RestController
@RequestMapping("/api")
public class WebRestController {
    private static Logger logger = LoggerFactory.getLogger(WebRestController.class);

    private IStaffService staffService;
    @Autowired
    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping("/home")
    public String home(){
        logger.info("Request [/home]");
        return "{\"home\": \"available\"}";
    }

    @RequestMapping(value = "/find/staff/id", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> staffById(@RequestParam(value = "id", required = false) String stfId){
        logger.info("Request [/staff] arg: id='{}'", stfId);
        return staffService.getStaffById(stfId);
    }

    @RequestMapping(value = "/find/staff", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaff(@RequestParam(value = "page", required = false) String page,
                                @RequestParam(value = "count", required = false) String count){
        logger.info("Request [/find/staff] arg: page='{}'; count='{}'", page, count);
        return staffService.getAllStaff(page, count);
    }

    @RequestMapping(value = "/find/staff/name", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaffByName(@RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "count", required = false) String count,
                                       @RequestParam(value = "name", required = false) String name){
        logger.info("Request [/find/staff/name] arg: page='{}'; count='{}'; name='{}'",
                new Object[] { page, count, name});
        return staffService.getStaffByName(name, page, count);
    }

    @RequestMapping(value = "/find/staff/surname", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaffBySurname(@RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "count", required = false) String count,
                                       @RequestParam(value = "surname", required = false) String surname){
        logger.info("Request [/find/staff/surname] arg: page='{}'; count='{}'; surname='{}'",
                new Object[] { page, count, surname});
        return staffService.getStaffBySurname(surname, page, count);
    }

    @RequestMapping(value = "/find/staff/patronymic", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaffByPatronymic(@RequestParam(value = "page", required = false) String page,
                                          @RequestParam(value = "count", required = false) String count,
                                          @RequestParam(value = "patronymic", required = false) String patronymic){
        logger.info("Request [/find/staff/patronymic] arg: page='{}'; count='{}'; patronymic='{}'",
                new Object[] { page, count, patronymic});
        return staffService.getStaffByPatronymic(patronymic, page, count);
    }

    @RequestMapping(value = "/find/staff/post", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaffByPost(@RequestParam(value = "page", required = false) String page,
                                          @RequestParam(value = "count", required = false) String count,
                                          @RequestParam(value = "post", required = false) String post){
        logger.info("Request [/find/staff/post] arg: page='{}'; count='{}'; surname='{}'",
                new Object[] { page, count, post});
        return staffService.getStaffByPost(post, page, count);
    }

    @RequestMapping(value = "/find/staff/birthday", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> pageStaffByBirthday(@RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "count", required = false) String count,
                                       @RequestParam(value = "birthday", required = false) String birthday){
        logger.info("Request [/find/staff/birthday] arg: page='{}'; count='{}'; birthday='{}'",
                new Object[] { page, count, birthday});
        return staffService.getStaffByBirthday(birthday, page, count);
    }
}

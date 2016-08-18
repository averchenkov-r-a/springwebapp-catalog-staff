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
import ru.devjava.averchenkov.staff.service.structure.IStaffService;

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

    @RequestMapping(value = "/find/staff", method = { RequestMethod.GET, RequestMethod.POST })
    public Page<Staff> findStaff(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "patronymic", required = false) String patronymic,
            @RequestParam(value = "post", required = false) String post,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "count", required = false) String count
    ) {
        logger.info("Request [/find/staff/] args: id='{}'; name='{}'; surname='{}'; " +
                        "patronymic='{}'; post='{}'; birthday='{}'; page='{}'; count='{}'",
                new Object[]{id, name, surname, patronymic,
                        post, birthday, page, count});
        return staffService.findStaff(page, count, new String[] {id, name, surname, patronymic, post, birthday });
    }
}

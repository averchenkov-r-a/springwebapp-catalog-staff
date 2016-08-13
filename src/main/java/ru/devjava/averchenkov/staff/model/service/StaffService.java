package ru.devjava.averchenkov.staff.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.repositories.StaffRepositories;
import ru.devjava.averchenkov.staff.model.service.structure.IStaffService;


/**
 * Класс работы с данными сотрудника.
 *
 * @author Averchenkov R.A.
 */
@Service
public class StaffService implements IStaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    private StaffRepositories staffRepositories;
    @Autowired
    public void setStaffRepositories(StaffRepositories staffRepositories) {
        this.staffRepositories = staffRepositories;
    }

    @Override
    public Staff getStaffById(String stfId) {
        logger.info("Invoke [getStaffById] with arg: stfId = '{}'", stfId);
        logger.debug("[getStaffById] with arg: stfId = '{}'", stfId);

        Staff staff = new Staff();
        if (stfId == null) {
            return staff;
        }
        Long id = null;
        try {
            id = Long.parseLong(stfId);
        } catch (NumberFormatException ex){
            logger.error("Error [getStaffById] parse to Long: '{}'", stfId);
        }
        if (id == null) {
            return staff;
        }
        staff = staffRepositories.findOne(id);
        logger.debug("[getStaffById] return staff: {}", staff);

        return staff;
    }

    @Override
    public Staff saveStaff(Staff staff) {
        logger.info("Invoke [saveStaff] with args: {}", staff);

        logger.debug("[saveStaff] with arg: {}", staff);
        Staff saveStaff = staffRepositories.save(staff);
        logger.debug("Saved in [saveStaff]: {}", saveStaff);

        return saveStaff;
    }
}

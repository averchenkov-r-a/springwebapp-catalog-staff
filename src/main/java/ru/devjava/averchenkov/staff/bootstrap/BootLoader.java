package ru.devjava.averchenkov.staff.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.service.structure.IStaffService;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Класс загрузки тестовых данных.
 *
 * @author Averchenkov R.A.
 */
@Component
public class BootLoader implements ApplicationListener<ContextRefreshedEvent> {
    private IStaffService staffService;
    @Autowired
    public void setStaffService(IStaffService staffService) {
        this.staffService = staffService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Staff staff1 = new Staff(
                "Виктор",
                "Литвинов",
                "Сергеевич",
                "программист",
                Date.valueOf(LocalDate.of(1994, 11, 25))
        );

        Staff staff2 = new Staff(
                "Иван",
                "Подубный",
                "Андреевич",
                "ст. инженер",
                Date.valueOf(LocalDate.of(1985, 2, 10))
        );

        Staff staff3 = new Staff(
                "Иван",
                "Лукин",
                "Александрович",
                "ст. инженер",
                Date.valueOf(LocalDate.of(1987, 6, 19))
        );

        staffService.saveStaff(staff1);
        staffService.saveStaff(staff2);
        staffService.saveStaff(staff3);
    }
}

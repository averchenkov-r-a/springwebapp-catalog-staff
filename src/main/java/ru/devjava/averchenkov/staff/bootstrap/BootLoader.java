package ru.devjava.averchenkov.staff.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.service.structure.IStaffService;

import java.sql.Date;

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
        createStaff("Виктор",
                    "Литвинов",
                    "Сергеевич",
                    "программист",
                    "1994-11-25"
        );
        createStaff("Андрей",
                    "Подубный",
                    "Андреевич",
                    "тестировщик",
                    "1985-2-10"
        );
        createStaff("Иван",
                    "Лукин",
                    "Александрович",
                    "инженер",
                    "1987-6-19"
        );
        createStaff("Роман",
                    "Стрыков",
                    "Сергеевич",
                    "hr",
                    "1990-1-25"
        );
        createStaff("Сергей",
                    "Кравченко",
                    "Павлович",
                    "web-разработчик",
                    "1996-6-4"
        );
        createStaff("Иван",
                    "Ананенков",
                    "Алексеевич",
                    "проектировщик",
                    "1987-7-28"
        );
        createStaff("Алексей",
                    "Охоцкий",
                    "Викторович",
                    "hr",
                    "1997-8-17"
        );
        createStaff("Юрий",
                    "Петренко",
                    "Юрьевич",
                    "программист",
                    "1989-3-1"
        );
        createStaff("Ирина",
                    "Турушева",
                    "Викторовна",
                    "программист",
                    "1990-10-16"
        );
        createStaff("Василий",
                    "Сарычев",
                    "Петрович",
                    "программист",
                    "1990-11-23"
        );
    }

    /**
     * Создает нового сотрудника.
     * @param name имя
     * @param surname фамилия
     * @param patronymic отчество
     * @param post должность
     * @param birthday дата рождения
     */
    public void createStaff(String name, String surname, String patronymic,
                            String post, String birthday){
        Staff staff = new Staff(
                name,
                surname,
                patronymic,
                post,
                Date.valueOf(birthday)
        );
        staffService.saveStaff(staff);
    }
}

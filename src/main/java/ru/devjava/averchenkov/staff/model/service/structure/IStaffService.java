package ru.devjava.averchenkov.staff.model.service.structure;

import org.springframework.data.domain.Page;
import ru.devjava.averchenkov.staff.model.entity.Staff;

import java.util.List;

/**
 * Интерфес сервиса работы с данными сотрудника.
 *
 * @author Averchenkov R.A.
 */
public interface IStaffService {


    /**
     * Сохраниение сотрудника
     * @param staff объект сотрудника
     */
    Staff saveStaff(Staff staff);

    /**
     * Получаем сотрудника по id.
     * @param stfId id сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffById(String stfId);

    /**
     * Возвращает всех сотрудников.
     * @param page номер страницы
     * @param count количество сотрудников на странице
     * @return страницу сотрудников
     */
    Page<Staff> getAllStaff(String page, String count);

    /**
     * Возвращает всех сотрудников с соответствующим именем.
     * @param stfName имя сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffByName(String stfName, String page, String count);

    /**
     * Возвращает всех сотрудников с соответствующей фамилией.
     * @param stfSurName фамилия сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffBySurname(String stfSurName, String page, String count);

    /**
     * Возвращает всех сотрудников с соответствующим отчеством.
     * @param stfPatronymic отчество сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffByPatronymic(String stfPatronymic, String page, String count);

    /**
     * Возвращает всех сотрудников с соответствующим постом.
     * @param stfPost пост сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffByPost(String stfPost, String page, String count);

    /**
     * Возвращает всех сотрудников с соответствии с днем рождения.
     * @param stfBirthday пост сотрудника
     * @return страницу сотрудников
     */
    Page<Staff> getStaffByBirthday(String stfBirthday, String page, String count);
}

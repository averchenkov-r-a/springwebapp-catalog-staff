package ru.devjava.averchenkov.staff.service.structure;

import org.springframework.data.domain.Page;
import ru.devjava.averchenkov.staff.model.entity.Staff;

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
     * Поиск по сотрудникам. Важен порядок передаваемых элеменов в params.
     * @param page номер страницы
     * @param count количество сотрудников на страницк
     * @param params набор параметров по всем полям { id, name, surname, patronymic, post, birthday }
     *               класса {@link ru.devjava.averchenkov.staff.model.entity.Staff}
     * @return страницу сотрудников
     */
    Page<Staff> findStaff(String page, String count, String[] params);

}

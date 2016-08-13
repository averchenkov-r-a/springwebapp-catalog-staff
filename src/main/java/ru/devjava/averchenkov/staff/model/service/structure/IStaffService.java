package ru.devjava.averchenkov.staff.model.service.structure;

import ru.devjava.averchenkov.staff.model.entity.Staff;

/**
 * Интерфес сервиса работы с данными сотрудника.
 *
 * @author Averchenkov R.A.
 */
public interface IStaffService {
    /***
     * Получаем сотрудника по id
     * @param stfId id сотрудника
     * @return
     */
    Staff getStaffById(String stfId);

    /***
     * Сохраниение сотрудника
     * @param staff объект сотрудника
     */
    Staff saveStaff(Staff staff);
}

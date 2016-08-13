package ru.devjava.averchenkov.staff.model.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.devjava.averchenkov.staff.model.entity.Staff;

/**
 * CRUD интерфейс доступа к данным сотрудника.
 *
 * @author Averchenkov R.A.
 */
public interface StaffRepositories extends CrudRepository<Staff, Long> {
}

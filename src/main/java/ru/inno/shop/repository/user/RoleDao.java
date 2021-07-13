package ru.inno.shop.repository.user;

import ru.inno.shop.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository <Role, Long> {
}

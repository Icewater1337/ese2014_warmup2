package org.sample.model.dao;

import java.util.List;

import org.sample.model.Team;
import org.sample.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Long> {

}

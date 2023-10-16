package com.Uypiren.jensyardsale.repository.authentication;

import com.Uypiren.jensyardsale.model.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

}

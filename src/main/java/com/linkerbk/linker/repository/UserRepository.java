package com.linkerbk.linker.repository;

import com.linkerbk.linker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.example.system.repositories;

import com.example.system.models.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleRepo extends JpaRepository<Rule, Integer> {

}

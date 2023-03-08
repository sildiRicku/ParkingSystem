package com.example.system.repositories;

import com.example.system.entities.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepo extends JpaRepository<Rule, Integer> {
}

package com.example.jobmicroservice.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRepository extends JpaRepository<Job, Long> {
}

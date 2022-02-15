package com.sgrconsulting.ticketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sgrconsulting.ticketing.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
	
	@Query(value = "SELECT i FROM Issue i WHERE i.solved = 0")
	public List<Issue> findAllOpen();
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.solved = 0")
	public Integer countOpenIssues();
	
	@Query(value = "SELECT i FROM Issue i WHERE i.solved = 1")
	public List<Issue> findAllClosed();
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.solved = 1")
	public Integer countClosedIssues();
	
	@Query(value = "SELECT i FROM Issue i WHERE i.assigneeId = ?1")
	public List<Issue> findAllAssigned(Long assigneeId);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.assigneeId = ?1 AND i.solved = 0", nativeQuery = true)
	public Integer countAssignedIssues(Long assigneeId);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i")
	public Integer countAllIssues();
	
}

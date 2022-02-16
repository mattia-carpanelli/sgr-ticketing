package com.sgrconsulting.ticketing.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sgrconsulting.ticketing.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
	
	@Transactional
	@Query(value = "SELECT i FROM Issue i WHERE i.solved = 0")
	public List<Issue> findAllOpen(Sort sort);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.solved = 0")
	public Integer countOpenIssues();
	
	@Transactional
	@Query(value = "SELECT i FROM Issue i WHERE i.solved = 1")
	public List<Issue> findAllClosed(Sort sort);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.solved = 1")
	public Integer countClosedIssues();
	
	@Transactional
	@Query(value = "SELECT i FROM Issue i WHERE i.assigneeId = :assigneeId")
	public List<Issue> findAllAssigned(Long assigneeId, Sort sort);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i WHERE i.assigneeId = :assigneeId AND i.solved = 0")
	public Integer countAssignedIssues(Long assigneeId);
	
	@Query(value = "SELECT COUNT(i) FROM Issue i")
	public Integer countAllIssues();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Issue i SET i.assigneeId = :assigneeId WHERE i.id = :id")
	public void assignIssue(Long assigneeId, Long id);
	
}

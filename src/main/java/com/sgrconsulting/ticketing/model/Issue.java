package com.sgrconsulting.ticketing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Table(name = "Issues")
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Builder.Default
	private Long assigneeId = null;
	
	@Builder.Default
	private Integer priority = 1;
	
	@Builder.Default
	private boolean solved = false;
	
	// Just for rendering
	@Transient
	private String priorityString;
	
	@Transient
	private String statusString;
	
	@Transient
	private String statusClassString;
	
	@Transient
	private String assigneeString;
	
	@Transient
	private String assigneeLinkString;
	
	
}

<#assign entityName = LOWER_UNDERSCORE.to(UPPER_CAMEL, tableName) />
package com.mhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhw.entity.${entityName};

@Repository
public interface ${entityName}Repository extends JpaRepository<${entityName}, Long> {
	
}

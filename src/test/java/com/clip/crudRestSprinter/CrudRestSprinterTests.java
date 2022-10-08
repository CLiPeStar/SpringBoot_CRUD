package com.clip.crudRestSprinter;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CrudRestSprinterTests {
	@Autowired
	private DataSource dataSource;

	@Test
	public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
		Assertions.assertThat(dataSource.getClass().getName())
				.isEqualTo("org.apache.tomcat.jdbc.pool.DataSource");
	}

}

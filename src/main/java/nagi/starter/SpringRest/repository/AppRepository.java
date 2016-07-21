package nagi.starter.SpringRest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public Map login(String id, String pwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select * from `admin_user_tbl` where user_id = :id";
		Map param = new HashMap<>();
		param.put("id", id);
		param.put("password", pwd);
		try {
			result = jdbcTemplate.queryForMap(sql, param);
		} catch (EmptyResultDataAccessException e) {
			result = null;
		}
		return result;
	}
}
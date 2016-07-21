package nagi.starter.SpringRest.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import nagi.starter.SpringRest.repository.mapper.ApRowMapper;

@Repository
public class ApRepository {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public List selectApList(int $limit) {
		String sql = "SELECT *, max(active) as active, max(rss) as rss, max(lastchange) as lastchange FROM ap_cur_list where active = 1 group by bssid ORDER BY isCategory DESC, active DESC";
		if ($limit != -1) {
			sql += " LIMIT " + $limit;
		}
		List result = jdbcTemplate.query(sql, new ApRowMapper());
		return result;
	}
}
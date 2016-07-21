package nagi.starter.SpringRest.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class ApRowMapper implements RowMapper<Object> {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> item = new HashMap<>();
		item.put("bssid", rs.getLong("bssid"));
		item.put("bssid_str", getMacAddrStr(rs.getLong("bssid")));
		item.put("ssid", rs.getString("ssid"));
		item.put("rss", rs.getInt("rss"));
		item.put("type", rs.getInt("ap_mode"));
		item.put("active", rs.getInt("active"));

		item.put("active_str", getActiveStr(rs.getInt("active")));
		item.put("type_str", getTypeStr(rs.getInt("ap_mode")));
		return item;
	}

	public String getActiveStr(int data) {
		return (data == 1) ? "ON" : "OFF";
	}

	public String getTypeStr(int data) {
		return (data == 0) ? "관리" : (data == 1) ? "미관리" : (data == 2) ? "외부" : "Rogue AP";
	}

	public String getMacAddrStr(Long mac_addr_number) {
		String mac_addr = "";
		String temp = mac_addr_number.toHexString(mac_addr_number);
		temp = temp.substring(1);

		for (int len = 0; len < 12 && temp.length() < 12; len++) {
			temp = "0" + temp;
		}

		mac_addr = temp.substring(0, 2) + ":" + temp.substring(2, 4) + ":" + temp.substring(4, 6) + ":" + temp.substring(6, 8) + ":" + temp.substring(8, 10) + ":"
				+ temp.substring(10, 12);

		if (mac_addr.toUpperCase().equals("FF:FF:FF:FF:FF:FF") == true || mac_addr.toUpperCase().equals("0F:FF:FF:FF:FF:FF") == true
				|| mac_addr.toUpperCase().equals("00:00:00:00:00:00") == true) {
			return "-";
		}

		return mac_addr.toUpperCase();
	}
}

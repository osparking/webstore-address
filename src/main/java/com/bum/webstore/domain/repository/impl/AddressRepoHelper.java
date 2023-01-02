package com.bum.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bum.webstore.domain.RoadAddress;

public class AddressRepoHelper {

	public static AddrSearchKey getAddrSearchKey(Scanner scanner)
			throws StopSearchingException {
		var asKey = new AddrSearchKey();

		System.out.println("검색 키 입력 형태 => ");
		System.out.println("\t-도로명(예, 덕영대로895)");
		System.out.println("\t-도로명 건물번호(예, 덕영대로 89)");
		System.out.println("\t-건물명(예, 세진)");
		System.out.print("(멈추려면 그냥 엔터 치세요 :-) : ");
		try {
			String inputText = null;

			if (scanner.hasNextLine()) {
				inputText = scanner.nextLine().trim();
			}
			String[] searchKeys = inputText.split("\\s+");

			assert (searchKeys.length == 1 || searchKeys.length == 2);
			if (searchKeys.length == 1) {
				// 도로명 혹은 건물명
				if (searchKeys[0].length() == 0)
					throw new StopSearchingException();
				asKey.set도로_건물(searchKeys[0]);
			} else {
				// 도로명 그리고 건물본번
				asKey.set도로_건물(searchKeys[0]);
				asKey.set건물본번(searchKeys[1]);
			}
		} catch (NoSuchElementException | IllegalStateException
				| NumberFormatException e) {
			System.out.println();
			throw new StopSearchingException();
		}

		return asKey;
	}

	private static String getAddressSelectQuery() {
		var sb = new StringBuilder();

		sb.append("SELECT A.관리번호, A.기초구역번호 AS 새우편번호, ");
		sb.append("concat( " + "B.시도명, ' ', ");
		sb.append("if (B.시군구 = '', '', concat(B.시군구,' ')), ");
		sb.append("case when B.읍면동구분 = 0 then concat(B.읍면동,' ') ");
		sb.append("else ''  ");
		sb.append("end,  ");
		sb.append("concat(B.도로명,' '), ");
		sb.append("case when A.지하여부 = 0 then ''  ");
		sb.append("	when A.지하여부 = 1 then '지하 '  ");
		sb.append("	when A.지하여부 = 2 then '공중 ' end, " + "A.건물본번, ");
		sb.append("if (A.건물부번 = 0, '', concat('-',A.건물부번)), ");
		sb.append("CASE WHEN (B.읍면동구분 = 0 AND D.공동주택여부 = 0) THEN '' ");
		sb.append("	WHEN (B.읍면동구분 = 0 AND D.공동주택여부 = 1) then ");
		sb.append("		case D.시군구건물명  ");
		sb.append("			when (D.시군구건물명 = '') then ''  ");
		sb.append("			else concat('(',D.시군구건물명,')') end  ");
		sb.append("	WHEN (B.읍면동구분 = 1 AND D.공동주택여부 = 0)  ");
		sb.append("		THEN concat('(',B.읍면동,')') ");
		sb.append("	WHEN (B.읍면동구분 = 1 AND D.공동주택여부 = 1)  ");
		sb.append("		THEN concat('(', B.읍면동 ");
		sb.append("			, case when (D.시군구건물명 = '') then ''  ");
		sb.append("				   else concat(',', D.시군구건물명) end ");
		sb.append("			,')')  ");
		sb.append("   	END  ");
		sb.append("   	) AS 도로명주소 ");
		sb.append("  FROM 도로명주소 A, 도로명코드 B, 부가정보 D  ");
		sb.append(" WHERE A.도로명코드    = B.도로명코드 ");
		sb.append("   AND A.읍면동일련번호 = B.읍면동일련번호 ");
		sb.append("   AND A.관리번호     = D.관리번호  ");
		sb.append("   AND %s");

		return sb.toString();
	}

	private static String getSearchCondString(AddrSearchKey key) {
		if (key.get건물본번() == null) {
			// 건물명 혹은 (건물 본번 없는)도로명
			return "(B.도로명 LIKE '%" + key.get도로_건물() + "%' or " + "D.시군구건물명 LIKE '%"
					+ key.get도로_건물() + "%')";
		} else {
			// 도로명 및 건물 본번으로 검색
			return "B.도로명 LIKE '" + key.get도로_건물() + "' " + "AND A.건물본번 LIKE '%"
					+ key.get건물본번() + "%'";
		}
	}

	public static List<RoadAddress> getAddressList(AddrSearchKey searchKey,
			NamedParameterJdbcTemplate jdbcTemplate) {
		var addresses = new ArrayList<RoadAddress>();
		String sKey = getSearchCondString(searchKey);
		String sqlList = getAddressSelectQuery();

		sqlList = String.format(sqlList, sKey);

		// @formatter:off
		jdbcTemplate.query(sqlList, (rs, rowNum) -> {
			var roadAddress = new RoadAddress(
					rs.getString(1), rs.getString(2), rs.getString(3), null);
			addresses.add(roadAddress);
			return roadAddress;
		});
		// @formatter:on
		return addresses;
	}
}

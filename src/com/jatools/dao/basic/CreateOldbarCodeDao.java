package com.jatools.dao.basic;

public interface CreateOldbarCodeDao {

	Long getStartIndex(String codeType, String year, String month, String userid);
	
	void updateStartIndex(String codeType, String year, String month, String userid, Long serial);

}

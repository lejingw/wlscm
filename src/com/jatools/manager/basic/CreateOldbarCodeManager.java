package com.jatools.manager.basic;

public interface CreateOldbarCodeManager {

	/**
	 * 生成饰品条码
	 * @return
	 */
	Long generateOldbarCode(String codeType, String year, String month, Long numCount, String userid);

}

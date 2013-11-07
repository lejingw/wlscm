package com.jatools.ws.remote;

import com.jatools.ws.review.vo.ReviewInfo;

public interface EnterReviewWebService {
	/**
	 * 进入审批
	 * @param reviewBean
	 * @return
	 */
	public String doEnterReview(ReviewInfo reviewBean);
}

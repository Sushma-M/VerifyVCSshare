/*Copyright (c) 2015-2016 vcs2.com All Rights Reserved.
 This software is the confidential and proprietary information of vcs2.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcs2.com*/

package com.verifyvcsshare.dbtest_shared.service;

import java.util.Map;
import java.util.List;


import com.wavemaker.runtime.data.model.CustomProcedure;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

public interface Dbtest_sharedProcedureExecutorService {

	
	List<Object> executeWMCustomProcedure(CustomProcedure procedure) ;


}


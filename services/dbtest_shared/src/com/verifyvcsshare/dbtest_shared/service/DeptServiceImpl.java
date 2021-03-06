/*Copyright (c) 2015-2016 vcs2.com All Rights Reserved.
 This software is the confidential and proprietary information of vcs2.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcs2.com*/
package com.verifyvcsshare.dbtest_shared.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.verifyvcsshare.dbtest_shared.Dept;


/**
 * ServiceImpl object for domain model class Dept.
 *
 * @see Dept
 */
@Service("dbtest_shared.DeptService")
public class DeptServiceImpl implements DeptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);


    @Autowired
    @Qualifier("dbtest_shared.DeptDao")
    private WMGenericDao<Dept, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Dept, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "dbtest_sharedTransactionManager")
    @Override
	public Dept create(Dept dept) {
        LOGGER.debug("Creating a new Dept with information: {}", dept);
        Dept deptCreated = this.wmGenericDao.create(dept);
        return deptCreated;
    }

	@Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
	@Override
	public Dept getById(Integer deptId) throws EntityNotFoundException {
        LOGGER.debug("Finding Dept by id: {}", deptId);
        Dept dept = this.wmGenericDao.findById(deptId);
        if (dept == null){
            LOGGER.debug("No Dept found with id: {}", deptId);
            throw new EntityNotFoundException(String.valueOf(deptId));
        }
        return dept;
    }

    @Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
	@Override
	public Dept findById(Integer deptId) {
        LOGGER.debug("Finding Dept by id: {}", deptId);
        return this.wmGenericDao.findById(deptId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "dbtest_sharedTransactionManager")
	@Override
	public Dept update(Dept dept) throws EntityNotFoundException {
        LOGGER.debug("Updating Dept with information: {}", dept);
        this.wmGenericDao.update(dept);

        Integer deptId = dept.getId();

        return this.wmGenericDao.findById(deptId);
    }

    @Transactional(value = "dbtest_sharedTransactionManager")
	@Override
	public Dept delete(Integer deptId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Dept with id: {}", deptId);
        Dept deleted = this.wmGenericDao.findById(deptId);
        if (deleted == null) {
            LOGGER.debug("No Dept found with id: {}", deptId);
            throw new EntityNotFoundException(String.valueOf(deptId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
	@Override
	public Page<Dept> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Depts");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
    @Override
    public Page<Dept> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Depts");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service dbtest_shared for table Dept to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "dbtest_sharedTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}


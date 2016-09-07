/*Copyright (c) 2015-2016 vcs2.com All Rights Reserved.
 This software is the confidential and proprietary information of vcs2.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcs2.com*/
package com.verifyvcsshare.dbtest_shared.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.verifyvcsshare.dbtest_shared.Dept;
import com.verifyvcsshare.dbtest_shared.service.DeptService;

/**
 * Controller object for domain model class Dept.
 * @see Dept
 */
@RestController("dbtest_shared.DeptController")
@Api(value = "DeptController", description = "Exposes APIs to work with Dept resource.")
@RequestMapping("/dbtest_shared/Dept")
public class DeptController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    @Qualifier("dbtest_shared.DeptService")
    private DeptService deptService;

    @ApiOperation(value = "Creates a new Dept instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Dept createDept(@RequestBody Dept dept) {
        LOGGER.debug("Create Dept with information: {}", dept);
        dept = deptService.create(dept);
        LOGGER.debug("Created Dept with information: {}", dept);
        return dept;
    }

    @ApiOperation(value = "Returns the Dept instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Dept getDept(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Dept with id: {}", id);
        Dept foundDept = deptService.getById(id);
        LOGGER.debug("Dept details with id: {}", foundDept);
        return foundDept;
    }

    @ApiOperation(value = "Updates the Dept instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Dept editDept(@PathVariable("id") Integer id, @RequestBody Dept dept) throws EntityNotFoundException {
        LOGGER.debug("Editing Dept with id: {}", dept.getId());
        dept.setId(id);
        dept = deptService.update(dept);
        LOGGER.debug("Dept details with id: {}", dept);
        return dept;
    }

    @ApiOperation(value = "Deletes the Dept instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteDept(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Dept with id: {}", id);
        Dept deletedDept = deptService.delete(id);
        return deletedDept != null;
    }

    /**
     * @deprecated Use {@link #findDepts(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Dept instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Dept> findDepts(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Depts list");
        return deptService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Dept instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Dept> findDepts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Depts list");
        return deptService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportDepts(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return deptService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Dept instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countDepts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Depts");
        return deptService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service DeptService instance
	 */
    protected void setDeptService(DeptService service) {
        this.deptService = service;
    }
}
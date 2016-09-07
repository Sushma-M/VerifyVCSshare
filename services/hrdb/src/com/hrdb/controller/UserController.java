/*Copyright (c) 2015-2016 vcs2.com All Rights Reserved.
 This software is the confidential and proprietary information of vcs2.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcs2.com*/
package com.hrdb.controller;

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
import com.hrdb.User;
import com.hrdb.service.UserService;

/**
 * Controller object for domain model class User.
 * @see User
 */
@RestController("hrdb.UserController")
@Api(value = "UserController", description = "Exposes APIs to work with User resource.")
@RequestMapping("/hrdb/User")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("hrdb.UserService")
    private UserService userService;

    @ApiOperation(value = "Creates a new User instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public User createUser(@RequestBody User user) {
        LOGGER.debug("Create User with information: {}", user);
        user = userService.create(user);
        LOGGER.debug("Created User with information: {}", user);
        return user;
    }

    @ApiOperation(value = "Returns the User instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public User getUser(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting User with id: {}", id);
        User foundUser = userService.getById(id);
        LOGGER.debug("User details with id: {}", foundUser);
        return foundUser;
    }

    @ApiOperation(value = "Updates the User instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public User editUser(@PathVariable("id") Integer id, @RequestBody User user) throws EntityNotFoundException {
        LOGGER.debug("Editing User with id: {}", user.getUserId());
        user.setUserId(id);
        user = userService.update(user);
        LOGGER.debug("User details with id: {}", user);
        return user;
    }

    @ApiOperation(value = "Deletes the User instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUser(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting User with id: {}", id);
        User deletedUser = userService.delete(id);
        return deletedUser != null;
    }

    /**
     * @deprecated Use {@link #findUsers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of User instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<User> findUsers(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Users list");
        return userService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of User instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<User> findUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Users list");
        return userService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUsers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return userService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of User instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Users");
        return userService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserService instance
	 */
    protected void setUserService(UserService service) {
        this.userService = service;
    }
}
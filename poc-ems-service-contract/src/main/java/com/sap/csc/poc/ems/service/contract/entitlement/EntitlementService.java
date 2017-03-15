package com.sap.csc.poc.ems.service.contract.entitlement;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sap.csc.poc.ems.model.dto.entitlement.EntitlementInfo;
import com.sap.csc.poc.ems.model.dto.entitlement.SoftwareLicenseEntitlementDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@FeignClient(name = "admin-service")
@Api(value = EntitlementService.SERVICE_NAME)
public interface EntitlementService {

	static final String SERVICE_NAME = "Entitlement Service API";

	/**
	 * Search all entitlement by keyword
	 * 
	 * @param keyword
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	@ApiOperation(
		// Operation
		value = "Search all entitlement by keyword",
		// Operation Note
		notes = "",
		// Operation Response Type
		response = EntitlementInfo.class,
		// Operation Response Container (Valid values are "List", "Set" or "Map")
		responseContainer = "List",
		// Operation Tags
		tags = EntitlementService.SERVICE_NAME)
	@ApiResponses(value = {
		// Response - 200
		@ApiResponse(code = 200, response = EntitlementInfo.class,
			// Message
			message = "Page of matching entitlements"),
		// Response - 204
		@ApiResponse(code = 204, response = EntitlementInfo.class,
			// Message
			message = "No entitlement match the keyword or out of page range") })
	@RequestMapping(value = "entitlements/search", method = RequestMethod.GET)
	ResponseEntity<Page<EntitlementInfo>> search(
		// Keyword
		@RequestParam(value = "keyword") @ApiParam(value = "Keyword for fuzzy search", required = false) String keyword,
		// Page
		@RequestParam(value = "page") @ApiParam(value = "Page index of entitlement list", required = false) Integer page,
		// Size
		@RequestParam(value = "size") @ApiParam(value = "Page size of entitlement list", required = false) Integer size,
		// Sort
		@RequestParam(value = "sort") @ApiParam(value = "Sort fields and corresponding directions, split by comma", required = false) String sort);

	/**
	 * Find one general entitlement by id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(
		// Operation
		value = "Find one general entitlement by id",
		// Operation Note
		notes = "",
		// Operation Response Type
		response = EntitlementInfo.class,
		// Operation Tags
		tags = EntitlementService.SERVICE_NAME)
	@ApiResponses(value = {
		// Response - 200
		@ApiResponse(code = 200, response = EntitlementInfo.class,
			// Message
			message = "Matching entitlement with specific id"),
		// Response - 204
		@ApiResponse(code = 404,
			// Message
			message = "No entitlement match specific id") })
	@RequestMapping(value = "entitlement/{id}", method = RequestMethod.GET)
	ResponseEntity<EntitlementInfo> findOne(
		// Id
		@ApiParam(value = "Specific entitlement id", required = true) @PathVariable("id") Long id);

	/**
	 * Find one software license entitlement by id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(
		// Operation
		value = "Find one software license entitlement by id",
		// Operation Note
		notes = "",
		// Operation Response Type
		response = SoftwareLicenseEntitlementDetail.class,
		// Operation Tags
		tags = EntitlementService.SERVICE_NAME)
	@ApiResponses(value = {
		// Response - 200
		@ApiResponse(code = 200, response = SoftwareLicenseEntitlementDetail.class,
			// Message
			message = "Matching software license entitlement with specific id"),
		// Response - 204
		@ApiResponse(code = 404,
			// Message
			message = "No software license entitlement match specific id") })
	@RequestMapping(value = "entitlement/softwareLicense/{id}", method = RequestMethod.GET)
	ResponseEntity<SoftwareLicenseEntitlementDetail> findOneSoftwareLicense(
		// Id
		@ApiParam(value = "Specific software license entitlement id", required = true) @PathVariable("id") Long id);
}

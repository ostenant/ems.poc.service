package com.sap.csc.poc.ems.service.brm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class BrmVocabularyTester {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private final static String BASE_URL = "basic";

	private final static String EXPECTED_PACKAGE_ID = "com.sap.csc.ems";
	private final static String EXPECTED_OBJECT_NAME = "EmployeeData";
	private final static String EXPECTED_OBEJCT_SUFFIX = "vocabulary";
	private final static String NAME = StringUtils.join(EXPECTED_PACKAGE_ID, "::", EXPECTED_OBJECT_NAME, ".",
			EXPECTED_OBEJCT_SUFFIX);

	private final static String VOCABULARY = "{\"meta_data\":{\"package_id\":\"com.sap.csc.ems\",\"object_name\":\"EmployeeData\",\"object_suffix\":\"vocabulary\"},\"content\":{\"dataObjects\":[{\"name\":\"EMPLOYEE\",\"description\":\"EMPLOYEE\",\"usage\":\"DBCTX\",\"attributes\":[{\"name\":\"designation\",\"description\":\"designation\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"position\",\"description\":\"position\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"MobileModel\",\"description\":\"MobileModel\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"LaptopEquipmentModel\",\"description\":\"LaptopEquipmentModel\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"AnyAdditionalDevices\",\"description\":\"AnyAdditionalDevices\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"DOJ\",\"description\":\"Date of Joining\",\"dataType\":\"DATE\",\"sourceType\":\"Data\"},{\"name\":\"EMP_ID\",\"description\":\"Employee ID\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"}],\"associations\":[{\"name\":\"Employer\",\"target\":\"COMPANY\",\"cardinality\":\"OneToOne\",\"attributeMappings\":[{\"source\":\"EMP_ID\",\"target\":\"EMP_ID\"}]}]},{\"name\":\"COMPANY\",\"usage\":\"DBCTX\",\"description\":\"\",\"attributes\":[{\"name\":\"ID\",\"description\":\"Employer ID\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"EMP_ID\",\"description\":\"Employee ID\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"LOCATION\",\"description\":\"Location\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"}],\"associations\":[]},{\"name\":\"Output\",\"description\":\"Output\",\"usage\":\"RESULT\",\"attributes\":[{\"name\":\"MobileModel\",\"description\":\"\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"LaptopEquipmentModel\",\"description\":\"\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"},{\"name\":\"AnyAdditionalDevices\",\"description\":\"\",\"dataType\":\"VARCHAR\",\"sourceType\":\"Data\"}]}]}}";
	private static final String ACTIVATION_IS_SUCCESSFUL = "Activation is successful";
	private static final String SUCCESS = "success";

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testRetrieve() throws Exception {
		this.mockMvc
				.perform(get(BASE_URL + "/rule/vocabulary")
						// Accept Content Type
						.accept(MediaType.APPLICATION_JSON)
						// Content Type
						.contentType(MediaType.APPLICATION_JSON)
						// Param
						.param("name", NAME))
				// Expected status 200 OK
				.andExpect(status().isOk())
				// Expected result
				.andExpect(jsonPath("meta_data").exists())
				// Expected result
				.andExpect(jsonPath("content.dataObjects").exists())
				// Expected result
				.andExpect(jsonPath("meta_data.package_id").value(EXPECTED_PACKAGE_ID))
				// Expected result
				.andExpect(jsonPath("meta_data.object_name").value(EXPECTED_OBJECT_NAME))
				// Expected result
				.andExpect(jsonPath("meta_data.object_suffix").value(EXPECTED_OBEJCT_SUFFIX))
				// Do some prints
				.andDo(print());
	}

	@Test
	public void testCreate() throws Exception {
		this.mockMvc
				.perform(post(BASE_URL + "/rule/vocabulary")
						// Accept Content Type
						.accept(MediaType.APPLICATION_JSON)
						// Content Type
						.contentType(MediaType.APPLICATION_JSON)
						// Body
						.content(VOCABULARY))
				// Expected status 201 Created
				.andExpect(status().isCreated());
	}

	@Test
	public void testUpdate() throws Exception {
		this.mockMvc
				.perform(patch(BASE_URL + "/rule/vocabulary")
						// Accept Content Type
						.accept(MediaType.APPLICATION_JSON)
						// Content Type
						.contentType(MediaType.APPLICATION_JSON)
						// Body
						.content(VOCABULARY))
				// Expected status 204 No Content
				.andExpect(status().isNoContent());
	}

	@Test
	public void testDelete() throws Exception {
		this.mockMvc
				.perform(delete(BASE_URL + "/rule/vocabulary")
						// Accept Content Type
						.accept(MediaType.APPLICATION_JSON)
						// Content Type
						.contentType(MediaType.APPLICATION_JSON)
						// Body
						.content(VOCABULARY))
				// Expected status 200 OK
				.andExpect(status().isOk());
	}

	@Test
	public void testActivate() throws Exception {
		this.mockMvc
				.perform(post(BASE_URL + "/rule/vocabulary/activate")
						// Accept Content Type
						.accept(MediaType.APPLICATION_JSON)
						// Content Type
						.contentType(MediaType.APPLICATION_JSON)
						// Param
						.param("name", NAME))
				// Expected status 200 OK
				.andExpect(status().isOk())
				// Expected result
				.andExpect(jsonPath("status").exists())
				// Expected result
				.andExpect(jsonPath("status").value(SUCCESS))
				// Expected result
				.andExpect(jsonPath("messages.message").exists())
				// Expected result
				.andExpect(jsonPath("messages.message").value(ACTIVATION_IS_SUCCESSFUL))
				// Do some prints
				.andDo(print());
	}

	@After
	public void teardown() throws Exception {
		this.mockMvc = null;
		this.wac = null;
	}

}

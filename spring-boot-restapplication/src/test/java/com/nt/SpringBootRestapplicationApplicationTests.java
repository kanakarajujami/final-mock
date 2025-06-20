package com.nt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nt.controller.DepartmentController;
import com.nt.controller.EmployeeController;
import com.nt.dto.DepartmentDto;
import com.nt.entity.Department;
import com.nt.service.IDepartmentService;
import com.nt.service.IEmployeeService;
import com.nt.util.DepartmentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value=DepartmentController.class)
class SpringBootRestapplicationApplicationTests {


	@MockitoBean
	//@Mock
	private IDepartmentService service;
	@InjectMocks
	private EmployeeController controller;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreateDepartment() throws Exception {
		DepartmentDto departmentDto=new DepartmentDto("itdepartment","chennai");

		DepartmentDto departmentDtoOut=new DepartmentDto(1l,"itdepartment","chennai");

		//provide dummy functionality service class createDepartment() method
		Mockito.when(service.createDepartment(departmentDto)).thenReturn(departmentDtoOut);

		//convert dto json content
		ObjectMapper mapper=new ObjectMapper();
		String jsonContent=mapper.writeValueAsString(departmentDto);

		//prepare request builder
		MockHttpServletRequestBuilder requestBuilder =MockMvcRequestBuilders.post("/department/api/save").contentType("application/json")
				                                                 .content(jsonContent);
		//send request by giving request builder
		MvcResult mvcResult =mockMvc.perform(requestBuilder).andReturn();

		//get response code from mvcResult
		int status=mvcResult.getResponse().getStatus();
		Assertions.assertEquals(201,status);
	}


	@Test
	public  void  testGetAllDepartmentsEndPoint() throws Exception {
	/*	DepartmentDto dept1=new DepartmentDto(1l,"itdepartment","chennai");
		DepartmentDto dept2=new DepartmentDto(2l,"electronicdept","bengaluru");

		List<DepartmentDto> departmentDtoList=List.of(dept1,dept2);
		Page<DepartmentDto> page=new PageImpl<DepartmentDto>(departmentDtoList);

		Pageable pageable= PageRequest.of(0,2);

		DepartmentResponse response=new DepartmentResponse();
		response.setTotalElements(2);
		response.setPageNo(0);
		response.setPageSize(2);
		response.setLast(true);
		response.setDepartmentDtoList(departmentDtoList);

		//provide dummy functionality to service class
		Mockito.when(service.getAllDepartments(pageable)).thenReturn(response);
		//prepare request builder
		MockHttpServletRequestBuilder requestBuilder=MockMvcRequestBuilders.get("/department/api/findAll");


		//send request
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
	*//*	int status = mvcResult.getResponse().getStatus();
		Assertions.assertEquals(200,status);*//*
		String contentAsString = mvcResult.getResponse().getContentAsString();

		;
		System.out.println("content "+contentAsString);
		//convert stringjson content to object
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		DepartmentResponse departmentResponse = objectMapper.readValue(contentAsString, DepartmentResponse.class);

		Assertions.assertEquals(0,departmentResponse.getPageNo());
		Assertions.assertEquals(2,departmentResponse.getPageSize());
		Assertions.assertEquals(1,departmentResponse.getTotalPages());
		Assertions.assertEquals(2,departmentResponse.getTotalElements());
		//Assertions.assertEquals(departmentDtoList,departmentResponse.getDepartmentDtoList());
*/





				DepartmentDto dept1 = new DepartmentDto(1L, "IT", "Chennai");
				DepartmentDto dept2 = new DepartmentDto(2L, "Electronics", "Bengaluru");

				List<DepartmentDto> dtoList = List.of(dept1, dept2);

				DepartmentResponse mockResponse = new DepartmentResponse();
				mockResponse.setDepartmentDtoList(dtoList);
				mockResponse.setPageNo(0);
				mockResponse.setPageSize(2);
				mockResponse.setTotalPages(1);
				mockResponse.setTotalElements(2);
				mockResponse.setLast(true);

				// Prepare pageable for mock call
				Pageable pageable = PageRequest.of(0, 2, Sort.by("id").descending());

				Mockito.when(service.getAllDepartments(pageable)).thenReturn(mockResponse);

				// Perform GET request with params
				MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/department/api/findAll")
								.param("pageNo", "0")
								.param("pageSize", "2")
								.param("sortBy", "id")
								.param("sortDir", "des")
								.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andReturn();

				// Get and convert JSON to object
				String json = result.getResponse().getContentAsString();
				ObjectMapper mapper = new ObjectMapper();
				DepartmentResponse response = mapper.readValue(json, DepartmentResponse.class);

				// Validate response
				Assertions.assertEquals(2, response.getDepartmentDtoList().size());
				Assertions.assertEquals(0, response.getPageNo());
				Assertions.assertEquals(2, response.getPageSize());
				Assertions.assertEquals(1, response.getTotalPages());
				Assertions.assertEquals(2, response.getTotalElements());
			}
		}






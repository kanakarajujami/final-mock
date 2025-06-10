package com.nt.util;

import com.nt.dto.DepartmentDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DepartmentResponse {

    public List<DepartmentDto> departmentDtoList;
    public Integer pageNo;
    public Integer pageSize;
    public boolean isLast;
    public Integer totalPages;
    public Integer totalElements;

}

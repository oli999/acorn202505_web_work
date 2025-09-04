package com.example.spring09.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring09.dto.DeptDto;
import com.example.spring09.dto.EmpDeptDto;
import com.example.spring09.dto.EmpDto;
import com.example.spring09.entity.Emp;
import com.example.spring09.repository.DeptRepository;
import com.example.spring09.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployServiceImpl implements EmployService{
	
	private final EmpRepository empRepo;
	private final DeptRepository deptRepo;
	
	@Transactional(readOnly = true)
	@Override
	public List<EmpDto> getEmpList() {
		
		return empRepo.findAll().stream().map(EmpDto::toDto).toList();
	}
	@Transactional(readOnly = true)
	@Override
	public List<DeptDto> getDeptList() {
		
		return deptRepo.findAll().stream().map(DeptDto::toDto).toList();
	}
	@Transactional(readOnly = true)
	@Override
	public EmpDeptDto getEmpDetail(int empno) {
		//사원 번호를 이용해서 Emp entity 를 얻어내고 
		Emp e = empRepo.findById(empno).get();
		// Emp entity 를 EmpDeptDto 로 변경해서 리턴한다.
		return EmpDeptDto.toDto(e);
	}
	@Transactional(readOnly = true)
	@Override
	public DeptDto getDeptDetail(int deptno) {
		
		return DeptDto.toDto(deptRepo.findById(deptno).get());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<EmpDto> getEmpListByDeptno(int deptno) {
		List<EmpDto> empList1=empRepo.findEmps(deptno).stream().map(EmpDto::toDto).toList();
		List<EmpDto> empList2=empRepo.findEmps2(deptno).stream().map(EmpDto::toDto).toList();
		List<EmpDto> empList3=empRepo.findByDept_DeptnoOrderByEnameAsc(deptno)
				.stream().map(EmpDto::toDto).toList();
		
		return empList3;
	}

}









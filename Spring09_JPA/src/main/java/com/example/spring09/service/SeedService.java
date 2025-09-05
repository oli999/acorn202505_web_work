package com.example.spring09.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring09.entity.Dept;
import com.example.spring09.entity.Emp;
import com.example.spring09.repository.DeptRepository;
import com.example.spring09.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SeedService {

    private final DeptRepository deptRepository;
    private final EmpRepository empRepository;

    public void seed() {
        // 이미 데이터가 있으면 재삽입 방지
        if (empRepository.count() > 0 || deptRepository.count() > 0) {
            return;
        }

        // 1) 부서 저장
        Dept d10 = new Dept(10, "ACCOUNTING", "NEW YORK");
        Dept d20 = new Dept(20, "RESEARCH",   "DALLAS");
        Dept d30 = new Dept(30, "SALES",      "CHICAGO");
        Dept d40 = new Dept(40, "OPERATIONS", "BOSTON");

        deptRepository.saveAll(List.of(d10, d20, d30, d40));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // 2) 사원 저장 (Dept 객체를 직접 주입)
        empRepository.saveAll(List.of(
            new Emp(7369, "SMITH",  "CLERK",    7902, LocalDate.parse("17-12-1980", fmt), 800.0,  null, d20),
            new Emp(7499, "ALLEN",  "SALESMAN", 7698, LocalDate.parse("20-02-1981", fmt), 1600.0, 300.0, d30),
            new Emp(7521, "WARD",   "SALESMAN", 7698, LocalDate.parse("22-02-1981", fmt), 1250.0, 500.0, d30),
            new Emp(7566, "JONES",  "MANAGER",  7839, LocalDate.parse("02-04-1981", fmt), 2975.0, null,  d20),
            new Emp(7654, "MARTIN", "SALESMAN", 7698, LocalDate.parse("28-09-1981", fmt), 1250.0, 1400.0,d30),
            new Emp(7698, "BLAKE",  "MANAGER",  7839, LocalDate.parse("01-05-1981", fmt), 2850.0, null,  d30),
            new Emp(7782, "CLARK",  "MANAGER",  7839, LocalDate.parse("09-06-1981", fmt), 2450.0, null,  d10),
            new Emp(7788, "SCOTT",  "ANALYST",  7566, LocalDate.parse("13-07-1987", fmt), 3000.0, null,  d20),
            new Emp(7839, "KING",   "PRESIDENT",null, LocalDate.parse("17-11-1981", fmt), 5000.0, null,  d10),
            new Emp(7844, "TURNER","SALESMAN",  7698, LocalDate.parse("08-09-1981", fmt), 1500.0, 0.0,   d30),
            new Emp(7876, "ADAMS",  "CLERK",    7788, LocalDate.parse("13-07-1987", fmt), 1100.0, null,  d20),
            new Emp(7900, "JAMES",  "CLERK",    7698, LocalDate.parse("03-12-1981", fmt), 950.0,  null,  d30),
            new Emp(7902, "FORD",   "ANALYST",  7566, LocalDate.parse("03-12-1981", fmt), 3000.0, null,  d20),
            new Emp(7934, "MILLER", "CLERK",    7782, LocalDate.parse("23-01-1982", fmt), 1300.0, null,  d10)
        ));
    }
}
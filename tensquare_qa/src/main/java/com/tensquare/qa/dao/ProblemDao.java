package com.tensquare.qa.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    @Query(value = "select * from tb_problem pro,tb_pl pl where pro.id = pl.problemid and pl.labelid=? order by ",nativeQuery = true)
    public List<Problem> newlist(String labelid, Pageable pageable);

    public List<Problem> hotlist();

    public List<Problem> waitlist();

}

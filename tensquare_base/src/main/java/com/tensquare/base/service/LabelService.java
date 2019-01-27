package com.tensquare.base.service;

import cn.hutool.core.util.StrUtil;
import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }


    public Label findById(String id) {
        return labelDao.findById(id).get();
    }


    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }


    public void update(Label label) {
        labelDao.save(label);
    }


    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSerch(Label label) {
        return labelDao.findAll(new Specification<Label>() {

            /**
             *
             * @param root  根对象,把条件封装到哪个对象,where 类名=label.getid
             * @param query 查询关键字
             * @param cb 用来封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StrUtil.isNotBlank(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (StrUtil.isNotBlank(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));//对都条件and连接
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //pageable基于0
        Pageable pageable = PageRequest.of(page-1, size);
        //返回一个封装对象
        return labelDao.findAll(new Specification<Label>() {

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StrUtil.isNotBlank(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (StrUtil.isNotBlank(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                return cb.and(list.toArray(new Predicate[list.size()]));//对都条件and连接
            }
        }, pageable);
    }
}

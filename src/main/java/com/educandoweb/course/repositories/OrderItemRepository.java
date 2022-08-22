package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Orderitem;
import com.educandoweb.course.entities.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<Orderitem, OrderItemPk> {
	

}

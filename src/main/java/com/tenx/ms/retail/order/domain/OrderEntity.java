package com.tenx.ms.retail.order.domain;

import com.tenx.ms.retail.common.util.AllowConverterAccess;
import com.tenx.ms.retail.common.util.DenyConverterAccess;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class OrderEntity {
    @Id
    @GeneratedValue
    @AllowConverterAccess
    private Long orderId;
    private Long storeId;
    private Date orderDate;
    private int  status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailsEntity> products;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    public Long getOrderId() { return orderId; }

    public Long getStoreId() { return this.storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public OrderStatus getStatus() { return OrderStatus.fromValue(this.status); }
    public void setStatus(OrderStatus status) { this.status = status.getValue(); }

    @DenyConverterAccess
    public List<OrderDetailsEntity> getProducts() { return products; }
    public void setProducts(List<OrderDetailsEntity> products) { this.products = products; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}

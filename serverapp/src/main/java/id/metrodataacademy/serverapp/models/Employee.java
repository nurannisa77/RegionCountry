package id.metrodataacademy.serverapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "employee")
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column (name = "name", length = 50, nullable = false)
    private String name;
    
    @Column (name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "phone",length =13)
    private String phone;

    @OneToOne (mappedBy = "employee", cascade = CascadeType.ALL)
    
    @PrimaryKeyJoinColumn
    private User user;

    
}

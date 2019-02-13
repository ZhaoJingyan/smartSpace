package com.hxb.smartspace.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Zhao Jinyan
 * @date 2019/2/1 22:14
 */
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ss_file_resource")
@EntityListeners(AuditingEntityListener.class)
public class FileResource {

    public static FileResource error(String message){
        return new FileResource(-1, message);
    }

    public FileResource() {
    }

    public FileResource(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="name", unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    /**
     *
     */
    @Column(name = "real_name", length = 255)
    private String realName;

    @Column(name = "save_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Column(name = "delete_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteTime;

    @Column(name = "created_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateTime;

    @Transient
    private int code = 0;

    @Transient
    private String message = null;

}

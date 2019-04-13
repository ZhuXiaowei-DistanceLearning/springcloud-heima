package cn.zxw.springcloud.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

//    @Transient
//    private String note;
}

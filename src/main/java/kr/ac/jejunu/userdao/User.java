package kr.ac.jejunu.userdao;

import lombok.*;

//@Getter
//@Setter
@Data //Getter Setter 모두 처리해줌.
@Builder //Object를 Builder패턴으로 만들어 준다.
@NoArgsConstructor
@AllArgsConstructor //모든
public class User {
    private Integer id;
    private String name;
    private String password;
    //private User user;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
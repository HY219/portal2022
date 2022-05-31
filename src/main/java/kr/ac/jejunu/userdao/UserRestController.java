package kr.ac.jejunu.userdao;

import jdk.jfr.Frequency;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api")
@RequestMapping("/api/user")
public class UserRestController {
    //@PostMapping("user")
    @GetMapping
    //@ResponseBody
    public User get(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return User.builder().id(id).name(name).build();
    }

    @PostMapping
     //@ResponseBody
            public User create(@RequestBody User user) { //수정
        return user;
    }

      @PutMapping
      //@ResponseBody
        public User modify(@RequestBody User user) {//삭제 x...?
            user.setName("modified");
                return user;
        }
        @DeleteMapping
    public void delete(@RequestParam Integer id){

        }
    }

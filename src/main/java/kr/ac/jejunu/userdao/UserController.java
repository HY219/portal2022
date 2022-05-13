package kr.ac.jejunu.userdao;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.MissingFormatArgumentException;

@Controller //Bean으로 알아서 등록이 된다.
//@RequestMapping("/user2")
//@RequestMapping("/user")
public class UserController {
    //@GetMapping(path="/user/{id}/{name}", produces="application/json") //localhost:8080/user/hulk //@PathVariable
    //@GetMapping(path="/user", produces="application/json")
    @RequestMapping("/user")
    //path? @RequestMapping(path = "/user", params = "id") //id가 1인 것만 허용
    public ModelAndView user(
            @RequestParam("id") Integer id, @RequestParam("name") String name
    ){
        User user = new User();
        user.setId(id);
        user.setName(name);
        ModelAndView modelAndView = new ModelAndView("user");
        return modelAndView;
    }

    //GetMappoing("/upload")
    //uplaod를 호출해주는 것을 넣어준다.
    //upload resorce 연결
    @RequestMapping("/upload") //view name의 default값=해당되는 path=/upload
    public void upload(){
        //아무 메서드가 없는 경우 upload.jsp를 찾아서 전달
    }


    //GetMapping(path="/upload")
    //파일을 업로드하는 로직
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/") + "/WEB-INF/static/" + file.getOriginalFilename()); //루트를 찾아올 수 있다. //static에 파일을 저장할 것이다.
        //파일을 받기위한 string을 지정
        //파일하고 연결을 해서 딱string으로 지정
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes()); //byte단위로 버퍼를 순서대로 쭉쭉..
        ModelAndView modelAndView = new ModelAndView("upload");
        //파일을 업로드 했을 때 화면에 이미지 띄워주기
        modelAndView.addObject("url", "/images/" +file.getOriginalFilename());
        return modelAndView;
    }

    //date
//    public ModelAndView date(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
//                             Date date) {
//        ModelAndView modelAndView = new Model
//    }


    //cookie 세션.. 로그인 시 저장해둘 곳이 필요함. 로그아웃 전까지?
//    @GetMapping(path="cookie", produces = "application/json")
//    public Cookie[] cookie(HttpServletRequest request, HttpServletResponse response){
//        Cookie cookie = new Cookie("hulk", "1234");
//        response.addCookie(cookie);
//        return request.getCookies();
//    }
//
//    @GetMapping(path="session", produces = "application/json")
//    public User seesion(HttpSession httpSession){
//        User user = (User) httpSession.getAttribute("user");
//        if(user ==null)
//            httpSession.setAttribute();
//        }
//    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("e", e);
        return modelAndView;
    }
}

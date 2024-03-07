package com.example.oauthsession.controller;

import com.example.oauthsession.dto.UserDTO;
import com.example.oauthsession.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final UserService userService;

    public MemberController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/signin")
    public String signinPage(){

        return "member/signin";
    }

    @GetMapping("/signup")
    public String signupPage(){

        return "member/signup";
    }

    @PostMapping("/signup")
    public String registerMember(@ModelAttribute UserDTO userDTO, BindingResult br,@RequestParam Map<String, String> params, Model model) {
        System.out.println("@#@#@#@# registerMember 진입 ======");
        if (br.hasErrors()) {
            //회원가입 실패시 기존 입력값 유지
            System.out.println("@#@#@#@# 회원가입 실패 시 기존 입력값 유지======");
            model.addAttribute("userDTO", userDTO);
            return "member/signup";
        }else{
            // 회원 정보 저장
            String phone = params.get("phoneA") + params.get("phoneB") + params.get("phoneC");
            String address = params.get("postalCode") + "$" + params.get("address") + "$" + params.get("detailAddress");
            userDTO.setPhone(phone);
            userDTO.setAddress(address);
            System.out.println("@#@#UserDTO ==========> "+userDTO);
            userService.signup(userDTO);
            System.out.println("@#@#@#@# 회원 가입 성공 ======");
            // 회원가입 성공 후 리다이렉트할 페이지 설정
            return "redirect:signin";
        }
    }

//    @PostMapping(value="/checkEmail", produces="application/json; charset=UTF-8")
//    @ResponseBody
//    public int checkEmail(String email) {
//
//        log.info("checkEmail 시작");
//        log.info("전달 받은 Email : {}", email);
//        int result = memberService.checkEmail(email);
//        log.info("checkEmail 결과 : {}", result);
//
//        return result;
//    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Integer> checkEmailId(@RequestBody String email){
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }

    @PostMapping("/checkId")
    public ResponseEntity<Boolean> checkMemberId(@RequestBody String memberId) {
//        boolean isDuplicate = userService.checkDuplicate(memberId);
        return ResponseEntity.ok(userService.checkUsernameDuplicate(memberId)); // 중복이면 true, 아니면 false 반환
    }


}

package com.example.oauthsession.controller;

import com.example.oauthsession.dto.CategoryDTO;
import com.example.oauthsession.dto.ProductDTO;
import com.example.oauthsession.entity.CategoryEntity;
import com.example.oauthsession.service.ProductService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        /* 복구기한 100일이 경과한 휴지통 삭제글 영구 삭제 */
//        List<Map<String, Integer>> postNoList = adminService.getTrashItemToDelete();
////        log.info("금일 기준 '영구 삭제 대상 휴지통 삭제글' 개수 : {}", postNoList.size());
//
//        int count = 0;
//        for(int i=0; i < postNoList.size(); i++) {
//            String refBoard = String.valueOf(postNoList.get(i).get("REF_BOARD")); //컬럼명 기준으로 값 호출
//            int refPostNo = Integer.parseInt(String.valueOf(postNoList.get(i).get("REF_POST_NO")));
//            int result = adminService.permanentlyDeleteFromTrashAndOriginalTableData(refBoard, refPostNo);
//            if(result == 1) count++;
//        }
//        log.info("영구 삭제글 count : {}", count);
//
//        /* 통계 자료 조회(기본 조회 기간 '최근 일주일') */
//        String range = "oneWeek";
//        String start = "";
//        String end = "";
//
//        List<Map<String, Integer>> memberData = adminService.getMemberDataByDate(range, start, end); //회원수
//        List<Map<String, Integer>> salesData = adminService.getSalesDataByDate(range, start, end); //매출액
//        List<Map<ProductDTO, Integer>> top8Product = adminService.getTopSalesProduct(range, start, end); //누적 판매량 Top 8
//        model.addAttribute("memberData", memberData);
//        model.addAttribute("salesData", salesData);
//        model.addAttribute("top8Product", top8Product);
        return "admin/dashboard";
    }

    /*관리자 상품등록 페이지 GET*/
    @GetMapping("/product/add")
//    public void addProduct(Model model) {
    public String addProduct_P(Model model) {
//        /* 카테고리 목록 호출 */
        List<CategoryDTO> category = productService.getCategoryList();
//        System.out.println("@#@#@#@# CategoryDTO =======> "+category);
        model.addAttribute("category", category);

        return "admin/product/add";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, BindingResult br,
                             @RequestParam("files") MultipartFile[] files, @RequestParam("categoryName") String categoryName, Model model) {
        System.out.println("@#@#@# productDTO를 어떤 값으로 가져오는지 ====>"+productDTO);
        if (br.hasErrors()){    //상품등록 실패시 기존 입력값 유지
            System.out.println("@#@#@# 회원가입 실패 -----------------------");
            model.addAttribute("productDTO",productDTO);
        }else{
            int categoryNo = productService.checkCategoryNo(categoryName);
            productDTO.setCategoryNo(String.valueOf(categoryNo));

            productService.addNewProduct(productDTO);
        }
        // ProductDTO에서 받은 데이터 처리
        // 예: 상품 정보 저장 로직

        // 파일 처리 로직
        // 대표 썸네일과 서브 썸네일을 Attachment 테이블에 저장
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 파일 저장 로직
                // 예: 파일 메타데이터를 Attachment 객체에 저장하고, 파일 시스템에 파일 저장
            }
        }

        // 처리 후 리다이렉트
        return "redirect:add";
    }




    /*새 카테고리 추가*/
    @PostMapping(value="/product/checkCategory", produces="application/json; charset=UTF-8")
    @ResponseBody
    public Map<String, Object> checkCategoryName(@RequestBody Map<String, String> param, Locale locale) {
        Map<String, Object> response = new HashMap<>();

        /* 카테고리 중복 검사 */
        String categoryName = param.get("category").replaceAll("\\s", ""); //문자 사이 공백 제거 후 비교
        boolean categoryExists = productService.checkCategoryNameExists(categoryName);


        if(categoryExists) { // 이미 존재하는 카테고리
            String errorMessage = "이미 존재하는 카테고리입니다.";
            response.put("errorMessage", errorMessage);
        } else { // 새 카테고리 추가
            String categorySection = param.get("section");
            CategoryEntity newCategory = productService.addNewCategory(categoryName, categorySection);
            if(newCategory != null) {
                response.put("successMessage", "카테고리가 성공적으로 추가되었습니다.");
            } else {
                response.put("errorMessage", "카테고리 추가에 실패했습니다.");
            }
        }
        return response;
    }

}

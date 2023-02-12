package com.discovery.category;

import com.discovery.category.model.CategoryDto;
import com.discovery.category.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("카테고리 서비스 검증")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs()
public class CategoryTest {

    @Autowired
    private MockMvc mockMvc;

    public CategoryService categoryService;
    @Autowired
    public void CategoryService(CategoryService categoryService){this.categoryService = categoryService;}

    @DisplayName("카테고리 단위 검증 전체")
    @Test
    public void SearchAllTest(){
        assertAll(
                this::timeoutTest,
                this::getCategoryTest,
                this::CategoryApiTest,
                this::RestdocsCreateDocs
        );
    }

    @DisplayName("카테고리 - DB timeout 확인 3000ms")
    @Test
    public void timeoutTest() throws Exception{
        assertTimeout(Duration.ofMillis(3000), () -> categoryService.getCategory("CU000001"));
    }

    @DisplayName("카테고리 - 개별 카테고리 서비스 검증")
    @Test
    public void getCategoryTest() throws Exception{
        //DB2 사용하기 때문에 기본 데이터 세팅
        setDefaultData();

        //데이터 체크
        assertNotNull(categoryService.getCategory("CU000001"));

        //하위 카테고리 확인
        assertNotNull(categoryService.getCategory("CU000001").getLow_category());
    }

    @DisplayName("카테고리 - 전체 카테고리 조회")
    @Test
    public void getCategorAllTest() throws Exception{
        //DB2 사용하기 때문에 기본 데이터 세팅
        setDefaultData();

        //데이터 체크
        assertNotNull(categoryService.getCategory());
        List<CategoryDto> dto = categoryService.getCategory();
        System.out.println(dto);
    }

    @DisplayName("카테고리 - 카테고리 조회 API 검증")
    @Test
    public void CategoryApiTest() throws Exception {
        //기본 데이터 세팅
        setDefaultData();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                    .get("/api/v1/category")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                                    .andReturn();

        System.out.println("HTTP STATUS : " + result.getResponse().getStatus());
        System.out.println("RESULT:" + URLDecoder.decode(result.getResponse().getContentAsString() , StandardCharsets.UTF_8));

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus(), "HTTP STATUS 200 이 아닙니다.");

    }

    @DisplayName("카테고리 - RestDocs Create")
    @Test
    public void RestdocsCreateDocs() throws Exception {
        setDefaultData();
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/category"))
                .andDo(print())
                .andExpect(status().isOk());
        // document set
        result.andExpect(status().isOk()).andDo(document("category",
                        responseFields(
                                beneathPath("data"),
                                fieldWithPath("category_id").type(JsonFieldType.STRING).description("카테고리 ID"),
                                fieldWithPath("upper_category_id").type(JsonFieldType.NULL).description("상위 카테고리 ID"),
                                fieldWithPath("category_name").type(JsonFieldType.STRING).description("카테고리 명"),
                                fieldWithPath("category_desc").type(JsonFieldType.STRING).description("카테고리 설명"),
                                fieldWithPath("category_level").type(JsonFieldType.STRING).description("카테고리 레벨(U : 상위카테고리 L: 하위카테고리)"),
                                fieldWithPath("category_order").type(JsonFieldType.NUMBER).description("카테고리 노출 순서"),
                                fieldWithPath("low_category.[].category_id").type(JsonFieldType.STRING).description("하위 카테고리 ID"),
                                fieldWithPath("low_category.[].upper_category_id").type(JsonFieldType.STRING).description("상위 카테고리 ID"),
                                fieldWithPath("low_category.[].category_name").type(JsonFieldType.STRING).description("카테고리 명"),
                                fieldWithPath("low_category.[].category_desc").type(JsonFieldType.STRING).description("카테고리 설명"),
                                fieldWithPath("low_category.[].category_level").type(JsonFieldType.STRING).description("카테고리 레벨(U : 상위카테고리 L: 하위카테고리)"),
                                fieldWithPath("low_category.[].category_order").type(JsonFieldType.NUMBER).description("카테고리 노출 순서"),
                                fieldWithPath("low_category.[].low_category").type(JsonFieldType.NULL).description("")
                        )
                )
        ).andDo(print());
    }

    //DB2 데이터 default 세팅용
    public void setDefaultData(){
        //상위 카테고리 등록
        categoryService.addCategory(CategoryDto.builder().category_id("CU000001").category_name("가전").category_desc("가전 카테고리 입니다. ").category_level("U").category_order(1).build());

        //하위 카테고리 등록
        categoryService.addCategory(CategoryDto.builder().category_id("CL000001").category_name("에어컨").category_desc("에어컨 카테고리 입니다. ").category_level("L").category_order(1).upper_category_id("CU000001").build());
        categoryService.addCategory(CategoryDto.builder().category_id("CL000002").category_name("TV").category_desc("티비 카테고리 입니다. ").category_level("L").category_order(2).upper_category_id("CU000001").build());
        categoryService.addCategory(CategoryDto.builder().category_id("CL000003").category_name("냉장고").category_desc("냉장고 카테고리 입니다. ").category_level("L").category_order(3).upper_category_id("CU000001").build());

        //상위 카테고리 등록
        categoryService.addCategory(CategoryDto.builder().category_id("CU000002").category_name("의류").category_desc("의류 카테고리 입니다. ").category_level("U").category_order(2).build());

        //하위 카테고리 등록
        categoryService.addCategory(CategoryDto.builder().category_id("CL000004").category_name("상의").category_desc("상의 카테고리 입니다. ").category_level("L").category_order(1).upper_category_id("CU000002").build());
        categoryService.addCategory(CategoryDto.builder().category_id("CL000005").category_name("하의").category_desc("하의 카테고리 입니다. ").category_level("L").category_order(2).upper_category_id("CU000002").build());
        categoryService.addCategory(CategoryDto.builder().category_id("CL000006").category_name("아우터").category_desc("아우터 카테고리 입니다. ").category_level("L").category_order(3).upper_category_id("CU000002").build());
    }
}

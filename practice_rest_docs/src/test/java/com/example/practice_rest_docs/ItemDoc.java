package com.example.practice_rest_docs;

import com.epages.restdocs.apispec.ConstrainedFields;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.springframework.restdocs.constraints.Constraint;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;

public class ItemDoc {

    public static RestDocumentationResultHandler createItem(){
        //adoc 용
        //ConstraintDescriptions cons = new ConstraintDescriptions(ItemCreateDto.class);

        //restdocs-api-spec 용
        //ConstrainedFields f = new ConstrainedFields(ItemCreateDto.class);


        return document(
                "items/create-item",
                resource(
                        ResourceSnippetParameters.builder()
                                .tag("Item")
                                .summary("아이템 생성")
                                .description("아이템을 생성한다.")
                                .requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름")
                                                .attributes(key("validationConstraints")
                                                        .value(List.of(new Constraint(
                                                                "javax.validation.constraints.NotBlank",
                                                                Map.of()
                                                        ))))
                                        ,
                                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명").optional(),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                                                .attributes(key("validationConstraints")
                                                        .value(List.of(new Constraint(
                                                                "javax.validation.constraints.NotNull",
                                                                        Map.of()),
                                                                new Constraint("javax.validation.constraints.Min",
                                                                        Map.of("value",0))
                                                                )
                                                        )
                                                )
                                ).
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이템 id"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름"),
                                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명"),
                                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                                ).build()
                )
        );
    }

    public static RestDocumentationResultHandler getItem(){
        return document(
                "items/get-item-by-id",
                resource(ResourceSnippetParameters.builder()
                        .tag("Item")
                        .summary("아이템 단일 조회")
                        .description("아이템을 id로 조회합니다.").
                        pathParameters(
                                parameterWithName("id").description("아이템 id")).
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이템 id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명"),
                                fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                        ).build()
                ));
    }

    public static RestDocumentationResultHandler errorSnippet(String identifier) {
        return document(identifier,
                resource(ResourceSnippetParameters.builder()
                        .tag("Item")
                        .summary("아이템 생성")
                        .description("새 아이템을 등록합니다.").
                        responseFields(fieldWithPath("status").description("HTTP 상태 코드"),
                                fieldWithPath("message").description("에러 메시지")).build()
                ));
    }
}
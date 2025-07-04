package com.example.practice_rest_docs;

import org.hibernate.validator.internal.metadata.raw.ConstrainedField;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

public class ItemDoc {

    public static RestDocumentationResultHandler createItem(){
        ConstraintDescriptions cons = new ConstraintDescriptions(ItemCreateDto.class);

        return document(
                "items/create-item",
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름")
                                .attributes(key("constraints")
                                        .value(String.join(", ", cons.descriptionsForProperty("name")))),
                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명"),
                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                                .attributes(key("constraints").value(
                                        String.join(", ", cons.descriptionsForProperty("price"))))
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이템 id"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름"),
                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명"),
                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                )
        );
    }

    public static RestDocumentationResultHandler getItem(){
        return document(
                "items/get-item-by-id",
                pathParameters(
                        parameterWithName("id").description("아이템 id")),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이템 id"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("아이템 이름"),
                        fieldWithPath("description").type(JsonFieldType.STRING).description("아이템 부가 설명"),
                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("아이템 가격")
                )
        );
    }

    public static RestDocumentationResultHandler errorSnippet(String identifier) {
        return document(identifier,
                responseFields(fieldWithPath("status").description("HTTP 상태 코드"),
                        fieldWithPath("message").description("에러 메시지"))
        );
    }
}

package com.example.api;

import com.alibaba.fastjson.JSON;
import com.example.entity.Book;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.SwaggerResultHandler;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Created by zhangrui on 2018/7/13.
 */
/*
* @ClassName SwaggerStaticDocTest
*@Description TODO
*@Author zhangrui
*@Date 9:57 9:57
*@Version
* */

@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//springboot中的事物回滚机制
@Transactional
@WebAppConfiguration
public class SwaggerStaticDocTest {
    private static Logger logger = LoggerFactory.getLogger(SwaggerStaticDocTest.class);
    private String snippetDir = "target/generated-snippets";
    private String outputDir  = "target/asciidoc";
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }

    @After
    public void Test() throws Exception {
        // 得到swagger.json,写入outputDir目录中
        mockMvc.perform(get("/v2/api-docs").accept(MediaType.APPLICATION_JSON))
                .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
                //.andExpect(status().is(403))
                .andReturn();
        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)// 格式
                .withExamples(snippetDir)
                .build()
                .intoFolder(outputDir);// 输出
    }
    @Test
    public void TestApi() throws Exception {
        mockMvc.perform(post("/book").param("111","测试API","1.11")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403))
                .andDo(MockMvcRestDocumentation.document("books", preprocessResponse(prettyPrint())));
        Book book = new Book();
        book.setPrice(1.11);
        book.setId(2233L);
        book.setName("测试API");
        logger.debug("这是一个debug");
        mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(book))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403))
                .andDo(MockMvcRestDocumentation.document("books", preprocessResponse(prettyPrint())));
    }

}

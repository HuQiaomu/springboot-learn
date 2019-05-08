package com.example.demo.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

/**
 * @author wh1507006
 * @date 2019/4/16 13:18
 */
@CrossOrigin(origins = {"*", "null"})
@RestController
public class PdfController extends ExceptionController {

    @GetMapping("/pdf")
    public void getPdf(HttpServletResponse response) throws Exception {
        File pdfFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX +
                "static/Java_manual.pdf");

        InputStream in = new FileInputStream(pdfFile);
        IOUtils.copy(in, response.getOutputStream());
        in.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}

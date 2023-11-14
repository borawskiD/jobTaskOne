package com.borawski.jobtaskone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class BankController {
    TransferRepository transferRepository;
    public BankController(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }


    @GetMapping("/")
    public String home(){
            return "index.html";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Document document = Jsoup.parse(new String(file.getBytes(), "UTF-8"));
                Element table = document.select("table").first();
                Elements rows = table.select("tr:gt(0)");
                for (Element row : rows) {
                    Elements cells = row.select("td, th");
                    for (Element cell : cells) {
                        System.out.print(cell.text() + "\t");
                    }
                    System.out.println();
                }
                return rows.text();
            } catch (Exception e) {
                //todo other errors
                e.printStackTrace();
            }
        } else {
            //TODO empty file validation
        }
        //TODO redirect error
        return "Error";
    }

}

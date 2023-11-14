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
        TransferEntity exampleTransferEntity = new TransferEntity("PL77 1160 2202 0000 0003 7893 9667",
                LocalDate.parse("2021-03-18"),
                LocalDate.parse("2021-03-18"),
                "PRZELEW WEWNĘTRZNY PRZYCHODZĄCY",
                "77 1160 2202 0000 0004 6844 3363",
                "inner@kanga.exchange",
                "Przelew wewnętrzny",
                9500.00,
                105140.24,
                "PLN",
                LocalDateTime.now()
                );
            transferRepository.save(exampleTransferEntity);
        System.out.println(transferRepository.findAll().get(0).getTotal());
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
                    Element[] elementsArray = cells.toArray(new Element[0]);
                    TransferEntityDTO exampleTransferEntity = TransferWrapper.convertElement(elementsArray);
                    TransferEntity transfer = TransferWrapper.convertToEntity(exampleTransferEntity);
                    System.out.println(transferRepository.save(transfer));
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

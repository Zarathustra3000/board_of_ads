package com.board_of_ads.component;

import com.board_of_ads.service.impl.KLADRServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    KLADRServiceImpl kladrService;

    @Autowired
    public CommandLineAppStartupRunner(KLADRServiceImpl kladrService) {
        this.kladrService = kladrService;
    }

    @Override
    public void run(String... strings) throws Exception {

        kladrService.streamDBKLADR();
    }
}
